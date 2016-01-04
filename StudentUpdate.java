package com.jdbcTest;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;

public class StudentUpdate extends JPanel implements ActionListener {

	Connection con;
	Statement sql;
	JButton b1, b2;
	JTextField tfi1, tfi2, tfi3, tfi4, tfi5, tfi6;
	Box baseBox, bv1, bv1h, bv2, bv2h, bv3, bv3h, bv4, bv4h, bv5, bv5h, bv6, bv6h;

	StudentUpdate() {
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

		tfi1 = new JTextField(16);
		tfi2 = new JTextField(16);
		tfi3 = new JTextField(16);
		tfi4 = new JTextField(16);
		tfi5 = new JTextField(16);
		tfi6 = new JTextField(16);

		b1 = new JButton("Update");
		b2 = new JButton("Reset");

		b1.addActionListener(this);
		b2.addActionListener(this);

		b1.setBackground(new Color(57, 228, 44));
		b2.setBackground(new Color(57, 228, 44));

		p1.add(b1);
		p1.add(b2);

		bv1 = Box.createHorizontalBox();
		bv2 = Box.createHorizontalBox();
		bv3 = Box.createHorizontalBox();
		bv4 = Box.createHorizontalBox();
		bv5 = Box.createHorizontalBox();
		bv6 = Box.createHorizontalBox();

		bv1h = Box.createHorizontalBox();
		bv2h = Box.createHorizontalBox();
		bv3h = Box.createHorizontalBox();
		bv4h = Box.createHorizontalBox();
		bv5h = Box.createHorizontalBox();
		bv6h = Box.createHorizontalBox();

		bv1.add(bv1h);
		bv1h.add(new JLabel("ID"));
		bv1h.add(Box.createHorizontalStrut(47));
		bv1h.add(tfi1);

		bv2.add(bv2h);
		bv2h.add(new JLabel("Name"));
		bv2h.add(Box.createHorizontalStrut(25));
		bv2h.add(tfi2);

		bv3.add(bv3h);
		bv3h.add(new JLabel("Gender"));
		bv3h.add(Box.createHorizontalStrut(17));
		bv3h.add(tfi3);

		bv4.add(bv4h);
		bv4h.add(new JLabel("Address"));
		bv4h.add(Box.createHorizontalStrut(10));
		bv4h.add(tfi4);

		bv5.add(bv5h);
		bv5h.add(new JLabel("Tel"));
		bv5h.add(Box.createHorizontalStrut(42));
		bv5h.add(tfi5);

		bv6.add(bv6h);
		bv6h.add(new JLabel("Dept"));
		bv6h.add(Box.createHorizontalStrut(31));
		bv6h.add(tfi6);

		baseBox = Box.createVerticalBox();

		baseBox.add(bv1);
		baseBox.add(Box.createVerticalStrut(10));
		baseBox.add(bv2);
		baseBox.add(Box.createVerticalStrut(10));
		baseBox.add(bv3);
		baseBox.add(Box.createVerticalStrut(10));
		baseBox.add(bv4);
		baseBox.add(Box.createVerticalStrut(10));
		baseBox.add(bv5);
		baseBox.add(Box.createVerticalStrut(10));
		baseBox.add(bv6);
		baseBox.add(Box.createVerticalStrut(10));

		p2.add(baseBox);
		add(p1, "South");
		add(p2, "Center");
		setSize(350, 300);
		setBackground(new Color(57, 228, 44));
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == b1) {
			try {
				update();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} else if (e.getSource() == b2) {
			tfi1.setText("");
			tfi2.setText("");
			tfi3.setText("");
			tfi4.setText("");
			tfi5.setText("");
			tfi6.setText("");
		}
	}

	public void update() throws SQLException {
		String s1 = "'" + tfi1.getText().trim() + "'";
		String s2 = "'" + tfi2.getText().trim() + "'";
		String s3 = "'" + tfi3.getText().trim() + "'";
		String s4 = "'" + tfi4.getText().trim() + "'";
		String s5 = "'" + tfi5.getText().trim() + "'";
		String s6 = "'" + tfi6.getText().trim() + "'";

		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/DataBase2", "root", "5094022aA");

		String temp1 = "UPDATE Student SET Name = " + s2 + " WHERE Id LIKE " + s1;
		String temp2 = "UPDATE Student SET Gender = " + s3 + " WHERE Id LIKE " + s1;
		String temp3 = "UPDATE Student SET Address = " + s4 + " WHERE Id LIKE " + s1;
		String temp4 = "UPDATE Student SET Phone = " + s5 + " WHERE Id LIKE " + s1;
		String temp5 = "UPDATE Student SET Major = " + s6 + " WHERE Id LIKE " + s1;

		int flag1 = sql.executeUpdate(temp1);
		int flag2 = sql.executeUpdate(temp2);
		int flag3 = sql.executeUpdate(temp3);
		int flag4 = sql.executeUpdate(temp4);
		int flag5 = sql.executeUpdate(temp5);

		if (flag1 == 0 && flag2 == 0 && flag3 == 0 && flag4 == 0 && flag5 == 0) {
			JOptionPane.showMessageDialog(this, "No such a stutent!", "i", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(this, "Data Updated!", "i", JOptionPane.INFORMATION_MESSAGE);
			tfi1.setText("");
			tfi2.setText("");
			tfi3.setText("");
			tfi4.setText("");
			tfi5.setText("");
			tfi6.setText("");
		}

		con.close();
	}
}
