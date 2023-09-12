package yujinns.bloguser.mapper;

import org.apache.ibatis.annotations.Mapper;
import yujinns.bloguser.dto.UserDto;

import java.util.List;

@Mapper
// UserMapper.xml의 namespace="yujinns.bloguser.mapper.UserMapper"와 interface명 UserMapper가 일치해야한다
public interface UserMapper {

    // UserMapper.xml의 id="insertUser"와 메서드명 insertUser가 일치해야한다
    // UserMapper.xml의 parameterType="yujinns.bloguser.dto.UserDto"과 매개변수 UserDto가 일치해야한다

    void insertUser(UserDto userDto);

    UserDto getUserById(String userId);

    List<UserDto> getAllUsers();

    void updateUser(UserDto userDto);

    void deleteUser(String userId);

    List<UserDto> getUsersByNickname(String nickname);
}