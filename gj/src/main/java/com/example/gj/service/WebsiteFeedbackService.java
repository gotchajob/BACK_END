package com.example.gj.service;

import com.example.gj.repository.WebsiteFeedbackRepository;
import org.springframework.stereotype.Service;

@Service
public class WebsiteFeedbackService {
    final WebsiteFeedbackRepository websiteFeedbackRepository;

    public WebsiteFeedbackService(WebsiteFeedbackRepository websiteFeedbackRepository) {
        this.websiteFeedbackRepository = websiteFeedbackRepository;
    }
}
