package com.edwards.todosapp.service;

import com.edwards.todosapp.dao.TodoRepository;
import com.edwards.todosapp.error.InvalidIdException;
import com.edwards.todosapp.model.ToDo;
import com.edwards.todosapp.model.TodoStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public ToDo save(ToDo newItem) {
        return todoRepository.save(newItem);
    }

    public List<ToDo> saveList(List<ToDo> newItems) {
        return todoRepository.saveAll(newItems);
    }

    /**
     *
     * Depending on traffic, caches can grow quite large, quite fast.
     * Adding @CacheEvict for specific items or limit cache expiration/size in application properties
     */
    @Cacheable("todos")
    public List<ToDo> getAllToDos() {
        return todoRepository.findAll();
    }

    @Cacheable("todos")
    public ToDo findToDoById(long id) throws InvalidIdException {
        return todoRepository.findById(id).orElseThrow(InvalidIdException::new);
    }

    public void deleteToDoById(long id) {
        todoRepository.deleteById(id);
    }

    public ToDo updateStatus(long id, TodoStatusEnum statusEnum) {
        ToDo currentItem = findToDoById(id);
        currentItem.setStatus(statusEnum);

        log.debug("Status for item:{} is updated to {}", id, statusEnum);
        return todoRepository.save(currentItem);
    }
}
