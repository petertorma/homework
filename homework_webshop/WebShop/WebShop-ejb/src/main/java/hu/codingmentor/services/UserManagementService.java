package hu.codingmentor.services;

import hu.codingmentor.annotations.IntValidator;
import hu.codingmentor.dto.UserDTO;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class UserManagementService {

    private final Map<String, UserDTO> userList = new HashMap<>();

    @PostConstruct
    public void init() {
        userList.put("admin", new UserDTO("admin", "Aa=123", "Peter", "Torma", "1995-05-02", "2016-02-02"));
        userList.put("user", new UserDTO("user", "Aa=123", "Szkájvóker", "Kanalas", "1993-02-12", "2015-02-02"));
    }

    @IntValidator
    public UserDTO addUser(UserDTO user) {
        userList.put(user.getUsername(), user);
        return user;
    }

    public UserDTO removeUser(String username) {
        return userList.remove(username);
    }

    public UserDTO editUser(UserDTO user) {
        userList.put(user.getUsername(), user);
        return user;
    }

    public Collection<UserDTO> getUsers() {
        return userList.values();
    }

    public UserDTO getUser(String username) {
        return userList.get(username);
    }

}
