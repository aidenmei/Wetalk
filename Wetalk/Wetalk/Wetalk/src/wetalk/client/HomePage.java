package wetalk.client;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.InetAddress;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class HomePage extends JFrame{
	JLabel jLtitle=new JLabel(new ImageIcon("src/file/title.jpg"));
	JLabel jLhead=new JLabel(new ImageIcon("src/file/1-1.jpg"));
	
	
	JTextField jTusernumber=new JTextField();
	JPasswordField jPassword=new JPasswordField();
	JCheckBox rememberPass=new JCheckBox("Remember password");
	JCheckBox Autologon =new JCheckBox("auto login",true);
	JButton jBmore=new JButton(">Multiple accounts");
	JButton jBset=new JButton("Setting");
	JButton jBenter=new JButton("Login");
	Register register;
	PersonelView personelView;
	HomePage h;
	Boolean pass=true;
	HomePage enter;
	public HomePage()
	{
		this.setSize(380, 290);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setTitle("We_Talk");
		this.add(jLtitle);
		this.add(jLhead);
		this.add(jTusernumber);
		this.add(jPassword);
		this.add(Autologon);
		this.add(rememberPass);
		this.add(jBmore);
		this.add(jBset);
		this.add(jBenter);
		init();
	}
	public void loginSuccess()
	{
		pass=true;
	}
	public void loginFail()
	{
		pass=false;
	}
	public void init()
	{
		jLtitle.setBounds(0 ,0, 380, 105);
		jTusernumber.setBounds(120, 110, 160, 28);
		jPassword.setBounds(120, 145, 160, 28);
		rememberPass.setFont(new Font("Times New Roman",Font.PLAIN,10));
		rememberPass.setForeground(Color.BLACK);
		rememberPass.setBounds(120, 175, 150, 25);
		Autologon.setFont(new Font("Times New Roman",Font.PLAIN,10));
		Autologon.setForeground(Color.BLACK);
		Autologon.setBounds(230, 175, 140, 25);
		jLhead.setBounds(20, 105, 87, 90);
		jBmore.setFont(new Font("Times New Roman",Font.PLAIN,10));
		jBmore.setForeground(Color.BLACK);
		jBmore.setBounds(10, 225, 140, 23);
		jBset.setFont(new Font("Times New Roman",Font.PLAIN,10));
		jBset.setBounds(95, 225, 55, 23);
		jBenter.setFont(new Font("Times New Roman",Font.PLAIN,10));
		jBenter.setBounds(300, 225, 100, 23);
		jBenter.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				String userName=jTusernumber.getText().trim();
				String userPass=String.valueOf(jPassword.getPassword()).trim();
				InetAddress ip = null;
				int port = 0;
				if(userName.equals(""))
				{
					JOptionPane.showMessageDialog(jBenter, "Please enter your account number and login");
					jTusernumber.requestFocus();
				}
				else if(userPass.equals(""))
				{
					JOptionPane.showMessageDialog(jBenter, "Please enter your password and login");
					jPassword.requestFocus();
				}
				else
				{
					HomePage.this.setVisible(false);
						try {
						ip=InetAddress.getByName("192.168.0.136");
						port=(6544);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						personelView= new PersonelView(userName,userPass,HomePage.this,ip,port);
					} 
				if(!pass)
				{
					personelView.setVisible(false);
					JOptionPane.showMessageDialog(null, "The account and password you entered do not match, please check");
					if(enter==null)
					{
					enter=new HomePage();
					}
				//enter.pack();
				enter.setVisible(true);
				}
			}
			
			
		});
		class JlRegist extends JLabel
		{
			private boolean isSupported;
			private String regist;
			
			public JlRegist(String regist) {
				this.regist=regist;
			 try {
			      this.isSupported = Desktop.isDesktopSupported()
			        && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE);
			     } catch (Exception e) {
			      this.isSupported = false;
			     }
			     setText(false);
			     addMouseListener(new MouseAdapter() {
			      public void mouseEntered(MouseEvent e) {
			       setText(isSupported);
			       if (isSupported)
			        setCursor(new Cursor(Cursor.HAND_CURSOR));
			      }
			     
			public void mouseExited(MouseEvent e) {
			       setText(false); 
			      }
			      public void mouseClicked(MouseEvent e) {
			    	 
			    	  if(register==null)
			    	  {
			    		  register=new Register();
			    		  register.setVisible(true);
							HomePage.this.setVisible(false);
			    	  }
			      }
			     });
			}
			private void setText(boolean b) {
			     if (!b)
			      setText( regist);
			     else
			      setText("<html><font color=blue><u>" + regist);
			  }
			}
	     JlRegist jLRegist=new JlRegist("Register an account");
			jLRegist.setFont(new Font("Times New Roman",Font.PLAIN,13));
			jLRegist.setForeground(Color.BLUE);
			jLRegist.setBounds(283, 110, 250, 28);
			this.add(jLRegist);
			class JlFindPass extends JLabel
			{
				private boolean isSupported;
				private String findPass;
				
				public JlFindPass(String findPass) {
 					this.findPass=findPass;
				 try {
				      this.isSupported = Desktop.isDesktopSupported()
				        && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE);
				     } catch (Exception e) {
				      this.isSupported = false;
				     }
				     setText(false);
				     addMouseListener(new MouseAdapter() {
				      public void mouseEntered(MouseEvent e) {
				       setText(isSupported);
				       if (isSupported)
				        setCursor(new Cursor(Cursor.HAND_CURSOR));
				      }
				     
				public void mouseExited(MouseEvent e) {
				       setText(false);
				      }
				      public void mouseClicked(MouseEvent e) {
				    	 
				    	   try {
						        Desktop.getDesktop().browse(
						          new java.net.URI("https://aq.qq.com/cn2/findpsw/pc/pc_find_pwd_input_account?source_id=1003&ptlang=2052&aquin=593253716"));	
						       } catch (Exception ex) {
						       }
				      }
				     });
				}
				private void setText(boolean b) {
				     if (!b)
				      setText( findPass);
				     else
				      setText("<html><font color=blue><u>" + findPass);
				  }
			}
			JlFindPass jLfindPass=new JlFindPass("Retrieve password");
			jLfindPass.setFont(new Font("Times New Roman",Font.PLAIN,13));
			jLfindPass.setForeground(Color.BLUE);
			jLfindPass.setBounds(283,145, 200, 28);
			this.add(jLfindPass);
		}
	
	public static void main(String args[])
	{
		HomePage enter=new HomePage();
			enter.setVisible(true);
	}

}
