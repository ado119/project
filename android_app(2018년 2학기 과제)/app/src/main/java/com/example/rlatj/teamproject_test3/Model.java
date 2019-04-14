package com.example.rlatj.teamproject_test3;

public class Model {
    private int id;
    private String place;
    private String people;
    private Double latitude;
    private Double longtitude;
    private byte[] image;



    public Model(int id, String place, String people, Double latitude, Double longtitude, byte[] image){
        this.id=id;
        this.place=place;
        this.people=people;
        this.latitude=latitude;
        this.longtitude=longtitude;
        this.image=image;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(Double longtitude) {
        this.longtitude = longtitude;
    }

    public byte[] getImage() { return image; }

    public void setImage(byte[] image) { this.image = image; }
}
