package vision.madhvi.tutorial;

public class DashboardCorse_Modal {

    String dascouNmae;

    public DashboardCorse_Modal(String dascouNmae, String dashcouDescription, String dashcouPrice, String dashcouAcademy) {
        this.dascouNmae = dascouNmae;
        this.dashcouDescription = dashcouDescription;
        this.dashcouPrice = dashcouPrice;
        this.dashcouAcademy = dashcouAcademy;
    }

    public String getDascouNmae() {
        return dascouNmae;
    }

    public void setDascouNmae(String dascouNmae) {
        this.dascouNmae = dascouNmae;
    }

    public String getDashcouDescription() {
        return dashcouDescription;
    }

    public void setDashcouDescription(String dashcouDescription) {
        this.dashcouDescription = dashcouDescription;
    }

    public String getDashcouPrice() {
        return dashcouPrice;
    }

    public void setDashcouPrice(String dashcouPrice) {
        this.dashcouPrice = dashcouPrice;
    }

    public String getDashcouAcademy() {
        return dashcouAcademy;
    }

    public void setDashcouAcademy(String dashcouAcademy) {
        this.dashcouAcademy = dashcouAcademy;
    }

    String dashcouDescription;
    String dashcouPrice;
    String dashcouAcademy;
}
