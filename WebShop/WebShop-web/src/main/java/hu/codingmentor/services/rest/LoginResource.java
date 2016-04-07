package hu.codingmentor.services.rest;

import hu.codingmentor.dto.UserDTO;
import hu.codingmentor.services.IntervalTimerBean;
import hu.codingmentor.services.UserManagementService;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

@Path("/login")
@SessionScoped
public class LoginResource implements Serializable {

    @Inject
    private IntervalTimerBean timerBean;

    @Inject
    UserManagementService userManagment;

    public LoginResource() {
    }

    @POST
    @Consumes("application/json")
    public boolean login(@Context HttpServletRequest request, UserDTO user ) {
        HttpSession session = request.getSession(true);
        for (UserDTO acceptedUser : userManagment.getUsers()) {
            if (acceptedUser.getUsername().equals(user.getUsername())) {
                session.setMaxInactiveInterval(200);
                session.setAttribute("user", user);
                timerBean.add();
                return true;
            }
        }
        session.invalidate();
        return false;
    }
}
