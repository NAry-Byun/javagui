import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;

public class ResortGUI extends JFrame {
    ArrayList<Accommodation.Room> rooms = new ArrayList<>();
    // Images
    ImageIcon[] icons = {
        new ImageIcon("images/bunker.jpg"), 
        new ImageIcon("images/double.jpg"),
        new ImageIcon("images/family.png"),
        new ImageIcon("images/queen.jpg")
    };

    Accommodation rfound = null;

    DefaultListModel<Accommodation.Room> roomsModel = new DefaultListModel<>();
    JList<Accommodation.Room> roomsList = new JList<>(roomsModel);

    ArrayList<Customer> customers = new ArrayList<>();
    DefaultListModel<Customer> customersModel = new DefaultListModel<>();
    JList<Customer> customersList = new JList<>(customersModel);

    ArrayList<TravelPackage> TravelPackages = new ArrayList<>();
    DefaultListModel<TravelPackage> TravelPackagesModel = new DefaultListModel<>();
    JList<TravelPackage> TravelPackagesList = new JList<>(TravelPackagesModel);

    // UI components
    JTabbedPane tabs = new JTabbedPane();
    JPanel roomsTab = new JPanel();
    JPanel customersTab = new JPanel();
    JPanel TravelPackagesTab = new JPanel();

    JPanel inputPanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    JLabel lblDisplay = new JLabel("Click on button to display room", null, SwingConstants.RIGHT);
    JButton btnDisplay = new JButton("Display selected");
    JButton btnRemove = new JButton("Remove");
    JButton btnClear = new JButton("Clear selection");

    JPanel inputBPanel = new JPanel();
    JTextField txtDate = new JTextField(10);
    JTextField txtDays = new JTextField(10);
    JButton btnBAdd = new JButton("Add TravelPackage");

    public ResortGUI() {
        // Setup tabs
        add(tabs);
        tabs.addTab("Accommodations", roomsTab);
        tabs.addTab("Customers", customersTab);
        tabs.addTab("TravelPackages", TravelPackagesTab);

        // Rooms tab
        inputPanel.add(lblDisplay);
        JScrollPane scrollRooms = new JScrollPane(roomsList);
        scrollRooms.setBorder(new TitledBorder("List of rooms"));
        buttonPanel.add(btnDisplay);
        buttonPanel.add(btnRemove);
        buttonPanel.add(btnClear);
        roomsTab.setLayout(new BorderLayout());
        roomsTab.add(inputPanel, BorderLayout.NORTH);
        roomsTab.add(scrollRooms, BorderLayout.CENTER);
        roomsTab.add(buttonPanel, BorderLayout.SOUTH);

        //Customer Tab
        

        // TravelPackages tab
        inputBPanel.setLayout(new GridLayout(2, 2, 10, 10));
        inputBPanel.add(new JLabel("Enter date (yyyy-mm-dd):"));
        inputBPanel.add(txtDate);
        inputBPanel.add(new JLabel("Duration (days):"));
        inputBPanel.add(txtDays);
        TravelPackagesTab.setLayout(new BorderLayout());
        TravelPackagesTab.add(inputBPanel, BorderLayout.NORTH);
        TravelPackagesTab.add(new JScrollPane(TravelPackagesList), BorderLayout.CENTER);
        TravelPackagesTab.add(btnBAdd, BorderLayout.SOUTH);

        // Register event handlers
        ButtonHandler bh = new ButtonHandler();
        btnDisplay.addActionListener(bh);
        btnRemove.addActionListener(bh);
        btnClear.addActionListener(bh);
        btnBAdd.addActionListener(bh);

        // Populate data
        populateLists();
        setSize(550, 550);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    private JPanel setupCustomersTab() {
        customersTab.setLayout(new BorderLayout());
    
        // Input panel for adding customers
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        inputPanel.setBorder(new EmptyBorder(10, 20, 10, 20));
        
        // Name and Level text fields
        inputPanel.add(new JLabel("Name:"));
        JTextField txtCustomerName = new JTextField(10);
        inputPanel.add(txtCustomerName);
    
        inputPanel.add(new JLabel("Level:"));
        JTextField txtCustomerLevel = new JTextField(10);
        inputPanel.add(txtCustomerLevel);
    
        // Buttons for managing customers
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton btnAddCustomer = new JButton("Add Customer");
        JButton btnListCustomers = new JButton("List Customers");
        JButton btnRemoveCustomer = new JButton("Remove Customer");
    
        buttonPanel.add(btnAddCustomer);
        buttonPanel.add(btnListCustomers);
        buttonPanel.add(btnRemoveCustomer);
    
        // Customer list with scroll pane
        JScrollPane scrollCustomers = new JScrollPane(customersList);
        scrollCustomers.setBorder(new TitledBorder("List of Customers"));
    
        // Add components to the customer tab
        customersTab.add(inputPanel, BorderLayout.NORTH);
        customersTab.add(scrollCustomers, BorderLayout.CENTER);
        customersTab.add(buttonPanel, BorderLayout.SOUTH);
    
        // Button functionality
        btnAddCustomer.addActionListener(e -> {
            String name = txtCustomerName.getText();
            String level = txtCustomerLevel.getText();
    
            if (!name.isEmpty() && !level.isEmpty()) {
                Customer newCustomer = new Customer(name, level);
                customers.add(newCustomer);
                customersModel.addElement(newCustomer);
                txtCustomerName.setText("");  // Clear the name field
                txtCustomerLevel.setText("");  // Clear the level field
            } else {
                JOptionPane.showMessageDialog(null, "Please enter both Name and Level.");
            }
        });
    
        btnListCustomers.addActionListener(e -> {
            customersModel.clear();  // Clear the list model
            customers.forEach(customersModel::addElement);  // Re-add all customers to the list
        });
    
        btnRemoveCustomer.addActionListener(e -> {
            Customer selectedCustomer = customersList.getSelectedValue();
            if (selectedCustomer != null) {
                customers.remove(selectedCustomer);
                customersModel.removeElement(selectedCustomer);
            } else {
                JOptionPane.showMessageDialog(null, "Please select a customer to remove.");
            }
        });
    
        return customersTab;
    }
    

    public void populateLists() {
        Accommodation.Room[] arrOfRooms = {  new Accommodation.Room("A-1", "Moose Apartment", "Apartment", 150),
        new Accommodation.Room("A-2", "The Park Apartment", "Apartment", 180),
        new Accommodation.Room("A-3", "White Horse Apartment", "Apartment", 200),
        new Accommodation.Room("H-1", "Abom Hotel", "Hotel", 110),
        new Accommodation.Room("H-2", "Duck Inn Hotel", "Hotel", 320),
        new Accommodation.Room("H-3", "Breath Take Hotel", "Hotel", 730),
        new Accommodation.Room("L-1", "Awsc Lodge", "Lodge", 150),
        new Accommodation.Room("L-2", "The Bike Lodge", "Lodge", 180),
        new Accommodation.Room("L-3", "Amber Lodge", "Lodge", 200),
        new Accommodation.Room("L-4", "Tinder Lodge", "Lodge", 220)
         };
        Customer[] arrOfCustomers = { new Customer("Natasha", "Beginner"), new Customer("Jeff", "Intermediate"), new Customer("Sam", "Advanced") };

        for (Accommodation.Room room : arrOfRooms) {
            rooms.add(room);
            roomsModel.addElement(room);
        }

        for (Customer customer : arrOfCustomers) {
            customers.add(customer);
            customersModel.addElement(customer);
        }
    }

    public double calculateTotalCost(TravelPackage b) {
        int days = b.getDuration();
        double totalCost = days * b.getDuration() * 100;  // Example calculation
        b.setTotalCost(totalCost);
        return totalCost;
    }

    class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnDisplay) {
                System.out.println("Clicked");
                int index = roomsList.getSelectedIndex();
                if (index != -1) {
                    lblDisplay.setIcon(icons[index]);
                    System.out.println(index);
                }
            }
            if (e.getSource() == btnBAdd) {
                Accommodation.Room room = roomsList.getSelectedValue();
                Customer customer = customersList.getSelectedValue();

                if (room != null && customer != null) {
                    TravelPackage TravelPackage = new TravelPackage(room, customer);
                    TravelPackage.setDateFromString(txtDate.getText());
                    TravelPackage.setDuration(Integer.parseInt(txtDays.getText()));
                    TravelPackages.add(TravelPackage);
                    TravelPackagesModel.addElement(TravelPackage);
                } else {
                    JOptionPane.showMessageDialog(null, "Select both a room and a customer.");
                }
            }
        }
    }

    // Main Method
public static void main(String[] args) {
    ResortGUI javaGUI = new ResortGUI();
    //cr.setDefaultCloseOperation(EXIT_ON_CLOSE);
    javaGUI.setSize(750,550);
    javaGUI.setLocationRelativeTo(null);
    javaGUI.setVisible(true);
}

}