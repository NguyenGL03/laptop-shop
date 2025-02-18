package vn.hoidanit.laptopshop.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

// @RestController
// public class UserController {

//   private UserService userService;

//   public UserController(UserService userService) {
//     this.userService = userService;
//   }

//   @GetMapping("/")
//   public String homePage() {
//     return this.userService.handleHello();
//   }
// }

@Controller
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @RequestMapping("/")
  public String getHomePage(Model model) {
    List<User> listUsers = this.userService.getAllUsersByEmail("123@gmail.com");

    String test = this.userService.handleHello();
    model.addAttribute("key", test);
    return "hello";
  }

  @RequestMapping("/admin/user")
  public String getUserPage(Model model) {
    List<User> listUsers = this.userService.getAllUsers();
    // System.out.println("listUsers: " + listUsers);
    model.addAttribute("listUsers", listUsers);
    return "admin/user/table-user";
  }

  @RequestMapping("/admin/user/{id}")
  public String getUserDetailPage(Model model, @PathVariable Long id) {
    User User = this.userService.getUserById(id);
    model.addAttribute("user", User);
    return "admin/user/show";
  }

  @RequestMapping("/admin/user/update/{id}")
  public String updateUserDetailPage(Model model, @PathVariable Long id) {
    User User = this.userService.getUserById(id);
    model.addAttribute("user", User);
    return "admin/user/update";
  }

  @PostMapping("/admin/user/update")
  public String updateUserDetailPage(Model model, @ModelAttribute("user") User updatedUser) {
    System.out.println("updated user: " + updatedUser);
    User currentUser = this.userService.getUserById(updatedUser.getId());
    if (currentUser != null) {
      currentUser.setName(updatedUser.getName());
      currentUser.setAddress(updatedUser.getAddress());
      currentUser.setPhone(updatedUser.getPhone());
      currentUser.setAddress(updatedUser.getAddress());
      this.userService.handleSaveUser(currentUser);
    }
    return "redirect:/admin/user";
  }

  @RequestMapping("/admin/user/create")
  public String createUserPage(Model model) {
    model.addAttribute("newUser", new User());
    return "admin/user/create";
  }

  @RequestMapping(value = "/admin/user/create", method = RequestMethod.POST)
  public String createUserPage(Model model, @ModelAttribute("newUser") User adminInfo) {
    System.out.println("admin data: " + adminInfo);
    this.userService.handleSaveUser(adminInfo);
    return "redirect:/admin/user";
  }

  @GetMapping("/admin/user/delete/{id}")
  public String deleteUserPage(Model model, @PathVariable Long id) {
    User User = this.userService.getUserById(id);
    // System.out.println("id in delete: " + id);
    model.addAttribute("user", User);
    return "admin/user/delete";
  }

  @PostMapping("/admin/user/delete")
  public String deleteUser(Model model, @ModelAttribute("user") User deletedUser) {

    User currentUser = this.userService.getUserById(deletedUser.getId());
    // System.out.println("user in delete: " + currentUser);
    if (currentUser != null) {
      System.out.println("id in delete: " + currentUser.getId());
      this.userService.handleDeleteUser(currentUser.getId());
    } else {
      System.out.println("user is null");
    }
    return "redirect:/admin/user";
  }

}