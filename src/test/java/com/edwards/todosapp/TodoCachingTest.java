package com.edwards.todosapp;

import com.edwards.todosapp.model.ToDo;
import com.edwards.todosapp.model.TodoStatusEnum;
import com.edwards.todosapp.service.TodoService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static java.util.Optional.ofNullable;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TodosAppApplication.class)
public class TodoCachingTest {

    @Autowired
    CacheManager cacheManager;

    @Autowired
    TodoService service;

    @BeforeEach
    void setUp() {
        service.save(ToDo.builder()
                .id(3)
                .title("relax")
                .description("watch a good netflix show")
                .createdTime(LocalDateTime.of(2024,03,13,17,15,03))
                .status(TodoStatusEnum.TODO)
                .build());
        service.save(ToDo.builder()
                .id(2)
                .title("laundry")
                .description("wash the kids pile")
                .createdTime(LocalDateTime.of(2024,03,13,17,15,03))
                .status(TodoStatusEnum.TODO)
                .build());
    }

    @AfterEach
    void tearDown() {
    }

    private Optional<ToDo> getCachedToDoItem(long id) {
        return ofNullable(cacheManager.getCache("todos")).map(c -> c.get(id, ToDo.class));
    }

    @Test
    public void cacheToDosTest() {
        ToDo todo = service.findToDoById(2);

        assertEquals(todo, getCachedToDoItem(2).get());
    }
}
