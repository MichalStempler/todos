package com.edwards.todosapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;


import java.time.LocalDateTime;

@Entity
@Table(name = "TODO")
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ToDo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    private String title;

    @NonNull
    private String description;

    private LocalDateTime  createdTime;

    private TodoStatusEnum status;

    @PrePersist
    protected void onCreate() {
        createdTime = LocalDateTime .now();
        status = TodoStatusEnum.TODO;
    }

}
