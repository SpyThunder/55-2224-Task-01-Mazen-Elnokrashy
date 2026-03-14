package s55_2224.t_27.Mazen_Elnokrashy.services;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import s55_2224.t_27.Mazen_Elnokrashy.repositories.NoteRepository;
import s55_2224.t_27.Mazen_Elnokrashy.models.Note;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {
    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    public Note getNoteById(String id) {
        Optional<Note> note = noteRepository.findById(id);
        if (note.isEmpty()) {
            throw new ResponseStatusException(
                    org.springframework.http.HttpStatus.NOT_FOUND,
                    "Note with id " + id + " not found");
        }
        return note.get();
    }

    public List<Note> getNotesByUserId(String userId) {
        List<Note> notes = noteRepository.findByUserId(userId);
        if (notes.isEmpty()) {
            throw new ResponseStatusException(
                    org.springframework.http.HttpStatus.NOT_FOUND,
                    "No notes found for user with id " + userId);
        }
        return notes;
    }

    public Note createNote(Note  note) {
        return noteRepository.save(note);
    } 

    public Note updateNote(String id, Note note) {
        Optional<Note> updated = noteRepository.update(id, note);
        if (updated.isEmpty()) {
            throw new ResponseStatusException(
                    org.springframework.http.HttpStatus.NOT_FOUND,
                    "Note with id " + id + " not found");
        }
        return updated.get();
    }

        public void deleteNote(String id) {
            boolean deleted = noteRepository.delete(id);
            if (!deleted) {
                throw new ResponseStatusException(
                        org.springframework.http.HttpStatus.NOT_FOUND,
                        "Note with id " + id + " not found");
            }
        }

}
