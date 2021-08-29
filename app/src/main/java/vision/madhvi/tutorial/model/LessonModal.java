package vision.madhvi.tutorial.model;

public class LessonModal {
//    public LessonModal(String lessonName, String videourl) {
//        this.lessonName = lessonName;
//        this.videourl=videourl;
//        this.lessonImageurl = lessonImageurl;
//    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public String getLessonImageurl() {
        return lessonImageurl;
    }

    public void setLessonImageurl(String lessonImageurl) {
        this.lessonImageurl = lessonImageurl;
    }

    String  lessonName;

    public String getVideourl() {
        return videourl;
    }

    public void setVideourl(String videourl) {
        this.videourl = videourl;
    }

    String videourl;

    public String getTvlink() {
        return tvlink;
    }

    public void setTvlink(String tvlink) {
        this.tvlink = tvlink;
    }

    String tvlink;
    String lessonImageurl;

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    String videoId;
}
