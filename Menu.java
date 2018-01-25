package snake;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;

import javax.swing.*;

import newIde.ComboBoxExample;
//Hafeez Khan
//Jan 23, 2017
//ICS 4U1
//Mrs.Kapustina
//Class : Menu
public class Menu extends JFrame implements ActionListener {
	
	//Declaring all variables
	public static Snake snake;
	public int tail;
	public int speed;
	//variable ghost determines which ghost will come in play the vertical or the horizontal 
	public static int ghost=(int)(Math.random()*2+1);
	Container homePanel = new Container();
	Container contentPanel =  new Container();
	Container menuContainer = getContentPane();
	String [] messageStrings = {"Controls", "Instructions", "Game Over Conditions"};
	JComboBox cmbMessageList = new JComboBox (messageStrings);
	JLabel Rule1 = new JLabel("    ");
	JLabel lblText = new JLabel ("  ");
	
	//Constructor
	public Menu (){
		//Setting Screen Size
		setSize( 820,800 );
		setDefaultCloseOperation( EXIT_ON_CLOSE );
		
		//Layout manager set to null for both containers
		//homePanel is a container where the play and rule button is displayed
		homePanel.setLayout(null);
		homePanel.setBackground(Color.GREEN);
		//contentPanel is a container where the combo box button, and level of difficulty is chosen
		contentPanel.setLayout (null);
		contentPanel.setBackground(Color.GREEN);
		setVisible(true);
		setResizable(false);
		
		Rule1.setForeground(Color.black);
		
		//Setting bounds for combo box
		cmbMessageList.setBounds(10, 70, 200, 50);
		lblText.setBounds(150,10,600,100);
		
		//Initializing play button 
		JButton Play = new JButton ("Play");
		Play.setBounds (250,400,300,48);
		Play.setActionCommand("Play");
		Play.setBackground(Color.black);
		Play.setForeground(Color.white);
		Play.addActionListener(this);
		
		//Initializing rules button
		JButton Rules = new JButton ("Rules");
		Rules.setBounds(250,317,300,48);
		Rules.setActionCommand("Rules");
		Rules.setBackground(Color.black);
		Rules.setForeground(Color.white);
		Rules.addActionListener(this);
		
		//Initializing easy button in the contentPanel container
		JButton Speed1 = new JButton ("Easy");
		Speed1.setBounds(30,170,220,50);
		Speed1.setActionCommand("Easy");
		Speed1.setBackground(Color.black);
		Speed1.setForeground(Color.white);
		Speed1.addActionListener(this);
		
		//Initializing medium button in the contentPanel container
		JButton Speed2 = new JButton ("Medium");
		Speed2.setBounds(285,170,230,50);
		Speed2.setActionCommand("Medium");
		Speed2.setBackground(Color.black);
		Speed2.setForeground(Color.white);
		Speed2.addActionListener(this);
		
		//Initializing hard button in the contentPanel container
		JButton Speed3 = new JButton ("Hard");
		Speed3.setBounds(545,170,200,50);
		Speed3.setActionCommand("Hard");
		Speed3.setBackground(Color.black);
		Speed3.setForeground(Color.white);
		Speed3.addActionListener(this);
		
		//Initializing return button in the contentPanel container
		JButton returnbtn  = new JButton ("Return");
		returnbtn.setBounds(200,317,380,48);
		returnbtn.setActionCommand("Return");
		returnbtn.setBackground(Color.black);
		returnbtn.setForeground(Color.white);
		returnbtn.addActionListener(this);
	
		Rule1.setBounds(10,10,600,20);
		contentPanel.add(Rule1);
		
		//Adding buttons and combo box to containers
		homePanel.add(Play);
		homePanel.add(Rules);
		contentPanel.add(Speed1);
		contentPanel.add(Speed2);
		contentPanel.add(Speed3);
		contentPanel.add(returnbtn);
		menuContainer.add(contentPanel);
		menuContainer.add(homePanel);
		cmbMessageList.setSelectedIndex(1);
		cmbMessageList.addActionListener (this);
		contentPanel.add(cmbMessageList);
		contentPanel.add (lblText);
				
	}
	
	
	public void actionPerformed(ActionEvent event){
		
			String eventName = event.getActionCommand(); 
			//If play is clicked all the containers are set to false, and removed but the snake class is called to run the game
			if( eventName.equals("Play")){
				homePanel.setVisible (false);
				contentPanel.setVisible(false);
				menuContainer.remove(contentPanel);
				menuContainer.add(homePanel);
				setVisible(false);
				//the ghost value is randomized between 1 to 2 and assigned into a variable name ghost in RenderPanel
				RenderPanel.ghost=(int)(Math.random()*2+1);
				//the snake class which runs the game is called after the play button is clicked with a tailLength of 15 and speed set to 1 tick
				snake = new Snake(15,1);
			}

			if( eventName.equals("Rules")){
				
				Rule1.setText("By clicking on one of the options in the drop down combo box you will get what you are looking for !");
			//When the rules button is clicked the homePanel (panel with play and rules) is removed but the contentPanel (panel with difficulty level) is added to the menuContainer hence the user can pick a level difficulty
				homePanel.setVisible (false);
				contentPanel.setVisible(true);
				menuContainer.remove(homePanel);
				menuContainer.add(contentPanel);	
				
			}
			
			if (eventName.equals("Return")){
				//When return is clicked the homePanel is added to menuContainer 
				homePanel.setVisible (true);
				contentPanel.setVisible(false);
				menuContainer.remove(contentPanel);
				menuContainer.add(homePanel);
				
			}
			
			if (eventName.equals("Easy")){
				//the same steps are taken when the easy button is clicked as when the play button is clicked 
				homePanel.setVisible (false);
				contentPanel.setVisible(false);
				menuContainer.remove(contentPanel);
				menuContainer.add(homePanel);
				setVisible(false);
				//the snake class is called with speed set to 2 ticks and taiLength set to 10
				snake = new Snake(10,2);
			}
			if (eventName.equals("Medium")){
				
				homePanel.setVisible (false);
				contentPanel.setVisible(false);
				menuContainer.remove(contentPanel);
				menuContainer.add(homePanel);
				setVisible(false);
				
				snake = new Snake(15,1);
			}
			if (eventName.equals("Hard")){
			
				homePanel.setVisible (false);
				contentPanel.setVisible(false);
				menuContainer.remove(contentPanel);
				menuContainer.add(homePanel);
				setVisible(false);
				
				snake = new Snake(20,0.25);
			}
			if ( event.getSource () == cmbMessageList){
				JComboBox cb = (JComboBox) event.getSource ();
				String msg = (String) cb.getSelectedItem();
				switch (msg){
				
				case "Controls" : lblText.setText ("In order to move the snake use the arrow keys to move the snake in the desired direction");
				break;
				case "Instructions" : lblText.setText ("Your goal is to obtain as many red boxes by touching it with the snake");
				break;
				case "Game Over Conditions" : lblText.setText ("If you make any contact with the edge of the screen, with the snakes body or the black obstacles you lose.");
				break;
				default : lblText.setText ("whoops");
				}	}
			

		
			
			
			}
	
	public static void main (String [] args){
		Menu gui = new Menu ();
		gui.setVisible (true);
		ComboBoxExample fr = new ComboBoxExample ();
		
	}


}
