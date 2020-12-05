package com.rpl.homebase.model;

/**
 *
 * @author Doni
 */
public class Kelas {
    int id;
    String kelasNama;
    String makulID;
    String makulKode;
    String makulNama;
    String dosenID;
    String dosenNama;

    public Kelas() {
    }

    public Kelas(int id, String kelasNama, String makulID, String makulKode, String makulNama, String dosenID, String dosenNama) {
        this.id = id;
        this.kelasNama = kelasNama;
        this.makulID = makulID;
        this.makulKode = makulKode;
        this.makulNama = makulNama;
        this.dosenID = dosenID;
        this.dosenNama = dosenNama;
    }

    public Kelas(int id, String makulID, String makulKode, String makulNama, String dosenID, String dosenNama) {
        this.id = id;
        this.makulID = makulID;
        this.makulKode = makulKode;
        this.makulNama = makulNama;
        this.dosenID = dosenID;
        this.dosenNama = dosenNama;
    }

    public void setKelasNama(String kelasNama) {
        this.kelasNama = kelasNama;
    }

    public String getKelasNama() {
        return kelasNama;
    }

    public int getId() {
        return id;
    }

    public String getMakulID() {
        return makulID;
    }

    public String getMakulKode() {
        return makulKode;
    }

    public String getMakulNama() {
        return makulNama;
    }

    public String getDosenID() {
        return dosenID;
    }

    public String getDosenNama() {
        return dosenNama;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMakulID(String makulID) {
        this.makulID = makulID;
    }

    public void setMakulKode(String makulKode) {
        this.makulKode = makulKode;
    }

    public void setMakulNama(String makulNama) {
        this.makulNama = makulNama;
    }

    public void setDosenID(String dosenID) {
        this.dosenID = dosenID;
    }

    public void setDosenNama(String dosenNama) {
        this.dosenNama = dosenNama;
    }
}
