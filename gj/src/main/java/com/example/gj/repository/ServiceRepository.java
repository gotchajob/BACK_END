package com.example.gj.repository;

import com.example.gj.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Integer> {

    @Query("SELECT s.title FROM Service s ORDER BY s.id")
    List<String> findAllTitles();
}
