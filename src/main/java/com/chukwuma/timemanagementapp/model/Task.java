package com.chukwuma.timemanagementapp.model;

import com.chukwuma.timemanagementapp.enums.Status;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long task_id;
    private String title;
    private String description;

    @Column(columnDefinition = " VARCHAR(255) default 'PENDING'")
    private Status status;
    private LocalDateTime createdTime;
    private LocalDateTime updateTime;
    private LocalDateTime completedTime;

    public Task(Long task_id, String title, String description, Status status) {
        this.task_id = task_id;
        this.title = title;
        this.description = description;
        this.status = status;
    }

    public Task(String title, String description) {
        this.title = title;
        this.description = description;

    }

    public Task(String title, String description, Status status, LocalDateTime createdTime) {
        this.title = title;
        this.status = status;
        this.description = description;
        this.createdTime = createdTime;
    }

    @ManyToOne
    @JoinColumn(name="user", referencedColumnName = "id")
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Task task = (Task) o;
        return task_id != null && Objects.equals(task_id, task.task_id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
