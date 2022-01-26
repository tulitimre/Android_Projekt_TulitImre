package com.example.androidprojekt;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Ettermek implements Serializable {

    @SerializedName("nev")
    @Expose
    private String nev;
    @SerializedName("ertekeles")
    @Expose
    private String ertekeles;
    @SerializedName("ertekeles_db")
    @Expose
    private Integer ertekelesDb;
    @SerializedName("hazhozszallit")
    @Expose
    private Integer hazhozszallit;
    @SerializedName("szal_ar")
    @Expose
    private String szalAr;
    @SerializedName("minimum_rendeles")
    @Expose
    private Integer minimumRendeles;
    @SerializedName("cim")
    @Expose
    private String cim;
    @SerializedName("telefonszam")
    @Expose
    private String telefonszam;

    /**
     * No args constructor for use in serialization
     */
    public Ettermek() {
    }

    /**
     * @param szalAr
     * @param minimumRendeles
     * @param cim
     * @param ertekeles
     * @param telefonszam
     * @param ertekelesDb
     * @param hazhozszallit
     * @param nev
     */
    public Ettermek(String nev, String ertekeles, Integer ertekelesDb, Integer hazhozszallit, String szalAr, Integer minimumRendeles, String cim, String telefonszam) {
        super();
        this.nev = nev;
        this.ertekeles = ertekeles;
        this.ertekelesDb = ertekelesDb;
        this.hazhozszallit = hazhozszallit;
        this.szalAr = szalAr;
        this.minimumRendeles = minimumRendeles;
        this.cim = cim;
        this.telefonszam = telefonszam;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public String getErtekeles() {
        return ertekeles;
    }

    public void setErtekeles(String ertekeles) {
        this.ertekeles = ertekeles;
    }

    public Integer getErtekelesDb() {
        return ertekelesDb;
    }

    public void setErtekelesDb(Integer ertekelesDb) {
        this.ertekelesDb = ertekelesDb;
    }

    public Integer getHazhozszallit() {
        return hazhozszallit;
    }

    public void setHazhozszallit(Integer hazhozszallit) {
        this.hazhozszallit = hazhozszallit;
    }

    public String getSzalAr() {
        return szalAr;
    }

    public void setSzalAr(String szalAr) {
        this.szalAr = szalAr;
    }

    public Integer getMinimumRendeles() {
        return minimumRendeles;
    }

    public void setMinimumRendeles(Integer minimumRendeles) {
        this.minimumRendeles = minimumRendeles;
    }

    public String getCim() {
        return cim;
    }

    public void setCim(String cim) {
        this.cim = cim;
    }

    public String getTelefonszam() {
        return telefonszam;
    }

    public void setTelefonszam(String telefonszam) {
        this.telefonszam = telefonszam;
    }

    @Override
    public String toString() {
        return "Ettermek{" +
                "nev='" + nev + '\'' +
                ", ertekeles='" + ertekeles + '\'' +
                ", ertekelesDb=" + ertekelesDb +
                ", hazhozszallit=" + hazhozszallit +
                ", szalAr='" + szalAr + '\'' +
                ", minimumRendeles=" + minimumRendeles +
                ", cim='" + cim + '\'' +
                ", telefonszam='" + telefonszam + '\'' +
                '}';
    }
}