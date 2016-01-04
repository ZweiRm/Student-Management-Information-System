package com.jdbcTest;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;

public class StudentAdd extends JPanel implements ActionListener {

	Connection con;
	Statement sql;
	JButton b1, b2;
	JTextField tf1, tf2, tf3, tf4, tf5, tf6;
	Box baseBox, bv1, bv1h, bv2, bv2h, bv3, bv3h, bv4, bv4h, bv5, bv5h, bv6, bv6h;
	int flag;

	StudentAdd() {
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
		tf2 = new JTextField(16);
		tf3 = new JTextField(16);
		tf4 = new JTextField(16);
		tf5 = new JTextField(16);
		tf6 = new JTextField(16);

		b1 = new JButton("Insert");
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
		bv1h.add(tf1);

		bv2.add(bv2h);
		bv2h.add(new JLabel("Name"));
		bv2h.add(Box.createHorizontalStrut(25));
		bv2h.add(tf2);

		bv3.add(bv3h);
		bv3h.add(new JLabel("Gender"));
		bv3h.add(Box.createHorizontalStrut(17));
		bv3h.add(tf3);

		bv4.add(bv4h);
		bv4h.add(new JLabel("Address"));
		bv4h.add(Box.createHorizontalStrut(10));
		bv4h.add(tf4);

		bv5.add(bv5h);
		bv5h.add(new JLabel("Tel"));
		bv5h.add(Box.createHorizontalStrut(42));
		bv5h.add(tf5);

		bv6.add(bv6h);
		bv6h.add(new JLabel("Dept"));
		bv6h.add(Box.createHorizontalStrut(31));
		bv6h.add(tf6);

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
				insert();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} else if (e.getSource() == b2) {
			tf1.setText("");
			tf2.setText("");
			tf3.setText("");
			tf4.setText("");
			tf5.setText("");
			tf6.setText("");
		}
	}

	public void insert() throws SQLException {
		String s1 = "'" + tf1.getText().trim() + "'";
		String s2 = "'" + tf2.getText().trim() + "'";
		String s3 = "'" + tf3.getText().trim() + "'";
		String s4 = "'" + tf4.getText().trim() + "'";
		String s5 = "'" + tf5.getText().trim() + "'";
		String s6 = "'" + tf6.getText().trim() + "'";

		String num;
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/DataBase1", "root", "5094022aA");
		num = tf1.getText().trim();
		ResultSet rs = sql.executeQuery("SELECT * FROM Student WHERE id = '" + num + "'");

		if (!rs.next()) {
			String temp = "INSERT INTO Student VALUES(" + s1 + "," + s2 + "," + s3 + "," + s4 + "," + s5 + "," + s6
					+ ")";
			sql.executeUpdate(temp);
			JOptionPane.showMessageDialog(this, "Data Added!", "i", JOptionPane.INFORMATION_MESSAGE);
			flag = 1;
			tf1.setText("");
			tf2.setText("");
			tf3.setText("");
			tf4.setText("");
			tf5.setText("");
			tf6.setText("");
		} else {
			JOptionPane.showMessageDialog(this, "Stutent is exist!", "i", JOptionPane.INFORMATION_MESSAGE);
		}
		con.close();
		if (flag == 0) {
			tf1.setText("");
		}
	}
}
