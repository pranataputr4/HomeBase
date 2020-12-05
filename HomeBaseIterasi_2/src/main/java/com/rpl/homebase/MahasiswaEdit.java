package com.rpl.homebase;

import com.rpl.homebase.model.Mahasiswa;

/**
 *
 * @author Pranata
 */
public class MahasiswaEdit {
    private static MahasiswaEdit instance;

    private Mahasiswa currentMahasiswa;
    
    private MahasiswaEdit(Mahasiswa mahasiswaInput) {
        this.currentMahasiswa = mahasiswaInput;
//        this.privileges = privileges;
    }
    
    public static MahasiswaEdit getInstace(Mahasiswa mahasiswaInput) {
        if(instance == null) {
            instance = new MahasiswaEdit(mahasiswaInput);
        }
        return instance;
    }

    public Mahasiswa getMahasiswa() {
        return currentMahasiswa;
    }


    public void cleanMahasiswaSession() {
        currentMahasiswa = null;// or null
        instance = null;
    }
    
    public void update(Mahasiswa newMahasiswa){
        this.currentMahasiswa = newMahasiswa;
    }
}