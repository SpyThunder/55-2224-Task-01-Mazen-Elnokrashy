package s55_2224.t_27.Mazen_Elnokrashy.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import s55_2224.t_27.Mazen_Elnokrashy.models.Note;
import s55_2224.t_27.Mazen_Elnokrashy.services.NoteService;
import s55_2224.t_27.Mazen_Elnokrashy.services.UserService;
import s55_2224.t_27.Mazen_Elnokrashy.models.User;
import s55_2224.t_27.Mazen_Elnokrashy.services.NoteService;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    private final NoteService noteService;
    private final UserService userService;
    UserController(UserService userService, NoteService noteService) {
        this.userService = userService;
        this.noteService = noteService;
    }

    @GetMapping("/{id}/notes")
    public List<Note> getNotesByUserId(@PathVariable String id) throws ResponseStatusException
    {
        return noteService.getNotesByUserId(id);
    }

    @GetMapping("/")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable String id) throws ResponseStatusException {
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

    @GetMapping("/search")
    public User getUserByUsername(@RequestParam String username) {
        return userService.getAllUsers().stream()
                .filter(u -> u.getUsername().equalsIgnoreCase(username))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "User not found"));
    }



}
