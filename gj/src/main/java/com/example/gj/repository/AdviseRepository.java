package com.example.gj.repository;

import com.example.gj.model.Advise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdviseRepository extends JpaRepository<Advise, String> {
    List<Advise> getAllByStatusIsTrue();
}
