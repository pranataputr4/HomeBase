package com.rpl.homebase.model;

/**
 *
 * @author Pranata
 */
public class Makul {
    private int id;
    private String kode;
    private String nama;
    private String dosenID;
    private String dosen;

    public Makul() {
    }

    public Makul(int id, String kode, String nama, String dosenID, String dosen) {
        this.id = id;
        this.kode = kode;
        this.nama = nama;
        this.dosenID = dosenID;
        this.dosen = dosen;
    }

    public int getId() {
        return id;
    }

    public String getKode() {
        return kode;
    }

    public String getNama() {
        return nama;
    }

    public String getDosenID() {
        return dosenID;
    }

    public String getDosen() {
        return dosen;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setDosenID(String dosenID) {
        this.dosenID = dosenID;
    }

    public void setDosen(String dosen) {
        this.dosen = dosen;
    }
}
