package com.example.gj.repository;

import com.example.gj.model.Book;
import com.example.gj.model.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MentorRepository extends JpaRepository<Mentor, String> {
}
