import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class client {
	DataOutputStream to = null;
	DataInputStream from = null;
	private JFrame frmClient;
	private JTextField input;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					client window = new client();
					window.frmClient.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public client() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmClient = new JFrame();
		frmClient.getContentPane().setBackground(Color.BLACK);
		frmClient.setTitle("Client");
		frmClient.setBounds(100, 100, 478, 300);
		frmClient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmClient.getContentPane().setLayout(null);
		
		input = new JTextField();
		input.setText("0");
		input.setBounds(301, 16, 96, 19);
		frmClient.getContentPane().add(input);
		input.setColumns(10);
		
		
		
		JLabel getUser = new JLabel("Please enter a number to check:");
		getUser.setForeground(Color.WHITE);
		getUser.setFont(new Font("Times New Roman", Font.BOLD, 20));
		getUser.setBounds(10, 14, 310, 19);
		frmClient.getContentPane().add(getUser);
		
		JTextArea results = new JTextArea("");
		results.setBackground(Color.BLACK);
		results.setLineWrap(true);
		results.setWrapStyleWord(true);
		results.setForeground(Color.WHITE);
		results.setBounds(10, 46, 387, 200);
		frmClient.getContentPane().add(results);
	
		JButton go = new JButton("Enter");
		go.addActionListener(new ActionListener() {
			@SuppressWarnings("null")
			public void actionPerformed(ActionEvent e) {
				
				int num = Integer.parseInt(input.getText());
				
				 try {
					to.writeInt(num);
					to.flush();
					
					int prime = from.readInt();
					
					if(prime == 1)
					{
						 results.setText(results.getText() + '\n' + "You have entered: " + Integer.toString(num) + ". " + num + " is a prime number!");
					}
					if(prime == 0)
					{
						results.setText(results.getText() + '\n' + "You have entered: " + Integer.toString(num) + ". " + num + " is not a prime number!");
					}
				
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		try {
			Socket socket = new Socket("localhost", 8000);
			from = new DataInputStream(socket.getInputStream());
			to = new DataOutputStream(socket.getOutputStream());
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		go.setFont(new Font("Tahoma", Font.PLAIN, 8));
		go.setBounds(407, 16, 47, 19);
		frmClient.getContentPane().add(go);
		
		
	}
}