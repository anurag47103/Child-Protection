package com.learningandroid.childprotection.model;
// this class is the structure of the items in our recycler view
public class recycler_View_item_Class {
    private final int imageview1;
    private final String nameText;
    private final String durationText;
    private String dividerText;

    // constructor

    public recycler_View_item_Class(int imageview1, String nameText, String durationText){
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

    public String getdividerText() {
        return dividerText;
    }
}
