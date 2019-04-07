package name.fapi.module;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int userId;
    private String userName;
    private String userSurname;
    private Date userBirthDate;
    private String userEmail;
    private String userLogin;
    private String userPassword;
    private Role role;
}
