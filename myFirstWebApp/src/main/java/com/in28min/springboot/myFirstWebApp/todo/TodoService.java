package com.in28min.springboot.myFirstWebApp.todo;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class TodoService {
    private static List<Todo> todos = new ArrayList<>();
    private static int todoCount = 0;
    static {
        todos.add(new Todo(++todoCount, "in28min", "Learn SpringBoot 1", LocalDate.now().plusYears(1), false));
        todos.add(new Todo(++todoCount, "in28min", "Learn Docker 1", LocalDate.now().plusYears(2), false));
        todos.add(new Todo(++todoCount, "in28min", "Learn DB 1", LocalDate.now().plusYears(3), false));
        todos.add(new Todo(++todoCount, "in28min", "Learn JPA 1", LocalDate.now().plusYears(4), false));
        todos.add(new Todo(++todoCount, "in28min", "Learn hibernate 1", LocalDate.now().plusYears(5), false));

    }

    public List<Todo> retrieveTodosByUser(String username) {
        Predicate<? super Todo> predicate = todo -> todo.getUsername().equalsIgnoreCase(username);
        return todos.stream().filter(predicate).toList();

    }

    public void addTodo(String username , String description, LocalDate targetDate, boolean done) {
            Todo todo = new Todo(++todoCount,username,description,targetDate,done);
            todos.add(todo);
    }

    public void deleteTodoByID(int id) {
        Predicate<? super Todo> predicate = todo -> todo.getId() == id ;
        todos.removeIf(predicate);
    }
    public Todo findByID(int id) {
        return todos.stream().filter(todo -> todo.getId() == id).findFirst().get();
    }

    public void updateTodo(@Valid Todo todo) {
        deleteTodoByID(todo.getId());
        todos.add(todo);
    }


}
