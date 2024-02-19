package com.example.gj.repository;

import com.example.gj.model.Advise;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdviseRepository extends JpaRepository<Advise, String> {
    List<Advise> getAllByStatusIn(List<Integer> status, Pageable pageable);
    int countByStatusIn(List<Integer> status);
}
