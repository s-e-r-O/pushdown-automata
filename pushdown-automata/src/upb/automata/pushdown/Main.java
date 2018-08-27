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
			s.run("aaabbb");
			s.run("aab");
			s.run("abbb");
			s.run("abbbc");
			s.run("aaabbbccc");
		}
	}
}
