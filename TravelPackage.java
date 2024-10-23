import java.io.Serializable;
import java.time.LocalDate;

public class TravelPackage<Customer, Room> implements Serializable, Comparable<TravelPackage> {
    private static int nextID = 10;
    private int travelPackageId;
    private Customer customer;
    private Room room;
    private LocalDate startDate;
    private int duration;
    private double cost;
    private boolean isPaid;

    // Constructors
    public TravelPackage(Room room, Customer customer) {
        this.room = room;
        this.customer = customer;
        this.travelPackageId = nextID++;
    }

    public TravelPackage(Customer customer, LocalDate startDate, int duration) {
        this.customer = customer;
        this.startDate = startDate;
        this.duration = duration;
        this.travelPackageId = nextID++;
    }

    // Getters and Setters
    public int getTravelPackageId() {
        return travelPackageId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public Room getRoom() {
        return room;
    }

    public int getDuration() {
        return duration;
    }

    public void setTotalCost(double cost) {
        this.cost = cost;
    }

    public double getTotalCost() {
        return cost;
    }

    public void setIsPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }

    public boolean getIsPaid() {
        return isPaid;
    }

    public void setDateFromString(String dateStr) {
        this.startDate = LocalDate.parse(dateStr);
    }

    public LocalDate getEndDate() {
        return startDate.plusDays(duration);
    }

    @Override
    public int compareTo(@SuppressWarnings("rawtypes") TravelPackage other) {
        return this.getEndDate().compareTo(other.getEndDate());
    }

    @Override
    public String toString() {
        return customer + " booked " + room + " for " + duration + " days starting " + startDate;
    }
}
