package yujinns.bloguser.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yujinns.bloguser.dto.UserDto;
import yujinns.bloguser.mapper.UserMapper;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void insertUser(UserDto userDto) {
        userMapper.insertUser(userDto);
    }

    public UserDto getUserById(String userId) {
        return userMapper.getUserById(userId);
    }

    public List<UserDto> getAllUsers() {
        return userMapper.getAllUsers();
    }

    public void updateUser(UserDto userDto) {
        userMapper.updateUser(userDto);
    }

    public void deleteUser(String userId) {
        userMapper.deleteUser(userId);
    }

//    public UserDto loginUserById(String userId) {
//        return userMapper.loginUserById(userId);
//    }
}
