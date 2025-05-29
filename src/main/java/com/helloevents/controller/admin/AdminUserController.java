package com.helloevents.controller.admin;

import com.helloevents.dto.UserResponseDTO;
import com.helloevents.model.User;
import com.helloevents.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/users")
@PreAuthorize("hasRole('ADMIN')")
@CrossOrigin("*")

public class AdminUserController {
    @Autowired
private UserService userService;

  /*  @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    } */
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<UserResponseDTO> getAllUsers() {
      return userService.getAllUserDTOs();
  }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDTO getUserById(@PathVariable Long id) {
        return userService.getUserDTOById(id);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "Utilisateur supprimé avec succès";
    }
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDTO updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

}
