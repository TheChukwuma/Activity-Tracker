package com.chukwuma.timemanagementapp.repository;

import com.chukwuma.timemanagementapp.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("select t from Task t where t.status =:status")
    List<Task> findAllByStatus(String status);


//    @Query(
//  value = "SELECT * FROM USERS u WHERE u.status = 1",
//  nativeQuery = true)
    @Query(value = "SELECT * FROM task t WHERE t.user = ?", nativeQuery = true)
    List<Task> findAllByUserId(Long userid);
}
