package com.example.gj.service;

import com.example.gj.model.Notify;
import com.example.gj.repository.NotifyRepository;
import com.example.gj.util.Status;
import com.example.gj.util.Util;
import com.example.gj.viewmodel.notify.GetNotifyResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class NotifyService {
    private final NotifyRepository notifyRepository;
    private final UserService userService;

    public NotifyService(NotifyRepository notifyRepository, UserService userService) {
        this.notifyRepository = notifyRepository;
        this.userService = userService;
    }

    public GetNotifyResponse getByUser(int page, int limit, String sortBy, String orderBy) {
        String currentUserId = userService.getCurrentUserId();
        Pageable pageable = Util.generatePage(page, limit, sortBy, orderBy);

        List<Notify> notifyList = notifyRepository.getAllByUserIdAndStatus(currentUserId, Status.ACTIVE, pageable);
        if (notifyList == null) {
            notifyList = Collections.EMPTY_LIST;
        }
        long total = notifyRepository.countByUserIdAndStatus(currentUserId, Status.ACTIVE);

        return new GetNotifyResponse(notifyList, total);
    }
}
