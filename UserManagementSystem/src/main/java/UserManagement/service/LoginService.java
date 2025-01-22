package UserManagement.service;

import UserManagement.entity.Login;
import UserManagement.entity.User;
import UserManagement.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    public boolean loginProcess(Login login) {
        Optional<User> user = userRepository.findByUsername(login.getUsername());
        if (user.isEmpty()) {
            return false;
        } else if(user.get().getPassword().equals(login.getPassword())) {
            return true;
        } else {
            return false;
        }
    }

    public User findByUsername(String name) {
        return userRepository.findByUsername(name).orElse(null);// Fetch user by username
    }


}
