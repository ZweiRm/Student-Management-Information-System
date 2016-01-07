package com.jdbcTest;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class StudentQuery extends JPanel implements ActionListener {

	Connection con;
	Statement sql;
	JTextField t1, t2, t3, t4, t5, t6;
	JButton but;
	Box baseBox, bv1, bv1h, bv2, bv2h, bv3, bv3h, bv4, bv4h, bv5, bv5h, bv6, bv6h;
	int flag = 0;

	StudentQuery() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
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

		but = new JButton("Inquiry");
		but.setBackground(Color.ORANGE);
		but.addActionListener(this);

		t1 = new JTextField(8);
		t2 = new JTextField(16);
		t3 = new JTextField(16);
		t4 = new JTextField(16);
		t5 = new JTextField(16);
		t6 = new JTextField(16);

		t2.setEditable(false);
		t3.setEditable(false);
		t4.setEditable(false);
		t5.setEditable(false);
		t6.setEditable(false);

		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();

		p1.add(new JLabel("Input Student ID To Search"));
		p1.add(t1);
		p1.add(but);

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
		bv1h.add(t1);

		bv2.add(bv2h);
		bv2h.add(new JLabel("Name"));
		bv2h.add(Box.createHorizontalStrut(25));
		bv2h.add(t2);

		bv3.add(bv3h);
		bv3h.add(new JLabel("Gender"));
		bv3h.add(Box.createHorizontalStrut(17));
		bv3h.add(t3);

		bv4.add(bv4h);
		bv4h.add(new JLabel("Address"));
		bv4h.add(Box.createHorizontalStrut(10));
		bv4h.add(t4);

		bv5.add(bv5h);
		bv5h.add(new JLabel("Tel"));
		bv5h.add(Box.createHorizontalStrut(42));
		bv5h.add(t5);

		bv6.add(bv6h);
		bv6h.add(new JLabel("Dept"));
		bv6h.add(Box.createHorizontalStrut(31));
		bv6h.add(t6);

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
		add(p1, "North");
		add(p2, "Center");
		setSize(350, 300);
		setBackground(Color.WHITE);
	}

	public void actionPerformed(ActionEvent e) {
		flag = 0;
		try {
			query();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	public void query() throws SQLException {
		String num, name, gender, address, tel, dept;
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/DataBase2", "root", "5094022aA");
		num = t1.getText().trim();
		ResultSet rs = sql.executeQuery("SELECT * FROM Student WHERE id = '" + num + "'");

		if (rs.next()) {
			name = rs.getString("name");
			gender = rs.getString("gender");
			address = rs.getString("address");
			tel = rs.getString("phone");
			dept = rs.getString("major");

			t2.setText(name);
			t3.setText(gender);
			t4.setText(address);
			t5.setText(tel);
			t6.setText(dept);
			flag = 1;
		} else {
			JOptionPane.showMessageDialog(this, "No such a stutent!", "i", JOptionPane.INFORMATION_MESSAGE);
		}
		con.close();
		if (flag == 0) {
			t1.setText("No such a student!");
		}
	}

}
