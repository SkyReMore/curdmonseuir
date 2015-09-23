package avt.sp.ln;

import java.awt.event.ActionListener;
import java.io.File;
import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.*;

public class AMLis implements ActionListener {
	JFrame ffset;
	JPanel panel;
	JFileChooser filec;
	
	Worker workr;
	
	AMLis (Worker workr) {
		this.workr = workr;
	}
	
	public void actionPerformed(ActionEvent mnact) {
		Component compt = (Component)mnact.getSource();
		if (mnact.getActionCommand().equals(JFileChooser.APPROVE_SELECTION)) {
			ffset.dispose();
			workr.apanl.removeAll();
			workr.patht = filec.getSelectedFile();
			workr.addPN();
			workr.apanl.repaint();
			workr.afram.pack();
			workr.afram.setLocationRelativeTo(null);
		}
		else if (mnact.getActionCommand().equals(JFileChooser.CANCEL_SELECTION)) {
			ffset.dispose();
		}
		else if (compt.getName().equals("Open Picture")) {
			ffset = new JFrame("Open Picture");
			setPN();
			setFR();
		}
		else {
			for (int i = 0; i < workr.group.length; i++) {
				if (compt.getName().equals(workr.group[i].getName())) {
					workr.addPN(i + 1);

					for (int j = 0; j < workr.group.length; j++)
						if (j != i)
							workr.group[j].setSelected(false);
				}
			}	
		}
	}
	public void setFR() {

		ffset.pack();
		ffset.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		ffset.setLocationRelativeTo(null);
		ffset.setResizable(false);
		ffset.setVisible(true);
	}
	public void setPN() {
		
		panel = new JPanel();
		filec = new JFileChooser();
		filec.setCurrentDirectory(new File("/"));
		filec.addActionListener(this);
		
		panel.add(filec);
		ffset.add(panel);
	}
}
