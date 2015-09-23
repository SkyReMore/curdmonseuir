package avt.sp.ln;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Worker {
	// int THUMB_WIDTH = 80;
	int level = 1;
	double width, height;
	int[] mrand;
	
	JFrame afram = new JFrame("Pictures");
	
	JPanel apanl = new JPanel();

	JMenuItem mnbtn = new JMenuItem("Open");
	JRadioButton[] group;
	String[] btnam = {"Easy", "Medium", "Hard"};
	JMenu amenu = new JMenu("Menu");
	JMenu diffc = new JMenu("Difficulty");
	JMenuBar ambar = new JMenuBar();
	
	Handler handl;
	AMLis amlis;
	
	ArrayList<ImageIcon> labls;
	
	File patht;
	BufferedImage bufim;
	
	Worker(String chose) {
		 patht = new File(chose);
	}
	
	void showF() {

		amlis = new AMLis(this);
		
		group = new JRadioButton[btnam.length];
		for (int i = 0; i < btnam.length; i++) {
			group[i] = new JRadioButton(btnam[i]);
			diffc.add(group[i]);
			group[i].setName(btnam[i]);
			group[i].addActionListener(amlis);
		}
		
		amenu.add(mnbtn);
		amenu.add(diffc);
		mnbtn.setName("Open Picture");
		ambar.add(amenu);
		afram.setJMenuBar(ambar);

		//"Easy" option by default
		group[0].setSelected(true);
		
		mnbtn.addActionListener(amlis);
		
		// Setting frame size by the size of the picture
		// afram.setSize((int)width + 50, (int)height + 50);
		afram.pack();
		afram.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		afram.setLocationRelativeTo(null);
		afram.setResizable(false);
		afram.setVisible(true);
	}
	
	void addPN() {
		
		apanl.setLayout(new GridLayout(3, 4, 2, 2));
		apanl.setBackground(Color.BLACK);
		
		ArrayList<ImageIcon> labls = lablS();
		int name = 0;

		handl = new Handler(mrand, apanl, patht);
		
		for (ImageIcon elemt : labls) {
			JLabel alabl = new JLabel(elemt);
			alabl.addMouseListener(handl);
			alabl.setName(Integer.toString(name));
			name++;
			apanl.add(alabl);
		}
		afram.add(apanl);
	}
	void addPN(int level) {

		this.level = level;
		
		apanl.removeAll();
		apanl.setVisible(false);
		apanl.setLayout(new GridLayout(3, 4 * level, 2, 2));
		
		ArrayList<ImageIcon> labls = lablS();
		int name = 0;

		// I'll try to set frame size by the size of the picture here
		/*afram.setVisible(false);
		afram.setSize((int)width + 50, (int)height + 50);
		afram.setLocationRelativeTo(null);
		afram.setVisible(true);*/
		
		handl = new Handler(mrand, apanl, patht);
		
		for (ImageIcon elemt : labls) {
			JLabel alabl = new JLabel(elemt);
			alabl.addMouseListener(handl);
			alabl.setName(Integer.toString(name));
			name++;
			apanl.add(alabl);
		}
		apanl.setVisible(true);
	}
	
	ArrayList<ImageIcon> lablS() {
		
		ArrayList<ImageIcon> labls  = new ArrayList<ImageIcon>();
		ImageIcon aimag;
		Random randm = new Random();
		
		mrand = new int[12 * level];
		
		for (int i = 0; i < mrand.length; i++)
			mrand[i] = i;
		for (int i = 1; i < mrand.length; i++) {
			int chang = randm.nextInt(i);
			int clone = mrand[i];
			mrand[i] = mrand[chang];
			mrand[chang] = clone;
		}
		
		// Creating temporary picture to get size
		ImageIcon tempp = new ImageIcon(patht.getAbsolutePath());
		width = tempp.getIconWidth();
		System.out.println(width);
		height = tempp.getIconHeight();
		System.out.println(height);
		System.out.println();
		
		
		bufim
		 = new BufferedImage(tempp.getIconWidth(), tempp.getIconHeight(), 1);
		try {
			bufim = ImageIO.read(patht);
			
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "IOException!");
		}
		
		for (int i = 0; i < mrand.length; i++) {
			aimag = new ImageIcon(cutPT(mrand[i]));
			labls.add(aimag);
		}
		return labls;
	}
	
	Image cutPT(int wpart) {
		
		/*width = width / (width / 1200);
		height = height / (height / 800);
		
		Image resiz = bufim.getScaledInstance((int)(width), (int)(height), 0);
		
		BufferedImage auimg = new BufferedImage((int)(width), (int)(height), 1);
		
		Graphics graph = auimg.getGraphics();
		graph.drawImage(resiz, 0, 0 , null);*/
		
		Image cuttd 
		= bufim.getSubimage((int)(wpart % (4 * level) * (width / 4)
				/ level), (int)(wpart / (4 * level)
				* height / 3), (int)(width / 4 / level), (int)(height / 3));
		
		System.out.println(wpart);
		System.out.println(wpart % (4 * level) * (width / 4) / level);
		System.out.println(wpart / (4 * level) * height / 3);
		System.out.println(width / 4 / level);
		System.out.println(height / 3);
		System.out.println();
		return cuttd;
	}
}
