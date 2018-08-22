package upb.automata.pushdown;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		System.out.println("L = { a^nb^m | n <= m <= 2n }");
		Simulator s = new Simulator(automataBInRangeA2A());
		s.run("aaabbbbbb");
		s.run("");
		s.run("bbbb");
		s.run("aaa");
		s.run("ab");
		System.out.println("L = { a^nb^n | n >= 0 }");
		s.changeAutomata(automataSameAB());
		s.run("aaabbbbbb");
		s.run("");
		s.run("bbbb");
		s.run("aaa");
		s.run("ab");
		System.out.println("L = { a^nb^m | n > m }");
		s.changeAutomata(automataMoreAThanB());
		s.run("aaabbbbbb");
		s.run("");
		s.run("bbbb");
		s.run("aaa");
		s.run("ab");
	}
	
	private static Automata automataSameAB() {
		Automata a = new Automata();
		State p = new State("p");
		State q = new State("q");
		State r = new State("r");
		a.startStackSymbol = 'Z';
		a.states = new ArrayList<State>();
		a.states.add(p);
		a.states.add(q);
		a.states.add(r);
		a.finalStates = new ArrayList<State>();
		a.finalStates.add(r);
		a.startState = p;
		a.transitionRelation = new ArrayList<TransitionFunction>();
		a.transitionRelation.add(new TransitionFunction(p, 'a', 'Z', p, 'A'));
		a.transitionRelation.add(new TransitionFunction(p, 'a', 'A', p, 'A'));
		a.transitionRelation.add(new TransitionFunction(p, Automata.EPSILON, 'A', q, Automata.NOTHING));
		a.transitionRelation.add(new TransitionFunction(p, Automata.EPSILON, 'Z', q, Automata.NOTHING));
		a.transitionRelation.add(new TransitionFunction(q, 'b', 'A', q, Automata.LAMBDA));
		a.transitionRelation.add(new TransitionFunction(q, Automata.EPSILON, 'Z', r, Automata.NOTHING));
		return a;
	}
	
	private static Automata automataMoreAThanB() {
		Automata a = new Automata();
		State p = new State("p");
		State q = new State("q");
		State r = new State("r");
		a.startStackSymbol = 'Z';
		a.states = new ArrayList<State>();
		a.states.add(p);
		a.states.add(q);
		a.states.add(r);
		a.finalStates = new ArrayList<State>();
		a.finalStates.add(r);
		a.startState = p;
		a.transitionRelation = new ArrayList<TransitionFunction>();
		a.transitionRelation.add(new TransitionFunction(p, 'a', 'Z', p, 'A'));
		a.transitionRelation.add(new TransitionFunction(p, 'a', 'A', p, 'A'));
		a.transitionRelation.add(new TransitionFunction(p, Automata.EPSILON, 'A', q, Automata.NOTHING));
		a.transitionRelation.add(new TransitionFunction(q, 'b', 'A', q, Automata.LAMBDA));
		a.transitionRelation.add(new TransitionFunction(q, Automata.EPSILON, 'A', r, Automata.NOTHING));
		return a;
	}

	private static Automata automataBInRangeA2A() {
		Automata a = new Automata();
		State p = new State("p");
		State x = new State("x");
		State q = new State("q");
		State r = new State("r");
		a.startStackSymbol = 'Z';
		a.states = new ArrayList<State>();
		a.states.add(p);
		a.states.add(x);
		a.states.add(q);
		a.states.add(r);
		a.finalStates = new ArrayList<State>();
		a.finalStates.add(r);
		a.startState = p;
		a.transitionRelation = new ArrayList<TransitionFunction>();
		a.transitionRelation.add(new TransitionFunction(p, 'a', 'Z', p, 'A'));
		a.transitionRelation.add(new TransitionFunction(p, 'a', 'Z', x, 'A'));
		a.transitionRelation.add(new TransitionFunction(p, 'a', 'A', p, 'A'));
		a.transitionRelation.add(new TransitionFunction(p, 'a', 'A', x, 'A'));
		a.transitionRelation.add(new TransitionFunction(x, Automata.EPSILON, 'A', p, 'A'));
		a.transitionRelation.add(new TransitionFunction(p, Automata.EPSILON, 'A', q, Automata.NOTHING));
		a.transitionRelation.add(new TransitionFunction(p, Automata.EPSILON, 'Z', q, Automata.NOTHING));
		a.transitionRelation.add(new TransitionFunction(q, 'b', 'A', q, Automata.LAMBDA));
		a.transitionRelation.add(new TransitionFunction(q, Automata.EPSILON, 'Z', r, Automata.NOTHING));
		return a;
	}

}
