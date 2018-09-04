package upb.automata.pushdown.ui;

import java.awt.Button;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JFrame;

import upb.automata.pushdown.Automata;
import upb.automata.pushdown.Simulator;
import upb.automata.pushdown.Step;

public class SimulatorFrame extends JFrame {

	private static final long serialVersionUID = 1827106875755891030L;

	StepViewer stepViewer;	
	Simulator simulator;
	JCheckBox showCorrect;
	TextField inputField;
	ArrayList<Step> steps;
	Integer index;
	
	Button back;
	Button next;
	
	
	public SimulatorFrame(Automata automata) {
		super();
		this.setLayout(new GridBagLayout());
		this.setTitle(automata.name);
		this.setFocusable(true);
		this.setFocusableWindowState(true);
		this.steps = new ArrayList<Step>();
		this.index = 0;
		this.simulator = new Simulator(automata);

		GridBagConstraints c = new GridBagConstraints();
		
		this.inputField = new TextField();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 7;
		c.weightx = 0.5;
		c.insets = new Insets(20, 20, 0, 20);
		
		this.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 37 || e.getKeyCode() == 39) {
					changeStep(e.getKeyCode()-38);
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {}

			@Override
			public void keyTyped(KeyEvent arg0) {}
		});
		
		this.inputField.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					run();
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {}

			@Override
			public void keyTyped(KeyEvent arg0) {}
		});
		this.add(inputField, c);
		
		Button button = new Button("Run");
		c.gridx = 7;
		c.gridy = 0;
		c.gridheight = 2;
		c.gridwidth = 3;
		c.weightx = 0.5;
		c.insets = new Insets(20, 20, 20, 20);
		button.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
				run();
			}
		});
		this.add(button, c);
		

		this.showCorrect = new JCheckBox("Show correct path only (if exists)");
		c.gridx = 0;
		c.gridy = 1;
		c.gridheight = 1;
		c.weighty = 0.05;
		c.gridwidth = 10;
		c.insets = new Insets(0, 20, 0, 20);
		this.showCorrect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				getSteps();
				requestFocus();
			}
			
		});
		this.add(this.showCorrect, c);

		
		this.back = new Button("< Previous Step");
		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 0.5;
		c.gridwidth = 5;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.insets = new Insets(0, 20, 20, 20);
		this.back.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
				changeStep(-1);
			}
		});
		this.add(this.back, c);
		
		this.next = new Button("Next Step >");
		c.gridx = 5;
		c.gridy = 2;
		c.gridwidth = 5;
		c.anchor = GridBagConstraints.FIRST_LINE_END;
		this.next.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
				changeStep(1);
			}
		});
		this.add(this.next, c);
		
		this.stepViewer = new StepViewer();
		c.insets = new Insets(0, 20, 20, 20);
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 0.8;
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 10;
		c.gridheight = 10;
		c.anchor = GridBagConstraints.PAGE_END;
		this.add(this.stepViewer, c);
		
		this.enableButtons();
		
		this.setSize(500, 500);
		this.setVisible(true);
		
		inputField.requestFocus();
	}
	
	public void run() {
		simulator.run(inputField.getText());
		this.getSteps();
		this.requestFocus();
	}
	
	private void changeStep(Integer direction) {
		index = index + direction;
		index = Math.max(0, index);
		index = Math.min(steps.size()-1, index);
		this.enableButtons();
		this.stepViewer.setStep(this.steps.get(index));
	}
	
	private void enableButtons() {
		back.setEnabled(index > 0);
		next.setEnabled(index < steps.size()-1);		
	}
	
	private void getSteps() {
		this.index = 0;
		if (showCorrect.isSelected()) {
			this.steps = simulator.getCorrectSteps();
			if (this.steps != null && this.steps.size() > 0) {
				this.stepViewer.setStep(this.steps.get(index));
			} else {
				this.stepViewer.setStep(null);
			}
		} else {
			this.steps = simulator.getAllSteps();
			if (this.steps != null && this.steps.size() > 0) {
				this.stepViewer.setStep(this.steps.get(index));
			} else {
				this.stepViewer.setStep(null);
			}
		}
		this.enableButtons();
	}
}
