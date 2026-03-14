package s55_2224.t_27.Mazen_Elnokrashy.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import s55_2224.t_27.Mazen_Elnokrashy.services.UserService;
import s55_2224.t_27.Mazen_Elnokrashy.models.User;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable String id) {
        return userService.getUserById(id);
    }

    @PostMapping("/")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable String id, @RequestBody User updated) {
        return userService.updateUser(id, updated);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
    }

    @GetMapping("/serch?username={username}")
    public Optional<User> getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username.toLowerCase());
    }



}
