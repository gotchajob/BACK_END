package com.example.gj.repository;

import com.example.gj.model.Verify;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerifyRepository extends JpaRepository<Verify, String> {
    Verify findByUserIdAndType(String userId, int type);
    Verify findByUserIdAndCodeAndType(String userId, String code, int type);

}
