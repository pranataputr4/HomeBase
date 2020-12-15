/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rpl.homebase.model;

/**
 *
 * @author SuryaNKT
 */
public class LaporanKehadiran {
    String total;
    String nama;
    String nim;
    String persentase;
    String status;

    public LaporanKehadiran() {
    }

    public LaporanKehadiran(String total, String nama, String nim, String persentase, String status) {
        this.total = total;
        this.nama = nama;
        this.nim = nim;
        this.persentase = persentase;
        this.status = status;
    }

    public String getTotal() {
        return total;
    }

    public String getNama() {
        return nama;
    }

    public String getNim() {
        return nim;
    }

    public String getPersentase() {
        return persentase;
    }

    public String getStatus() {
        return status;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public void setPersentase(String persentase) {
        this.persentase = persentase;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
