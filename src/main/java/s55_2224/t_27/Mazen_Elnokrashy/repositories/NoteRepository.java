package s55_2224.t_27.Mazen_Elnokrashy.repositories;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;
import s55_2224.t_27.Mazen_Elnokrashy.models.Note;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.util.List;
import java.io.InputStream;
import java.util.Optional;

@Repository
public class NoteRepository {
    private List<Note> notes;
    private java.io.File jsonFile;

    public NoteRepository() {
        InputStream inputStream = getClass().getResourceAsStream("/notes.json");
        if (inputStream == null) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Unable to read notes.json");
        }
        try {
            this.jsonFile = new java.io.File(getClass().getResource("/notes.json").toURI());
        } catch (Exception e) {
            this.jsonFile = new java.io.File("/data/notes.json");
        }
        ObjectMapper objectMapper = new ObjectMapper();
        this.notes = objectMapper.readValue(inputStream, new TypeReference<List<Note>>()
        {});
    }

    public List<Note> findAll() {
        return notes;
    }

    public Optional<Note> findById(String id) {
        return notes.stream()
                .filter(note -> note.getId().equals(id))
                .findFirst();
    }

    public List<Note> findByUserId(String userId) {
        return notes.stream()
                .filter(note -> note.getUserId().equals(userId))
                .toList();
    }

    public Note save(Note note) {
        notes.add(note);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(jsonFile, notes);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Unable to write to notes.json");
        }
        return note;
    }

    public Optional<Note> update(String id, Note updated) {
        Optional<Note> existingNoteOpt = findById(id);
        if (existingNoteOpt.isEmpty()) {
            return Optional.empty();
        }
        Note existingNote = existingNoteOpt.get();
        existingNote = new Note(
                existingNote.getId(),
                updated.getTitle(),
                updated.getContent(),
                existingNote.getUserId(),
                existingNote.getCreatedAt()
        );
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(jsonFile, notes);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Unable to write to notes.json");
        }
        return Optional.of(existingNote);
    }

     public boolean delete(String id) {
        Optional<Note> existingNoteOpt = findById(id);
        if (existingNoteOpt.isEmpty()) {
            return false;
        }
        notes.remove(existingNoteOpt.get());
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(jsonFile, notes);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Unable to write to notes.json");
        }
        return true;
    }
}
