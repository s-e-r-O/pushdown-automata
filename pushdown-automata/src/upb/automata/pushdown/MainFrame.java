package upb.automata.pushdown;

import java.awt.Button;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;

import upb.automata.pushdown.ui.SimulatorFrame;
import upb.automata.pushdown.util.Formatter;

public class MainFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	
	ArrayList<Automata> automata;
	AutomataManager am;
	
	ArrayList<String> statesList;
	ArrayList<Character> alphabetList;
	ArrayList<Character> stackAlphabetList;
	ArrayList<String> finalStatesList;
	ArrayList<TransitionFunction> transitionList;
	
	JPanel manager;
	JComboBox<Automata> automataComboBox;
	TextField automataName;
	TextField automataDescription;

	TextField textState;
	JComboBox<String> initialState;
	JLabel statesLabel;
	
	TextField textAlphabet;
	JLabel alphabetLabel;
	
	JSpinner numberOfPiles;
	
	TextField textStackAlphabet;
	JLabel stackAlphabetLabel;
	JComboBox<Character> initialStack;
	
	JComboBox<String> finalStates;
	JLabel finalStatesLabel;
	
	JComboBox<String> initialRuleState;
	JComboBox<Character> initialRuleAlphabet;
	JComboBox<Character> initialRuleStack;
	JComboBox<String> finalRuleState;
	JComboBox<StackAction> finalRuleStackAction;

	JLabel rulesLabel;

	JButton addTransition = new JButton();

	ArrayList<Character> initialRuleStacks = new ArrayList<Character>();
	ArrayList<Character> finalRuleStacks = new ArrayList<Character>();
	
	int nStacks = 1;
	
	public MainFrame() {
		super("Automata Editor");
		this.setLayout(new GridBagLayout());
		
		this.am = new AutomataManager();
		this.statesList = new ArrayList<String>();
		this.stackAlphabetList = new ArrayList<Character>();
		this.alphabetList = new ArrayList<Character>();
		this.finalStatesList = new ArrayList<String>();
		this.transitionList = new ArrayList<TransitionFunction>();
		
		this.automata = this.am.loadAllAutomata();
				
		GridBagConstraints c = new GridBagConstraints();
		
		this.manager = new JPanel(new GridBagLayout());
		this.manager.setBorder(BorderFactory.createEtchedBorder());
		this.automataComboBox = new JComboBox<Automata>();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(10,10,10,10);
		for(Automata a : this.automata) {
			this.automataComboBox.addItem(a);			
		}
		this.manager.add(this.automataComboBox, c);
		
		Button button = new Button("Load");
		c.gridx = 2;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		button.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
				loadAutomata();
			}
		});
		this.manager.add(button, c);
		
		button = new Button("Save");
		c.gridx = 2;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		button.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
				saveAutomata();
				automata = am.loadAllAutomata();
				automataComboBox.removeAllItems();
				for (Automata aut : automata) {
					automataComboBox.addItem(aut);			
				}
			}
		});
		this.manager.add(button, c);
		
		this.automataName = new TextField();
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		this.manager.add(this.automataName, c);
		
		c = new GridBagConstraints();
		
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 12;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.insets = new Insets(20,20,20,20);
		this.add(this.manager, c);
		
		this.automataDescription = new TextField();
		
		JLabel[] labels = new JLabel[] {
			new JLabel("Add to States:", JLabel.RIGHT),
			new JLabel("", JLabel.RIGHT),
			new JLabel("Initial State:", JLabel.RIGHT),
			new JLabel("Add to Alphabet:", JLabel.RIGHT),
			new JLabel("", JLabel.RIGHT),
			new JLabel("Number of Stacks:", JLabel.RIGHT),
			new JLabel("Add to Alphabet of Stacks:", JLabel.RIGHT),
			new JLabel("", JLabel.RIGHT),
			new JLabel("Initial Value of Stacks:", JLabel.RIGHT),
			new JLabel("Add to Final States:", JLabel.RIGHT),
			new JLabel("", JLabel.RIGHT),
			new JLabel("Add Rules:", JLabel.LEFT)
		};
		
		c.anchor = GridBagConstraints.CENTER;
		c.gridx = 0;
		c.ipady = 0;
		c.ipadx = 0;
		c.gridwidth = 1;
		c.weightx = 0;
		c.insets = new Insets(10,20,0,20);
		c.fill = GridBagConstraints.HORIZONTAL;
		
		for (int i = 0; i < labels.length; i++) {
			labels[i].setFont(new Font("Verdana", Font.BOLD, 12));
			c.gridy = i+1;
			c.weighty = 0.1;
			this.add(labels[i], c);
		}
		
		this.textState = new TextField();
		c.gridx = 1;
		c.gridwidth = 7;
		
		c.gridy = 1;
		c.weightx = 0.5;
		
		this.add(this.textState, c);
		
		this.initialState = new JComboBox<String>();
		c.gridy = 3;
		
		this.add(this.initialState, c);
		
		this.textAlphabet = new TextField();
		c.gridy = 4;
		
		this.add(this.textAlphabet, c);
		
		SpinnerListModel stackModel = new SpinnerListModel(new Integer[] {1, 2, 3, 4});
		this.numberOfPiles = new JSpinner(stackModel);
		c.gridy = 6;
		this.add(this.numberOfPiles, c);

		this.textStackAlphabet = new TextField();
		c.gridy = 7;
		
		this.add(this.textStackAlphabet, c);
		
		this.initialStack = new JComboBox<Character>();
		c.gridy = 9;
		
		this.add(this.initialStack, c);
		
		this.finalStates = new JComboBox<String>();
		c.gridy = 10;
		
		this.add(this.finalStates, c);
		
		this.initialRuleState = new JComboBox<String>();
		c.gridx = 0;
		c.gridwidth = 1;
		c.gridy = 13;

		this.add(this.initialRuleState, c);
		
		this.initialRuleAlphabet = new JComboBox<Character>();
		c.gridx = 1;
		c.gridy = 13;
		this.initialRuleAlphabet.addItem(Automata.EPSILON);
		this.add(this.initialRuleAlphabet, c);
		
		this.initialRuleStack = new JComboBox<Character>();
		c.gridx = 2;
		c.gridy = 13;
		
		this.initialRuleStack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				stackTransition();
			}
		});
		
		this.add(this.initialRuleStack, c);
		
		
		this.finalRuleState = new JComboBox<String>();
		c.gridx = 3;
		c.gridy = 13;

		this.add(this.finalRuleState, c);
		
		this.finalRuleStackAction = new JComboBox<StackAction>();
		c.gridx = 4;
		c.gridy = 13;

		this.add(this.finalRuleStackAction, c);

		this.statesLabel = new JLabel("  States:", JLabel.LEFT);
		this.statesLabel.setBorder(BorderFactory.createEtchedBorder());
		this.statesLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
		c.gridx = 0;
		c.gridwidth = 12;
		c.gridy = 2;
		c.ipady = 20;
		
		this.add(this.statesLabel, c);
		
		this.alphabetLabel = new JLabel("  Alphabet:", JLabel.LEFT);
		this.alphabetLabel.setBorder(BorderFactory.createEtchedBorder());
		this.alphabetLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
		c.gridy = 5;
		
		this.add(this.alphabetLabel, c);

		this.stackAlphabetLabel = new JLabel("  Stack Alphabet:", JLabel.LEFT);
		this.stackAlphabetLabel.setBorder(BorderFactory.createEtchedBorder());
		this.stackAlphabetLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
		c.gridy = 8;
		
		this.add(this.stackAlphabetLabel, c);

		this.finalStatesLabel = new JLabel("  Final States:", JLabel.LEFT);
		this.finalStatesLabel.setBorder(BorderFactory.createEtchedBorder());
		this.finalStatesLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
		c.gridy = 11;
		
		this.add(this.finalStatesLabel, c);
		

		this.rulesLabel = new JLabel("  Rules:", JLabel.LEFT);
		this.rulesLabel.setBorder(BorderFactory.createEtchedBorder());
		this.rulesLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
		c.gridy = 14;
		
		this.add(this.rulesLabel, c);
		
		button = new Button("Add");
		c.gridx = 9;
		c.gridy = 1;
		c.gridwidth = 3;
		c.weightx = 0.2;
		c.ipady = 5;
		button.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
				if (textState.getText().trim().length() > 0 && !statesList.contains(textState.getText().trim())) {
					statesList.add(textState.getText().trim());
					initialState.addItem(textState.getText().trim());
					finalStates.addItem(textState.getText().trim());
					statesLabel.setText("  States: " + Formatter.arrayToString(statesList.toArray()));					
					initialRuleState.addItem(textState.getText().trim());
					finalRuleState.addItem(textState.getText().trim());
					textState.setText("");
				}
			}
		});
		this.add(button, c);
		
		button = new Button("Add");
		c.gridy = 4;
		button.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
				if (textAlphabet.getText().trim().length() > 0 && !alphabetList.contains(textAlphabet.getText().trim().toLowerCase().toCharArray()[0])) {
					Character c = textAlphabet.getText().trim().toLowerCase().toCharArray()[0];
					if (c != Automata.EPSILON && c != StackAction.DO_NOTHING) {
						alphabetList.add(c);
						alphabetLabel.setText("  Alphabet: " + Formatter.arrayToString(alphabetList.toArray()));						
						initialRuleAlphabet.addItem(c);
						
					}
					textAlphabet.setText("");
				}
			}
		});
		this.add(button, c);
		
		button = new Button("Add");
		c.gridy = 7;
		button.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
				if (textStackAlphabet.getText().trim().length() > 0 && !stackAlphabetList.contains(textStackAlphabet.getText().trim().toUpperCase().toCharArray()[0])) {
					Character c = textStackAlphabet.getText().trim().toUpperCase().toCharArray()[0];
					if (c != StackAction.REMOVE && c != StackAction.DO_NOTHING) {
						stackAlphabetList.add(c);
						initialStack.addItem(c);
						stackAlphabetLabel.setText(" Stack Alphabet: " + Formatter.arrayToString(stackAlphabetList.toArray()));						
						initialRuleStack.addItem(c);
						stackTransition();
					}
					textStackAlphabet.setText("");
				}
			}
		});
		this.add(button, c);
		
		button = new Button("Add");
		c.gridy = 10;
		button.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
				if (finalStates.getSelectedItem() != null) {
					finalStatesList.add((String) finalStates.getSelectedItem());
					finalStatesLabel.setText("  Final States: " + Formatter.arrayToString(finalStatesList.toArray()));					
				}
			}
		});
		this.add(button, c);

		button = new Button("Add");
		c.gridy = 13;
		button.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
//				if (initialRuleStacks != null) {
//					initialRuleStacks.clear();					
//				}
//				if (finalRuleStacks != null) {
//					finalRuleStacks.clear();					
//				}
//				for (int i = 0;i<(Integer.parseInt(numberOfPiles.getValue().toString()));i++) {
//					initialRuleStacks.add(initialRulePile[i].getSelectedItem().toString().charAt(0));
//				}
//				for (int i = 0;i<(Integer.parseInt(numberOfPiles.getValue().toString()));i++) {
//					finalRuleStacks.add(((StackAction) finalRulePile[i].getSelectedItem()).action);
//				}
				transitionList.add(new TransitionFunction(
						new State( initialRuleState.getSelectedItem().toString()),
						initialRuleAlphabet.getSelectedItem().toString().charAt(0), 
						//initialRuleStacks.toArray(new Character[finalRuleStacks.size()]),
						new Character[] {(Character)initialRuleStack.getSelectedItem()},
						new State( finalRuleState.getSelectedItem().toString()),
						//finalRuleStacks.toArray(new Character[finalRuleStacks.size()])));
						new Character[] {((StackAction)finalRuleStackAction.getSelectedItem()).action}));
				rulesLabel.setText("  Rules: " + Formatter.arrayToString(transitionList.toArray()));
			}
		});
		this.add(button, c);

		button = new Button("Play");
		c.gridx = 1;
		c.gridwidth = 2;
		c.gridy = 15;
		c.ipady = 10;
		c.insets = new Insets(10, 20, 20, 20);
		button.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
				if (!checkEmptiness()) {
					new SimulatorFrame(generateAutomata());					
				}
			}
		});
		this.add(button, c);
				
		this.setSize(500, 700);
		this.setVisible(true);
		
	}
	
	public void loadAutomata() {
		Automata a = (Automata) this.automataComboBox.getSelectedItem();
		this.automata = this.am.loadAllAutomata();
		automataComboBox.removeAllItems();
		for (Automata aut : this.automata) {
			this.automataComboBox.addItem(aut);			
		}
		if (a.transitionRelation.size() > 0) {
			this.nStacks = a.transitionRelation.get(0).stackValues.length;			
		} else {
			this.nStacks = 1;
		}
		this.resetAll();
		for (State s : a.states) {
			this.statesList.add(s.id);
			this.initialState.addItem(s.id);
			this.finalStates.addItem(s.id);
			this.initialRuleState.addItem(s.id);
			this.finalRuleState.addItem(s.id);
		}
		this.alphabetList = a.inputAlphabet;
		this.stackAlphabetList = a.stackAlphabet.get(0);
		for (State s : a.finalStates) {
			this.finalStatesList.add(s.id);
		}
		for (Character s : a.inputAlphabet) {
			this.initialRuleAlphabet.addItem(s);
		}
		for (Character s : a.stackAlphabet.get(0)) {
			this.initialStack.addItem(s);
			this.initialRuleStack.addItem(s);
		}
		this.automataName.setText(a.name);
		this.automataDescription.setText(a.description);
		this.initialState.setSelectedItem(a.startState.id);
		this.statesLabel.setText("  States: " + Formatter.arrayToString(statesList.toArray()));	
		alphabetLabel.setText("  Alphabet: " + Formatter.arrayToString(alphabetList.toArray()));
		stackAlphabetLabel.setText(" Stack Alphabet: " + Formatter.arrayToString(stackAlphabetList.toArray()));

//		initialRulePile[0].addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				stackTransition();
//			}
//		});
		
		this.initialStack.setSelectedItem(a.startStackSymbols.get(0));
		finalStatesLabel.setText("  Final States: " + Formatter.arrayToString(finalStatesList.toArray()));
		stackTransition();
		
		this.transitionList = a.transitionRelation;
		rulesLabel.setText("  Rules: " + Formatter.arrayToString(transitionList.toArray()));
		
	}
	
	public void saveAutomata() {
		if (!checkEmptiness()) {
			Automata a = generateAutomata();
			this.am.saveAutomata(a, a.name);			
		}
	}
	
	public Automata generateAutomata() {
		Automata auto = new Automata();
		ArrayList<State> statesAutomaton = new ArrayList<State>();
		ArrayList<State> finalStatesAutomaton = new ArrayList<State>();
		for (String st : statesList) {
			statesAutomaton.add(new State(st));
		}
		
		for (String fst : finalStatesList) {
			finalStatesAutomaton.add(new State(fst));
		}
		auto.name = this.automataName.getText();
		auto.states = statesAutomaton;
		auto.inputAlphabet = alphabetList;
		auto.stackAlphabet = new ArrayList<ArrayList<Character>>();
		auto.stackAlphabet.add(stackAlphabetList);
		auto.transitionRelation = this.transitionList;
		auto.startState = new State ((String) initialState.getSelectedItem());
		auto.startStackSymbols = new ArrayList<Character>();
		for (int i = 0; i < nStacks; i++) {
			auto.startStackSymbols.add((Character) this.initialStack.getSelectedItem());			
		}
		auto.finalStates = finalStatesAutomaton;
		
		return auto;
	}
	
	public void resetAll() {
		this.statesList.clear();
		this.alphabetList.clear();
		this.stackAlphabetList.clear();
		this.finalStatesList.clear();
		
		this.automataName.setText("");
		this.automataDescription.setText("");

		this.textState.setText("");
		this.initialState.removeAll();
		this.statesLabel.setText("  States:");
		
		this.textAlphabet.setText("");
		this.alphabetLabel.setText("  Alphabet:");
		this.numberOfPiles.setValue(1);
		

		this.textStackAlphabet.setText("");
		this.stackAlphabetLabel.setText("  Stack Alphabet:");
		this.initialStack.removeAll();
		this.finalStates.removeAll();
		this.finalStatesLabel.setText("  Final States:");

		this.initialRuleState.removeAll();
		this.initialRuleStack.removeAll();
		this.finalRuleState.removeAll();
		this.finalRuleStackAction.removeAll();
		this.initialRuleAlphabet.removeAll();
		this.transitionList.clear();


		this.rulesLabel.setText("  Rules:");
	}
	
	public void stackTransition() {
			for (int i = 0;i<(Integer.parseInt(numberOfPiles.getValue().toString()));i++) {
				if (stackAlphabetList.size() > 0 && initialRuleStack.getSelectedItem() != null) {
				finalRuleStackAction.removeAllItems();
				finalRuleStackAction.addItem(new StackAction(
						initialRuleStack.getSelectedItem().toString().charAt(0), 
						StackAction.DO_NOTHING)
				);
				for (Character ch : stackAlphabetList) {
					finalRuleStackAction.addItem(new StackAction(
							initialRuleStack.getSelectedItem().toString().charAt(0), 
							ch)
					);
				}
				finalRuleStackAction.addItem(new StackAction(
						initialRuleStack.getSelectedItem().toString().charAt(0), 
						StackAction.REMOVE)
				);
				
			}
		}
		
	}
	
	public boolean checkEmptiness() {
		boolean isEmpty = false;
		if (statesList.size() <= 0 || initialState.getSelectedItem() == null || alphabetList.size() <= 0 
				|| stackAlphabetList.size() <= 0 || finalStatesList.size() <= 0 || initialRuleState.getSelectedItem() == null
				|| initialRuleAlphabet.getSelectedItem() == null || finalRuleState.getSelectedItem() == null
				|| transitionList.size() <= 0 || initialRuleStack.getSelectedItem() == null 
				|| finalRuleStackAction.getSelectedItem() == null || initialStack.getSelectedItem() == null
				) {
			isEmpty = true;
		}
		return isEmpty;
	}
}
