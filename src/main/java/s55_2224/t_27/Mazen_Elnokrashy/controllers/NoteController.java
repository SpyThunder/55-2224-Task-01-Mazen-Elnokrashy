package s55_2224.t_27.Mazen_Elnokrashy.controllers;

import org.springframework.web.bind.annotation.*;
import s55_2224.t_27.Mazen_Elnokrashy.services.NoteService;
import s55_2224.t_27.Mazen_Elnokrashy.models.Note;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/notes")
class NoteController {
    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/hello")
    public String testNoteController() {
        return "Hello from Note Controller!";
    }

    @GetMapping
    public java.util.List<Note> getAllNotes() {
        return noteService.getAllNotes();
    }

    @GetMapping("/{id}")
    public Note getNoteById(@PathVariable String id) throws ResponseStatusException {
        return noteService.getNoteById(id);
    }

    @PostMapping
    public Note createNote(@RequestBody Note note) {
        return noteService.createNote(note);
    }

    @GetMapping("/search")
    public List<Note> getNotesByTitle(@RequestParam String title) {
        return noteService.getAllNotes().stream()
                .filter(note -> note.getTitle()
                        .toLowerCase()
                        .contains(title.toLowerCase()))
                .toList();
    }

    @PutMapping("/{id}")
    public Note updateNote(@PathVariable String id, @RequestBody Note note) throws ResponseStatusException {
        return noteService.updateNote(id, note);
    }

    @DeleteMapping("/{id}")
    public void deleteNote(@PathVariable String id) throws ResponseStatusException {
        noteService.deleteNote(id);
    }

}
