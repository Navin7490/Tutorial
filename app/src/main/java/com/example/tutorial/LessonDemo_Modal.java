package com.example.tutorial;

public class LessonDemo_Modal {
    String demovideotitle;

    public LessonDemo_Modal(String demovideotitle, int demovideourl) {
        this.demovideotitle = demovideotitle;
        this.demovideourl = demovideourl;
    }

    public String getDemovideotitle() {
        return demovideotitle;
    }

    public void setDemovideotitle(String demovideotitle) {
        this.demovideotitle = demovideotitle;
    }

    public int getDemovideourl() {
        return demovideourl;
    }

    public void setDemovideourl(int demovideourl) {
        this.demovideourl = demovideourl;
    }

    public int getImagedemo() {
        return imagedemo;
    }

    public void setImagedemo(int imagedemo) {
        this.imagedemo = imagedemo;
    }

    int demovideourl;
    int imagedemo;
}
