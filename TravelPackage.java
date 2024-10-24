import java.io.Serializable;
import java.time.LocalDate;

public class TravelPackage implements Serializable {
    static int nextID = 10;
    private int travelPackageId;
    private Customer customer;
    private Accommodation.Room room;
    private LocalDate startDate;
    private int duration;
    private int dayLiftPass;
    private boolean seasonPass;
    private int lessons;

    public TravelPackage(Accommodation.Room room, Customer customer) {
        this.room = room;
        this.customer = customer;
        this.travelPackageId = nextID++;
    }

    public int getTravelPackageId() {
        return travelPackageId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Accommodation.Room getRoom() {
        return room;
    }

    public void setDateFromString(String dateStr) {
        this.startDate = LocalDate.parse(dateStr);
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getDayLiftPass() {
        return dayLiftPass;
    }

    public void setDayLiftPass(int dayLiftPass) {
        this.dayLiftPass = dayLiftPass;
    }

    public boolean isSeasonPass() {
        return seasonPass;
    }

    public void setSeasonPass(boolean seasonPass) {
        this.seasonPass = seasonPass;
    }

    public int getLessons() {
        return lessons;
    }

    public void setLessons(int lessons) {
        this.lessons = lessons;
    }

    public double getliftCost() {
        int lCost = 0;
        if(this.isSeasonPass()) {
            lCost += 200.00;
        }
        if(this.dayLiftPass < 5) {
            lCost += this.dayLiftPass * 26;
        }
        else {
            lCost += this.dayLiftPass * 26 * 0.9;
        }
        return lCost;
    }

    public double getLessonCost() {
        int lnCost = 0;
        if(this.customer.getLevel().equals("Beginner")) {
            lnCost += this.lessons * 25;
        }
        else if(this.customer.getLevel().equals("Intermediate")) {
            lnCost += this.lessons * 20;
        }
        else if (this.customer.getLevel().equals("Advanced")) {
            lnCost += this.lessons * 15;
        }

        return lnCost;
    }

    public double getRoomCost() {
        return this.duration * room.getPricePerDay();
    }


    @Override
    public String toString() {
        return "<html>" + "<br/>" +
        customer + "<br/>" +
         room + "<br/>" +
         " for " + duration + " days starting " + startDate + " will be cost " + this.getRoomCost() + "<br/>" + 
         "Lift cost will be " + this.getliftCost() + "<br/>" + 
         "Lessons cost will be "+ this.getLessonCost() + "</html>";
    }
}
