package com.main;
import java.awt.Button;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
public class Calculator extends WindowAdapter implements ActionListener {
	 private double result = 0, data1 = 0, radixPointDepth = 1;
	 private boolean radixPointIndicate = false, resultIndicate = false;
	 private char prec = '+';
	 private Frame f;//整个框架
	 private TextField tf;//文本域
	 private Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14,
	   b15, b16, b17;//一些按钮
	 private Panel p;

	 static public void main(String args[]) {
	  Calculator de = new Calculator();
	  de.go();
	 }

	 public void go() {
	  f = new Frame("计算器");
	  p = new Panel();
	  p.setLayout(new GridLayout(4, 4));//布局
	  tf = new TextField(30);//30个字符
	  b1 = new Button("7");//添加按钮，下同
	  b2 = new Button("8");
	  b3 = new Button("9");
	  b4 = new Button("+");
	  b5 = new Button("4");
	  b6 = new Button("5");
	  b7 = new Button("6");
	  b8 = new Button("-");
	  b9 = new Button("1");
	  b10 = new Button("2");
	  b11 = new Button("3");
	  b12 = new Button("*");
	  b13 = new Button("0");
	  b14 = new Button(".");
	  b15 = new Button("=");
	  b16 = new Button("/");
	  b17 = new Button("清零");
	  f.add(tf, "North");
	  f.add(p, "Center");
	  f.add(b17, "South");
	  p.add(b1);
	  p.add(b2);
	  p.add(b3);
	  p.add(b4);
	  p.add(b5);
	  p.add(b6);
	  p.add(b7);
	  p.add(b8);
	  p.add(b9);
	  p.add(b10);
	  p.add(b11);
	  p.add(b12);
	  p.add(b13);
	  p.add(b14);
	  p.add(b15);
	  p.add(b16);
	  b1.addActionListener(this);//添加事件，下同
	  b2.addActionListener(this);
	  b3.addActionListener(this);
	  b4.addActionListener(this);
	  b5.addActionListener(this);
	  b6.addActionListener(this);
	  b7.addActionListener(this);
	  b8.addActionListener(this);
	  b9.addActionListener(this);
	  b10.addActionListener(this);
	  b11.addActionListener(this);
	  b12.addActionListener(this);
	  b13.addActionListener(this);
	  b14.addActionListener(this);
	  b15.addActionListener(this);
	  b16.addActionListener(this);
	  b17.addActionListener(this);
	  f.addWindowListener(this);
	  f.setSize(250, 190);
	  f.setVisible(true);
	 }

	 public void actionPerformed(ActionEvent e) {//事件触发
	  String s;
	  s = e.getActionCommand();
	  switch (s.charAt(0)) {//得到字符
	  case '0'://如果是数字
	  case '1':
	  case '2':
	  case '3':
	  case '4':
	  case '5':
	  case '6':
	  case '7':
	  case '8':
	  case '9':
	   if (resultIndicate) {
	    result = 0;
	    data1 = 0;
	    prec = '+';
	   }
	   Integer Int1 = new Integer(s);
	   if (radixPointIndicate) {
	    radixPointDepth = radixPointDepth / 10;
	    data1 = data1 + (Int1.intValue()) * radixPointDepth;
	   } else {
	    data1 = data1 * 10 + (Int1.intValue());
	   }
	   Double displayNumber = new Double(data1);
	   tf.setText(displayNumber.toString());
	   resultIndicate = false;
	   break;
	  case '+'://如果是符号
	  case '-':
	  case '*':
	  case '/':
	  case '=':
	   if (s.charAt(0) != '=' && resultIndicate) {
	    prec = s.charAt(0);
	    resultIndicate = false;
	   } else {
	    switch (prec) {
	    case '+':
	     result = result + data1;
	     break;
	    case '-':
	     result = result - data1;
	     break;
	    case '*':
	     result = result * data1;
	     break;
	    case '/':
	     result = result / data1;
	     break;
	    }
	   }
	   radixPointIndicate = false;
	   radixPointDepth = 1;
	   displayNumber = new Double(result);
	   tf.setText(displayNumber.toString());
	   if (s.charAt(0) != '=') {
	    data1 = 0;
	    prec = s.charAt(0);
	   } else {
	    resultIndicate = true;
	   }
	   break;
	  case '.':
	   radixPointIndicate = true;
	   break;
	  }
	  if (s.equals("清零")) {//如果是清零
	   result = 0;
	   data1 = 0;
	   radixPointDepth = 1;
	   tf.setText("");
	  }
	 }

	 public void windowClosing(WindowEvent e) {
	  System.exit(0);
	 }
}
