package vision.madhvi.tutorial;

public class MyPurchaseCourse_Modal {
    String purchaseId;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    String image;

//    public MyPurchaseCourse_Modal(String purchaseId, String coursename) {
//        this.purchaseId = purchaseId;
//        this.coursename = coursename;
//        this.coursedetail = coursedetail;
//    }

    public String getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(String purchaseId) {
        this.purchaseId = purchaseId;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public String getCoursedetail() {
        return coursedetail;
    }

    public void setCoursedetail(String coursedetail) {
        this.coursedetail = coursedetail;
    }

    String coursename;
    String coursedetail;
}
