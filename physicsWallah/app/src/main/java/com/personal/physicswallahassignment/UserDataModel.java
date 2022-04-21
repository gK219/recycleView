package com.personal.physicswallahassignment;

import java.util.ArrayList;
import java.util.List;

public class UserDataModel {
    private int id;
    private String name;
    private ArrayList<String> subject;
    private ArrayList<String> qualification;
    private String imageURI;
    public UserDataModel(int id, String name, ArrayList<String> subject,
                         ArrayList<String> qualification, String imageURI) {
        this.id = id;
        this.name = name;
        this.subject = subject;
        this.qualification = qualification;
        this.imageURI = imageURI;
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

    public ArrayList<String> getSubject() {
        return subject;
    }

    public void setSubject(ArrayList<String> subject) {
        this.subject = subject;
    }

    public ArrayList<String> getQualification() {
        return qualification;
    }

    public void setQualification(ArrayList<String> qualification) {
        this.qualification = qualification;
    }

    public String getImageURI() {
        return imageURI;
    }

    public void setImageURI(String imageURI) {
        this.imageURI = imageURI;
    }
}
