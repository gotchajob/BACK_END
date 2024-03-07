package com.example.gj.service;

import com.example.gj.repository.MentorRepository;
import org.springframework.stereotype.Service;

@Service
public class MentorService {
    private final MentorRepository mentorRepository;

    public MentorService(MentorRepository mentorRepository) {
        this.mentorRepository = mentorRepository;
    }
}
