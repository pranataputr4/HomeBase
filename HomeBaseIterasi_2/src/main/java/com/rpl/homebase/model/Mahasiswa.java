package com.rpl.homebase.model;

/**
 *
 * @author Pranata
 */
public class Mahasiswa {
    private int id;
    private String nim;
    private String nama;
    private String kelamin;
    private String jurusan;

    public Mahasiswa() {
    }

    public Mahasiswa(int id, String nim, String nama, String kelamin, String jurusan) {
        this.id = id;
        this.nim = nim;
        this.nama = nama;
        this.kelamin = kelamin;
        this.jurusan = jurusan;
    }

    public int getId() {
        return id;
    }

    public String getNim() {
        return nim;
    }

    public String getNama() {
        return nama;
    }

    public String getKelamin() {
        return kelamin;
    }

    public String getJurusan() {
        return jurusan;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setKelamin(String kelamin) {
        this.kelamin = kelamin;
    }

    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }
}