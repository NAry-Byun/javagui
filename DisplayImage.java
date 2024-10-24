import javax.swing.*;
import java.awt.*;
import java.io.File;

public class DisplayImage {
    public static void main(String[] args) {
        // Load all the images
        ImageIcon familyIcon = loadImage("images/family.jpeg");
        ImageIcon doubleIcon = loadImage("images/double.jpeg");
        ImageIcon queenIcon = loadImage("images/queen.jpeg");
        ImageIcon bunkerIcon = loadImage("images/bunker.jpeg");
        ImageIcon comportableIcon = loadImage("images/comportable.jpg");
        ImageIcon kingIcon = loadImage("images/king.jpg");
        ImageIcon king2Icon = loadImage("images/king2.jpg");
        ImageIcon luxuryIcon = loadImage("images/luxery.jpg");
        ImageIcon suitroomIcon = loadImage("images/suitroom.jpeg");
        ImageIcon twinIcon = loadImage("images/twin.jpeg");

        // Display each image with room type
        displayRoomImage("Family Room", familyIcon);
        displayRoomImage("Double Room", doubleIcon);
        displayRoomImage("Queen Room", queenIcon);
        displayRoomImage("Bunker Room", bunkerIcon);
        displayRoomImage("Comportable Room", comportableIcon);
        displayRoomImage("King Room", kingIcon);
        displayRoomImage("Second King Room", king2Icon);
        displayRoomImage("Luxury Room", luxuryIcon);
        displayRoomImage("Suit Room", suitroomIcon);
        displayRoomImage("Twin Room", twinIcon);
    }

    // Method to load an image and handle missing files
    private static ImageIcon loadImage(String path) {
        File imageFile = new File(path);
        if (!imageFile.exists()) {
            System.out.println("Image not found: " + path);
            return null;  // Return null if the image file is not found
        }
        return new ImageIcon(path);
    }

    // Method to display the room image
    private static void displayRoomImage(String roomType, ImageIcon icon) {
        if (icon == null || icon.getImageLoadStatus() == MediaTracker.ERRORED) {
            JOptionPane.showMessageDialog(null, "Image not found for: " + roomType, "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(
                    null, new JLabel(roomType, icon, JLabel.CENTER),
                    "Room Type - " + roomType, JOptionPane.INFORMATION_MESSAGE
            );
        }
    }
}
