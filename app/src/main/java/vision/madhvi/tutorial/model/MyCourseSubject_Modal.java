package vision.madhvi.tutorial.model;

public class MyCourseSubject_Modal {
//    public MyCourseSubject_Modal(String subjectname) {
//        this.subjectname = subjectname;
//    }

    public String getSubjectname() {
        return subjectname;
    }

    public void setSubjectname(String subjectname) {
        this.subjectname = subjectname;
    }

    String subjectname;

    public String getSubcourse() {
        return subcourse;
    }

    public void setSubcourse(String subcourse) {
        this.subcourse = subcourse;
    }

    String subcourse;

    public String getSubid() {
        return subid;
    }

    public void setSubid(String subid) {
        this.subid = subid;
    }

    String subid;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    String description,image;
}
