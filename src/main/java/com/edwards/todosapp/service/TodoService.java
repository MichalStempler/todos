package com.edwards.todosapp.service;

import com.edwards.todosapp.controller.LoggingController;
import com.edwards.todosapp.dao.TodoRepository;
import com.edwards.todosapp.error.InvalidIdException;
import com.edwards.todosapp.model.ToDo;
import com.edwards.todosapp.model.TodoStatusEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    Logger logger = LoggerFactory.getLogger(LoggingController.class);
    @Autowired
    private TodoRepository repository;

    public ToDo save(ToDo newItem) {
        return repository.save(newItem);
    }

    public List<ToDo> saveList(List<ToDo> newItems) {
        return repository.saveAll(newItems);
    }

    @CachePut(value="todos")
    public List<ToDo> getAllToDos() {
        return repository.findAll();
    }

    @CachePut(value="todos")
    public ToDo findToDoById(long id) throws InvalidIdException {
        return repository.findById(id).orElseThrow(InvalidIdException::new);
    }

    public void deleteToDoById(long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            logger.error("Id is not valid, try something else.");
            throw new InvalidIdException();
        }
    }

    public ToDo updateStatus(long id, TodoStatusEnum statusEnum) {
        ToDo currentItem = findToDoById(id);

        currentItem.setStatus(statusEnum);
        logger.debug("Status for item:" + id + " is updated to " + statusEnum.toString());
        return repository.save(currentItem);
    }
}
