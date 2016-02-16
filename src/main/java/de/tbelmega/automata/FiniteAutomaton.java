package de.tbelmega.automata;

import de.tbelmega.automata.state.SimpleState;

/**
 * Created by Thiemo on 16.02.2016.
 */
public class FiniteAutomaton extends Automaton {

    /**
     * Computation of a finite automaton:
     * - Execute that transition from the current state, that corresponds to the current input character.
     * - Repeat with the next current state and the next character, until the input word is finished.
     * - When the input word is finished, check if the current state is an accepting state.
     * - Accept iff true, reject otherwise.
     */
    @Override
    public boolean emulate(String inputWord) {
        SimpleState currentState = startState;

        for (int i = 0; i < inputWord.length(); i++) {
            char inputCharacter = inputWord.charAt(i);
            currentState = currentState.doTransitionDeterministic(inputCharacter);
        }
        return acceptStates.contains(currentState.getStateId());
    }
}
