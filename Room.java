public class Room {
  private int roomNo;                 // Room number
  private String type;                // Room type (e.g., Double, Bunker, etc.)
  private double pricePerDay;         // Price per day for the room
  private boolean availability = true; // Availability status of the room
  static int nextId = 1;              // Static counter to assign room numbers automatically

  // Default constructor
  public Room() {
      roomNo = nextId++;  // Automatically assign room number
  }

  // Constructor with room type only
  public Room(String type) {
      this.type = type;
      roomNo = nextId++; 
      setPricePerDay(type);  // Automatically set price based on the type
  }

  // New constructor with type and price per day
  public Room(String type, double pricePerDay) {
      this.type = type;
      this.pricePerDay = pricePerDay;
      roomNo = nextId++;
  }

  // Getters
  public int getRoomNo() {
      return roomNo;
  }

  public String getType() {
      return type;
  }

  public double getPricePerDay() {
      return pricePerDay;
  }

  public boolean getAvailability() {
      return availability;
  }

  // Setters
  public void setType(String type) {
      this.type = type;
  }

  // Method to set price per day based on the room type
  public void setPricePerDay(String type) {
      if (type.equalsIgnoreCase("double"))
          pricePerDay = 125;
      else if (type.equalsIgnoreCase("bunker"))
          pricePerDay = 256;
      else if (type.equalsIgnoreCase("queen"))
          pricePerDay = 359;
      else if (type.equalsIgnoreCase("family"))
          pricePerDay = 559;
      else
          pricePerDay = 0.0;  // Default to 0 if type is not recognized
  }

  public void setAvailability(boolean availability) {
      this.availability = availability;
  }

  // toString method for easy display of room details
  @Override
  public String toString() {
      return "Room #: " + roomNo +
             ", Type: " + type +
             ", Price per day: $" + pricePerDay +
             ", Available: " + availability;
  }
}
