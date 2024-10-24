import java.io.Serializable;
import java.time.LocalDate;

public class TravelPackage implements Serializable {
    private static int nextID = 10;
    private int travelPackageId;
    private Customer customer;
    private Accommodation.Room room;
    private LocalDate startDate;
    private int duration;
    private double totalCost;
    private boolean isPaid;

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

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setIsPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }

    @Override
    public String toString() {
        return customer + " booked " + room + " for " + duration + " days starting " + startDate;
    }
}
