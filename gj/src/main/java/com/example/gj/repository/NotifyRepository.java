package com.example.gj.repository;

import com.example.gj.model.Notify;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotifyRepository extends JpaRepository<Notify, String> {

    List<Notify> getAllByUserIdAndStatus(String userId, int status, Pageable pageable);
    long countByUserIdAndStatus(String userId, int status);
}
