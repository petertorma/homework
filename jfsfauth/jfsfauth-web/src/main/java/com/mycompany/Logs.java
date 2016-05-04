package com.mycompany;

import java.util.HashMap;
import java.util.Map;
import javax.ejb.Singleton;
import javax.ejb.Stateful;
import javax.ejb.Stateless;

/**
 *
 * @author Torma Péter
 */
@Singleton
public class Logs {
    
    Map<String,String> todos = new HashMap<>();

    public Logs() {
    }

    public Map<String, String> getTodos() {
        return todos;
    }

    public void setTodos(Map<String, String> todos) {
        this.todos = todos;
    }
    
    
    
    
}
