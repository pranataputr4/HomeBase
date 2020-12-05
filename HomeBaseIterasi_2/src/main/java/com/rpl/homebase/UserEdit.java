package com.rpl.homebase;

import com.rpl.homebase.model.User;

/**
 *
 * @author Pranata
 */
public class UserEdit {
    private static UserEdit instance;

    private User currentUser;
    
    private UserEdit(User userInput) {
        this.currentUser = userInput;
//        this.privileges = privileges;
    }
    
    public static UserEdit getInstace(User userInput) {
        if(instance == null) {
            instance = new UserEdit(userInput);
        }
        return instance;
    }

    public User getUser() {
        return currentUser;
    }


    public void cleanUserSession() {
        currentUser = null;// or null
        instance = null;
    }
    
    public void update(User newUser){
        this.currentUser = newUser;
    }
}
