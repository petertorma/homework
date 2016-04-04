package hu.codingmentor.services.rest;

import hu.codingmentor.UserDTO;
import hu.codingmentor.services.UserManagementService;
import java.io.Serializable;
import java.util.Collection;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("users")
@Stateless
@Produces(MediaType.APPLICATION_JSON)
public class UserRESTService implements Serializable {

    @EJB
    UserManagementService userService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public UserDTO addUser(UserDTO user) {
        return UserManagementService.INSTANCE.addUser(user);
    }

    @DELETE
    @Path("/{userName}")
    @Consumes(MediaType.APPLICATION_JSON)
    public UserDTO removeUser(@PathParam("userName") String username) {
        return UserManagementService.INSTANCE.removeUser(username);
    }

    @PUT
    @Path("/{userName")
    @Consumes(MediaType.APPLICATION_JSON)
    public UserDTO editUser(@PathParam("userName") String username, UserDTO user) {
        if (!user.getUsername().equals(username)) {
            throw new IllegalArgumentException("Username error");
        }

        return UserManagementService.INSTANCE.editUser(user);
    }

    @GET
    public Collection<UserDTO> getUsers() {
        return UserManagementService.INSTANCE.getUsers();
    }

    @GET
    @Path("/{userName}")
    public UserDTO getUserByUsername(@PathParam("userName") String username) {
        return UserManagementService.INSTANCE.getUser(username);
    }
}
