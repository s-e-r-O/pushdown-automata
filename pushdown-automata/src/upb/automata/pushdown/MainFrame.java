package upb.automata.pushdown;

import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerListModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import upb.automata.pushdown.ui.SimulatorFrame;

public class MainFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	
	JFrame myFrame = new JFrame();
	
	TextField textState = new TextField();
	JButton addStates = new JButton();
	String[] states = {};
	ArrayList<String> statesList = new ArrayList<>();
	JComboBox initialState = new JComboBox(states);

	JLabel labelStates = new JLabel();
	
	Character[] alphabet = {};
	ArrayList<Character> alphabetList = new ArrayList<>();
	TextField textAlphabet = new TextField();
	JButton addLetter = new JButton();
	
	String[] numbers = {"1","2","3","4"};
	SpinnerListModel pileModel = new SpinnerListModel(numbers);
	JSpinner numberOfPiles = new JSpinner(pileModel);
	
	
	
	Character[] pileAlphabet = {};
	ArrayList<Character> pileAlphabetList = new ArrayList<>();
	TextField textPileAlphabet = new TextField();
	JButton addPileLetter = new JButton();
	
	JComboBox[] initialPiles = {new JComboBox(pileAlphabet),new JComboBox(pileAlphabet),new JComboBox(pileAlphabet),new JComboBox(pileAlphabet)};
	
	JComboBox acceptedStates = new JComboBox(states);
	String[] finalStates = {};
	ArrayList<String> finalStatesList = new ArrayList<>();
	
	JButton addFinalState = new JButton();
	JTextArea finalStatesLabel = new JTextArea();
	
	JComboBox initialRuleState = new JComboBox(states);
	JComboBox initialRuleAlphabet = new JComboBox(alphabet);
	JComboBox initialRulePile = new JComboBox(pileAlphabet);
	JComboBox finalRuleState = new JComboBox(states);
	JComboBox finalRulePile = new JComboBox(pileAlphabet);
	
	JButton nextView = new JButton();

	
	//Automata Inputs
	String description;
	ArrayList<State> statesAutomaton;
	ArrayList<TransitionFunction> transitionRelation;
	ArrayList<ArrayList<Character>> stackAlphabet;
	ArrayList<Character> startStackSymbols;
	ArrayList<State> finalStatesAutomaton;
	
	
	
	
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
		
		initialPiles[0].setBounds(25, 125, 50, 20);
		acceptedStates.setBounds(25, 150, 50, 20);
		addFinalState.setBounds(100, 150, 45, 20);
		addFinalState.setText("+");
		
		finalStatesLabel.setBounds(300, 25, 200, 200);
		finalStatesLabel.setText("Final States \u03BB");
		

		initialRuleState.setBounds(25, 325, 50, 20);
		initialRuleAlphabet.setBounds(100, 325, 50, 20);
		initialRulePile.setBounds(175, 325, 50, 20);
		finalRuleState.setBounds(250, 325, 50, 20);
		finalRulePile.setBounds(315, 325, 50, 20);
		

		nextView.setBounds(1300,600,100,20);
		nextView.setText("Simulate");
		
		setBehavior();
		
		myFrame.add(textState);
		myFrame.add(addStates);
		myFrame.add(initialState);
		
		myFrame.add(textAlphabet);
		myFrame.add(addLetter);
		
		myFrame.add(numberOfPiles);
		
		myFrame.add(textPileAlphabet);
		myFrame.add(addPileLetter);
		
		myFrame.add(initialPiles[0]);
		myFrame.add(initialPiles[1]);
		myFrame.add(initialPiles[2]);
		myFrame.add(initialPiles[3]);
		myFrame.add(acceptedStates);
		myFrame.add(addFinalState);
		myFrame.add(finalStatesLabel);
		
		myFrame.add(initialRuleState);
		myFrame.add(initialRuleAlphabet);
		myFrame.add(initialRulePile);
		myFrame.add(finalRuleState);
		myFrame.add(finalRulePile);

		myFrame.add(nextView);
		
		myFrame.setSize(1500, 800);
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
				initialRuleState.addItem(textState.getText());
				finalRuleState.addItem(textState.getText());
			}
		});
		
		numberOfPiles.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {

				System.out.println("click bitch");
				
				for (int i = 0;i<(Integer.parseInt(numberOfPiles.getValue().toString()));i++) {

					initialPiles[i].setBounds(25 + 75*i, 125, 50, 20);
				}

			}
		});
		
		addLetter.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
				alphabetList.add(textAlphabet.getText().toCharArray()[0]);
				alphabet = alphabetList.toArray(alphabet);
				initialRuleAlphabet.addItem(textAlphabet.getText());
//				for(String s : states)
//				    System.out.println(s);
			}
		});
		
		addPileLetter.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
				pileAlphabetList.add(textPileAlphabet.getText().toCharArray()[0]);
				System.out.println(pileAlphabetList);
				pileAlphabet = pileAlphabetList.toArray(pileAlphabet);
//				for(String s : states)
//				    System.out.println(s);
				for (int i = 0;i<(Integer.parseInt(numberOfPiles.getValue().toString()));i++) {

					initialPiles[i].addItem(textPileAlphabet.getText());
				}
				initialRulePile.addItem(textPileAlphabet.getText());
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
		
		nextView.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
				for (String st : states) {
					statesAutomaton.add(new State(st));
					
				}
				
				for (String fst : finalStates) {
					finalStatesAutomaton.add(new State(fst));
					
				}
				Automata auto = new Automata();
				auto.states = statesAutomaton;
				auto.inputAlphabet = alphabetList;
				auto.stackAlphabet = stackAlphabet;
				auto.transitionRelation = transitionRelation;
				auto.startState = new State ((String) initialState.getSelectedItem());
				auto.startStackSymbols = startStackSymbols;
				auto.finalStates = finalStatesAutomaton;
				
				
				
			}
		});
		
		
		
	}

}
