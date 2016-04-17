package hu.codingmentor.services.rest;

import hu.codingmentor.annotations.IntValidator;
import hu.codingmentor.annotations.exceptions.IllegalRequestException;
import hu.codingmentor.dto.MobileDTO;
import hu.codingmentor.services.InventoryService;
import hu.codingmentor.services.UserManagementService;
import java.util.Collection;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("mobile")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class InventoryRESTService {

    @Inject
    private InventoryService inventoryService;

    @Inject
    private UserManagementService userManagementService;

    private static final String USER = "user";

    @POST
    @IntValidator
    public MobileDTO addMobile(@Context HttpServletRequest request, MobileDTO mobile) {
        HttpSession session = request.getSession(true);
        Object username = session.getAttribute(USER);
        if ((username == null || !(username instanceof String)) || userManagementService.getUser(username.toString()) == null) {
            session.invalidate();
            throw new IllegalRequestException("There is no user logged in");
        } else if (!userManagementService.getUser(username.toString()).isAdmin()) {
            session.invalidate();
            throw new IllegalRequestException("Only an admin user can add new mobile!");
        } else {
            return inventoryService.addMobile(mobile);
        }

    }

    @GET
    public Collection<MobileDTO> getMobiles() {
        return inventoryService.getMobileList();
    }

}
