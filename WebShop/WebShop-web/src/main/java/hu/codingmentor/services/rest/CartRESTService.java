package hu.codingmentor.services.rest;

import hu.codingmentor.annotations.IntValidator;
import hu.codingmentor.dto.MobileDTO;
import hu.codingmentor.dto.UserDTO;
import hu.codingmentor.services.CartService;
import hu.codingmentor.services.InventoryService;
import hu.codingmentor.services.UserManagementService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("cart")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CartRESTService implements Serializable {

    @Inject
    InventoryService inventoryService;

    @Inject
    CartService cartService;

    @Inject
    UserManagementService userManagementService;

    public CartRESTService() {
    }

    @POST
    @IntValidator
    @Path("/add")
    public List<MobileDTO> addToCart(@Context HttpServletRequest request, MobileDTO mobile) {
        HttpSession session = request.getSession(true);
        session.setMaxInactiveInterval(1500);
        Object username = session.getAttribute("user");
        if (userManagementService.getUser(username.toString()) == null) {
            session.invalidate();
            throw new BadRequestException("There is no user logged in"); // TODO create a runtime exception
        } else {
            return cartService.addToCart(mobile);
        }
    }

    @GET
    @Path("/")
    public List<MobileDTO> itemsInCart(@Context HttpServletRequest request) {

        HttpSession session = request.getSession(true);
        session.setMaxInactiveInterval(1500);

        Object username = session.getAttribute("user");
        if ((username == null || !(username instanceof String)) || userManagementService.getUser(username.toString()) == null) {
            session.invalidate();
            throw new BadRequestException("There is no user logged in");  // TODO create a runtime exception
        } else {
            return cartService.itemsInCart();   
        }
    }

    @GET
    @Path("/checkout")
    public List<MobileDTO> checkout(@Context HttpServletRequest request) {

        HttpSession session = request.getSession(true);
        session.setMaxInactiveInterval(1500);
        Object username = session.getAttribute("user");
        UserDTO user = userManagementService.getUser((String) username);
        if ((username == null || !(username instanceof String)) || userManagementService.getUser(username.toString()) == null) {
            session.invalidate();
            throw new BadRequestException("There is no user logged in"); //todo exception!
        } else {
            List<MobileDTO> items = user.getCart();
            cartService.checkout();
            request.getSession().invalidate();
            return items;
        }
    }

   

}
