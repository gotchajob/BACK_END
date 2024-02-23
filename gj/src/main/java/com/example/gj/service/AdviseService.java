package com.example.gj.service;

import com.example.gj.config.response.Message;
import com.example.gj.config.swagger.OpenApiConfig;
import com.example.gj.model.Advise;
import com.example.gj.repository.AdviseRepository;
import com.example.gj.util.Util;
import com.example.gj.viewmodel.advise.AdviseRequest;
import com.example.gj.viewmodel.advise.GetAdviseResponse;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.fasterxml.jackson.databind.type.LogicalType.Array;

@Service
public class AdviseService {
    final AdviseRepository adviseRepository;
    final EmailService emailService;
    final UserService userService;

    public AdviseService(AdviseRepository adviseRepository, EmailService emailService, UserService userService) {
        this.adviseRepository = adviseRepository;
        this.emailService = emailService;
        this.userService = userService;
    }

    public void create(AdviseRequest request) {
        if (request == null || request.getFullName() == null || request.getPhone() == null
                || request.getEmail() == null || request.getAdvise() == null) {
            throw new RuntimeException(Message.NULL_INPUT);
        }

        Advise advise = new Advise(request.getFullName(), request.getPhone(), request.getEmail(), request.getAdvise());
        adviseRepository.save(advise);

        emailService.sendMailAdvise(request);
    }

    public GetAdviseResponse getAdvise(int page, int limit, String sortBy, String sortOrder) {

        List<Integer> statusList = Arrays.asList(1,2,3);
        Sort.Direction direction = sortOrder.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page - 1, limit, direction, sortBy);

        List<Advise> advises = adviseRepository.getAllByStatusIn(statusList, pageable);
        if (advises == null) {
            advises = new ArrayList<>();
        }
        int total = adviseRepository.countByStatusIn(statusList);

        return new GetAdviseResponse(advises, total);
    }

    public boolean updateStatus(String id, int status) throws Exception {
        if (id == null || status < 0 || status > 3) {
            throw new Exception(Message.INVALID_INPUT);
        }

        Optional<Advise> adviseOptional = adviseRepository.findById(id);
        if (adviseOptional.isEmpty()) {
            throw new Exception(Message.NOT_FOUND);
        }

        Advise advise = adviseOptional.get();
        if (status == advise.getStatus()) {
            return false;
        }

        String userName = userService.getCurrentUsername();
        if (status == 1) {
            advise.setProcessingBy(null);
        }

        if (status == 2) {
            if (advise.getStatus() != 1) {
                throw new Exception("Status is invalid");
            }
            advise.setProcessingBy(userName);

        }

        if (status == 3) {

            if (advise.getStatus() != 2) {
                throw new Exception("Status is not processing");
            }

            if (!advise.getProcessingBy().equals(userName)) {
                throw new Exception("You are not allow to finish advise");
            }

        }

        advise.setStatus(status);
        advise.setUpdatedAt(new Date());
        adviseRepository.save(advise);

        return true;
    }

    public long countAdviseByMonth(int year, int month) {
        Date[] date = Util.getStartDateAndEndDate(year, month);
        return adviseRepository.countByCreatedAtBetween(date[0], date[1]);
    }


}
