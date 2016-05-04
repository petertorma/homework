package com.mycompany;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author Torma Péter
 */
@ManagedBean(name = "logout",eager = true)
public class LogoutBean {
    public String logout(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/login" ;
   }
}
