package yujinns.bloguser.dto;

import lombok.Data;

@Data
public class UserDto {

    private Long id; // 시퀀스로 생성될 ID
    private String userId;
    private String password;
    private String nickname;
    private String email;
}
