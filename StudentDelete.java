package com.jdbcTest;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;

public class StudentDelete extends JPanel implements ActionListener {

	Connection con;
	Statement sql;
	JButton b1, b2;
	JTextField tf1, tf2, tf3, tf4, tf5, tf6;
	Box baseBox, bv1, bv2;

	public StudentDelete() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/DataBase2?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull",
					"root", "5094022aA");
			sql = con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		setLayout(new BorderLayout());

		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();

		tf1 = new JTextField(16);

		b1 = new JButton("Delete");
		b2 = new JButton("Reset");

		b1.addActionListener(this);
		b2.addActionListener(this);

		b1.setBackground(new Color(57, 228, 44));
		b2.setBackground(new Color(57, 228, 44));

		p1.add(b1);
		p1.add(b2);

		bv1 = Box.createVerticalBox();

		bv1.add(new JLabel("ID"));
		bv1.add(Box.createVerticalStrut(8));

		bv2 = Box.createVerticalBox();

		bv2.add(tf1);
		bv2.add(Box.createVerticalStrut(8));

		baseBox = Box.createHorizontalBox();

		baseBox.add(bv1);
		baseBox.add(Box.createHorizontalStrut(10));
		baseBox.add(bv2);

		p2.add(baseBox);
		add(p1, "South");
		add(p2, "Center");
		setSize(350, 300);
		setBackground(new Color(57, 228, 44));
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == b1) {
			try {
				delete();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} else if (e.getSource() == b2) {
			tf1.setText("");
		}
	}

	public void delete() throws SQLException {
		String s1 = "'" + tf1.getText().trim() + "'";
		String num;
		num = tf1.getText().trim();
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/DataBase2", "root", "5094022aA");

		String temp = "DELETE FROM Student WHERE Id = " + s1;
		int flag = sql.executeUpdate(temp);
		if (flag == 0) {
			JOptionPane.showMessageDialog(this, "No such a stutent!", "i", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(this, "Data Deleted!", "i", JOptionPane.INFORMATION_MESSAGE);
		}
		con.close();
	}
}
