/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rpl.homebase.model;

/**
 *
 * @author Pranata
 */
public class Pertemuan {
    int id;
    int no;
    String nama;
    String tanggal;
    String jadwalID;
    String jumlahHadir;
    String jumlahAbsen;

    public Pertemuan() {
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public Pertemuan(int id, int no, String nama, String tanggal, String jadwalID, String jumlahHadir, String jumlahAbsen) {
        this.id = id;
        this.no = no;
        this.nama = nama;
        this.tanggal = tanggal;
        this.jadwalID = jadwalID;
        this.jumlahHadir = jumlahHadir;
        this.jumlahAbsen = jumlahAbsen;
    }

    public Pertemuan(int id, String nama, String tanggal, String jadwalID) {
        this.id = id;
        this.nama = nama;
        this.tanggal = tanggal;
        this.jadwalID = jadwalID;
    }

    public Pertemuan(int id, String nama, String tanggal, String jadwalID, String jumlahHadir) {
        this.id = id;
        this.nama = nama;
        this.tanggal = tanggal;
        this.jadwalID = jadwalID;
        this.jumlahHadir = jumlahHadir;
    }

    public Pertemuan(int id, String nama, String tanggal, String jadwalID, String jumlahHadir, String jumlahAbsen) {
        this.id = id;
        this.nama = nama;
        this.tanggal = tanggal;
        this.jadwalID = jadwalID;
        this.jumlahHadir = jumlahHadir;
        this.jumlahAbsen = jumlahAbsen;
    }

    public String getJumlahAbsen() {
        return jumlahAbsen;
    }

    public String getJumlahHadir() {
        return jumlahHadir;
    }

    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getTanggal() {
        return tanggal;
    }

    public String getJadwalID() {
        return jadwalID;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setJumlahAbsen(String jumlahAbsen) {
        this.jumlahAbsen = jumlahAbsen;
    }

    public void setJumlahHadir(String jumlahHadir) {
        this.jumlahHadir = jumlahHadir;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public void setJadwalID(String jadwalID) {
        this.jadwalID = jadwalID;
    }
    
    
}
