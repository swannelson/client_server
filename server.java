import java.awt.EventQueue;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.awt.Color;



public class server {

	private JFrame frmServer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					server window = new server();
					window.frmServer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public server() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmServer = new JFrame();
		frmServer.getContentPane().setBackground(Color.BLACK);
		frmServer.setTitle("Server");
		frmServer.setBounds(100, 100, 450, 300);
		frmServer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmServer.getContentPane().setLayout(null);
		
		JLabel num1 = new JLabel("");
		num1.setForeground(Color.WHITE);
		num1.setBounds(10, 30, 379, 13);
		frmServer.getContentPane().add(num1);
		
		JTextArea num2 = new JTextArea("");
		num2.setForeground(Color.WHITE);
		num2.setBackground(Color.BLACK);
		num2.setLineWrap(true);
		num2.setWrapStyleWord(true);
		num2.setBounds(10, 53, 379, 200);
		frmServer.getContentPane().add(num2);
		
		 
		      try {
		       
		        ServerSocket serverSocket = new ServerSocket(8000);
		
		          num1.setText("Server started at " + new Date() + '\n');
		  
		      
		        Socket socket = serverSocket.accept();
		  
		       
		        DataInputStream inputFromClient = new DataInputStream(
		          socket.getInputStream());
		        DataOutputStream outputToClient = new DataOutputStream(
		          socket.getOutputStream());
		  
		        while (true) {
		         
		          int number = inputFromClient.readInt();
		  
		     
		         int isPrime = 1;
		          for (int i = 2; i <= number / 2; ++i) {
		          
		            if (number % i == 0) {
		              isPrime = 0;
		              break;
		            }
		          }

		         
		          if(isPrime == 1)
		          {
		      
		          outputToClient.writeInt(1);
		          }
		          else
		          {
		        	  outputToClient.writeInt(0);
		          }
		         
		            num2.setText(num2.getText() + '\n' + "Number received from client: " 
		              + number + '\n');
		       
		         
		        }
		      }
		      catch(IOException ex) {
		        ex.printStackTrace();
		      }
		    

	}}