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
@RequestMapping("todos")
public class TodoController {
    @Autowired
    TodoService todoService;

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST, path = "/create")
    public ToDo createTodo(@RequestBody ToDo newItem) {
        return todoService.save(newItem);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST, path = "/create-list")
    public List<ToDo> createTodosList(@RequestBody List<ToDo> newItems) {
        return todoService.saveList(newItems);
    }

    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, value = "/")
    public List<ToDo> getAllTodos() {
        return todoService.getAllToDos();
    }

    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ToDo getToDoById(@PathVariable long id) throws InvalidIdException {
        return todoService.findToDoById(id);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(method = RequestMethod.PUT, path = "/update/status/{id}/{status}")
    public ToDo updateStatus(@PathVariable("id") long id,@PathVariable("status") String status) {
        return todoService.updateStatus(id, TodoStatusEnum.valueOf(status));
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(method = RequestMethod.DELETE, path = "/delete/{id}")
    public void deleteToDo(@PathVariable long id) {
        todoService.deleteToDoById(id);
    }

}
