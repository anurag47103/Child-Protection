package com.learningandroid.childprotection;

public class ModelClass {
    private int imageview1;
    private String nameText;
    private String durationText;

    // constructor

    ModelClass(int imageview1,String nameText,String durationText){
        this.imageview1=imageview1;
        this.nameText=nameText;
        this.durationText=durationText;
    }


    //getters
    public int getImageview1() {
        return imageview1;
    }

    public String getNameText() {
        return nameText;
    }

    public String getDurationText() {
        return durationText;
    }
}
