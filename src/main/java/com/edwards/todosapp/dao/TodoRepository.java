package com.edwards.todosapp.dao;

import com.edwards.todosapp.model.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TodoRepository extends JpaRepository<ToDo, Long> {
    /**
     * Customized queries to DB can be added
     */

}
