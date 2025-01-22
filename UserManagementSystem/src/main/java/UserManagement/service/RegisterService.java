package UserManagement.service;

import UserManagement.entity.User;
import UserManagement.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegisterService {

    @Autowired
    private UserRepository userRepository;

    public boolean createUser(User user) {

        Optional<User> optionalUser = userRepository.findByUsername(user.getUsername());
        if (optionalUser.isPresent()) {
            return false;
        }
        user.setFirstname(user.getFirstname());
        user.setLastname(user.getLastname());
        user.setEmail(user.getEmail());
        user.setUsername(user.getUsername());
        user.setPassword(user.getPassword());
        userRepository.save(user);

        return true;
    }
}
