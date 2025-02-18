package vn.hoidanit.laptopshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.repository.UserRepository;

@Service
public class UserService {
  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public List<User> getAllUsers() {
    return this.userRepository.findAllByOrderByIdAsc();
  }

  public List<User> getAllUsersByEmail(String email) {
    return this.userRepository.findByEmail(email);
  }

  public User getUserById(Long id) {
    return this.userRepository.findById(id).orElse(null);
  }

  public User handleSaveUser(User user) {
    return this.userRepository.save(user);
  }

  public void handleDeleteUser(Long id) {
    this.userRepository.deleteById(id);
  }

  public String handleHello() {
    return "Hello from controller";
  }
}
