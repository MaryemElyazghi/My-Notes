package com.example.mynotes.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Note implements Parcelable {
    private String nom;
    private String description;
    private String date;
    private String priorite;
    private String photoPath;

    public Note(String nom, String description, String date, String priorite) {
        this.nom = nom;
        this.description = description;
        this.date = date;
        this.priorite = priorite;
        this.photoPath = "";
    }

    protected Note(Parcel in) {
        nom = in.readString();
        description = in.readString();
        date = in.readString();
        priorite = in.readString();
        photoPath = in.readString();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    // Getters
    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getPriorite() {
        return priorite;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    // Setters
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPriorite(String priorite) {
        this.priorite = priorite;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nom);
        dest.writeString(description);
        dest.writeString(date);
        dest.writeString(priorite);
        dest.writeString(photoPath);
    }
}