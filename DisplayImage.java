import javax.swing.*;
import java.awt.*;

public class DisplayImage {
    public static void main(String[] args) {
        ImageIcon singleIcon = new ImageIcon("images/single.jpeg");
        ImageIcon doubleIcon = new ImageIcon("images/double.jpeg");
        ImageIcon queenIcon = new ImageIcon("images/queen.jpeg");
        ImageIcon bunkerIcon = new ImageIcon("images/bunker.jpeg");

        displayRoomImage("Single Room", singleIcon);
        displayRoomImage("Double Room", doubleIcon);
        displayRoomImage("Queen Room", queenIcon);
        displayRoomImage("Bunker Room", bunkerIcon);
    }

    private static void displayRoomImage(String roomType, ImageIcon icon) {
        if (icon.getImageLoadStatus() == MediaTracker.ERRORED) {
            JOptionPane.showMessageDialog(null, "Image not found for: " + roomType, "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, new JLabel(roomType, icon, JLabel.CENTER),
                    "Room Type - " + roomType, JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
