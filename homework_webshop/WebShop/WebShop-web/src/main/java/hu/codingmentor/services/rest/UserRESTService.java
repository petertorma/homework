package hu.codingmentor.services.rest;

import hu.codingmentor.annotations.IntValidator;
import hu.codingmentor.exceptions.IllegalRequestException;
import hu.codingmentor.dto.UserDTO;
import hu.codingmentor.services.UserManagementService;
import java.io.Serializable;
import java.util.Collection;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("users")
@Produces(MediaType.APPLICATION_JSON)
public class UserRESTService implements Serializable {

    private String USER = "user";

    @Inject
    private UserManagementService userService;

    public String getUSER() {
        return USER;
    }

    public void setUSER(String USER) {
        this.USER = USER;
    }

    @POST
    @IntValidator
    @Consumes(MediaType.APPLICATION_JSON)
    public UserDTO addUser(UserDTO user) {
        return userService.addUser(user);
    }

    @DELETE
    @IntValidator
    @Path("/{userName}")
    @Consumes(MediaType.APPLICATION_JSON)
    public UserDTO removeUser(@PathParam("userName") String username) {
        UserDTO user = userService.removeUser(username);
        if (user == null) {
            throw new IllegalRequestException("there is no user with this username: " + username);
        } else {
            return user;
        }
    }

    @PUT
    @IntValidator
    @Path("/{username}")
    @Consumes(MediaType.APPLICATION_JSON)
    public UserDTO editUser(@PathParam("username") String username, UserDTO user) {
        if (!user.getUsername().equals(username)) {
            throw new IllegalRequestException("you did not enter valid parameters to edit the user");
        }

        return userService.editUser(user);
    }

    @GET
    public Collection<UserDTO> getUsers() {
        return userService.getUsers();
    }

    @GET
    @Path("/{userName}")
    public UserDTO getUserByUsername(@PathParam("userName") String username) {
        userService.getUser(username);
        UserDTO user = userService.getUser(username);
        if (!user.getUsername().equals(username)) {
            throw new IllegalRequestException("There is no user with these values");
        } else {
            return user;
        }
    }

    @PUT
    @Path("/login/user/{username}/pass/{password}")
    public UserDTO login(@Context HttpServletRequest request, @PathParam("username") String username, @PathParam("password") String password) {
        HttpSession session = request.getSession(true);
        if (!(userService.getUser(username) instanceof UserDTO)) {
            throw new BadRequestException("there is no user with this username");
        } else {
            UserDTO tempUser = userService.getUser(username);
            if (tempUser == null) {
                session.invalidate();
                throw new BadRequestException("there is no such a user:  " + username + " ,with this password" + password);
            } else if (tempUser.getPassowrd().equals(password)) {
                session.setMaxInactiveInterval(20000);
                session.setAttribute(USER, tempUser.getUsername());
                return tempUser;
            } else {
                session.invalidate();
                throw new IllegalRequestException("you did not enter correct parameters to login");
            }
        }
    }

    @POST
    @Path("/logout")
    public void logout(@Context HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        session.invalidate();
    }

}
