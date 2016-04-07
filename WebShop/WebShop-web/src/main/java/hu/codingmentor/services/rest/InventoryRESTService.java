package hu.codingmentor.services.rest;

import hu.codingmentor.dto.MobileDTO;
import hu.codingmentor.services.InventoryService;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Stateless
@Path("mobile")
@Produces(MediaType.APPLICATION_JSON)
public class InventoryRESTService {

    @Inject
    InventoryService inventory;

    @POST
    @Consumes("application/json")
    public MobileDTO addMobile(MobileDTO mobile) {
        return inventory.addMobile(mobile);
    }

    @GET
    public Collection<MobileDTO> getMobiles() {
        return inventory.getMobileList();
    }

}
