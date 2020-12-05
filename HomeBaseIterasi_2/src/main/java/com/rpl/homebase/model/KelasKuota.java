package com.rpl.homebase.model;

/**
 *
 * @author Doni
 */
public class KelasKuota {
    int id;
    String mahasiswaID;
    String mahasiswaNama;
    String kelasID;
    String kelasNama;
    String dosenID;
    String dosenNama;
    String makulID;
    String makulCode;
    String makulNama;

    public KelasKuota() {
    }

    public KelasKuota(int id, String mahasiswaID, String mahasiswaNama, String kelasID, String kelasNama, String dosenID, String dosenNama, String makulID, String makulCode, String makulNama) {
        this.id = id;
        this.mahasiswaID = mahasiswaID;
        this.mahasiswaNama = mahasiswaNama;
        this.kelasID = kelasID;
        this.kelasNama = kelasNama;
        this.dosenID = dosenID;
        this.dosenNama = dosenNama;
        this.makulID = makulID;
        this.makulCode = makulCode;
        this.makulNama = makulNama;
    }

    public int getId() {
        return id;
    }

    public String getMahasiswaID() {
        return mahasiswaID;
    }

    public String getMahasiswaNama() {
        return mahasiswaNama;
    }

    public String getKelasID() {
        return kelasID;
    }

    public String getKelasNama() {
        return kelasNama;
    }

    public String getDosenID() {
        return dosenID;
    }

    public String getDosenNama() {
        return dosenNama;
    }

    public String getMakulID() {
        return makulID;
    }

    public String getMakulCode() {
        return makulCode;
    }

    public String getMakulNama() {
        return makulNama;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMahasiswaID(String mahasiswaID) {
        this.mahasiswaID = mahasiswaID;
    }

    public void setMahasiswaNama(String mahasiswaNama) {
        this.mahasiswaNama = mahasiswaNama;
    }

    public void setKelasID(String kelasID) {
        this.kelasID = kelasID;
    }

    public void setKelasNama(String kelasNama) {
        this.kelasNama = kelasNama;
    }

    public void setDosenID(String dosenID) {
        this.dosenID = dosenID;
    }

    public void setDosenNama(String dosenNama) {
        this.dosenNama = dosenNama;
    }

    public void setMakulID(String makulID) {
        this.makulID = makulID;
    }

    public void setMakulCode(String makulCode) {
        this.makulCode = makulCode;
    }

    public void setMakulNama(String makulNama) {
        this.makulNama = makulNama;
    }
}
