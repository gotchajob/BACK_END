package com.example.gj.repository;

import com.example.gj.model.WebsiteFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebsiteFeedbackRepository extends JpaRepository<WebsiteFeedback, String> {
}
