package com.eneskoc.turkeypharmaciesonduty.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PharmacyModel {

    @SerializedName("eczaneAdi")
    @Expose
    private String eczaneAdi;

    @SerializedName("eczaneIlAdi")
    @Expose
    private String eczaneIlAdi;

    @SerializedName("eczaneIlceAdi")
    @Expose
    private String eczaneIlceAdi;

    @SerializedName("eczaneAdres")
    @Expose
    private String eczaneAdres;

    @SerializedName("tarih")
    @Expose
    private String tarih;

    @SerializedName("baslangic")
    @Expose
    private String baslangic;

    @SerializedName("bitis")
    @Expose
    private String bitis;

    @SerializedName("enlem")
    @Expose
    private String enlem;

    @SerializedName("boylam")
    @Expose
    private String boylam;

    @SerializedName("glnNo")
    @Expose
    private String glnNo;

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("olusturmaTarihi")
    @Expose
    private String olusturmaTarihi;

    @SerializedName("guncellemeTarihi")
    @Expose
    private Object guncellemeTarihi;

    @SerializedName("silindi")
    @Expose
    private Object silindi;

    public String getEczaneAdi() {
        return eczaneAdi;
    }

    public void setEczaneAdi(String eczaneAdi) {
        this.eczaneAdi = eczaneAdi;
    }

    public String getEczaneIlAdi() {
        return eczaneIlAdi;
    }

    public void setEczaneIlAdi(String eczaneIlAdi) {
        this.eczaneIlAdi = eczaneIlAdi;
    }

    public String getEczaneIlceAdi() {
        return eczaneIlceAdi;
    }

    public void setEczaneIlceAdi(String eczaneIlceAdi) {
        this.eczaneIlceAdi = eczaneIlceAdi;
    }

    public String getEczaneAdres() {
        return eczaneAdres;
    }

    public void setEczaneAdres(String eczaneAdres) {
        this.eczaneAdres = eczaneAdres;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public String getBaslangic() {
        return baslangic;
    }

    public void setBaslangic(String baslangic) {
        this.baslangic = baslangic;
    }

    public String getBitis() {
        return bitis;
    }

    public void setBitis(String bitis) {
        this.bitis = bitis;
    }

    public String getEnlem() {
        return enlem;
    }

    public void setEnlem(String enlem) {
        this.enlem = enlem;
    }

    public String getBoylam() {
        return boylam;
    }

    public void setBoylam(String boylam) {
        this.boylam = boylam;
    }

    public String getGlnNo() {
        return glnNo;
    }

    public void setGlnNo(String glnNo) {
        this.glnNo = glnNo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOlusturmaTarihi() {
        return olusturmaTarihi;
    }

    public void setOlusturmaTarihi(String olusturmaTarihi) {
        this.olusturmaTarihi = olusturmaTarihi;
    }

    public Object getGuncellemeTarihi() {
        return guncellemeTarihi;
    }

    public void setGuncellemeTarihi(Object guncellemeTarihi) {
        this.guncellemeTarihi = guncellemeTarihi;
    }

    public Object getSilindi() {
        return silindi;
    }

    public void setSilindi(Object silindi) {
        this.silindi = silindi;
    }

}