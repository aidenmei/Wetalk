package wetalk.server;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import wetalk.common.ShowTimeTask;
import wetalk.common.UserBean;
import wetalk.common.UserInfo;

/*Server main interface*/
public class ServerFrame extends JFrame {
	JLabel jshowList=new JLabel("<Refresh every 10 seconds>");
	JLabel jshowServerLog=new JLabel("Server log");
	JLabel jUserCount=new JLabel("online users :");
	JLabel jCount=new JLabel("0");
	JLabel jLtime=new JLabel();
	JButton jBgetInfo=new JButton("information");
	JButton jBkickOut=new JButton("Kick out");
	JButton jBpauseServer=new JButton("service paused");
	JButton jBexit=new JButton("drop out");
	DefaultListModel listModel=new DefaultListModel();
	JList userList=new JList(listModel);
	JScrollPane jSuserList=new JScrollPane(userList);
	JTextArea jTServerLog=new JTextArea();
	JScrollPane jServerLog=new JScrollPane(jTServerLog);
	private Connection con=null;
	ServerThread serverThread=null;
	private Hashtable userTable= new Hashtable();
	public ServerFrame()
	{
		this.setSize(800, 700);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setTitle("Server-side control interface");
		con=ConnectionDao.getConnection();
		init();
		this.add(jshowList);
		this.add(jSuserList);
		this.add(jBgetInfo);
		this.add(jBkickOut);
		this.add(jUserCount);
		this.add(jCount);
		this.add(jshowServerLog);
		this.add(jServerLog);
		this.add(jBpauseServer);
		this.add(jBexit);
		this.add(jLtime);
	   serverThread=new ServerThread(jTServerLog);
		serverThread.start();
		java.util.Timer myTime=new java.util.Timer();
		java.util.TimerTask task_showtime=new ShowTimeTask(jLtime);
		myTime.schedule(task_showtime, 0,1000);
		java.util.Timer time=new java.util.Timer();
		java.util.TimerTask task_time=new LoginUser(listModel,userList,jCount,userTable,con);
		time.schedule(task_time, 0,10000);//Refresh every 10 seconds
		try {
			System.out.println(InetAddress.getLocalHost());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	//Layout server main interface
	public void init()
	{
		jshowList.setBounds(20, 10, 150, 25);
		jshowList.setFont(new Font("Times New Roman",Font.PLAIN,11));
		jSuserList.setBounds(10, 40, 190, 500);
		jBgetInfo.setBounds(20, 550, 90, 25);
		jBgetInfo.setFont(new Font("Times New Roman",Font.PLAIN,10));
		jBgetInfo.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String selectedUser=null;
				String userNum=null;
				selectedUser=(String)userList.getSelectedValue();
				if(selectedUser==null)
				{
					JOptionPane.showMessageDialog(jBgetInfo, "Please click to select a user");
				}
				else
				{
					System.out.println(selectedUser);
					userNum=selectedUser.substring(selectedUser.indexOf("<")+1,selectedUser.indexOf(">"));
					UserBean user=(UserBean)userTable.get(userNum);
					UserInfo userInfo=new UserInfo(ServerFrame.this,"Information View",true,user);
					userInfo.setVisible(true);
				}
			}
			
		});
		jBkickOut.setBounds(110, 550, 80, 25);
		jBkickOut.setFont(new Font("Times New Roman",Font.PLAIN,10));
		
		jBkickOut.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int index=userList.getSelectedIndex();
				String userNum=null;
				if(index==-1)
				{
					JOptionPane.showMessageDialog(jBkickOut, "Please click to select a user");
				}
				else{
					String userInfo=(String)listModel.getElementAt(index);
					userNum=userInfo.substring(userInfo.indexOf("<")+1,userInfo.indexOf(">"));
					System.out.println(userNum);
					removeUser(userNum);
					listModel.remove(index);
					int num=Integer.parseInt(jCount.getText())-1;
					jCount.setText(new Integer(num).toString());
				}
			}
			
		});
		jUserCount.setBounds(25, 595, 100, 30);
		jUserCount.setFont(new Font("Times New Roman",Font.PLAIN,12));
		jCount.setBounds(125, 595, 20, 30);
		jCount.setFont(new Font("Times New Roman",Font.PLAIN,12));
		jshowServerLog.setBounds(220, 10, 150, 25);
		jshowServerLog.setFont(new Font("Times New Roman",Font.PLAIN,11));
		jServerLog.setBounds(210, 40, 575, 550);
		jBpauseServer.setBounds(340, 595, 110, 25);
		jBpauseServer.setFont(new Font("Times New Roman",Font.PLAIN,11));
		jBpauseServer.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				String command=e.getActionCommand();
				if(command.equals("service paused"))
				{
					serverThread.pauseThread();
					jBpauseServer.setText("Resume service");
				}
				else if(command.equals("Resume service"))
				{
					serverThread.reStartThread();
					jBpauseServer.setText("service paused");
				}
			}
			
		});
		jBexit.setBounds(470, 595, 80, 25);
		jBexit.setFont(new Font("Times New Roman",Font.PLAIN,11));
		jBexit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int option=JOptionPane.showConfirmDialog(jBexit, "Are you sure you want to quit£¿");
				if(option==JOptionPane.YES_OPTION)
				{
					try {
						con.close();
						System.exit(0);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
			
		});
		jLtime.setBounds(600, 615, 250, 50);
		jLtime.setFont(new Font("Times New Roman",Font.PLAIN,12));
	}

//	protected void processWindowEvent(WindowEvent e)
//	{
//		if(e.getID()==WindowEvent.WINDOW_CLOSED)
//		{
//			if(option==JOptionPane.YES_OPTION)
//			{
//				try {
//					con.close();
//					System.exit(0);
//				} catch (SQLException e1) {
//					e1.printStackTrace();
//				}
//			}
//		}
//	}
	public void removeUser(String userNum)
	{
		String sql="UPDATE UserInformation SET Status = 0 where UserNum= '"+userNum+"'";
		try {
			Statement stmt=con.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String args[])
	{
		ServerFrame f=new ServerFrame();
		f.setVisible(true);
	}
}
