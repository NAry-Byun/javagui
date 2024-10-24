import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;

public class ResortGUI extends JFrame {
    ArrayList<Room> rooms = new ArrayList<>();
    ImageIcon[] icons = {
        new ImageIcon("images/double.jpeg"), 
        new ImageIcon("images/double.jpeg"),
        new ImageIcon("images/family.jpg"),
        new ImageIcon("images/bunker.jpg")
    };

    Room rfound = null;

    DefaultListModel<Room> roomsModel = new DefaultListModel<>();
    JList<Room> roomsList = new JList<>(roomsModel);

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
        tabs.addTab("Rooms", roomsTab);
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

    public void populateLists() {
        Room[] arrOfRooms = { new Room("Double"), new Room("Queen"), new Room("Bunker"), new Room("Family") };
        Customer[] arrOfCustomers = { new Customer("Natasha"), new Customer("Jeff"), new Customer("Sam") };

        for (Room room : arrOfRooms) {
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
        double totalCost = days * b.getDuration().length * 100;  // Example calculation
        b.setTotalCost(totalCost);
        return totalCost;
    }

    class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnDisplay) {
                int index = roomsList.getSelectedIndex();
                if (index != -1) {
                    lblDisplay.setIcon(icons[index]);
                }
            }
            if (e.getSource() == btnBAdd) {
                Room room = roomsList.getSelectedValue();
                Customer customer = customersList.getSelectedValue();

                if (room != null && customer != null) {
                    TravelPackage TravelPackage = new TravelPackage(new Room[] { room }, new Customer[] { customer });
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

    public static void main(String[] args) {
        new ResortGUI();
    }
}

// Sample Room class
class Room {
    private String type;

    public Room(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return type;
    }
}

// Sample Customer class
class Customer {
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
