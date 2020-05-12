package wetalk.client;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AboutMy extends JDialog{
	JPanel jp=new JPanel();
	JLabel jl=new JLabel("We_Talk Bate");
	JLabel jimage=new JLabel(new ImageIcon("src/file/about.jpg"));
	JLabel j1=new JLabel("If you have questions about this We_Talk software");
	JLabel j2=new JLabel("Please contact aidenmei@gmail.com");
	JLabel j3=new JLabel("service department¡£");
	JLabel j4=new JLabel("-----------------------------------------");
	JLabel j5=new JLabel("Thank you for using We_Talk software");
	JLabel j6=new JLabel("See you next time");
	JLabel j7=new JLabel("You guess");
	JButton jb=new JButton("Yes");
public AboutMy(
		Frame info, String title, boolean b) {

	super(info,title,b);
	this.setSize(465, 550);
	this.setLocationRelativeTo(null);
	this.setLayout(null);
	this.add(jp);
	init();
}
public void init()
{
	jp.setSize(465, 550);
	jp.setLayout(null);
	jp.setBackground(Color.WHITE);
	jimage.setBounds(10,20, 100, 100);
	jp.add(jimage);
	jl.setFont(new Font("Times New Roman",Font.BOLD,38));
	jl.setBounds(165, 55, 300, 40);
	jp.add(jl);
	j1.setFont(new Font("Times New Roman",Font.PLAIN,18));
	j1.setBounds(80,140, 380, 25);
	jp.add(j1);
	j2.setFont(new Font("Times New Roman",Font.PLAIN,18));
	j2.setBounds(44, 190, 380, 25);
	jp.add(j2);
	j3.setFont(new Font("Times New Roman",Font.PLAIN,18));
	j3.setBounds(44, 240, 380, 25);
	jp.add(j3);
	j4.setFont(new Font("Times New Roman",Font.PLAIN,18));
	j4.setBounds(44, 290, 380, 25);
	jp.add(j4);
	j5.setFont(new Font("Times New Roman",Font.PLAIN,18));
	j5.setBounds(80, 330, 380, 25);
	jp.add(j5);
	j6.setFont(new Font("Times New Roman",Font.PLAIN,18));
	j6.setBounds(44, 380, 380, 25);
	jp.add(j6);
	j7.setFont(new Font("Times New Roman",Font.PLAIN,18));
	j7.setBounds(220, 430, 100, 25);
	jp.add(j7);
	jb.setBounds(345, 480, 70, 25);
	jb.setFont(new Font("Times New Roman",Font.PLAIN,14));
	jb.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent arg0) {
			AboutMy.this.setVisible(false);
		}
		
	});
	jp.add(jb);
}
}
