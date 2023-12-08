package ihmproject1;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
	        login log = new login();
	        log.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit the application when the main login window is closed
	    });
	}
}
