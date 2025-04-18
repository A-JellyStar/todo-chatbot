package com.example.todochatbot.controller;

import com.example.todochatbot.entity.Todo;
import com.example.todochatbot.service.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TodoController {

    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    // Hiển thị trang chủ với danh sách công việc
    @GetMapping("/")
    public String home(Model model) {
        List<Todo> todos = todoService.getAllTodos();
        model.addAttribute("todos", todos);
        model.addAttribute("newTodo", new Todo());
        return "index";
    }

    // Hiển thị công việc đã hoàn thành
    @GetMapping("/completed")
    public String completedTodos(Model model) {
        List<Todo> completedTodos = todoService.getCompletedTodos();
        model.addAttribute("todos", completedTodos);
        model.addAttribute("newTodo", new Todo());
        return "index";
    }

    // Hiển thị công việc chưa hoàn thành
    @GetMapping("/uncompleted")
    public String uncompletedTodos(Model model) {
        List<Todo> uncompletedTodos = todoService.getUncompletedTodos();
        model.addAttribute("todos", uncompletedTodos);
        model.addAttribute("newTodo", new Todo());
        return "index";
    }

    // Thêm công việc mới
    @PostMapping("/add")
    public String addTodo(@Valid @ModelAttribute("newTodo") Todo todo) {
        todoService.createTodo(todo);
        return "redirect:/";
    }

    // Thêm công việc mới với AI
    @PostMapping("/generate")
    public String generateTodo(@RequestParam String prompt) {
        todoService.createTodoWithAI(prompt);
        return "redirect:/";
    }

    // Cập nhật trạng thái hoàn thành
    @PostMapping("/{id}/toggle")
    public String toggleTodoStatus(@PathVariable Long id) {
        Todo todo = todoService.getTodoById(id);
        todo.setCompleted(!todo.isCompleted());
        todoService.updateTodo(id, todo);
        return "redirect:/";
    }

    // Xóa công việc
    @PostMapping("/{id}/delete")
    public String deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return "redirect:/";
    }

    // Hiển thị form chỉnh sửa
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Todo todo = todoService.getTodoById(id);
        model.addAttribute("todo", todo);
        return "edit";
    }

    // Cập nhật công việc
    @PostMapping("/{id}/update")
    public String updateTodo(@PathVariable Long id, @Valid @ModelAttribute Todo todo) {
        todoService.updateTodo(id, todo);
        return "redirect:/";
    }

    // API endpoints for Swagger documentation
    @RestController
    @RequestMapping("/api/todos")
    @Tag(name = "Todo API", description = "API quản lý công việc cần làm với tích hợp AI")
    private class TodoApiController {
        @GetMapping
        public ResponseEntity<List<Todo>> getAllTodos() {
            return ResponseEntity.ok(todoService.getAllTodos());
        }

        @PostMapping
        public ResponseEntity<Todo> createTodo(@Valid @RequestBody Todo todo) {
            return ResponseEntity.ok(todoService.createTodo(todo));
        }

        @GetMapping("/{id}")
        public ResponseEntity<Todo> getTodoById(@PathVariable Long id) {
            return ResponseEntity.ok(todoService.getTodoById(id));
        }

        @PutMapping("/{id}")
        public ResponseEntity<Todo> updateTodo(@PathVariable Long id, @Valid @RequestBody Todo todo) {
            return ResponseEntity.ok(todoService.updateTodo(id, todo));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
            todoService.deleteTodo(id);
            return ResponseEntity.ok().build();
        }
    }
}