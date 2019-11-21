package com.example.demo_pro1211.model;

public class HighScore {
    public int id;
    public String name;
    public String Diem;

    public HighScore() {
    }

    public HighScore(int id, String name, String diem) {
        this.id = id;
        this.name = name;
        Diem = diem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiem() {
        return Diem;
    }

    public void setDiem(String diem) {
        Diem = diem;
    }
}
