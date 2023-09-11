package yujinns.bloguser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import yujinns.bloguser.dto.UserDto;
import yujinns.bloguser.service.UserService;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @PostMapping("/signup")
    public String createUser(UserDto userDto) {
        userService.insertUser(userDto);
        return "redirect:/list";
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<UserDto> users = userService.getAllUsers();
        model.addAttribute("userList", users);
        return "list";
    }

    @GetMapping("/{userId}")
    public String getUserById(@PathVariable String userId, Model model) {
        UserDto userDto = userService.getUserById(userId);
        model.addAttribute("userDto", userDto);
        return "userpage";
    }

    @GetMapping("/update/{userId}")
    public String updateUser(@PathVariable String userId, Model model) {
        UserDto userDto = userService.getUserById(userId);
        model.addAttribute("userDto", userDto);
        return "update";
    }

    @PostMapping("/update")
    public String updateUser(UserDto userDto) {
        userService.updateUser(userDto);
        return "redirect:/list";
    }

    @GetMapping("/delete/{userId}")
    public String deleteUser(@PathVariable String userId, Model model) {
        UserDto userDto = userService.getUserById(userId);
        model.addAttribute("userDto", userDto);
        return "delete";
    }

    @PostMapping("/delete")
    public String deleteUser(UserDto userDto) {
        userService.deleteUser(userDto.getUserId());
        return "redirect:/list";
    }
}
