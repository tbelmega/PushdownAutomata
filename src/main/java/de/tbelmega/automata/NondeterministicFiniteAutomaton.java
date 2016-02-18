package de.tbelmega.automata;

import de.tbelmega.automata.state.State;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Thiemo on 16.02.2016.
 */
public class NondeterministicFiniteAutomaton extends Automaton {


    /**
     * Computation of a non-deterministic finite automatonRepresentation:
     * 1 - Execute that transitions from all the current states, that correspond to the current input character.
     * 2 - Repeat (1) with the next set of current states and the next character, until the input word is finished.
     * 3 - When the input word is finished, create the intersection of the sets of current states and accepting states.
     * 4 - Reject iff the intersection is empty, accept otherwise.
     */
    @Override
    public boolean emulate(String inputWord) {
        Set<State> startStates = evaluateStartStates();

        Set<State> endStates = processInputWord(inputWord, startStates);

        boolean accept = endStatesContainsAnAcceptingState(endStates);

        return accept;
    }

    /**
     * Returns a set with the start state and all states that can be reached with epsilon transitions from the start state.
     */
    private Set<State> evaluateStartStates() {
        Set<State> startStates = new HashSet<>();
        startStates.add(startState);
        int numberOfStates = 0;
        int newNumberOfStates = 1;

        while (numberOfStates != newNumberOfStates){
            startStates.addAll(getAllTargetStates(startStates, EPSILON));
            numberOfStates = newNumberOfStates;
            newNumberOfStates = startStates.size();
        }
        return startStates;
    }

    /**
     * Processes the input word by executing iteratively
     * - all possible transitions with the current input character
     * - all possible epsilon transitions after that.
     */
    protected Set<State> processInputWord(String inputWord, Set<State> currentStates) {
        for (int i = 0; i < inputWord.length(); i++) {
            char inputCharacter = inputWord.charAt(i);
            currentStates = getAllTargetStates(currentStates, inputCharacter);
            currentStates.addAll(getAllTargetStates(currentStates, EPSILON));
        }
        return currentStates;
    }

    /**
     * Evaluate all target states that can be reached from all the current states with the inputCharacter.
     */
    protected Set<State> getAllTargetStates(Set<? extends State> currentStates, char inputCharacter) {
        Set<State> nextStates = new HashSet<>();
        for (State s: currentStates){
            nextStates.addAll(s.doTransition(inputCharacter));
        }
        return nextStates;
    }

    /**
     * Evaluate if the set of end states contains at least one accepting state.
     */
    protected boolean endStatesContainsAnAcceptingState(Set<? extends State> endStates) {
        for (State s: endStates){
            if (acceptStates.contains(s.getStateId())) return true;
        }
        return false;
    }
}
