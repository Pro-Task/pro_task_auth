package pro_task_authentification.auth.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pro_task_authentification.auth.model.User;
import pro_task_authentification.auth.repository.UserRepository;

@Service
public class AuthService {
    private final UserRepository userRepository;

    @Autowired
    public AuthService (UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public Optional<User> authenticate(String username, String password){
        return userRepository.findByUsername(username)
            .filter(user -> user.getPassword().equals(password));
    }
}
