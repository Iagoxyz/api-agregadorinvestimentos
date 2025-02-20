package tech.backend.agregadorinvestimentos.service;

import org.springframework.stereotype.Service;
import tech.backend.agregadorinvestimentos.controller.CreateUserDto;
import tech.backend.agregadorinvestimentos.entity.User;
import tech.backend.agregadorinvestimentos.repositories.UserRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Long createUser(CreateUserDto createUserDto) {

        var entity = new User(null,
                createUserDto.username(),
                createUserDto.email(),
                createUserDto.password(),
                Instant.now(),
                null);

        var userSaved = userRepository.save(entity);
        return userSaved.getUserId();
    }

    public Optional<User> getUserById(String userId) {
       return userRepository.findById(Long.parseLong(userId));
    }

    public List<User> listUsers() {
       return userRepository.findAll();
    }

    public void deleteById(String userId) {
        var id = Long.parseLong(userId);

        var userExists = userRepository.existsById(id);
        if (userExists) {
            userRepository.deleteById(id);
        }
    }

}
