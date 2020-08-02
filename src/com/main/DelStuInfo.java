package com.main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.bean.Student;
import com.dao.StudentDao;
import com.db.DB;
import com.main.AddStudent.BackAction;

//删除学生信息

public class DelStuInfo extends JFrame{
	StudentDao studao = new StudentDao();
	DB db = new DB();
	Connection conn = db.getConnection();
	JComboBox snocom = null;
	public void init() {
		// 窗体设计
		this.setLayout(null);
		this.setTitle("学生信息管理系统");
		this.setSize(500, 400);
		JLabel title = new JLabel("学生信息维护模块---删除数据");
		title.setBounds(180, 20, 200, 30);
		this.add(title);
		// 选择学号
		JLabel snoLabel = new JLabel("请选择要删除学生信息的学号");
		snoLabel.setBounds(20, 60, 200,25);
		this.add(snoLabel);
		try {
			Vector vec = studao.getSno();
			snocom = new JComboBox(vec); // 将数据库里的学号信息装入下拉框中
			snocom.setBounds(30, 90, 150, 25);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.add(snocom);
		// 删除数据
		JButton delbtn = new JButton("删除");
		delbtn.setBounds(100, 180, 60, 30);
		delbtn.addActionListener(new delAction());
		this.add(delbtn);
		// 返回
		JButton backbtn = new JButton("返回");
		backbtn.setBounds(180, 180, 60, 30);
		backbtn.addActionListener(new BackAction ());
		this.add(backbtn);
				this.setLocationRelativeTo(null); // 使窗体居中
		this.setResizable(false);// 固定窗体大小
		this.setVisible(true); // 设置窗体可见
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 窗体关闭时，程序结束
	}
	public static void main(String[] args) {
		DelteStudent delstu = new DelteStudent();
		delstu.init();

	}
	// 删除的监听器
	class delAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String delsno = (String) snocom.getSelectedItem();
			boolean b = studao.delete(delsno, conn);
			if (b) {
				JOptionPane.showMessageDialog(null, "删除录入成功！");
			} else {
				JOptionPane.showMessageDialog(null, "删除录入失败！");
			}
		}
	}
	class BackAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			 MainFrame  m=new  MainFrame ();
			 m.init();
			
		}
		
	}

}
