import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.time.LocalDate;

public class ResortGUI extends JFrame {
    // Fields and components
    ArrayList<Room> rooms = new ArrayList<>();
    ArrayList<Customer> customers = new ArrayList<>();
    ArrayList<TravelPackage> travelPackages = new ArrayList<>();

    DefaultListModel<Room> roomsModel = new DefaultListModel<>();
    DefaultListModel<Customer> customersModel = new DefaultListModel<>();
    DefaultListModel<TravelPackage> travelPackagesModel = new DefaultListModel<>();

    JList<Room> roomsList = new JList<>(roomsModel);
    JList<Customer> customersList = new JList<>(customersModel);
    JList<TravelPackage> travelPackagesList = new JList<>(travelPackagesModel);

    ImageIcon[] icons = {
        new ImageIcon("images/single small.jpeg"),
        new ImageIcon("images/double small.jpeg"),
        new ImageIcon("images/double business small.jpg"),
        new ImageIcon("images/single view small.jpg")
    };

    // Components for the GUI
    JTabbedPane tabs = new JTabbedPane();
    JPanel roomsTab = new JPanel();
    JPanel customersTab = new JPanel();
    JPanel travelPackageTab = new JPanel();

    JPanel inputPanel = new JPanel();
    JPanel inputCPanel = new JPanel();
    JPanel inputBPanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    JPanel buttonCPanel = new JPanel();
    JPanel buttonBPanel = new JPanel();

    JLabel lblDisplay = new JLabel("Click on button to display room", SwingConstants.LEFT);
    JButton btnDisplay = new JButton("Display selected");
    JButton btnRemove = new JButton("Remove");
    JButton btnClear = new JButton("Clear selection");

    JButton btnCAdd = new JButton("Add customer");
    JButton btnCRemove = new JButton("Remove");
    JButton btnCClear = new JButton("Clear selection");

    JButton btnBAdd = new JButton("Add Travel Package");
    JButton btnBRemove = new JButton("Remove");
    JButton btnBClear = new JButton("Clear selection");

    JLabel lblDate = new JLabel("Enter date (yyyy-mm-dd):");
    JLabel lblDays = new JLabel("Duration (days):");
    JTextField txtDate = new JTextField(10);
    JTextField txtDays = new JTextField(10);

    JRadioButton[] payments = new JRadioButton[4];
    String[] paymentsLabels = {"Visa", "Master card", "Cash", "Pay later"};
    JButton btnBCheckOut = new JButton("Checkout");
    JCheckBox chkBreakfast = new JCheckBox("Breakfast:");
    JTextField txtTotalAmount = new JTextField(10);
    JLabel lblIsPaid = new JLabel();

    // Constructor
    public ResortGUI() {
        setupTabs();
        setupButtons();

        populateLists();
        setSize(600, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Setup tab structure
    private void setupTabs() {
        add(tabs);
        tabs.addTab("Rooms", roomsTab);
        tabs.addTab("Customers", customersTab);
        tabs.addTab("Travel Packages", travelPackageTab);

        setupRoomsTab();
        setupCustomersTab();
        setupTravelPackageTab();
    }

    // Configure the Rooms tab
    private void setupRoomsTab() {
        inputPanel.add(lblDisplay);
        JScrollPane scrollRooms = new JScrollPane(roomsList);
        scrollRooms.setBorder(new TitledBorder("List of Rooms"));
        buttonPanel.add(btnDisplay);
        buttonPanel.add(btnRemove);
        buttonPanel.add(btnClear);

        roomsTab.setLayout(new BorderLayout());
        roomsTab.add(inputPanel, BorderLayout.NORTH);
        roomsTab.add(scrollRooms, BorderLayout.CENTER);
        roomsTab.add(buttonPanel, BorderLayout.SOUTH);
    }

    // Configure the Customers tab
    private void setupCustomersTab() {
        inputCPanel.setLayout(new GridLayout(1, 2, 10, 10));
        inputCPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        inputCPanel.add(new JLabel("Name:"));
        JTextField txtCName = new JTextField(10);
        inputCPanel.add(txtCName);

        JScrollPane scrollCustomers = new JScrollPane(customersList);
        scrollCustomers.setBorder(new TitledBorder("List of Customers"));

        buttonCPanel.add(btnCAdd);
        buttonCPanel.add(btnCRemove);
        buttonCPanel.add(btnCClear);

        customersTab.setLayout(new BorderLayout());
        customersTab.add(inputCPanel, BorderLayout.NORTH);
        customersTab.add(scrollCustomers, BorderLayout.CENTER);
        customersTab.add(buttonCPanel, BorderLayout.SOUTH);
    }

    // Configure the Travel Packages tab
    private void setupTravelPackageTab() {
        inputBPanel.setLayout(new GridLayout(2, 2, 10, 10));
        inputBPanel.setBorder(new EmptyBorder(10, 20, 10, 20));
        inputBPanel.add(lblDate);
        inputBPanel.add(txtDate);
        inputBPanel.add(lblDays);
        inputBPanel.add(txtDays);

        JScrollPane scrollTravelPackages = new JScrollPane(travelPackagesList);
        scrollTravelPackages.setBorder(new TitledBorder("List of Travel Packages"));

        buttonBPanel.add(btnBAdd);
        buttonBPanel.add(btnBRemove);
        buttonBPanel.add(btnBClear);

        travelPackageTab.setLayout(new BorderLayout());
        travelPackageTab.add(inputBPanel, BorderLayout.NORTH);
        travelPackageTab.add(scrollTravelPackages, BorderLayout.CENTER);
        travelPackageTab.add(buttonBPanel, BorderLayout.SOUTH);
    }

    // Set up button actions
    private void setupButtons() {
        ButtonHandler handler = new ButtonHandler();
        btnDisplay.addActionListener(handler);
        btnRemove.addActionListener(handler);
        btnClear.addActionListener(handler);
        btnCAdd.addActionListener(handler);
        btnCRemove.addActionListener(handler);
        btnCClear.addActionListener(handler);
        btnBAdd.addActionListener(handler);
        btnBRemove.addActionListener(handler);
        btnBClear.addActionListener(handler);
        btnBCheckOut.addActionListener(handler);
    }

    // Populate initial data
    private void populateLists() {
        rooms.add(new Room("Single", 125));
        rooms.add(new Room("Double", 200));

        customers.add(new Customer("Natasha"));
        customers.add(new Customer("Jeff"));

        for (Room room : rooms) {
            roomsModel.addElement(room);
        }

        for (Customer customer : customers) {
            customersModel.addElement(customer);
        }
    }

    // Button handler class
    class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Handle button actions here
        }
    }

    // Main method
    public static void main(String[] args) {
        new ResortGUI();
    }
}

// Room class
class Room {
    private String type;
    private double pricePerDay;

    public Room(String type, double pricePerDay) {
        this.type = type;
        this.pricePerDay = pricePerDay;
    }

    @Override
    public String toString() {
        return type + " Room - $" + pricePerDay + " per day";
    }
}

// Customer class
class Customer {
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Customer: " + name;
    }
}

// TravelPackage class
class TravelPackage {
    private Room room;
    private Customer customer;

    public TravelPackage(Room room, Customer customer) {
        this.room = room;
        this.customer = customer;
    }

    @Override
    public String toString() {
        return customer + " booked " + room;
    }
}

   public static void main(String[] args) {
    ResortGUI cr = new ResortGUI();
      cr.populateLists();
      cr.setSize(550, 550);
      cr.setLocationRelativeTo((Component)null);
      cr.setVisible(true);
   }
}
