package com.example.androidprojekt;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Etelek implements Serializable {

    @SerializedName("nev")
    @Expose
    private String nev;
    @SerializedName("típus")
    @Expose
    private String típus;

    @SerializedName("tipus")
    @Expose
    private String tipus;
    @SerializedName("ar")
    @Expose
    private String ar;


    @SerializedName("mennyiseg")
    @Expose
    private Integer mennyiseg;
    @SerializedName("etterem")
    @Expose
    private String etterem;
    @SerializedName("legjobb_ar_mennyiseg")
    @Expose
    private Integer legjobb_ar_mennyiseg;

    @SerializedName("eredmény")
    @Expose
    private Boolean eredmény;

    @SerializedName("üzenet")
    @Expose
    private String üzenet;

    @SerializedName("eteltipusok_id")
    @Expose
    private Integer eteltipusok_id;
    @SerializedName("ettermek_id")
    @Expose
    private Integer ettermek_id;

    /**
     * No args constructor for use in serialization
     */
    public Etelek() {
    }

    public Etelek(String nev) {
        this.nev = nev;
    }

    /**
     * @param ar
     * @param etterem
     * @param mennyiseg
     * @param típus
     * @param nev
     * @param legjobb_ar_mennyiseg
     * @param eredmény
     * @param üzenet
     */
    public Etelek(String nev, String típus, String ar, Integer mennyiseg, String etterem, Integer legjobb_ar_mennyiseg, Boolean eredmény, String üzenet) {
        super();
        this.nev = nev;
        this.típus = típus;
        this.ar = ar;
        this.mennyiseg = mennyiseg;
        this.etterem = etterem;
        this.legjobb_ar_mennyiseg = legjobb_ar_mennyiseg;
        this.eredmény = eredmény;
        this.üzenet = üzenet;
    }

    public Etelek(String nev, String ar, Integer mennyiseg, Integer eteltipusok_id, Integer ettermek_id) {
        this.nev = nev;
        this.ar = ar;
        this.mennyiseg = mennyiseg;
        this.eteltipusok_id = eteltipusok_id;
        this.ettermek_id = ettermek_id;
    }

    public Etelek(String nev, Integer eteltipusok_id, Integer ettermek_id, Integer ar, Integer mennyiseg) {

    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public String getTípus() {
        return típus;
    }

    public void setTípus(String típus) {
        this.típus = típus;
    }

    public String getTipus() {
        return tipus;
    }

    public void setTipus(String tipus) {
        this.típus = tipus;
    }

    public String getAr() {
        return ar;
    }

    public void setAr(String ar) {
        this.ar = ar;
    }

    public Integer getMennyiseg() {
        return mennyiseg;
    }

    public void setMennyiseg(Integer mennyiseg) {
        this.mennyiseg = mennyiseg;
    }

    public String getEtterem() {
        return etterem;
    }

    public void setEtterem(String etterem) {
        this.etterem = etterem;
    }

    public Integer getLegjobb_ar_mennyiseg() {
        return legjobb_ar_mennyiseg;
    }

    public void setLegjobb_ar_mennyiseg(Integer legjobb_ar_mennyiseg) {
        this.legjobb_ar_mennyiseg = legjobb_ar_mennyiseg;
    }

    public Boolean getEredmény() {
        return eredmény;
    }

    public void setEredmény(Boolean eredmény) {
        this.eredmény = eredmény;
    }

    public String getÜzenet() {
        return üzenet;
    }

    public void setÜzenet(String üzenet) {
        this.üzenet = üzenet;
    }

    @Override
    public String toString() {
        return "Etelek{" +
                "nev='" + nev + '\'' +
                ", típus='" + típus + '\'' +
                ", ar='" + ar + '\'' +
                ", mennyiseg=" + mennyiseg +
                ", etterem='" + etterem + '\'' +
                ", legjobb_ar_mennyiseg=" + legjobb_ar_mennyiseg +
                ", eredmény=" + eredmény +
                ", üzenet='" + üzenet + '\'' +
                '}';
    }
}