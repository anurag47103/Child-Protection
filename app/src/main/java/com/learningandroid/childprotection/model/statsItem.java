package com.learningandroid.childprotection.model;
// this class is the structure of the items in our recycler view
public class statsItem {
    private final int imageview1;
    private final String nameText;
    private final String durationText;

    // constructor

    public statsItem(int imageview1, String nameText, String durationText){
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
