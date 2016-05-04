package com.mycompany;

import javax.annotation.security.RolesAllowed;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

/**
 *
 * @author Torma Péter
 */
@ManagedBean(name = "protected", eager = true)
public class ProtectedBean {

    @Inject
    Logs logs;

    @RolesAllowed("privileged-user")
    public String getProtectedMessage() {
        if (logs.getTodos() != null) {
            return logs.getTodos().values().toString();
        }
        return "the todos is empty";
    }

    public void createLog(String name, String todo) {
        logs.getTodos().put(name, todo);
    }

    @RolesAllowed("basic-user")
    public String getBasicMessage() {
        if (logs.getTodos() != null) {
            return logs.getTodos().values().toString();
        }
        return "the todos is empty";
    }

}
