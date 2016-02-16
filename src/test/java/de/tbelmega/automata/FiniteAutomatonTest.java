package de.tbelmega.automata;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by Thiemo on 03.02.2016.
 *
 * This class tests a Finite Automaton, that should accept the language
 * L = {a^n, where n > 0}.
 */
public class FiniteAutomatonTest {

    private FiniteAutomaton m1;

    @BeforeClass
    public void createTestAutomaton(){
        m1 = new FiniteAutomaton();
        m1.setStartState(00);
        m1.addAcceptState(99);
        m1.addTransition(00,'a', 99);
        m1.addTransition(99,'a', 99);
    }

    @Test
    public void testThatWordIsAccepted() throws Exception {
        //arrange
        String inputWord = "aaa";

        //act
        boolean accepted = m1.emulate(inputWord);

        //assert
        assertTrue(accepted);
    }

    @Test
    public void testThatEmptyWordIsNotAccepted() throws Exception {
        //arrange
        String inputWord = "";

        //act
        boolean accepted = m1.emulate(inputWord);

        //assert
        assertFalse(accepted);
    }
}
