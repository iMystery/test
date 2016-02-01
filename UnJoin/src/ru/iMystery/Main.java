package ru.iMystery;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Main extends JFrame{

	boolean groupPrivate;
	
	public static void main(String[] args)
	{
		new Main("Вк помощник");
	}
	
	public Main(String name)
	{
		super(name);
		setLayout(new FlowLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(300,270);
		
		JLabel l1, l2;
		final JLabel errors;
		JButton b1;
		final JTextField text1, text2;
		final JCheckBox box;
		
		l1 = new JLabel("С каких групп не выходить? - ");
		l2 = new JLabel("Токен - ");
		errors = new JLabel("");
		b1 = new JButton("Ок, погнали!");
		text1 = new JTextField(10);
		text2 = new JTextField(20);
		box = new JCheckBox("Выходить с закрытых групп?              ");
		
		add(errors); 
		add(l1);
		add(text1);
		add(box);
		box.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if(box.isSelected() == true)//check exit private group - yes / no
				{
					groupPrivate = true;
				}else{
					groupPrivate = false;
				}
			}
			
		});
		
		add(l2);
		add(text2);
		add(b1); 
		b1.addActionListener(new ActionListener(){
			//click to button, if token != null - join class UnJoingroup
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(text2.getText().equals(""))
				{
					errors.setText("Заполните поле \"Токен\"!!");
					return;
				}else{
					new Thread(new UnJoinGroup(text2.getText()));//open the window in a new thread
				}
			}
			
		});
		
		setVisible(true);
	}
}
