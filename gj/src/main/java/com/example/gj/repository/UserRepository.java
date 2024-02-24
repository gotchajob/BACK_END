package com.example.gj.repository;

import com.example.gj.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByEmail(String email);
    List<User> findAllByRoleId(int roleId, Pageable pageable);

    int countByRoleId(int roleId);

    long countByCreatedAtBetween(Date start, Date end);

    @Query("SELECT DAY(u.createdAt), COUNT(u) FROM User u WHERE u.createdAt >= :startDate AND u.createdAt <= :endDate GROUP BY DAY(u.createdAt)")
    List<Object[]> countUsersPerDay(Date startDate, Date endDate);

    long countByCreatedAtBefore(Date date);

}
