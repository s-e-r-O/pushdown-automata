package upb.automata.pushdown;

import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;

public class MainFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	
	JFrame myFrame = new JFrame();
	
	TextField textState = new TextField();
	JButton addStates = new JButton();
	String[] states = {};
	JComboBox initialState = new JComboBox(states);

	JLabel labelStates = new JLabel();
	
	String[] alphabet = {};
	TextField textAlphabet = new TextField();
	JButton addLetter = new JButton();
	
	String[] numbers = {"1","2","3","4"};
	SpinnerListModel pileModel = new SpinnerListModel(numbers);
	JSpinner numberOfPiles = new JSpinner(pileModel);
	
	String[] pileAlphabet = {};
	TextField textPileAlphabet = new TextField();
	JButton addPileLetter = new JButton();
	
	JComboBox initialPile = new JComboBox(pileAlphabet);
	
	JComboBox acceptedStates = new JComboBox(states);
	String[] finalStates = {};
	JButton addFinalState = new JButton();
	JLabel finalStatesLabel = new JLabel();
	
	
	public MainFrame() {
		
		
		textState.setBounds(25, 25, 50, 20);
		addStates.setBounds(100, 25, 45, 20);
		addStates.setText("+");
		initialState.setBounds(170, 25, 50, 20);
		
		textAlphabet.setBounds(25,50,50,20);
		addLetter.setBounds(100,50,45,20);
		addLetter.setText("+");
		
		numberOfPiles.setBounds(25, 75, 50, 20);
		
		textPileAlphabet.setBounds(25, 100, 50, 20);
		addPileLetter.setBounds(100,100,45,20);
		addPileLetter.setText("+");
		
		initialPile.setBounds(25, 125, 50, 20);
		acceptedStates.setBounds(25, 150, 50, 20);
		addFinalState.setBounds(100, 150, 45, 20);
		addFinalState.setText("+");
		
		finalStatesLabel.setBounds(25, 175, 200, 25);
		finalStatesLabel.setText("Some final states");
		
		myFrame.add(textState);
		myFrame.add(addStates);
		myFrame.add(initialState);
		
		myFrame.add(textAlphabet);
		myFrame.add(addLetter);
		
		myFrame.add(numberOfPiles);
		
		myFrame.add(textPileAlphabet);
		myFrame.add(addPileLetter);
		
		myFrame.add(initialPile);
		myFrame.add(acceptedStates);
		myFrame.add(addFinalState);
		myFrame.add(finalStatesLabel);
		
		myFrame.setSize(500, 500);
		myFrame.setLayout(null);
		myFrame.setVisible(true);
		
	}
	
	public void iNeedHelp() {
		addStates.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
				finalStatesLabel.setText("Button Pressed");
				System.out.println("123");
			}
		});
	}

}
