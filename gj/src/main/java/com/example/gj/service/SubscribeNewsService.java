package com.example.gj.service;

import com.example.gj.config.response.Message;
import com.example.gj.model.SubscribeNews;
import com.example.gj.repository.SubscribeNewsRepository;
import com.example.gj.viewmodel.subscribe_news.GetSubscribeNewsResponse;
import com.example.gj.viewmodel.subscribe_news.SubscribeNewsRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SubscribeNewsService {
    final SubscribeNewsRepository subscribeNewsRepository;
    final EmailService emailService;

    public SubscribeNewsService(SubscribeNewsRepository subscribeNewsRepository, EmailService emailService) {
        this.subscribeNewsRepository = subscribeNewsRepository;
        this.emailService = emailService;
    }

    public void create(SubscribeNewsRequest request) throws Exception {
         if (request == null || request.getEmail() == null) {
            throw new Exception(Message.NULL_INPUT);
        }


         SubscribeNews subscribeNews = subscribeNewsRepository.findByEmail(request.getEmail());

        if (subscribeNews != null && subscribeNews.getStatus() == 1) {
            throw new Exception("subscribed news");
        }

        if (subscribeNews != null && subscribeNews.getStatus() == 0) {
            subscribeNews.setStatus(1);
            subscribeNews.setUpdatedAt(new Date());
            subscribeNewsRepository.save(subscribeNews);
        }

         if (subscribeNews == null) {
             subscribeNewsRepository.save(new SubscribeNews(request.getEmail()));
         }

        emailService.sendMailSubscribeNews(request.getEmail());
    }

    public List<GetSubscribeNewsResponse> getSubscribeNews() {
        List<SubscribeNews> subscribeNewsList = subscribeNewsRepository.getAllByStatusIsTrue();
        if (subscribeNewsList == null) {
            return new ArrayList<>();
        }


        List<GetSubscribeNewsResponse> responses = new ArrayList<>();
        for (SubscribeNews s : subscribeNewsList) {
            responses.add(new GetSubscribeNewsResponse(s.getId(), s.getEmail()));
        }

        return responses;
    }
}
