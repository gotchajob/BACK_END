package com.example.gj.service;

import com.example.gj.config.response.Message;
import com.example.gj.viewmodel.advise.AdviseRequest;
import com.example.gj.viewmodel.order_service.OrderServiceRequest;
import com.example.gj.viewmodel.subscribe_news.SubscribeNewsRequest;
import org.springframework.stereotype.Service;

@Service
public class HomeService {
    final EmailService emailService;

    public HomeService(EmailService emailService) {
        this.emailService = emailService;
    }



    public void subscribeNews(SubscribeNewsRequest request) throws Exception {
        if (request == null || request.getEmail() == null) {
            throw new Exception(Message.NULL_INPUT);
        }

        emailService.sendMailSubscribeNews(request.getEmail());
    }

    public void adviseService(AdviseRequest request) throws Exception {
        if (request == null || request.getFullName() == null || request.getPhone() == null || request.getEmail() == null || request.getAdvise() == null) {
            throw new Exception(Message.NULL_INPUT);
        }

        emailService.sendMailAdvise(request);
    }
}
