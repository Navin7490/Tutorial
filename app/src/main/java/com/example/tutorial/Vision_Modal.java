package com.example.tutorial;

public class Vision_Modal  {

    int visionimage;
    String visiontitle;

    public Vision_Modal(int visionimage, String visiontitle, String visiondescription) {
        this.visionimage = visionimage;
        this.visiontitle = visiontitle;
        this.visiondescription = visiondescription;
    }

    public int getVisionimage() {
        return visionimage;
    }

    public void setVisionimage(int visionimage) {
        this.visionimage = visionimage;
    }

    public String getVisiontitle() {
        return visiontitle;
    }

    public void setVisiontitle(String visiontitle) {
        this.visiontitle = visiontitle;
    }

    public String getVisiondescription() {
        return visiondescription;
    }

    public void setVisiondescription(String visiondescription) {
        this.visiondescription = visiondescription;
    }

    String visiondescription;
}
