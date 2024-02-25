package com.example.gj.service;

import com.example.gj.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ServiceService {

    private final ServiceRepository serviceRepository;

    public ServiceService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public List<String> getServiceTitle() {
        List<String> titles = serviceRepository.findAllTitles();
        return titles != null ? titles : Collections.emptyList();
    }
}
