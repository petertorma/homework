package hu.codingmentor.services.rest;

import hu.codingmentor.dto.MobileDTO;
import hu.codingmentor.dto.UserDTO;
import hu.codingmentor.services.CartService;
import hu.codingmentor.services.InventoryService;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("cart")
@SessionScoped
@Produces(MediaType.APPLICATION_JSON)
public class CartRESTService implements Serializable {

    public CartRESTService() {
    }

    @Inject
    private CartService cartService;

    @Inject
    InventoryService inventory;
    
    @POST
    @Consumes("application/json")
    public Integer addProduct(@Context HttpServletRequest request, MobileDTO product) {
        HttpSession session = request.getSession(true);
        session.setMaxInactiveInterval(200);
        Object userObject = session.getAttribute("user");
        if ((userObject != null) && (userObject instanceof UserDTO)) {
            UserDTO user = (UserDTO) userObject;

        }
        for (MobileDTO availableProduct : inventory.getMobileList()) {
            if (availableProduct.getId().equals(product.getId())) {
                return cartService.addProduct(product);
            }
        }
        return -1;
    }
    // TODO checkout
}
