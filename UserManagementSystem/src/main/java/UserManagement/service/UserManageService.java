package UserManagement.service;

import UserManagement.entity.User;
import UserManagement.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserManageService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUser(){
        List<User> user = userRepository.findAll();
        return user;
    }

    public User updateUser(User user, Long id){
       User existingUser = userRepository.findById(id).orElseThrow(()->new RuntimeException("User not found: " + user.getUsername()));
            existingUser.setFirstname(user.getFirstname());
            existingUser.setLastname(user.getLastname());
            existingUser.setUsername(user.getUsername());
            existingUser.setEmail(user.getEmail());
            return userRepository.save(existingUser);
    }

    public void deleteUser(Long id){
         userRepository.deleteById(id);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);  // Fetch user by ID
    }


}

