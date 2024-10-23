import java.io.*;
import java.time.*;

public class TravelPackage implements Serializable, Comparable<TravelPackage> {
  private int TravelPackageId;
  private Customer customer;
  private Room room;
  private LocalDate startDate;
  private int duration;
  private double cost;
  private double breakfast = 34; 
  private boolean isPaid;
  static int nextID = 10;
  public TravelPackage () {
   TravelPackageId = nextID++;
  }
  
  public TravelPackage (Room room, Customer customer) {
    this.customer = customer;
    this.room = room;
    TravelPackageId = nextID++;
  }
   public TravelPackage (Customer customer, LocalDate startDate, int duration) {
    this.customer = customer;
    this.startDate = startDate;
    this.duration = duration;
    TravelPackageId= nextID++;
  }
   
    public TravelPackage (Customer customer,  int duration) {
    this.customer = customer;
    this.duration = duration;
    TravelPackageId = nextID++;
  }
  public int getTravelPackageId () {
    return TravelPackageId;
  }
  
  public Customer getCustomer () {
    return customer;
  }
  
  public LocalDate getDate () {
    return startDate;
  }
  
  public int getDuration () {
    return duration;
  }
   
  public Room getRoom () {
    return room;
  }
  public double getBreakfast(){
    return breakfast;
  } 
  public void setTotalCost(double cost){
    this.cost = cost;
  }
  public boolean getIsPaid(){
    return isPaid;
  }
  public void setDateFromString(String dateStr) {
    startDate = LocalDate.parse(dateStr);
  }
  
  public void setBreakfast(double breakfast){
    this.breakfast = breakfast;
  }
  public void setDays (int duration){ 
        this.duration = duration;
  }
  
 public void setRoomNo (Room  room) {
    this.room = room;
  }
 public void setIsPaid(boolean isPaid){
   this.isPaid = isPaid;
 }
 public LocalDate getEndDate(){
   return startDate.plusDays(duration);
 }
 public double getTotalCost(){
   return cost;
 }
 public int compareTo(TravelPackage b){
   LocalDate endDate = this.getEndDate();
   LocalDate endDate1 = b.getEndDate();
   return endDate.compareTo(endDate1);
 }
 
  public String toString () {
    return "<html>" +"<br />" + "TravelPackage ID: " + TravelPackageId+ "<br />" + "Customer " + customer.toString() +"<br />" + " Start date: " + startDate +
      ", duration: " + duration + "<br />" + room.toString() +"<br />" + "</html>" ;
  }
  
}