package com.example.gj.service;

import com.example.gj.config.response.Message;
import com.example.gj.model.Advise;
import com.example.gj.repository.AdviseRepository;
import com.example.gj.viewmodel.advise.AdviseRequest;
import com.example.gj.viewmodel.advise.GetAdviseResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdviseService {
    final AdviseRepository adviseRepository;
    final EmailService emailService;

    public AdviseService(AdviseRepository adviseRepository, EmailService emailService) {
        this.adviseRepository = adviseRepository;
        this.emailService = emailService;
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

    public List<GetAdviseResponse> getAdvise() {
        List<Advise> list = adviseRepository.getAllByStatusIsTrue();
        if (list == null) {
            return new ArrayList<>();
        }

        List<GetAdviseResponse> responses = new ArrayList<>();
        for (Advise advise : list) {
            GetAdviseResponse response = GetAdviseResponse.builder()
                    .id(advise.getId())
                    .fullName(advise.getFullName())
                    .phone(advise.getPhone())
                    .email(advise.getEmail())
                    .build();

           responses.add(response);
        }

        return responses;
    }
}
