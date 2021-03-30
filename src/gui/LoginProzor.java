package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class LoginProzor{
    
	public static void placeComponents(JPanel panel) {

		panel.setLayout(null);

		JLabel userLabel = new JLabel("Korisnicko ime");
		userLabel.setBounds(70, 25, 120, 25);
		panel.add(userLabel);

		JTextField userText = new JTextField(20);
		userText.setBounds(180, 25, 160, 25);
		panel.add(userText);

		JLabel passwordLabel = new JLabel("Lozinka");
		passwordLabel.setBounds(70, 65, 80, 25);
		panel.add(passwordLabel);

		JPasswordField passwordText = new JPasswordField(20);
		passwordText.setBounds(180, 65, 160, 25);
		panel.add(passwordText);

		JButton loginButton = new JButton("Prijava");
		loginButton.setBounds(65, 120, 90, 35);
		panel.add(loginButton);
		
		JButton exitButton = new JButton("Otkazi");
		exitButton.setBounds(250, 120, 90, 35);
		panel.add(exitButton);
		
	    loginButton.addActionListener(new ActionListener()
	    {
	      public void actionPerformed(ActionEvent e)
	      {
	    	  String userNameInput = userText.getText().trim();
	    	  String passwordInput = new String(passwordText.getPassword()).trim();
//	    	     try {
//	    	            Scanner in = new Scanner(inputFile);
//	    	            while (in.hasNextLine())
//	    	            {
//	    	              String s = in.nextLine();  
//	    	              String[] sArray = s.split(",");
//	    	              
//	    	              if (userNameInput == sArray[0] && passwordInput == sArray[1])
//	    	              {
//	    	                JOptionPane.showMessageDialog(null,"Login Successful", "Success", JOptionPane.INFORMATION_MESSAGE);
//	    	              }
//	    	              else
//	    	              {
//	    	                JOptionPane.showMessageDialog(null, "Invalid Username / Password Combo", "Error",JOptionPane.ERROR_MESSAGE);
//	    	              }
//	    	            }
//	    	            in.close();
//	    	            
//	    	        } catch (FileNotFoundException e1) {
//	    	            JOptionPane.showMessageDialog(null, "User Database Not Found", "Error", JOptionPane.ERROR_MESSAGE);
//	    	        }
	      }
	    });	

	    exitButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	            JOptionPane.showMessageDialog(loginButton, "Uspesno ste izasli iz aplikacije!", null, JOptionPane.WARNING_MESSAGE);
	    		System.exit(0);
	    	}
	    });
	    
	}

}
