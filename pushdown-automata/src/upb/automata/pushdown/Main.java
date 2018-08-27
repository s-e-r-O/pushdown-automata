package upb.automata.pushdown;

public class Main {
	public static void main(String[] args) {
		AutomataManager a = new AutomataManager();
		Simulator s = new Simulator(a.loadAutomata("same_a_as_b"));
		s.run("aaabbb");
		s.run("abbb");
	}
}
