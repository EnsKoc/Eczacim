package com.eneskoc.turkeypharmaciesonduty.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProvinceModel {

    @SerializedName("il")
    @Expose
    private String il;

    @SerializedName("plaka")
    @Expose
    private Integer plaka;

    @SerializedName("ilceleri")
    @Expose
    private List<String> ilceleri = null;

    public String getIl() {
        return il;
    }

    public void setIl(String il) {
        this.il = il;
    }

    public Integer getPlaka() {
        return plaka;
    }

    public void setPlaka(Integer plaka) {
        this.plaka = plaka;
    }

    public List<String> getIlceleri() {
        return ilceleri;
    }

    public void setIlceleri(List<String> ilceleri) {
        this.ilceleri = ilceleri;
    }
}
