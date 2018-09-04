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
	JComboBox[] initialRulePile = {new JComboBox(),new JComboBox(),new JComboBox(),new JComboBox()};
	JComboBox finalRuleState = new JComboBox(states);
	JComboBox[] finalRulePile = {new JComboBox(),new JComboBox(),new JComboBox(),new JComboBox()};
	

	JButton addTransition = new JButton();
	
	JButton nextView = new JButton();

	ArrayList<Character> initialRuleStacks = new ArrayList<Character>();
	ArrayList<Character> finalRuleStacks = new ArrayList<Character>();
	//Automata Inputs
	String description;
	ArrayList<State> statesAutomaton = new ArrayList<>();
	ArrayList<TransitionFunction> transitionRelation = new ArrayList<>();
	ArrayList<ArrayList<Character>> stackAlphabet = new ArrayList<>();
	ArrayList<Character> startStackSymbols = new ArrayList<>();
	ArrayList<State> finalStatesAutomaton = new ArrayList<>();
	
	
	
	
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
		initialRulePile[0].setBounds(175, 325, 50, 20);
		finalRuleState.setBounds(250, 325, 50, 20);
		finalRulePile[0].setBounds(325, 325, 50, 20);
	
		addTransition.setBounds(425, 325, 45, 20);
		addTransition.setText("+");
		

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
		myFrame.add(initialRulePile[0]);
		myFrame.add(initialRulePile[1]);
		myFrame.add(initialRulePile[2]);
		myFrame.add(initialRulePile[3]);
		myFrame.add(finalRuleState);
		myFrame.add(finalRulePile[0]);
		myFrame.add(finalRulePile[1]);
		myFrame.add(finalRulePile[2]);
		myFrame.add(finalRulePile[3]);
		myFrame.add(addTransition);

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
		
		initialRulePile[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				stackTransition();
			}
		});
		
		numberOfPiles.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				
				for (int i = 0;i<(Integer.parseInt(numberOfPiles.getValue().toString()));i++) {

					initialPiles[i].setBounds(25 + 75*i, 125, 50, 20);
					initialRuleState.setBounds(25, 325, 50, 20);
					initialRuleAlphabet.setBounds(100, 325, 50, 20);
					initialRulePile[i].setBounds(175 + 75*i, 325, 50, 20);
					finalRuleState.setBounds(250 + 75*i, 325, 50, 20);
					
					for (int j = 0;j<(Integer.parseInt(numberOfPiles.getValue().toString()));j++) {
						finalRulePile[i].setBounds(325 + 75 * j+ 75*i , 325, 50, 20);
						addTransition.setBounds(425 + 75*i + 75*j , 325, 45, 20);	
					}
					initialRulePile[i].addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							stackTransition();
						}
					});
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

					initialRulePile[i].addItem(textPileAlphabet.getText());
					initialPiles[i].addItem(textPileAlphabet.getText());
				}
				//stackTransition();
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
		
		addTransition.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
				if (initialRuleStacks != null) {
					initialRuleStacks.clear();					
				}
				if (finalRuleStacks != null) {
					finalRuleStacks.clear();					
				}
				for (int i = 0;i<(Integer.parseInt(numberOfPiles.getValue().toString()));i++) {
					initialRuleStacks.add(initialRulePile[i].getSelectedItem().toString().charAt(0));
				}
				for (int i = 0;i<(Integer.parseInt(numberOfPiles.getValue().toString()));i++) {
					finalRuleStacks.add(((StackAction) finalRulePile[i].getSelectedItem()).action);
				}
				System.out.println(initialRuleStacks);
				transitionRelation.add(new TransitionFunction(
						new State( initialRuleState.getSelectedItem().toString()),
						initialRuleAlphabet.getSelectedItem().toString().charAt(0), 
						initialRuleStacks.toArray(new Character[finalRuleStacks.size()]),
						new State( finalRuleState.getSelectedItem().toString()),
						finalRuleStacks.toArray(new Character[finalRuleStacks.size()])));
				System.out.println(transitionRelation);
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
				
				SimulatorFrame sf = new SimulatorFrame(auto);
			}
		});
	}
	
	public void stackTransition() {
		
		
		
			for (int i = 0;i<(Integer.parseInt(numberOfPiles.getValue().toString()));i++) {
				if (pileAlphabetList != null && initialRulePile[i].getSelectedItem() != null) {
				finalRulePile[i].removeAllItems();
				finalRulePile[i].addItem(new StackAction(initialRulePile[i].getSelectedItem().toString().charAt(0), StackAction.DO_NOTHING));
				for (Character ch : pileAlphabetList) {
					finalRulePile[i].addItem(new StackAction(initialRulePile[i].getSelectedItem().toString().charAt(0),ch));
				}
				finalRulePile[i].addItem(new StackAction(initialRulePile[i].getSelectedItem().toString().charAt(0),StackAction.REMOVE));
				
			}
		}
		
	}
}
