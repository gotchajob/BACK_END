package com.example.gj.service;

import com.example.gj.repository.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartnerService {
    @Autowired
    PartnerRepository partnerRepository;
}
