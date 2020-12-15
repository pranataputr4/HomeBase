/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rpl.homebase;

import com.rpl.homebase.model.Jadwal;

/**
 *
 * @author Doni
 */
public class JadwalSession {
    private static JadwalSession instance;

    private Jadwal currentJadwal;
    
    private JadwalSession(Jadwal jadwalInput) {
        this.currentJadwal = jadwalInput;
//        this.privileges = privileges;
    }
    
    public static JadwalSession getInstace(Jadwal jadwalInput) {
        if(instance == null) {
            instance = new JadwalSession(jadwalInput);
        }
        return instance;
    }

    public Jadwal getJadwal() {
        return currentJadwal;
    }


    public void cleanJadwalSession() {
        currentJadwal = null;// or null
        instance = null;
    }
    
    public void update(Jadwal newJadwal){
        this.currentJadwal = newJadwal;
    }
}
