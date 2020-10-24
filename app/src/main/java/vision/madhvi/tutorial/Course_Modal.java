package vision.madhvi.tutorial;

public class Course_Modal {
//    public Course_Modal(String coursename, String coursedescription, int courseimage) {
//        this.coursename = coursename;
//        this.courseimage = courseimage;
//        this.coursedescription=coursedescription;
//    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public String getCourseimage() {
        return courseimage;
    }

    public void setCourseimage(String courseimage) {
        this.courseimage = courseimage;
    }



    public String getCoursedescription() {
        return coursedescription;
    }

    public void setCoursedescription(String coursedescription) {
        this.coursedescription = coursedescription;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCoursePrice() {
        return coursePrice;
    }

    public void setCoursePrice(String coursePrice) {
        this.coursePrice = coursePrice;
    }

    String courseId;
    String coursename;
    String coursedescription;
    String coursePrice;
    String courseimage;

    public String getCourseGroupId() {
        return courseGroupId;
    }

    public void setCourseGroupId(String courseGroupId) {
        this.courseGroupId = courseGroupId;
    }

    String courseGroupId;

    public String getCourseGroupname() {
        return courseGroupname;
    }

    public void setCourseGroupname(String courseGroupname) {
        this.courseGroupname = courseGroupname;
    }

    String courseGroupname;
}
