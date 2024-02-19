package com.example.gj.repository;

import com.example.gj.model.SubscribeNews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscribeNewsRepository extends JpaRepository<SubscribeNews, String> {
    List<SubscribeNews> getAllByStatusIsTrue();
    SubscribeNews findByEmail(String email);
}
