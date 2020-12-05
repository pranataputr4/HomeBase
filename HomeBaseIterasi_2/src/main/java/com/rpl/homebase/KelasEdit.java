package com.rpl.homebase;

import com.rpl.homebase.model.Kelas;

/**
 *
 * @author Doni
 */
public class KelasEdit {
    private static KelasEdit instance;

    private Kelas currentKelas;
    
    private KelasEdit(Kelas kelasInput) {
        this.currentKelas = kelasInput;
//        this.privileges = privileges;
    }
    
    public static KelasEdit getInstace(Kelas kelasInput) {
        if(instance == null) {
            instance = new KelasEdit(kelasInput);
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