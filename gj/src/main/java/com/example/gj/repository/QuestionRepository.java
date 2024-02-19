package com.example.gj.repository;

import com.example.gj.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, String> {
    List<Question> findByQuestionTypeId(int questionTypeId);

    @Query(value = "SELECT * FROM question WHERE question_type_id = :type ORDER BY RAND() LIMIT :limit", nativeQuery = true)
    List<Question> findRandomRowsByType(@Param("type") int type, @Param("limit") int limit);
}
