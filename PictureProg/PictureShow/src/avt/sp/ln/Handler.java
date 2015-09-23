package avt.sp.ln;

import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.*;

public class Handler implements MouseListener {
	JPanel apanl;
	JLabel first, secon;
	
	int post1, post2;
	File patht;
	
	int[] mrand;
	ArrayList<ImageIcon> picts;
	boolean nexcl = false;
	
	SoENG sound = new SoENG();
	
	Handler(int[] rmass, JPanel apanl, File patht) {
		mrand = rmass;
		this.apanl = apanl;
		this.patht = patht;
	}
	
	public void mousePressed(MouseEvent mouse) {


		if (!nexcl) {
			nexcl = true;
			first = (JLabel)mouse.getComponent();
			post1 = Integer.parseInt(mouse.getComponent().getName());
		}
		else {
			secon = (JLabel)mouse.getComponent();
			if (secon != first) {
				
				post2 = Integer.parseInt(mouse.getComponent().getName());
				
				// Here one image replaces another
				Icon templ = first.getIcon();
				first.setIcon(secon.getIcon());
				secon.setIcon(templ);
				nexcl = false;
				
				int tempn = mrand[post1];
				mrand[post1] = mrand[post2];
				mrand[post2] = tempn;
				//Checking if massive of integer numbers is in right
				// sequence
				 if (chkir()){
					 apanl.setVisible(false);
					 apanl.removeAll();
					 apanl.setLayout(new FlowLayout());
					 JLabel finll = new JLabel(new ImageIcon(patht.getPath()));
					 // finll.setBounds(0, 0, 320, 240);
					 apanl.add(finll);
					 apanl.setVisible(true);

					 sound.clipr.setFramePosition(0);
					 sound.clipr.start();
				 }
			}
		}
	}

	boolean chkir() {
		
		for (int i = 0; i < mrand.length; i++) {
			if (mrand[i] != i) {
				return false;
			}
		}
		return true;
	}
	public void mouseEntered(MouseEvent enter) {
		((JComponent)enter.getComponent()).setBorder(BorderFactory.createEtchedBorder());

		sound.bclip.setFramePosition(0);
		sound.bclip.start();
	}

	public void mouseExited(MouseEvent outer) {
		((JComponent)outer.getComponent()).setBorder(null);
		
		sound.bclip.stop();
	}
	public void mouseClicked(MouseEvent arg0) {
	}

	public void mouseReleased(MouseEvent arg0) {
	}
	
}
