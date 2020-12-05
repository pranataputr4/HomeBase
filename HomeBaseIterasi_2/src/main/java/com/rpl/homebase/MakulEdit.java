package com.rpl.homebase;

import com.rpl.homebase.model.Makul;

/**
 *
 * @author Pranata
 */
public class MakulEdit {
    private static MakulEdit instance;

    private Makul currentMakul;
    
    private MakulEdit(Makul makulInput) {
        this.currentMakul = makulInput;
//        this.privileges = privileges;
    }
    
    public static MakulEdit getInstace(Makul makulInput) {
        if(instance == null) {
            instance = new MakulEdit(makulInput);
        }
        return instance;
    }

    public Makul getMakul() {
        return currentMakul;
    }


    public void cleanMakulSession() {
        currentMakul = null;// or null
        instance = null;
    }
    
    public void update(Makul newMakul){
        this.currentMakul = newMakul;
    }
}