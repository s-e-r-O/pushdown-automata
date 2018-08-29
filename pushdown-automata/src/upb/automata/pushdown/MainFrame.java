package upb.automata.pushdown;

import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerListModel;

import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	
	JFrame myFrame = new JFrame();
	
	TextField textState = new TextField();
	JButton addStates = new JButton();
	String[] states = {};
	List<String> statesList = new ArrayList<>();
	JComboBox initialState = new JComboBox(states);

	JLabel labelStates = new JLabel();
	
	String[] alphabet = {};
	List<String> alphabetList = new ArrayList<>();
	TextField textAlphabet = new TextField();
	JButton addLetter = new JButton();
	
	String[] numbers = {"1","2","3","4"};
	SpinnerListModel pileModel = new SpinnerListModel(numbers);
	JSpinner numberOfPiles = new JSpinner(pileModel);
	
	String[] pileAlphabet = {};
	List<String> pileAlphabetList = new ArrayList<>();
	TextField textPileAlphabet = new TextField();
	JButton addPileLetter = new JButton();
	
	JComboBox initialPile = new JComboBox(pileAlphabet);
	
	JComboBox acceptedStates = new JComboBox(states);
	String[] finalStates = {};
	List<String> finalStatesList = new ArrayList<>();
	
	JButton addFinalState = new JButton();
	JTextArea finalStatesLabel = new JTextArea();
	
	
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
		
		finalStatesLabel.setBounds(25, 175, 200, 200);
		finalStatesLabel.setText("Final States");
		
		setBehavior();
		
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
	
	public void setBehavior() {
		addStates.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
				statesList.add(textState.getText());
				states = statesList.toArray(states);
//				for(String s : states)
//				    System.out.println(s);
				initialState.addItem(textState.getText());
				acceptedStates.addItem(textState.getText());
			}
		});
		
		addLetter.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
				alphabetList.add(textAlphabet.getText());
				alphabet = alphabetList.toArray(alphabet);
//				for(String s : states)
//				    System.out.println(s);
			}
		});
		
		addPileLetter.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
				pileAlphabetList.add(textPileAlphabet.getText());
				pileAlphabet = pileAlphabetList.toArray(pileAlphabet);
//				for(String s : states)
//				    System.out.println(s);
				initialPile.addItem(textPileAlphabet.getText());
			}
		});
		
		addFinalState.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
				finalStatesList.add((String) acceptedStates.getSelectedItem());
				finalStates = finalStatesList.toArray(finalStates);
//				for(String s : states)
//				    System.out.println(s);
				finalStatesLabel.append("\n" + (String) acceptedStates.getSelectedItem());
			}
		});
		
	}

}
