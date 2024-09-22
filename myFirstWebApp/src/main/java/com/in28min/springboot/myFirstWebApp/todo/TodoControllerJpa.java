package com.in28min.springboot.myFirstWebApp.todo;

import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
@SessionAttributes("name")
public class TodoControllerJpa {
    public TodoControllerJpa(TodoRepository todoRepository) {
        super();
        this.todoRepository = todoRepository;
    }
    private TodoRepository todoRepository;

    @RequestMapping(value = "/list-todos")
    public String showTodosList(ModelMap model) {
        String userName = getLoggedInUsername(model);
        List<Todo> todos = todoRepository.findByUsername(userName);
        model.put("todos",todos);
        return "listTodos";
    }

    private String getLoggedInUsername(ModelMap model) {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    @RequestMapping(value = "/add-todo",method = RequestMethod.GET)
    public String showNewTodoPage(ModelMap model) {
        String name = (String) model.get("name");
        Todo todo = new Todo(0,name,"Enter your description",LocalDate.now().plusYears(1),false);
        model.put("todo",todo);
        return "todo";
    }

    @RequestMapping(value = "/add-todo",method = RequestMethod.POST)
    public String addNewTodo(ModelMap model , @Valid Todo todo,
                             BindingResult result) {

        if (result.hasErrors()) {
            return "todo";
        }

        String name = getLoggedInUsername(model);

        todo.setUsername(name);
        todoRepository.save(todo);
//        todoService.addTodo(name,todo.getDescription(),
//                todo.getTargetDate(),todo.isDone());
        return "redirect:/list-todos";
    }
    @RequestMapping(value = "/delete-todo")
    public String deleteTodo(@RequestParam int id) {
        todoRepository.deleteById(id);
//        todoService.deleteTodoByID(id);
        return "redirect:/list-todos";
    }

    @RequestMapping(value = "/update-todo",method = RequestMethod.GET)
    public String showUpdateTodoPage(@RequestParam int id,ModelMap model) {
        Todo todo = todoRepository.findById(id).get();
        model.addAttribute("todo",todo);
        return "todo";
    }

    @RequestMapping(value = "/update-todo",method = RequestMethod.POST)
    public String updateNewTodo(ModelMap model , @Valid Todo todo, BindingResult result) {

        if (result.hasErrors()) {
            return "todo";
        }

        String name = getLoggedInUsername(model);
        todo.setUsername(name);
        todoRepository.save(todo);
        return "redirect:/list-todos";
    }
}
