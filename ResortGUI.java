import java.awt.*;
import java.awt.event.*;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.format.DateTimeParseException;

import javax.swing.*;
import javax.swing.border.*;
import java.util.*;

public class ResortGUI extends JFrame {
    private FileInputStream fis;
    private ObjectInputStream ois;
    private FileOutputStream fos;
    private ObjectOutputStream oos;

    ArrayList<Accommodation.Room> rooms = new ArrayList<>();
    ImageIcon[] icons = {
        new ImageIcon("images/bunker.jpg"), 
        new ImageIcon("images/double.jpg"),
        new ImageIcon("images/family.png"),
        new ImageIcon("images/queen.jpg"),
        new ImageIcon("images/comportable.jpg"),
        new ImageIcon("images/king.jpg"),
        new ImageIcon("images/king2.jpg"),
        new ImageIcon("images/luxury.jpg"),
        new ImageIcon("images/suitroom.jpg"),
        new ImageIcon("images/twin.jpeg")
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
    JPanel LiftPassTab = new JPanel();
    JPanel LessonsTab = new JPanel();

    JPanel inputPanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    JLabel lblDisplay = new JLabel("Click on button to display room", null, SwingConstants.RIGHT);
    JButton btnDisplay = new JButton("Display selected");
    JButton btnDispalyAll = new JButton("Display All Rooms");
    JButton btnDispalyAvailable = new JButton("Display Available Rooms");
    JButton btnDeletePackage = new JButton("Delete Package");

    JPanel inputBPanel = new JPanel();
    JTextField txtDate = new JTextField(10);
    JTextField txtDays = new JTextField(10);
    JButton btnBAdd = new JButton("Add TravelPackage");
    JButton btnDiaplayPackages = new JButton("List Packages");
    JButton btnReadPg = new JButton("Read Package");
    JButton btnSavePg = new JButton("Save Package");

    // Add Lift Pass Tab Components
    JButton btnLift = new JButton("Add Lift Pass");
    JTextField txtLiftPassDays = new JTextField(10);
    JRadioButton rbtnYes = new JRadioButton("Yes");
    JRadioButton rbtnNo = new JRadioButton("No");
    ButtonGroup seasonPassGroup = new ButtonGroup();

    // Add Lessons Components
    JTextField txtLessons = new JTextField(10);
    JButton btnAddLesson = new JButton("Add Lesson");

    // Customer Tab components
    JPanel customerInputPanel = new JPanel();
    JTextField txtName = new JTextField(10);
    JTextField txtLevel = new JTextField(10);
    JButton btnListCustomers = new JButton("List Customers");
    JButton btnAddCustomer = new JButton("Add Customer");
    JButton btnRemoveCustomer = new JButton("Remove Customer");

    public ResortGUI() {
        // Setup tabs
        add(tabs);
        tabs.addTab("Accommodations", roomsTab);
        tabs.addTab("Customers", customersTab);
        tabs.addTab("TravelPackages", TravelPackagesTab);
        tabs.addTab("Add Lift Pass", LiftPassTab);
        tabs.addTab("Add Lessons", LessonsTab);

        // Rooms tab setup
        inputPanel.add(lblDisplay);
        JScrollPane scrollRooms = new JScrollPane(roomsList);
        scrollRooms.setBorder(new TitledBorder("List of rooms"));
        buttonPanel.add(btnDisplay);
        buttonPanel.add(btnDispalyAll);
        buttonPanel.add(btnDispalyAvailable);
        roomsTab.setLayout(new BorderLayout());
        roomsTab.add(inputPanel, BorderLayout.NORTH);
        roomsTab.add(scrollRooms, BorderLayout.CENTER);
        roomsTab.add(buttonPanel, BorderLayout.SOUTH);

        // Customer Tab setup
        customerInputPanel.setLayout(new GridLayout(2, 2, 10, 10));
        customerInputPanel.add(new JLabel("Name:"));
        customerInputPanel.add(txtName);
        customerInputPanel.add(new JLabel("Level:"));
        customerInputPanel.add(txtLevel);

        JPanel customerButtonPanel = new JPanel();
        customerButtonPanel.add(btnListCustomers);
        customerButtonPanel.add(btnAddCustomer);
        customerButtonPanel.add(btnRemoveCustomer);

        customersTab.setLayout(new BorderLayout());
        customersTab.add(customerInputPanel, BorderLayout.NORTH);
        customersTab.add(new JScrollPane(customersList), BorderLayout.CENTER);
        customersTab.add(customerButtonPanel, BorderLayout.SOUTH);

        // TravelPackages tab setup
        JPanel packageButtonPanel = new JPanel();
        inputBPanel.setLayout(new GridLayout(2, 2, 10, 10));
        inputBPanel.add(new JLabel("Enter date (yyyy-mm-dd):"));
        inputBPanel.add(txtDate);
        inputBPanel.add(new JLabel("Duration (days):"));
        inputBPanel.add(txtDays);
        TravelPackagesTab.setLayout(new BorderLayout());
        TravelPackagesTab.add(inputBPanel, BorderLayout.NORTH);
        TravelPackagesTab.add(new JScrollPane(TravelPackagesList), BorderLayout.CENTER);
        packageButtonPanel.add(btnBAdd);
        packageButtonPanel.add(btnDiaplayPackages);
        packageButtonPanel.add(btnDeletePackage);
        packageButtonPanel.add(btnSavePg);
        packageButtonPanel.add(btnReadPg);
        TravelPackagesTab.add(packageButtonPanel, BorderLayout.SOUTH);

        // Add Lift Pass Tab Layout
        LiftPassTab.setLayout(new GridLayout(4, 1, 10, 10));

        // Label: Select Travel Package
        LiftPassTab.add(new JLabel("Please select the package from TravelPackage Tab"));

        // Label and Text Field: How many days for Lift Pass
        JPanel liftPassDaysPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        liftPassDaysPanel.add(new JLabel("How many day Lift Pass you want to buy:"));
        liftPassDaysPanel.add(txtLiftPassDays);
        LiftPassTab.add(liftPassDaysPanel);

        // Label and Radio Buttons: Season Pass
        JPanel seasonPassPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        seasonPassPanel.add(new JLabel("Do you want to buy season pass?"));

        rbtnNo.setSelected(true);  // Default selection
        seasonPassGroup.add(rbtnYes);
        seasonPassGroup.add(rbtnNo);

        seasonPassPanel.add(rbtnYes);
        seasonPassPanel.add(rbtnNo);
        LiftPassTab.add(seasonPassPanel);
        
        // Add Lift Pass Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(btnLift);
        LiftPassTab.add(buttonPanel);

        // Lessons Tab Layout
        LessonsTab.setLayout(new BorderLayout(10, 10));

        JPanel lessonInputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        lessonInputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        lessonInputPanel.add(new JLabel("Please select the package from TravelPackage Tab:"));
        lessonInputPanel.add(new JLabel()); // Placeholder to align grid properly
        lessonInputPanel.add(new JLabel("How many day Lesson you want to buy:"));
        lessonInputPanel.add(txtLessons);

        LessonsTab.add(lessonInputPanel, BorderLayout.NORTH);

        JPanel lessonButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        lessonButtonPanel.add(btnAddLesson);
        LessonsTab.add(lessonButtonPanel, BorderLayout.SOUTH);


        // Event handlers
        ButtonHandler bh = new ButtonHandler();
        btnDisplay.addActionListener(bh);
        btnDispalyAll.addActionListener(bh);
        btnDispalyAvailable.addActionListener(bh);
        btnBAdd.addActionListener(bh);
        btnDiaplayPackages.addActionListener(bh);
        btnDeletePackage.addActionListener(bh);
        btnReadPg.addActionListener(bh);
        btnSavePg.addActionListener(bh);
        btnAddCustomer.addActionListener(bh);
        btnRemoveCustomer.addActionListener(bh);
        btnListCustomers.addActionListener(bh);
        btnLift.addActionListener(bh);
        btnAddLesson.addActionListener(bh);


        populateLists();
        setSize(750, 550);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void populateLists() {
        Accommodation.Room[] arrOfRooms = {  
            new Accommodation.Room("A-1", "Moose Apartment", "Apartment", 150),
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
        Customer[] arrOfCustomers = {
            new Customer("Natasha", "Beginner"), 
            new Customer("Jeff", "Intermediate"), 
            new Customer("Sam", "Advanced")
        };

        for (Accommodation.Room room : arrOfRooms) {
            rooms.add(room);
        }

        for (Customer customer : arrOfCustomers) {
            customers.add(customer);
        }

        TravelPackage Tpackage = new TravelPackage(arrOfRooms[0], arrOfCustomers[0]);
        Tpackage.setDateFromString("2024-12-12");
        Tpackage.setDuration(4);
        TravelPackages.add(Tpackage);
    }

    class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //Display photo of accommodation
            if (e.getSource() == btnDisplay) {
                int index = roomsList.getSelectedIndex();
                if (index != -1) {
                    lblDisplay.setIcon(icons[index]);
                }
            }

             //Display all rooms
             if(e.getSource() == btnDispalyAll) {
                roomsModel.clear();
                for(Accommodation.Room r: rooms) {
                    roomsModel.addElement(r);
                }
                roomsList.repaint();
            }
            if (e.getSource() == btnDeletePackage) {
                TravelPackage selectedPackage = TravelPackagesList.getSelectedValue();
                if (selectedPackage != null) {
                    TravelPackages.remove(selectedPackage);
                    TravelPackagesModel.removeElement(selectedPackage);
                    JOptionPane.showMessageDialog(null, "Package deleted successfully.");
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a package to delete.");
                }
            }
            
            //Display only available rooms
               if(e.getSource() == btnDispalyAvailable) {
                    roomsModel.clear();
                for(Accommodation.Room r: rooms) {
                    if(r.isAvailable()){
                    roomsModel.addElement(r);
                    }
                }
                roomsList.repaint();
                }

            // Add customers
            if (e.getSource() == btnAddCustomer) {
                String name = txtName.getText();
                String level = txtLevel.getText();
                if (!name.isEmpty() && !level.isEmpty()) {
                    Customer newCustomer = new Customer(name, level);
                    customers.add(newCustomer);
                    customersModel.addElement(newCustomer);
                }
            }
            // Remove customers
            if (e.getSource() == btnRemoveCustomer) {
                Customer selectedCustomer = customersList.getSelectedValue();
                if (selectedCustomer != null) {
                    customers.remove(selectedCustomer);
                    customersModel.removeElement(selectedCustomer);
                }
            }
            // Listing customers
             //#4 Display Customers
                if(e.getSource() == btnListCustomers){
                    customersModel.clear();
                    for(Customer c: customers) {
                    customersModel.addElement(c);
                }   
                    }

            //Add a package
                if(e.getSource() == btnBAdd){
                    TravelPackage Tpackage = null;
                    //get room and customer from the lists
                    Accommodation.Room rm = roomsList.getSelectedValue();
                    Customer customer = customersList.getSelectedValue();
                    if(rm != null && customer!=null){
                    //read other data
                        try{
                        String date = txtDate.getText();
                    
                        String daysStr = txtDays.getText();
                        int days = 0;
                        if(daysStr!=""){
                        days = Integer.parseInt(daysStr);
                        Tpackage = new TravelPackage(rm, customer);
                        Tpackage.setDateFromString(date);
                        Tpackage.setDuration(days);
                        rm.setAvailability("no");
                        TravelPackages.add(Tpackage);
                        TravelPackagesModel.addElement(Tpackage);
                        
                        //clear the text fields
                        txtDate.setText("");
                        txtDays.setText("");
                        }
                    /*else{
                        JOptionPane.showMessageDialog(null, "Please enter duration in days");
                        txtDays.setText("Please enter duration in days");
                    }*/
                        
                    }
                    catch(DateTimeParseException de){
                        TravelPackage.nextID--;
                        JOptionPane.showMessageDialog(null, 
                        "Try the date format as yyyy-mm-dd again!!!!.");
                    }
                    catch(NumberFormatException ne){
                        TravelPackage.nextID--;
                        JOptionPane.showMessageDialog(null, 
                        "Please enter duration in valid number!!!!.");
                    }
                    catch(Exception ee){
                        TravelPackage.nextID--;
                        System.out.println(ee);
                        JOptionPane.showMessageDialog(null, "Input Error!!!");
                    }
                    }
                    else
                        JOptionPane.showMessageDialog(null, "Please select an accommodation and a customer from lists");
                    } 

            //List Packages
            if(e.getSource() == btnDiaplayPackages){
                TravelPackagesModel.clear();
                for(TravelPackage p: TravelPackages) {
                    TravelPackagesModel.addElement(p);
            }
            }
            
            //Add Lift Pass
            if(e.getSource() == btnLift){
                TravelPackage Tpackage = TravelPackagesList.getSelectedValue();
                try{
                if(Tpackage != null){
                String liftStr = txtLiftPassDays.getText().trim();
                int lifts = 0;
                    if(liftStr!=""){
                    lifts = Integer.parseInt(liftStr);
                    Tpackage.setDayLiftPass(lifts);
                    //set season pass cost
                    if(rbtnYes.isSelected()){
                        Tpackage.setSeasonPass(true);
                    }
                    //clear the text field
                    txtLiftPassDays.setText("");
                }

                }
                else
                    JOptionPane.showMessageDialog(null, "Please select a package from lists");
            }
            catch(NumberFormatException ne){
                JOptionPane.showMessageDialog(null, 
                    "Please enter valid numbers for Day Lift Passes.", 
                    "Input Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }

        //Add Lessons
        if(e.getSource() == btnAddLesson){
            TravelPackage Tpackage = TravelPackagesList.getSelectedValue();
            try{
            if(Tpackage != null){
            //read data from check box

            //read data from txt field
            String lessonStr = txtLessons.getText().trim();
            int lessons = 0;
                if(lessonStr!=""){
                lessons = Integer.parseInt(lessonStr);
                Tpackage.setLessons(lessons);
                //get customer form selected package
                Customer cust = Tpackage.getCustomer();
                System.out.println(cust.getLevel());
                //set lesson cost
                Tpackage.setLessons(lessons);
                System.out.println(Tpackage.getLessons());
                //clear the text field
                txtLessons.setText("");
            }

            }
            else
                JOptionPane.showMessageDialog(null, "Please select a package from lists");
        }
        catch(NumberFormatException ne){
            JOptionPane.showMessageDialog(null, 
                "Please enter valid numbers for Day Lift Passes.", 
                "Input Error", 
                JOptionPane.ERROR_MESSAGE);
        }
        }

  //Save package
    if(e.getSource() == btnSavePg){
      try {
	  		fos = new FileOutputStream("packages.dat");
	  		oos = new ObjectOutputStream(fos);
	  		for(TravelPackage Tpackage: TravelPackages) {
	  			oos.writeObject(Tpackage);
	  		}
	  		fos.close();
	  		oos.close();
	  	} catch (Exception ex) {
	  		JOptionPane.showMessageDialog(null, ex.getMessage());
	  	}
    }

    //Read packages
    if(e.getSource() == btnReadPg){
      TravelPackages.clear();
      TravelPackagesModel.clear();
		 try {
		      fis = new FileInputStream("packages.dat");
		      ois = new ObjectInputStream(fis);
		      
		      while (true) {
		        try {
		          Object object = ois.readObject();
		          TravelPackage pg = (TravelPackage)object;
		          //update accommdation status
		          Accommodation.Room r = pg.getRoom();
		          r.setAvailability("no");
		          //add to array list
		          TravelPackages.add(pg);
		          TravelPackagesModel.addElement(pg);
		        } catch (EOFException eof) {
		          fis.close();
		          ois.close();
		          break;
		        }
		      }
		      
		    } catch (Exception ex) {
		      ex.printStackTrace();
		    }
    }
        }
    }

    public static void main(String[] args) {
        new ResortGUI();
    }
}