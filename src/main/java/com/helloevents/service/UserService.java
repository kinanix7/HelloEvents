package com.helloevents.service;

import com.helloevents.dto.SignupRequest;
import com.helloevents.dto.UserResponseDTO;
import com.helloevents.model.User;
import com.helloevents.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

/**  public List<User> getAllUsers() {
        return userRepository.findAll();
 }
 // hadi kanet ta tjibe kolchi
 **/
public List<UserResponseDTO> getAllUserDTOs() {
    return userRepository.findAll().stream()
            .map(user -> new UserResponseDTO(user.getId(),
                                                   user.getEmail(),
                                                    user.getRole().name()))
                                                     .toList();
}
/**   public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
 **/

public UserResponseDTO getUserDTOById(Long id) {
    return userRepository.findById(id)
            .map(user -> new UserResponseDTO(user.getId(), user.getEmail(), user.getRole().name()))
            .orElse(null);
}


    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public User registerUser(SignupRequest signupRequest) {
        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            throw new IllegalArgumentException("Username is already taken");
        }
        User user = new User();
        user.setUsername(signupRequest.getUsername());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        user.setRole(signupRequest.getRole());

        return userRepository.save(user);
    }

    public boolean deleteUser(Long id){
        Optional<User> userOpt = userRepository.findById(id);
        if(userOpt.isPresent()){
            return false;
        }
        userRepository.deleteById(id);
        return true;
    }
   /** public User updateUser(Long id, User signupRequest) {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isEmpty()) {
            return null;
        }
        User user = userOpt.get();
        user.setUsername(signupRequest.getUsername());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        user.setRole(signupRequest.getRole());

        return userRepository.save(user);
    } **/

   public UserResponseDTO updateUser(Long id, User signupRequest) {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isEmpty()) {
            return null;
        }
        User user = userOpt.get();
        user.setEmail(signupRequest.getEmail());
        user.setRole(signupRequest.getRole());

        User updatedUser = userRepository.save(user);
        return new UserResponseDTO(updatedUser.getId(), updatedUser.getEmail(), updatedUser.getRole().name());
    }



}
