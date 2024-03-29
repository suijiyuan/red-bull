package xyz.outerringroad.redbull.bean.dto;

public class UserDTO {

    private String username;
    private String password;

    public UserDTO() {
    }

    public static UserDTO createUserDTO() {
        return new UserDTO();
    }

    private UserDTO(String username) {
        this.username = username;
    }

    public static UserDTO createUserDTO(String username) {
        return new UserDTO(username);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
