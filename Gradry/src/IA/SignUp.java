package IA;

import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JCheckBox;

public class SignUp extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	
	public SignUp() throws IOException {
		CSVEditor.CreateUserTable();
		
		
		setSize(1050, 700);
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    getContentPane().setLayout(null);
	    setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1050, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		textField = new JTextField();
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		
		passwordField_1 = new JPasswordField();
		
		JLabel UsernameText = new JLabel("Username");
		
		JLabel PasswordText = new JLabel("Password");
		
		JLabel PasswordTextConfirm = new JLabel("Confirm Password");
		
		JLabel WrongPassword = new JLabel("Try again");
		WrongPassword.setForeground(new Color(237, 40, 11));
		
		WrongPassword.setVisible(false);
		JCheckBox AdminCheck = new JCheckBox("Administrator?");
		
		JButton CreateAccount = new JButton("Create account");
		CreateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (!(passwordField.getText().equals(passwordField_1.getText()))) {
					
					WrongPassword.setVisible(true);
					
				}
				
				else {
					if (AdminCheck.isSelected())
					{
						//add admin account
						
						try {
							CSVEditor.addAdmin(textField.getText(), passwordField.getText());
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
					else {
						try {
							//add teacher account
							CSVEditor.addTeacher(textField.getText(), passwordField.getText());
						} catch (Exception e2) {
							e2.printStackTrace();
						}
					}
					
					
					//Close Sign Up Page
					try {
						setVisible(false);
						dispose();
					} catch (Exception e1) {
						
						e1.printStackTrace();
					}
				
					
				}
				
			}
		});
		
		
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(CreateAccount)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(UsernameText)
										.addComponent(PasswordText)
										.addComponent(PasswordTextConfirm))
									.addGap(50)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(passwordField_1, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
											.addComponent(passwordField, Alignment.LEADING)
											.addComponent(textField, Alignment.LEADING)))))
							.addGap(35)
							.addComponent(WrongPassword))
						.addComponent(AdminCheck))
					.addContainerGap(47, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(42)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(UsernameText))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(PasswordText))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(passwordField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(PasswordTextConfirm))
					.addGap(18)
					.addComponent(CreateAccount)
					.addPreferredGap(ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
					.addComponent(AdminCheck)
					.addGap(17))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(150, Short.MAX_VALUE)
					.addComponent(WrongPassword)
					.addGap(96))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
