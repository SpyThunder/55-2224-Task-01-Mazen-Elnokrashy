package s55_2224.t_27.Mazen_Elnokrashy.services;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import s55_2224.t_27.Mazen_Elnokrashy.repositories.UserRepository;
import s55_2224.t_27.Mazen_Elnokrashy.models.User;

@Service
 public class UserService {
        private final UserRepository userRepository;
        public UserService(UserRepository userRepository) {
            this.userRepository = userRepository;
        }

        public List<User> getAllUsers() {
            return userRepository.findAll();
        }

        public Optional<User> getUserById(String id) {
            Optional<User> user = userRepository.findById(id);
            if(user.isEmpty()) {
                throw new ResponseStatusException(
                        org.springframework.http.HttpStatus.NOT_FOUND,
                        "User with id " + id + " not found");
            }
            return user;
        }
        public Optional<User> getUserByUsername(String username) {
            Optional<User> user = userRepository.findByUsername(username);
            if(user.isEmpty()) {
                throw new ResponseStatusException(
                        org.springframework.http.HttpStatus.NOT_FOUND,
                        "User with username " + username + " not found");
            }
            return user;
        }
        public User createUser(User user) {
            return userRepository.save(user);
        }
        public User updateUser(String id, User updated) throws ResponseStatusException {
            return userRepository.update(id, updated);
        }

        public void deleteUser(String id) {
            userRepository.deletebyId(id);
        }



}
