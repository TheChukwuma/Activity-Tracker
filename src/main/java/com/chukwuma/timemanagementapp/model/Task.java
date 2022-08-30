package com.chukwuma.timemanagementapp.model;

import com.chukwuma.timemanagementapp.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long task_id;
    private String title;
    private String description;
    private Status status;
    private Date createdTime;
    private Date updateTime;
    private Date completedTime;

    public Task(Long task_id, String title, String description, Status status) {
        this.task_id = task_id;
        this.title = title;
        this.description = description;
        this.status = status;
    }
}
