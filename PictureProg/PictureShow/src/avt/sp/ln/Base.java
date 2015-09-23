package avt.sp.ln;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Base {
	
	public static void main(String[] args) {
		try {
		FirFR ffram = new FirFR();
		
		while (ffram.isntC)
			Thread.sleep(50);
		Worker work2 = new Worker(ffram.chose);
		work2.addPN();
		work2.showF();
		} catch (InterruptedException excep) {
			JOptionPane.showMessageDialog(null, "Interrupted Exception");
		}
	}
}
class FirFR implements ActionListener{

	JFrame ffram = new JFrame("Set Picture");
	JPanel panel = new JPanel();
	JFileChooser filec = new JFileChooser("/");
	String chose = "";
	boolean isntC = true;
	
	ImageIcon icon1 = new ImageIcon("icon.jpg");
	
	FirFR() {
		JOptionPane.showMessageDialog(null, "Welcome to my puzzle. You can choose\n"
				+ "any .jpg or .png picture to play, but I don't recomend\n "
				+ "you to use monotone pictures, because it'll be hard\n"
				+ " to differ onepiece from another. Also please use pictures with \n"
				+ "4 x 3 resolution... soon I'll fix it",
				"Introduction", 2, icon1);
		
		panel.add(filec);
		ffram.add(panel);
		filec.addActionListener(this);
		
		ffram.pack();
		ffram.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ffram.setLocationRelativeTo(null);
		ffram.setResizable(false);
		ffram.setVisible(true);
	}

	public void actionPerformed(ActionEvent event) {
		if (event.getActionCommand().equals(JFileChooser.APPROVE_SELECTION)) {
			chose = filec.getSelectedFile().getAbsolutePath();
			ffram.dispose();
			isntC = false;
		}
		else if (event.getActionCommand().equals(JFileChooser.CANCEL_SELECTION)) {
			isntC = false;
			System.exit(0);
		}
	}
}