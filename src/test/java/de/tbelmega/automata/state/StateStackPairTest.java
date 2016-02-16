package de.tbelmega.automata.state;

import org.testng.annotations.Test;

import java.util.Set;
import java.util.Stack;

import static de.tbelmega.automata.Automaton.EPSILON;
import static org.testng.AssertJUnit.assertEquals;

/**
 * Created by Thiemo on 16.02.2016.
 */
public class StateStackPairTest {

    @Test
    public void testThatStateDoesTransitionWithEmptyStack() throws Exception {
        //arrange
        StateManager states = new StateManager();
        SimpleState s1 = states.getState(00);
        s1.addTransition('a', EPSILON, '$', states.getState(99));

        StateStackPair ssp1 = new StateStackPair(s1, new Stack<Character>());

        //act
        Set<StateStackPair> endStates = ssp1.doTransition('a');

        //assert
        for (StateStackPair ssp2: endStates){
            assertEquals("99", ssp2.state.getStateId());
            assertEquals('$', ssp2.peek());
        }
    }

    @Test
    public void testThatStateDoesTransitionWithNonEmptyStack() throws Exception {
        //arrange
        StateManager states = new StateManager();
        SimpleState s1 = states.getState(00);
        s1.addTransition('a', 'A', 'B', states.getState(99));

        Stack<Character> stack = new Stack<>();
        stack.push('A');
        StateStackPair ssp1 = new StateStackPair(s1, stack);

        //act
        Set<StateStackPair> endStates = ssp1.doTransition('a');

        //assert
        for (StateStackPair ssp2: endStates){
            assertEquals("99", ssp2.state.getStateId());
            assertEquals('B', ssp2.peek());
        }
    }

}
