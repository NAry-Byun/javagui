import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;
import java.time.*;

public class ResortGUI extends JFrame {
    ArrayList<Accommodation.Room> rooms = new ArrayList<>();
    ArrayList<Customer> customers = new ArrayList<>();
    ArrayList<TravelPackage> travelPackages = new ArrayList<>();

    DefaultListModel<Accommodation.Room> roomsModel = new DefaultListModel<>();
    JList<Accommodation.Room> roomsList = new JList<>(roomsModel);

    DefaultListModel<Customer> customersModel = new DefaultListModel<>();
    JList<Customer> customersList = new JList<>(customersModel);

    DefaultListModel<TravelPackage> travelPackagesModel = new DefaultListModel<>();
    JList<TravelPackage> travelPackagesList = new JList<>(travelPackagesModel);

    // Room images/icons
    ImageIcon[] icons = {
        new ImageIcon("images/bunker.jpg"),
        new ImageIcon("images/double.jpg"),
        new ImageIcon("images/family.png"),
        new ImageIcon("images/queen.jpg")
    };

    // UI Components
    JTabbedPane tabs = new JTabbedPane();
    JPanel roomsTab = new JPanel();
    JPanel customersTab = new JPanel();
    JPanel travelPackagesTab = new JPanel();

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
        tabs.addTab("Accommodations", setupRoomsTab());
        tabs.addTab("Customers", setupCustomersTab());
        tabs.addTab("Travel Packages", setupTravelPackagesTab());

        // Populate initial data
        populateLists();

        // GUI properties
        setSize(750, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private JPanel setupRoomsTab() {
        roomsTab.setLayout(new BorderLayout());

        inputPanel.add(lblDisplay);
        JScrollPane scrollRooms = new JScrollPane(roomsList);
        scrollRooms.setBorder(new TitledBorder("List of rooms"));

        buttonPanel.add(btnDisplay);
        buttonPanel.add(btnRemove);
        buttonPanel.add(btnClear);

        btnDisplay.addActionListener(new ButtonHandler());
        btnRemove.addActionListener(new ButtonHandler());
        btnClear.addActionListener(new ButtonHandler());

        roomsTab.add(inputPanel, BorderLayout.NORTH);
        roomsTab.add(scrollRooms, BorderLayout.CENTER);
        roomsTab.add(buttonPanel, BorderLayout.SOUTH);

        return roomsTab;
    }

    private JPanel setupCustomersTab() {
        customersTab.setLayout(new BorderLayout());

        JScrollPane scrollCustomers = new JScrollPane(customersList);
        scrollCustomers.setBorder(new TitledBorder("List of customers"));

        JPanel inputPanel = new JPanel(new GridLayout(1, 2));
        inputPanel.add(new JLabel("Add Customer:"));
        JTextField txtCustomerName = new JTextField(10);
        inputPanel.add(txtCustomerName);

        JButton btnAddCustomer = new JButton("Add Customer");
        btnAddCustomer.addActionListener(e -> {
            String name = txtCustomerName.getText();
            if (!name.isEmpty()) {
                Customer newCustomer = new Customer(name, "Beginner");
                customers.add(newCustomer);
                customersModel.addElement(newCustomer);
                txtCustomerName.setText("");
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnAddCustomer);

        customersTab.add(inputPanel, BorderLayout.NORTH);
        customersTab.add(scrollCustomers, BorderLayout.CENTER);
        customersTab.add(buttonPanel, BorderLayout.SOUTH);

        return customersTab;
    }

    private JPanel setupTravelPackagesTab() {
        travelPackagesTab.setLayout(new BorderLayout());

        inputBPanel.setLayout(new GridLayout(2, 2, 10, 10));
        inputBPanel.add(new JLabel("Enter date (yyyy-mm-dd):"));
        inputBPanel.add(txtDate);
        inputBPanel.add(new JLabel("Duration (days):"));
        inputBPanel.add(txtDays);

        travelPackagesTab.add(inputBPanel, BorderLayout.NORTH);
        travelPackagesTab.add(new JScrollPane(travelPackagesList), BorderLayout.CENTER);
        travelPackagesTab.add(btnBAdd, BorderLayout.SOUTH);

        btnBAdd.addActionListener(new ButtonHandler());

        return travelPackagesTab;
    }

    public void populateLists() {
        Accommodation.Room[] arrOfRooms = {
            new Accommodation.Room("A-1", "Moose Apartment", "Apartment", 150),
            new Accommodation.Room("A-2", "The Park Apartment", "Apartment", 180),
            new Accommodation.Room("A-3", "White Horse Apartment", "Apartment", 200),
            new Accommodation.Room("H-1", "Abom Hotel", "Hotel", 110),
            new Accommodation.Room("H-2", "Duck Inn Hotel", "Hotel", 320),
            new Accommodation.Room("L-1", "Awsc Lodge", "Lodge", 150)
        };

        Customer[] arrOfCustomers = {
            new Customer("Natasha", "Beginner"),
            new Customer("Jeff", "Intermediate"),
            new Customer("Sam", "Advanced")
        };

        for (Accommodation.Room room : arrOfRooms) {
            rooms.add(room);
            roomsModel.addElement(room);
        }

        for (Customer customer : arrOfCustomers) {
            customers.add(customer);
            customersModel.addElement(customer);
        }
    }

    class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnDisplay) {
                int index = roomsList.getSelectedIndex();
                if (index != -1) {
                    lblDisplay.setIcon(icons[index]);
                }
            } else if (e.getSource() == btnBAdd) {
                Accommodation.Room room = roomsList.getSelectedValue();
                Customer customer = customersList.getSelectedValue();

                if (room != null && customer != null) {
                    TravelPackage travelPackage = new TravelPackage(room, customer);
                    travelPackage.setDateFromString(txtDate.getText());
                    travelPackage.setDuration(Integer.parseInt(txtDays.getText()));
                    travelPackages.add(travelPackage);
                    travelPackagesModel.addElement(travelPackage);
                } else {
                    JOptionPane.showMessageDialog(null, "Select both a room and a customer.");
                }
            }
        }
    }

    public static void main(String[] args) {
        new ResortGUI();
    }
}
