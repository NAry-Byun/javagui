import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class Accommodation implements Serializable {

    public static class Room implements Serializable {
        private String code;             
        private String name;            
        private String type;             
        private double pricePerDay;      
        private String availability = "yes";
        private static int nextId = 1;   
        private int roomNo;              
// this private method only access in under public static class room 
// static works for keyword already overlaped nested class no need to make outside class(Accommodation) it can use Room class itself
        
        public Room(String code, String name, String type, double pricePerDay) {
            this.code = code;
            this.name = name;
            this.type = type;
            this.pricePerDay = pricePerDay;
            this.roomNo = nextId++;
        }// Constructor with code, name, type, and price

        public Room(String name) {
            this.name = name;
            this.roomNo = nextId++;
        }// Constructor with code, name, type, and price

        // get information and return  back 
        public String getCode() {
            return code;
        }

        public String getName() {
            return name;
        }

        public String getType() {
            return type;
        }

        public double getPricePerDay() {
            return pricePerDay;
        }

        // Check if the room is available ("yes" means available) returne type is boolean but i want to use yes or no 
        public boolean isAvailable() {
            return availability.equalsIgnoreCase("yes");
        }

        // Set availability as "yes" or "no"
        public void setAvailability(String availability) {
            if (availability.equalsIgnoreCase("yes") || availability.equalsIgnoreCase("no")) {
                this.availability = availability.toLowerCase();
            } else {
                throw new IllegalArgumentException("Availability must be 'yes' or 'no'.");
            }
        }

        public int getRoomNo() {
            return roomNo; // Ensures getRoomNo() method is defined
        }

        @Override
        public String toString() {
            return "Room No: " + roomNo + ", Code: " + code + ", Name: " + name + ", Type: " + type +
                    ", Price per Day: $" + pricePerDay + ", Available: " + availability;
        }
    }

}