//package com.in28min.springboot.myFirstWebApp.todo;
//
//import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//
//import java.time.LocalDate;
//import java.util.List;
//
////@Controller
//@SessionAttributes("name")
//public class TodoController {
//    public TodoController(TodoService todoService) {
//        super();
//        this.todoService = todoService;
//    }
//
//    TodoService todoService ;
//
//    @RequestMapping(value = "/list-todos")
//    public String showTodosList(ModelMap model) {
//        String userName = getLoggedInUsername(model);
//        List<Todo> todos = todoService.retrieveTodosByUser(userName);
//        model.put("todos",todos);
//        return "listTodos";
//    }
//
//    private String getLoggedInUsername(ModelMap model) {
//        Authentication authentication =
//                SecurityContextHolder.getContext().getAuthentication();
//        return authentication.getName();
//    }
//
//    @RequestMapping(value = "/add-todo",method = RequestMethod.GET)
//    public String showNewTodoPage(ModelMap model) {
//        String name = (String) model.get("name");
//        Todo todo = new Todo(0,name,"Enter your description",LocalDate.now().plusYears(1),false);
//        model.put("todo",todo);
//        return "todo";
//    }
//
//    @RequestMapping(value = "/add-todo",method = RequestMethod.POST)
//    public String addNewTodo(ModelMap model , @Valid Todo todo, BindingResult result) {
//
//        if (result.hasErrors()) {
//            return "todo";
//        }
//
//        String name = (String) model.get("name");
//        todoService.addTodo(name,todo.getDescription(), todo.getTargetDate(),false);
//        return "redirect:/list-todos";
//    }
//    @RequestMapping(value = "/delete-todo")
//    public String deleteTodo(@RequestParam int id) {
//        todoService.deleteTodoByID(id);
//        return "redirect:/list-todos";
//    }
//
//    @RequestMapping(value = "/update-todo",method = RequestMethod.GET)
//    public String showUpdateTodoPage(@RequestParam int id,ModelMap model) {
//        Todo todo = todoService.findByID(id);
//        model.addAttribute("todo",todo);
//        return "todo";
//    }
//
//    @RequestMapping(value = "/update-todo",method = RequestMethod.POST)
//    public String updateNewTodo(ModelMap model , @Valid Todo todo, BindingResult result) {
//
//        if (result.hasErrors()) {
//            return "todo";
//        }
//
//        String name = (String) model.get("name");
//        todo.setUsername(name);
//        todoService.updateTodo(todo);
//        return "redirect:/list-todos";
//    }
//}
