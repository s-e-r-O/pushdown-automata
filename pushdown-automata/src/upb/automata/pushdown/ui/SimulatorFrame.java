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

import javax.swing.JCheckBox;
import javax.swing.JFrame;

import upb.automata.pushdown.Automata;
import upb.automata.pushdown.Simulator;

public class SimulatorFrame extends JFrame {

	private static final long serialVersionUID = 1827106875755891030L;

	StepViewer stepViewer;	
	Simulator simulator;
	JCheckBox checkAll;
	TextField inputField;
	
	public SimulatorFrame(Automata automata) {
		super();
		this.setLayout(new GridBagLayout());
		this.setTitle(automata.description);
		
		this.simulator = new Simulator(automata);

		GridBagConstraints c = new GridBagConstraints();
		
		this.inputField = new TextField();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.7;
		c.insets = new Insets(20, 20, 0, 20);
		
		this.inputField.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					run();
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		this.add(inputField, c);
		
		Button button = new Button("Run");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 0.3;
		this.add(button, c);
		button.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
				run();
			}
		});
		
		this.checkAll = new JCheckBox("Show all paths");
		c.gridx = 0;
		c.gridy = 1;
		c.weighty = 0.1;
		c.insets = new Insets(0, 20, 0, 20);
		this.add(this.checkAll, c);
		
		this.stepViewer = new StepViewer();
		c.insets = new Insets(0, 20, 20, 20);
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		c.gridheight = 2;
		c.anchor = GridBagConstraints.PAGE_END;
		this.add(this.stepViewer, c);
		this.setSize(500, 500);
		this.setVisible(true);
	}
	
	public void run() {
		if (inputField.getText().length() > 0) {
			boolean isCorrect = simulator.run(inputField.getText()); 
			inputField.setText("");
			if (!checkAll.isSelected()) {
				if (isCorrect) {
					stepViewer.setSteps(simulator.getCorrectSteps());
					stepViewer.requestFocus();
				}
			} else {
				stepViewer.setSteps(simulator.getAllSteps());
				stepViewer.requestFocus();
			}
		}
	}
}
