import javax.swing.*;
import java.awt.*;

public class DisplayImage {

    public static void main(String[] args) {
        // Load the images for different room types
        ImageIcon singleIcon = new ImageIcon("images/single.jpeg");
        ImageIcon doubleIcon = new ImageIcon("images/double.jpeg");
        ImageIcon queenIcon = new ImageIcon("images/queen.jpeg");
        ImageIcon bunkerIcon = new ImageIcon("images/bunker.jpeg");

        // Display the images with room type descriptions
        displayRoomImage("Single Room", singleIcon);
        displayRoomImage("Double Room", doubleIcon);
        displayRoomImage("Queen Room", queenIcon);
        displayRoomImage("Bunker Room", bunkerIcon);
    }

    // Helper method to display each room image in a dialog
    private static void displayRoomImage(String roomType, ImageIcon icon) {
        if (icon.getImageLoadStatus() == MediaTracker.ERRORED) {
            JOptionPane.showMessageDialog(
                    null,
                    "Image not found for: " + roomType,
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        } else {
            JOptionPane.showMessageDialog(
                    null,
                    new JLabel(roomType, icon, JLabel.CENTER),
                    "Mt Buller - " + roomType,
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }
}
