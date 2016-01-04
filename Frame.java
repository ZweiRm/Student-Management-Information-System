package com.jdbcTest;

import java.sql.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.JOptionPane;
import javax.swing.*;

public class Frame extends JFrame implements ActionListener {

	JMenuBar bar = null;
	JMenu menu1, menu2, menu3, menu4, menu5;
	JMenuItem item1, item2, item3, item4, item5;

	StudentAdd sadd;
	StudentQuery que;
	StudentUpdate upd;
	StudentDelete del;

	public Frame() {
		super("Student Menagement Information System");
		sadd = new StudentAdd();
		que = new StudentQuery();
		upd = new StudentUpdate();
		del = new StudentDelete();

		bar = new JMenuBar();

		menu1 = new JMenu("Insert Information");
		menu2 = new JMenu("Inquiry Information");
		menu3 = new JMenu("Update Information");
		menu4 = new JMenu("Delete Information");
		menu5 = new JMenu("Exit");

		item1 = new JMenuItem("Insert");
		item2 = new JMenuItem("Inquiry");
		item3 = new JMenuItem("Update");
		item4 = new JMenuItem("Delete");
		item5 = new JMenuItem("Exit");

		menu1.add(item1);
		menu2.add(item2);
		menu3.add(item3);
		menu4.add(item4);
		menu5.add(item5);

		bar.add(menu1);
		bar.add(menu2);
		bar.add(menu3);
		bar.add(menu4);
		bar.add(menu5);

		setJMenuBar(bar);

		item1.addActionListener(this);
		item2.addActionListener(this);
		item3.addActionListener(this);
		item4.addActionListener(this);
		item5.addActionListener(this);

		JLabel lab = new JLabel("Student Menagement Information System", JLabel.CENTER);
		lab.setFont(new Font("Student Management Information System", 0, 35));
		String str = " ";
		Font fon = new Font(str, Font.BOLD, 30);
		lab.setBackground(new Color(0, 255, 255));
		lab.setForeground(new Color(0, 0, 0));
		add(lab, "Center");
		setVisible(true);
		setSize(725, 320);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == item1) {
			this.getContentPane().removeAll();
			this.getContentPane().add(sadd, "Center");
			this.getContentPane().repaint();
			this.getContentPane().validate();
		}
		if (e.getSource() == item2) {
			this.getContentPane().removeAll();
			this.getContentPane().add(que, "Center");
			this.getContentPane().repaint();
			this.getContentPane().validate();
		}
		if (e.getSource() == item3) {
			this.getContentPane().removeAll();
			this.getContentPane().add(upd, "Center");
			this.getContentPane().repaint();
			this.getContentPane().validate();
		}
		if (e.getSource() == item4) {
			this.getContentPane().removeAll();
			this.getContentPane().add(del, "Center");
			this.getContentPane().repaint();
			this.getContentPane().validate();
		}
		if (e.getSource() == item5) {
			System.exit(0);
		}
	}

	public static void main(String[] args) {
		int width = 725;
		int height = 320;

		Frame stuM = new Frame();
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		Component com = stuM;
		com.setBounds((dimension.width - width) / 2, (dimension.height - height) / 2, width, height);

		stuM.setVisible(true);
		stuM.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
}
