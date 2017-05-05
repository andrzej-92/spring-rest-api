package agency.security.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User
{
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_USER = "ROLE_USER";

    private String username;
    private String passWord;
    private String email;
    private String description;
    private String role;
    private Boolean isActivated;
    private Boolean isAdmin;
}