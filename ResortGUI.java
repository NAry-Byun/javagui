import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;

public class ResortGUI extends JFrame  {
 ArrayList<Room> rooms = new ArrayList<Room>() ;
 ImageIcon[] icons = {new ImageIcon("images/double.jpeg"), new ImageIcon("images/double.jpeg"),
   new ImageIcon("images/family.jpg"),new ImageIcon("images/bunker.jpg")} ;
 
   Room rfound = null;
 
// Define the Booking class separately
class Booking {
    private Room room;
    private Customer customer;
    private String date;
    private int days;
    private double totalCost;
    private boolean isPaid;
    private static int nextID = 1;
    private int bookingID;

    public Booking(Room room2, Customer customer2) {
        this.room = room2;
        this.customer = customer2;
        this.bookingID = nextID++;
    }

    // Getters and setters for the fields
    public Room getRoom() {
        return room;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getDate() {
        return date;
    }

    public void setDateFromString(String date) {
        this.date = date;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public boolean getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }

    public int getBookingID() {
        return bookingID;
    }

    @Override
    public String toString() {
        return "Booking ID: " + bookingID + ", Room: " + room + ", Customer: " + customer + ", Date: " + date + ", Days: " + days + ", Total Cost: " + totalCost + ", Paid: " + isPaid;
    }

   public int getDuration() {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'getDuration'");
   }

   public int getBreakfast() {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'getBreakfast'");
   }
}
 DefaultListModel<Room> roomsModel = new DefaultListModel<Room>();
 JList <Room> roomsList = new JList<Room>(roomsModel); 
 ArrayList<Customer> customers = new ArrayList<Customer>();
 DefaultListModel<Customer> customersModel = new DefaultListModel<Customer>();
 JList <Customer> customersList = new JList<Customer>(customersModel); 
 ArrayList<Booking> bookings = new ArrayList<Booking>();
 DefaultListModel<Booking> bookingsModel = new DefaultListModel<Booking>();
 JList <Booking> bookingsList = new JList<Booking>(bookingsModel); 
 
 JTabbedPane tabs = new JTabbedPane();
 //tabs
 JPanel roomsTab = new JPanel();
 JPanel customersTab = new JPanel();
 JPanel bookingsTab = new JPanel();
 //containers
 JPanel inputPanel = new JPanel(), inputCPanel = new JPanel(), inputBPanel = new JPanel();
 JPanel displayPanel = new JPanel(), displayCPanel = new JPanel(), displayBPanel = new JPanel();//delete later
 JPanel buttonPanel = new JPanel(), buttonCPanel = new JPanel(),buttonBPanel = new JPanel();
 //components for room
 JLabel lblDisplay = new JLabel("Click on button to display room", null, SwingConstants.RIGHT);
 JButton btnDisplay = new JButton("Display selected"), btnRemove= new JButton("Remove"), btnClear = new JButton("Clear selection");
 JButton btnCAdd = new JButton("Add customer"), btnCRemove= new JButton("Remove"), btnCClear = new JButton("Clear selection");
 JButton btnBAdd = new JButton("Add booking"), btnBRemove= new JButton("Remove"), btnBClear = new JButton("Clear selection");
 
 //components for booking
 JLabel lblDate = new JLabel("Enter date as yyyy-mm-dd"), lblDays = new JLabel("Duration");
 JTextField txtDate = new JTextField(10), txtDays=new JTextField(10);
 JLabel lblName = new JLabel("Name");
 JTextField  txtName=new JTextField(10);
 //checkout
 JLabel lblIsPaid = new JLabel();
 JRadioButton payments[] = new JRadioButton[4];
 String paymentsLabels[] = {"Visa", "Master card", "Cash", "Pay later"};
 JButton btnBCheckOut = new JButton("Checkout");
 JCheckBox chkBreakfast = new JCheckBox("Breakfast:");
 JTextField txtTotalAmount = new JTextField(10);
 //componenets for customer
 JLabel lblCName = new JLabel("Name");
 JTextField  txtCName=new JTextField(10);
 //constructor
 public ResortGUI(){
   //tabbed pane added
  add(tabs);
  //tabs are created 
  tabs.addTab("Rooms", roomsTab);
  tabs.addTab("Customers", customersTab);
  tabs.addTab("Bookings", bookingsTab);
  //roomsTab
   //inputPanel.setLayout(new GridLayout(3,2,10,10));
   inputPanel.add(lblDisplay);
   JScrollPane scrollRooms = new JScrollPane(roomsList, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
   scrollRooms.setBorder(new TitledBorder("List of rooms"));
   buttonPanel.add(btnDisplay);
   buttonPanel.add(btnRemove);
   buttonPanel.add(btnClear);
   roomsTab.setLayout(new BorderLayout());
  //build roomsTab panel
    roomsTab.add(inputPanel, BorderLayout.NORTH);
    roomsTab.add(scrollRooms, BorderLayout.CENTER);
    roomsTab.add(buttonPanel, BorderLayout.SOUTH);
  //register the action listener with buttons on carsTab
  ButtonHandler bh = new ButtonHandler();
  btnDisplay.addActionListener(bh);
  btnRemove.addActionListener(bh);
  btnClear.addActionListener(bh);
   //set customerTab panel
   customersTab.setLayout(new BorderLayout());
   customersTab.add(inputCPanel, BorderLayout.NORTH);
   
   JScrollPane scrollCustomers = new JScrollPane(customersList, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
   scrollCustomers.setBorder(new TitledBorder("List of customers"));
   customersTab.add(scrollCustomers, BorderLayout.CENTER);
   customersTab.add(buttonCPanel, BorderLayout.SOUTH);
   inputCPanel.setLayout(new GridLayout(1,2,10,10));
   inputCPanel.setBorder(new EmptyBorder(20,20,20,20));
   inputCPanel.add(lblCName);
   inputCPanel.add(txtCName);
  
   buttonCPanel.add(btnCAdd);
   buttonCPanel.add(btnCRemove);
   buttonCPanel.add(btnCClear);
  //register the action listener
  btnCAdd.addActionListener(bh);
  btnCRemove.addActionListener(bh);
  btnCClear.addActionListener(bh);
  //set and build bookingsTab
   JScrollPane scrollBookings = new JScrollPane(bookingsList, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
   bookingsTab.setLayout(new BorderLayout());
   inputBPanel.setLayout(new GridLayout(2,2,10,10));
   inputBPanel.setBorder(new EmptyBorder(10,20,10,20));
   inputBPanel.add(lblDate);
   inputBPanel.add(txtDate);
   inputBPanel.add(lblDays);
   inputBPanel.add(txtDays);
   bookingsTab.add(inputBPanel, BorderLayout.NORTH);
   bookingsTab.add(scrollBookings, BorderLayout.CENTER);
   bookingsTab.add(buttonBPanel, BorderLayout.SOUTH); 
   buttonBPanel.add(btnBAdd);
   buttonBPanel.add(btnBRemove);
   buttonBPanel.add(btnBClear);
   btnBAdd.addActionListener(bh);
   btnBRemove.addActionListener(bh);
   btnBClear.addActionListener(bh);
   btnBCheckOut.addActionListener(bh);
   JPanel pnlCheckOut = new JPanel();
   pnlCheckOut.setLayout(new GridLayout(2, 1));
     JPanel pnlPaymentOpt = new JPanel();
     pnlPaymentOpt.setBorder(new TitledBorder("Payment options"));
     pnlPaymentOpt.setLayout(new GridLayout(4,1));
     //button group  
     ButtonGroup group = new ButtonGroup();
     for(int i = 0; i < 4; i++){
     payments[i]= new JRadioButton(paymentsLabels[i]);
     group.add(payments[i]);
     pnlPaymentOpt.add(payments[i]);
     }
     
     pnlCheckOut.setBorder(new EmptyBorder(10,20,10,20));
     JPanel pnlTotalAmount = new JPanel();
     pnlTotalAmount.setLayout(new GridLayout(4,1,30,30));
     pnlTotalAmount.setBorder(new TitledBorder("Process payment"));
     pnlTotalAmount.add(chkBreakfast);
     pnlTotalAmount.add(btnBCheckOut);
     pnlTotalAmount.add(txtTotalAmount);
     pnlTotalAmount.add(lblIsPaid);
     pnlCheckOut.add(pnlTotalAmount);
     pnlCheckOut.add(pnlPaymentOpt);
   bookingsTab.add(pnlCheckOut, BorderLayout.EAST);
 }

 public void populateLists(){
           Room[] arrOfRooms = {new Room("double"), new Room("queen"), 
           new Room("bunker"),new Room("family")};
           Customer[] arrOfCustomers = {new Customer("Natasha"), 
           new Customer("Jeff"), new Customer("Sam"),
           new Customer("John"), new Customer("Dow")};
           
           for(int i = 0; i < arrOfRooms.length; i++){
            arrOfRooms[i].setPricePerDay(arrOfRooms[i].getType()); 
            rooms.add(arrOfRooms[i]);
            roomsModel.addElement(arrOfRooms[i]);
            //icons[i] = new ImageIcon("images/double.jpeg");
           }
           for(int i = 0; i < arrOfCustomers.length; i++){
             customers.add(arrOfCustomers[i]);
             customersModel.addElement(arrOfCustomers[i]);
           }
           Booking booking = new Booking(arrOfRooms, arrOfCustomers);
           booking.setDateFromString("2018-10-05");
           booking.setDays(10);
       
           bookings.add(booking);
           bookingsModel.addElement(booking);
       
           Room rfound = searchRoomsByRoomNo(1);
           rfound.setAvailability(false);
       }

   public Room searchRoomsByRoomNo(int roomNo){
    for(Room r: rooms){
      if(r.getRoomNo() == roomNo)
        return r;
    }
    return null;
   }
   public double calculateTotalCost(Booking b){
     int duration = b.getDuration();
     Room r = b.getRoom();
     double totalCost = duration * r.getPricePerDay();
        if(chkBreakfast.isSelected())
           totalCost +=b.getBreakfast()*duration;
       b.setTotalCost(totalCost);
       txtTotalAmount.setText(" " + totalCost);
       bookings.remove(b);
       for(int i = 0; i < 3; i++){
         if(payments[i].isSelected()){
           lblIsPaid.setText("Paid by " + payments[i].getText());
           b.setIsPaid(true);
         }
       if(payments[3].isSelected()){ 
           lblIsPaid.setText("Customer will pay later");
           b.setIsPaid(false);
         }
 }
       return totalCost;
 }         
 public static void main(String[] args){
  ResortGUI cr = new ResortGUI();
  cr.populateLists();
  //cr.setDefaultCloseOperation(EXIT_ON_CLOSE);
  cr.setSize(550,550);
  cr.setLocationRelativeTo(null);
  cr.setVisible(true);
 }

 class ButtonHandler implements ActionListener{
  public void actionPerformed(ActionEvent e){
   if(e.getSource() == btnDisplay){
     int index = roomsList.getSelectedIndex();
        if(index !=-1){
          lblDisplay.setIcon(icons[index]);
          lblDisplay.setText("Click clear selection");
        }
   }
    if(e.getSource() == btnCAdd){
    //read the data
    String name = txtCName.getText();
    System.out.println(name);
    Customer customer = new Customer(name);
    System.out.println(customer);
    customers.add(customer);
    customersModel.addElement(customer);
    //clear the text fields
    txtName.setText("");
     
   }
   if(e.getSource() == btnRemove){
     int index = roomsList.getSelectedIndex();
        if(index !=-1){
          roomsModel.remove(index);
          rooms.remove(index);
        }
   }
  if(e.getSource() == btnCRemove){
     int index = customersList.getSelectedIndex();
        if(index !=-1){
          customersModel.remove(index);
          customers.remove(index);
        }
   }
   if(e.getSource() == btnBRemove){
     int index = bookingsList.getSelectedIndex();
        if(index !=-1){
          bookingsModel.remove(index);
          bookings.remove(index);
        }
   }
   if(e.getSource() == btnClear){
     roomsList.clearSelection();
     lblDisplay.setIcon(null);
    }
    if(e.getSource() == btnCClear){
     customersList.clearSelection();
     
    }
    if(e.getSource() == btnBClear){
     bookingsList.clearSelection();
    }
   if(e.getSource() == btnBAdd){
     Booking booking = null;
    //get room and customer from the lists
    Room room = roomsList.getSelectedValue();
    Customer customer = customersList.getSelectedValue();
    if(room != null && customer!=null){
      booking = new Booking(room, customer);
    //read other data
      try{
      String date = txtDate.getText();
      booking.setDateFromString(date);
     
      String daysStr = txtDays.getText();
      int days = 0;
      if(daysStr!=""){
       days = Integer.parseInt(daysStr);
      }
     /*else{
       JOptionPane.showMessageDialog(null, "Please enter duration in days");
       txtDays.setText("Please enter duration in days");
     }*/
      booking.setDays(days);
      bookings.add(booking);
      bookingsModel.addElement(booking);
      room.setAvailability(false);
        //clear the text fields
    txtDate.setText("");
    txtDays.setText("");
    }
    catch(Exception ee){
        System.out.println(ee.getMessage());
        Booking.nextID--;
     }
   }
    else
      JOptionPane.showMessageDialog(null, "Please select a room and a customer from lists");
  }
   if(e.getSource() == btnBCheckOut){
      Booking b = bookingsList.getSelectedValue();
      if(b!=null){
       @SuppressWarnings("unused")
      double cost = calculateTotalCost(b);
       //if(!b.getIsPaid()){
       // JOptionPane.showMessageDialog(null, "You have to pay " + cost + "$");
      }
      else{
           JOptionPane.showMessageDialog(null, "Please select a booking from the list");
       }
  }
    
 }//end of class ButtonHandler
  }
}//end of class 