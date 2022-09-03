package com.chukwuma.timemanagementapp.model;

import com.chukwuma.timemanagementapp.enums.Status;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
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

    private String status = Status.PENDING.name();
    private String startedTime;
    private String createdTime;

    private String updateTime;
    private String completedTime ;
    private Integer progress = 0;

    public Task(Long task_id, String title, String description, String status) {
        this.task_id = task_id;
        this.title = title;
        this.description = description;
        this.status = status;
    }

    public Task(String title, String description) {
        this.title = title;
        this.description = description;

    }

    public Task(String title, String description, Integer progress, String startedTime) {
        this.title = title;
      
        this.description = description;
        this.startedTime = startedTime;
        this.progress = progress;
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
