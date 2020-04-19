package cn.itbaizhan.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.JTextArea;

/*Server connection thread*/
public class ServerThread extends Thread{
	JTextArea jTServerLog=null;
	Boolean flag=true;
	String line_separator=System.getProperty("line.separator");
	ServerSocket server;
	Vector clients=new Vector();
	/*-----------------------------------------------------------*/
	public ServerThread(JTextArea jTServerLog) {
		this.jTServerLog=jTServerLog;
	}
//Resume service
	public void reStartThread() {
		this.flag=true;	
	}
//service paused
	public void pauseThread() {
		this.flag=false;
	}
	//The main method in the thread
	public void  run()
	{
		try {
			 server=new ServerSocket(6544);
			jTServerLog.append("Chat server system starts， ， ， ， ， "+line_separator);
			jTServerLog.append(line_separator);
		} catch (IOException e) {
			e.printStackTrace();
			jTServerLog.append("Server port open error， ， ， ， ， ，"+line_separator);
			jTServerLog.append(line_separator);
		}
		if(server!=null)
		{
		while(flag)
		{
			try {
				System.out.println("server��"+flag);
				Socket socket=server.accept();
				jTServerLog.append("****************************"+line_separator);
				jTServerLog.append("Connection accept : "+socket+line_separator);
				Date time=new java.util.Date();
				SimpleDateFormat format=new SimpleDateFormat("yyy-MM-dd kk:mm:ss");
				String timeInfo=format.format(time);
				jTServerLog.append("Processing time : "+timeInfo+line_separator);
				jTServerLog.append("****************************"+line_separator);
//				Server c=new Server(socket,clients);
//				Server c=new Server(socket,ServerThread.this);
//				clients.addElement(c);
				//new Thread(c).start();
			//	clients.addElement(socket);
				new Thread(new Server(socket)).start();
				
			} catch (IOException e) {
				e.printStackTrace();
				jTServerLog.append("Client connection failed， ， ， ， ， ，"+line_separator);
				jTServerLog.append(line_separator);
			}
		}
	}
	}
}
