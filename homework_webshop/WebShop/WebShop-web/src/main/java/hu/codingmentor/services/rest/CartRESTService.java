package hu.codingmentor.services.rest;

import hu.codingmentor.annotations.IntValidator;
import hu.codingmentor.annotations.exceptions.IllegalRequestException;
import hu.codingmentor.dto.MobileDTO;
import hu.codingmentor.dto.UserDTO;
import hu.codingmentor.services.CartService;
import hu.codingmentor.services.InventoryService;
import hu.codingmentor.services.UserManagementService;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
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

@Path("cart")
@SessionScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CartRESTService implements Serializable {

    @Inject
    private InventoryService inventoryService;

    @Inject
    private CartService cartService;

    @Inject
    private UserManagementService userManagementService;

    private static final String USER = "user";
    @POST
    @IntValidator
    @Path("/add")
    public List<MobileDTO> addToCart(@Context HttpServletRequest request, MobileDTO mobile) {
        HttpSession session = request.getSession(true);
        Object username = session.getAttribute(USER);
        if (userManagementService.getUser(username.toString()) == null) {
            session.invalidate();
            throw new IllegalRequestException("There is no user logged in, or you hace called the checkout method");
        }
        for (MobileDTO mob : inventoryService.getMobileList()) {
            if (mob.getId().equals(mobile.getId())) {
                return cartService.addToCart(mobile);
            } else {
                throw new IllegalRequestException("there is no such mobile");
            }
        }
        return cartService.itemsInCart();
    }

    @GET
    @Path("/")
    public List<MobileDTO> itemsInCart(@Context HttpServletRequest request) {

        HttpSession session = request.getSession(true);

        Object username = session.getAttribute(USER);
        if ((username == null || !(username instanceof String)) || userManagementService.getUser(username.toString()) == null) {
            session.invalidate();
            throw new IllegalRequestException("There is no user logged in");  // TODO create a runtime exception
        } else {
            return cartService.itemsInCart();
        }
    }

    @GET
    @Path("/checkout")
    public List<MobileDTO> checkout(@Context HttpServletRequest request) {

        HttpSession session = request.getSession(true);
        Object username = session.getAttribute(USER);
        UserDTO user = userManagementService.getUser(username.toString());
        if ((username == null || !(username instanceof String)) || userManagementService.getUser(username.toString()) == null) {
            session.invalidate();
            throw new IllegalRequestException("There is no user logged in"); //todo exception!
        } else {
            List<MobileDTO> items = cartService.itemsInCart();
            cartService.checkout();
            request.getSession().invalidate();
            return items;
        }
    }

}
