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

    @GetMapping("/login/{userId}")
    public String login(@PathVariable String userId, Model model) {
        // 데이터베이스에서 userId가 있는지 확인하는 로직을 추가할 수 있습니다.
        // userId가 존재하면 해당 사용자 정보를 가져와서 login.html 페이지로 전달합니다.
//        UserDto userDto = userService.loginUserById(userId);
        UserDto userDto = userService.getUserById(userId);
        if (userDto != null) {
            model.addAttribute("userDto", userDto);
            return "login";
        } else {
            // userId가 데이터베이스에 존재하지 않는 경우를 처리합니다.
            // 필요한 경우 오류 페이지로 리디렉션하거나 다른 작업을 수행할 수 있습니다.
            return "redirect:/list"; // 단순성을 위해 목록 페이지로 리디렉션합니다.
        }
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute("userDto") UserDto userDto) {

        // userId 및 비밀번호가 데이터베이스와 일치하는지 확인하는 로직을 추가합니다.
        // 인증되면 userpage.html로 리디렉션하고, 인증 실패 시 인증 실패를 처리합니다.
        if (isUserAuthenticated(userDto)) {
            return "redirect:/userpage/" + userDto.getUserId();
        } else {
            // 인증 실패 처리, 예를 들어 오류 메시지를 표시합니다.
            return "redirect:/list"; // 단순성을 위해 목록 페이지로 리디렉션합니다.
        }
    }


    private boolean isUserAuthenticated(UserDto userDto) {
        // 사용자의 id와 password를 가져옵니다.
        String enteredId = userDto.getUserId();
        String enteredPassword = userDto.getPassword();

        // 데이터베이스에서 사용자 정보를 가져옵니다. (가짜 데이터로 가정)
//        UserDto storedUser = userService.loginUserById(enteredId);
        UserDto storedUser = userService.getUserById(enteredId);
        // 사용자 정보가 데이터베이스에 존재하고, 비밀번호가 일치하면 인증 성공
        if (storedUser != null && enteredPassword.equals(storedUser.getPassword())) {
            return true;
        }

        // 그렇지 않으면 인증 실패
        return false;
    }

    @GetMapping("/userpage/{userId}")
    public String userpage(@PathVariable String userId, Model model) {
        // userId를 기반으로 사용자 정보를 가져와서 userpage.html로 전달합니다.
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
