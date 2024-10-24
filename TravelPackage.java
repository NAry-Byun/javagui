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

    public double getLiftCost() {
        double liftCost = 0;
        if (this.isSeasonPass()) {
            liftCost += 200.00;
        }
        if (this.dayLiftPass < 5) {
            liftCost += this.dayLiftPass * 26;
        } else {
            liftCost += this.dayLiftPass * 26 * 0.9;
        }
        return liftCost;
    }

    public double getLessonCost() {
        double lessonCost = 0;
        switch (this.customer.getLevel()) {
            case "Beginner":
                lessonCost += this.lessons * 25;
                break;
            case "Intermediate":
                lessonCost += this.lessons * 20;
                break;
            case "Advanced":
                lessonCost += this.lessons * 15;
                break;
        }
        return lessonCost;
    }

    public double getRoomCost() {
        return this.duration * room.getPricePerDay();
    }

    public double getTotalCost() {
        return getRoomCost() + getLiftCost() + getLessonCost();
    }

    @Override
    public String toString() {
        return String.format(
            "<html><br/>Customer: %s<br/>Room: %s<br/>Duration: %d days<br/>" +
            "Start Date: %s<br/>Room Cost: $%.2f<br/>Lift Cost: $%.2f<br/>" +
            "Lesson Cost: $%.2f<br/>Total Cost: $%.2f</html>",
            customer, room, duration, startDate, getRoomCost(), getLiftCost(), getLessonCost(), getTotalCost()
        );
    }
}
