/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rpl.homebase;

import com.rpl.homebase.model.Kelas;

/**
 *
 * @author SuryaNKT
 */
public class KelasSession {
    private static KelasSession instance;

    private Kelas currentKelas;
    
    private KelasSession(Kelas kelasInput) {
        this.currentKelas = kelasInput;
//        this.privileges = privileges;
    }
    
    public static KelasSession getInstace(Kelas kelasInput) {
        if(instance == null) {
            instance = new KelasSession(kelasInput);
        }
        return instance;
    }

    public Kelas getKelas() {
        return currentKelas;
    }


    public void cleanKelasSession() {
        currentKelas = null;// or null
        instance = null;
    }
    
    public void update(Kelas newKelas){
        this.currentKelas = newKelas;
    }
}
