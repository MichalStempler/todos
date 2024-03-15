package com.edwards.todosapp.controller;

import com.edwards.todosapp.error.InvalidIdException;
import com.edwards.todosapp.model.ToDo;
import com.edwards.todosapp.model.TodoStatusEnum;
import com.edwards.todosapp.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("todos-api")
public class ToDoController {
    @Autowired
    TodoService service;

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST, path = "/create")
    public ToDo createTodo(@RequestBody ToDo newItem) {
        return service.save(newItem);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST, path = "/create-list")
    public List<ToDo> createTodosList(@RequestBody List<ToDo> newItems) {
        return service.saveList(newItems);
    }

    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public List<ToDo> getAllTodos() {
        return service.getAllToDos();
    }

    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, value = "/id/{id}")
    public ToDo getToDoById(@PathVariable long id) throws InvalidIdException {
        return service.findToDoById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.PUT, path = "/update/status/{id}/{status}")
    public ToDo updateStatus(@PathVariable("id") long id,@PathVariable("status") String status) {
        return service.updateStatus(id, TodoStatusEnum.valueOf(status));
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.PUT, path = "/delete/{id}")
    public void deleteToDo(@PathVariable long id) {
        service.deleteToDoById(id);
    }


}
