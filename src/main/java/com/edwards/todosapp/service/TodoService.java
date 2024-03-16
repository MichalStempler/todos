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
    private TodoRepository repository;

    public ToDo save(ToDo newItem) {
        return repository.save(newItem);
    }

    public List<ToDo> saveList(List<ToDo> newItems) {
        return repository.saveAll(newItems);
    }

    @Cacheable("todos")
    public List<ToDo> getAllToDos() {
        return repository.findAll();
    }

    @Cacheable("todos")
    public ToDo findToDoById(long id) throws InvalidIdException {
        return repository.findById(id).orElseThrow(InvalidIdException::new);
    }

    public void deleteToDoById(long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            log.error("Id is not valid, try something else.");
            throw new InvalidIdException();
        }
    }

    public ToDo updateStatus(long id, TodoStatusEnum statusEnum) {
        ToDo currentItem = findToDoById(id);
        currentItem.setStatus(statusEnum);

        log.debug("Status for item:{} is updated to {}", id, statusEnum);
        return repository.save(currentItem);
    }

    public ToDo updateEntity(ToDo newItem) {
        log.debug("Item with id:{} is updated.", newItem.getId());
        return repository.save(newItem);
    }
}
