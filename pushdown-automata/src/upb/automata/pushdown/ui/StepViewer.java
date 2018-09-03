package upb.automata.pushdown.ui;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import upb.automata.pushdown.Step;

public class StepViewer extends JPanel {
	private static final long serialVersionUID = -4030991670368622832L;
	
	ArrayList<Step> steps;
	Integer index;
	JLabel input;
	JLabel state;
	JLabel stacks;
	JLabel rule;
	
	Button back;
	Button next;
	
	public StepViewer() {
		super(new GridBagLayout());
		this.setBackground(Color.WHITE);
		this.steps = new ArrayList<Step>();
		this.index = 0;
		this.setFocusable(true);
		this.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 37 || e.getKeyCode() == 39) {
					changeStep(e.getKeyCode()-38);
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
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.ipady = 10;
		
		this.back = new Button("< Previous Step");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		this.back.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
				changeStep(-1);
			}
		});
		this.add(this.back, c);
		
		this.next = new Button("Next Step >");
		c.gridx = 1;
		c.gridy = 0;
		c.anchor = GridBagConstraints.FIRST_LINE_END;
		this.next.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
				changeStep(1);
			}
		});
		this.add(this.next, c);
		
		JLabel label = new JLabel("State:", JLabel.LEFT);
		label.setFont(new Font("Verdana", 1, 20));

		c.anchor = GridBagConstraints.CENTER;
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 0.5;
		c.insets = new Insets(0,20,0,0);
		
		this.add(label, c);
		
		label = new JLabel("Input:", JLabel.LEFT);
		label.setFont(new Font("Verdana", 1, 20));
		c.gridy = 2;
		
		this.add(label, c);
		
		label = new JLabel("Stacks:", JLabel.LEFT);
		label.setFont(new Font("Verdana", 1, 20));
		c.gridy = 4;
		
		this.add(label, c);
		
		label = new JLabel("Rule:", JLabel.LEFT);
		label.setFont(new Font("Verdana", 1, 20));
		c.gridy = 3;
		
		this.add(label, c);
		
		this.input = new JLabel("", JLabel.LEFT);
		this.input.setFont(new Font("Verdana", 1, 20));
		c.gridx = 1;
		c.gridy = 2;
		c.weightx = 0.5;
		
		this.add(input, c);
		this.state = new JLabel("", JLabel.LEFT);
		this.state.setFont(new Font("Verdana", 1, 20));
		c.gridy = 1;
		
		this.add(state, c);
		
		this.stacks = new JLabel("", JLabel.LEFT);
		this.stacks.setFont(new Font("Verdana", 1, 15));
		c.gridy = 4;
		
		this.add(stacks, c);
		
		this.rule = new JLabel("", JLabel.LEFT);
		this.rule.setFont(new Font("Verdana", 1, 15));
		c.gridy = 3;
		
		this.add(rule, c);
		
		this.enableButtons();
	}

	public void setSteps(ArrayList<Step> steps) {
		this.steps = steps;
		this.index = 0;
		this.enableButtons();
		this.updateView();
	}
	
	private void changeStep(Integer direction) {
		index = index + direction;
		index = Math.max(0, index);
		index = Math.min(steps.size()-1, index);
		enableButtons();
		updateView();
	}
	
	private void updateView() {
		if (steps.size() > 0 && (index >= 0 && index < steps.size())) {
			input.setText(steps.get(index).input);
			state.setText(steps.get(index).state.toString());
			stacks.setText(steps.get(index).stacks.toString());
			if (steps.get(index).futureTransitionFunction != null) {
				rule.setText(steps.get(index).futureTransitionFunction.toString());				
			} else {
				rule.setText("-");
			}
		}
	}
	
	private void enableButtons() {
		back.setEnabled(index > 0);
		next.setEnabled(index < steps.size() - 1);		
	}
}
