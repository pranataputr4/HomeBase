package com.rpl.homebase.model;

/**
 *
 * @author Doni
 */
public class Jadwal {
    int id;
    String kelasID;
    String hari;
    String jamMulai;
    String jamBerakhir;
    String kelasNama;
    String makulID;
    String makulNama;
    String userID;
    String userNama;
    String makulCode;

    public Jadwal() {
    }

    public Jadwal(int id, String kelasID, String hari, String jamMulai, String jamBerakhir, String kelasNama, String makulID, String makulNama, String userID, String userNama, String makulCode) {
        this.id = id;
        this.kelasID = kelasID;
        this.hari = hari;
        this.jamMulai = jamMulai;
        this.jamBerakhir = jamBerakhir;
        this.kelasNama = kelasNama;
        this.makulID = makulID;
        this.makulNama = makulNama;
        this.userID = userID;
        this.userNama = userNama;
        this.makulCode = makulCode;
    }

    public int getId() {
        return id;
    }

    public String getKelasID() {
        return kelasID;
    }

    public String getHari() {
        return hari;
    }

    public String getJamMulai() {
        return jamMulai;
    }

    public String getJamBerakhir() {
        return jamBerakhir;
    }

    public String getKelasNama() {
        return kelasNama;
    }

    public String getMakulID() {
        return makulID;
    }

    public String getMakulNama() {
        return makulNama;
    }

    public String getUserID() {
        return userID;
    }

    public String getUserNama() {
        return userNama;
    }

    public String getMakulCode() {
        return makulCode;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setKelasID(String kelasID) {
        this.kelasID = kelasID;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public void setJamMulai(String jamMulai) {
        this.jamMulai = jamMulai;
    }

    public void setJamBerakhir(String jamBerakhir) {
        this.jamBerakhir = jamBerakhir;
    }

    public void setKelasNama(String kelasNama) {
        this.kelasNama = kelasNama;
    }

    public void setMakulID(String makulID) {
        this.makulID = makulID;
    }

    public void setMakulNama(String makulNama) {
        this.makulNama = makulNama;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setUserNama(String userNama) {
        this.userNama = userNama;
    }

    public void setMakulCode(String makulCode) {
        this.makulCode = makulCode;
    } 
}
