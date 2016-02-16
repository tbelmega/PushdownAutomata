package de.tbelmega.automata;

import de.tbelmega.automata.state.SimpleState;
import de.tbelmega.automata.state.StateManager;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Thiemo on 03.02.2016.
 */
public abstract class Automaton {

    /**
     * In this program, the empty character (epsilon) is symbolized by the dash character.
     */
    public static final char EPSILON = '-';


    /**
     * The state manager ensures that there is only one state with the same name.     *
     * Use stateManager.getState(name) to get the SimpleState object with a given name.
     */
    private StateManager stateManager = new StateManager();

    protected SimpleState startState;
    protected Set<String> acceptStates = new HashSet<>();

    /**
     * Emulate this automaton's computation of the input word,
     * depending on the type of automaton.
     * @return true, iff the automaton accepts the input word. false otherwise.
     */
    public abstract boolean emulate(String inputWord);

    /**
     * Set the given state as this automaton's start state.
     */
    public void setStartState(int stateName) {
        this.startState = this.stateManager.getState(stateName);
    }

    /**
     * Add a state to this automaton's list of accepted states.
     */
    public void addAcceptState(Integer stateName) {
        this.acceptStates.add(stateName.toString());
    }

    /**
     * Create a new transition to targetState. Store it in fromState.
     * This transition does not use stack operations.
     */
    public void addTransition(int fromStateName, char inputChar, int targetStateName) {
        this.addTransition(fromStateName, inputChar, EPSILON, EPSILON, targetStateName);
    }

    /**
     * Create a new transition to targetState with input and stack operations. Store it in fromState.
     */
    public void addTransition(int fromStateName, char inputChar, char popChar, char pushChar, int targetStateName) {
        SimpleState fromState = stateManager.getState(fromStateName);
        SimpleState targetState = stateManager.getState(targetStateName);
        fromState.addTransition(inputChar, popChar, pushChar, targetState);
    }
}
