package upb.automata.pushdown;

import java.util.ArrayList;

import upb.automata.pushdown.ui.SimulatorFrame;

public class Main {
	public static void main(String[] args) {
		AutomataManager a = new AutomataManager();
		ArrayList<Automata> arr = a.loadAllAutomata();
		//for(Automata automata : arr) {
		//SimulatorFrame sf = new SimulatorFrame(arr.get(2));
		SimulatorFrame sf = new SimulatorFrame(arr.get(3));
		//}
		MainFrame myFrame = new MainFrame();
	}
}
