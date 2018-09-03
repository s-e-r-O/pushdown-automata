package upb.automata.pushdown;

import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		AutomataManager a = new AutomataManager();
		ArrayList<Automata> arr = a.loadAllAutomata();
		Simulator s = new Simulator();
		for(Automata automata : arr) {
			s.setAutomata(automata);
			s.run("aabbbb");
			System.out.println(s.getAllSteps().toString());
			System.out.println(s.getCorrectSteps().toString());
			s.run("aaabbb");
			s.run("aab");
			s.run("abbb");
			s.run("abbbc");
			s.run("aaabbbccc");
		}
		MainFrame myFrame = new MainFrame();
	}
}
