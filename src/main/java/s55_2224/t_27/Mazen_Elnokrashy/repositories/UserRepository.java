package s55_2224.t_27.Mazen_Elnokrashy.repositories;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;
import s55_2224.t_27.Mazen_Elnokrashy.models.User;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;



import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserRepository {
    private List<User> users = new ArrayList<>();
    private java.io.File jsonFile;

    public UserRepository() {
        InputStream inputStream = getClass().getResourceAsStream("/users.json");
        if (inputStream == null) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Unable to read users.json");
        }
        try {
            this.jsonFile = new java.io.File(getClass().getResource("/users.json").toURI());
        } catch (Exception e) {
            this.jsonFile = new java.io.File("/data/users.json");
        }
        ObjectMapper objectMapper = new ObjectMapper();
        this.users = objectMapper.readValue(inputStream, new TypeReference<List<User>>() {});
    }

    public List<User> findAll() {
        return users;
    }
    public Optional<User> findById(String id) {
        return users.stream().filter(user -> user.getId().equals(id)).findFirst();
    }
    public Optional<User> findByUsername(String name) {
        return users.stream().filter(user -> user.getUsername().equalsIgnoreCase(name)).findFirst();
    }
    public User save(User user) {
        user.setId(UUID.randomUUID().toString());
        users.add(user);
        try{
            new ObjectMapper().writeValue(jsonFile, users);

        }catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Unable to save user data");
        }
        return user;
    }
    public User update(String id, User updated) {
        Optional<User> existingUserOpt = findById(id);
        if (existingUserOpt.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "User not found with id: " + id);
        }
        User existingUser = existingUserOpt.get();
        existingUser.setUsername(updated.getUsername());
        existingUser.setEmail(updated.getEmail());
        try{
            new ObjectMapper().writeValue(jsonFile, users);

        }catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Unable to save user data");
        }
        return existingUser;
    }
    public boolean deletebyId(String id) {
        Optional<User> existingUserOpt = findById(id);
        if (existingUserOpt.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "User with id: " + id + " does not exist");
        }
        boolean removed = users.remove(existingUserOpt.get());
        if (removed) {
            try{
                new ObjectMapper().writeValue(jsonFile, users);

            }catch (Exception e) {
                throw new ResponseStatusException(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        "Unable to save user data");
            }
        }
        return removed;
    }
}
