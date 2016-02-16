package de.tbelmega.automata.state;

import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

/**
 * Created by Thiemo on 03.02.2016.
 */
public class SimpleStateTest {


    @Test
    public void testThatStateDoesTransition() throws Exception {
        //arrange
        StateManager states = new StateManager();
        SimpleState s1 = states.getState(00);
        s1.addTransition('a', states.getState(99));

        //act
        SimpleState s2 = s1.doTransitionDeterministic('a');

        //assert
        assertEquals("99", s2.getStateId());
    }

    @Test
    public void testThatStateDoesTransitionChain() throws Exception {
        //arrange
        StateManager states = new StateManager();
        SimpleState s1 = states.getState(00);
        SimpleState s2 = states.getState(01);
        SimpleState s3 = states.getState(99);
        s1.addTransition('a', s2);
        s2.addTransition('b', s3);
        s2.addTransition('a', s1);

        //act
        SimpleState resultingState = s1.doTransitionDeterministic('a').doTransitionDeterministic('b');

        //assert
        assertEquals("99", resultingState.getStateId());
    }




}
