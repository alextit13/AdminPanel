package com.bingerdranch.android.administrationconsolle;

public class Data {
    private String site;
    private String name;
    private String marka;
    private String model;
    private String okrug;
    private String rayon;
    private String metro;
    private String adress;
    private String number;
    private String vid_rabot;
    private String otzivi;
    private int rating;
    private String numOfRating;
    private String grafik_raboti;
    private String image_path;

    public Data() {
    }

    public Data(String site, String name, String marka, String model, String okrug, String rayon, String metro, String adress, String number, String vid_rabot, String otzivi, int rating, String numOfRating, String grafik_raboti, String image_path, String site1) {
        this.site = site;
        this.name = name;
        this.marka = marka;
        this.model = model;
        this.okrug = okrug;
        this.rayon = rayon;
        this.metro = metro;
        this.adress = adress;
        this.number = number;
        this.vid_rabot = vid_rabot;
        this.otzivi = otzivi;
        this.rating = rating;
        this.numOfRating = numOfRating;
        this.grafik_raboti = grafik_raboti;
        this.image_path = image_path;
        this.site = site1;
    }

    public Data(String site, String name, String marka, String model, String okrug, String rayon, String metro, String adress, String number, String vid_rabot, String otzivi, int rating, String numOfRating, String grafik_raboti, String image_path) {
        this.site = site;
        this.name = name;
        this.marka = marka;
        this.model = model;
        this.okrug = okrug;
        this.rayon = rayon;
        this.metro = metro;
        this.adress = adress;
        this.number = number;
        this.vid_rabot = vid_rabot;
        this.otzivi = otzivi;
        this.rating = rating;
        this.numOfRating = numOfRating;
        this.grafik_raboti = grafik_raboti;
        this.image_path = image_path;
    }

    public Data(String name, String marka, String model, String okrug, String rayon, String metro, String adress, String number, String vid_rabot, String otzivi, int rating, String numOfRating, String grafik_raboti) {
        this.name = name;
        this.marka = marka;
        this.model = model;
        this.okrug = okrug;
        this.rayon = rayon;
        this.metro = metro;
        this.adress = adress;
        this.number = number;
        this.vid_rabot = vid_rabot;
        this.otzivi = otzivi;
        this.rating = rating;
        this.numOfRating = numOfRating;
        this.grafik_raboti = grafik_raboti;
    }

    public Data(String site, String name, String marka, String model, String okrug, String rayon, String metro, String adress, String number, String vid_rabot, String otzivi, int rating, String numOfRating, String grafik_raboti) {
        this.site = site;
        this.name = name;
        this.marka = marka;
        this.model = model;
        this.okrug = okrug;
        this.rayon = rayon;
        this.metro = metro;
        this.adress = adress;
        this.number = number;
        this.vid_rabot = vid_rabot;
        this.otzivi = otzivi;
        this.rating = rating;
        this.numOfRating = numOfRating;
        this.grafik_raboti = grafik_raboti;

    }

    public String getSite() {
        return site;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getOkrug() {
        return okrug;
    }

    public void setOkrug(String okrug) {
        this.okrug = okrug;
    }

    public String getRayon() {
        return rayon;
    }

    public void setRayon(String rayon) {
        this.rayon = rayon;
    }

    public String getMetro() {
        return metro;
    }

    public void setMetro(String metro) {
        this.metro = metro;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getVid_rabot() {
        return vid_rabot;
    }

    public void setVid_rabot(String vid_rabot) {
        this.vid_rabot = vid_rabot;
    }

    public String getOtzivi() {
        return otzivi;
    }

    public void setOtzivi(String otzivi) {
        this.otzivi = otzivi;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getNumOfRating() {
        return numOfRating;
    }

    public void setNumOfRating(String numOfRating) {
        this.numOfRating = numOfRating;
    }

    public String getGrafik_raboti() {
        return grafik_raboti;
    }

    public void setGrafik_raboti(String grafik_raboti) {
        this.grafik_raboti = grafik_raboti;
    }
}