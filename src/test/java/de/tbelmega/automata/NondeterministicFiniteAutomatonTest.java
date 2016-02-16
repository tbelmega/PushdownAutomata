package de.tbelmega.automata;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by Thiemo on 03.02.2016.
 *
 * This class tests a Non-deterministic Finite Automaton m2, that should accept the language
 * L = {a^n, where n > 0}
 *
 * and a Non-deterministic Finite de.tbelmega.automata.Automaton m3, that should accept the language
 * L = {a^n, where n >= 0}.
 */
public class NondeterministicFiniteAutomatonTest {

    private NondeterministicFiniteAutomaton m2;
    private NondeterministicFiniteAutomaton m3;

    @BeforeClass
    public void createTestAutomata(){
        m2 = new NondeterministicFiniteAutomaton();
        m2.setStartState(00);
        m2.addAcceptState(99);
        m2.addTransition(00,'a', 98);
        m2.addTransition(00,'a', 00); // this transition adds non-determinism
        m2.addTransition(98,'a', 98);
        m2.addTransition(98,'-', 99); // this is an epsilon transition

        m3 = new NondeterministicFiniteAutomaton();
        m3.setStartState(00);
        m3.addAcceptState(99);
        m3.addTransition(00,'-', 99); // this is an epsilon transition
        m3.addTransition(00,'a', 00);
        m3.addTransition(99,'a', 99);
    }

    @Test
    public void testThatM1AcceptsWord() throws Exception {
        //arrange
        String inputWord = "aaa";

        //act
        boolean accepted = m2.emulate(inputWord);

        //assert
        assertTrue(accepted);
    }

    @Test
    public void testThatM1DoesNotAcceptEmptyWord() throws Exception {
        //arrange
        String inputWord = "";

        //act
        boolean accepted = m2.emulate(inputWord);

        //assert
        assertFalse(accepted);
    }

    @Test
    public void testThatM2AcceptsWord() throws Exception {
        //arrange
        String inputWord = "aaa";

        //act
        boolean accepted = m3.emulate(inputWord);

        //assert
        assertTrue(accepted);
    }

    @Test
    public void testThatM2AcceptsEmptyWord() throws Exception {
        //arrange
        String inputWord = "";

        //act
        boolean accepted = m3.emulate(inputWord);

        //assert
        assertTrue(accepted);
    }
}
