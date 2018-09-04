package upb.automata.pushdown.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import upb.automata.pushdown.Step;
import upb.automata.pushdown.util.Formatter;

public class StepViewer extends JPanel {
	private static final long serialVersionUID = -4030991670368622832L;
	
	Step step;
	
	JLabel[] labels;
	JLabel[] values;
	
	DefaultTableModel stacks;
	JTable table;
	
	public StepViewer() {
		super(new GridBagLayout());
		this.step = null;
		this.setFocusable(true);
		
		
		GridBagConstraints c = new GridBagConstraints();
		this.labels = new JLabel[] {
			new JLabel("State:", JLabel.RIGHT),
			new JLabel("Input:", JLabel.RIGHT),
			new JLabel("Rule:", JLabel.RIGHT),
		};
		
		c.anchor = GridBagConstraints.EAST;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.4;
		c.weighty = 0.1;
		c.ipady = 0;
		c.insets = new Insets(0,20,0,0);
		
		for (int i = 0; i < this.labels.length; i++) {
			this.labels[i].setFont(new Font("Verdana", Font.BOLD, 15));
			c.gridy = i;
			c.weighty = 0.1;
			this.add(labels[i], c);
		}
		
		this.values = new JLabel[] {
				new JLabel("", JLabel.LEFT),
				new JLabel("", JLabel.LEFT),
				new JLabel("", JLabel.LEFT),
		};
		
		c.gridx = 1;
		c.weightx = 0.6;
		c.anchor = GridBagConstraints.WEST;
		for (int i = 0; i < this.values.length; i++) {
			this.values[i].setFont(new Font("Verdana", Font.PLAIN, 15));
			c.gridy = i;
			c.weighty = 0.1;
			this.add(values[i], c);
		}
		
		this.stacks = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		this.stacks.setColumnCount(0);
		this.stacks.setRowCount(12);
		
		this.table = new JTable(stacks);
		this.table.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		c.anchor = GridBagConstraints.CENTER;
		c.gridx = 0;
		c.gridwidth = 2;
		c.gridy = 3;
		c.weighty = 0.7;
		
		this.add(table, c);
		this.updateView();
		
	}

	public void setStep(Step step) {
		this.step = step;
		this.updateView();
	}
	
	private void updateView() {
		if (step != null) {
			this.labels[0].setText("State:");
			this.labels[1].setText("Input:");
			this.values[0].setText(step.state.toString());
			this.values[1].setText(Formatter.inputToString(step.input));
			this.values[2].setForeground(Color.BLACK);
			if (step.status.equals(Step.ACCEPTED_STATE_STEP)) {
				this.labels[2].setText("Result:");
				this.values[2].setText("Accepted (Final State)");				
				this.values[2].setForeground(Color.BLUE);
			} else if (step.status.equals(Step.EMPTY_STACK_STEP)) {
				this.labels[2].setText("Result:");
				this.values[2].setText("Accepted (Empty Stack)");				
				this.values[2].setForeground(Color.BLUE);
			} else if (step.status.equals(Step.RULE_FOUND)) {
				this.labels[2].setText("Rule:");
				this.values[2].setText(step.futureTransitionFunction.toString());				
			} else if (step.status.equals(Step.RULE_NOT_FOUND)) {
				this.labels[2].setText("Rule:");
				this.values[2].setText("<Not Found>");
				this.values[2].setForeground(Color.RED);
			}
		} else {
			for (int i = 0; i < this.values.length; i++) {
				this.values[i].setText("");
				this.labels[i].setText("");
			}
		}
		this.updateTable();
	}
	
	private void updateTable() {
		this.stacks.setColumnCount(0);
		if (this.step != null) {
			this.stacks.setColumnCount(this.step.stacks.size());
			for (int i=0; i<this.step.stacks.size(); i++) {
				for (int j = 0; j < this.step.stacks.get(i).size(); j++) {
					this.stacks.setValueAt(this.step.stacks.get(i).get(j), this.stacks.getRowCount() - 1 - j, i);
				}
			}			
		}
	}
}
