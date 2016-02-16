package de.tbelmega.automata;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by Thiemo on 03.02.2016.
 *
 * This class tests a Pushdown Automaton m4, that should accept the language
 * L = {a^n b^n},
 * and a Pushdown Automaton m5, that should accept the language
 * L = {ww^R | w € {0,1}* }.
 *
 * The examples are taken from Michael Sipser, Introduction to the Theory of Computation,
 * First Edition, Examples of Pushdown Automata (Example 2.9 and 2.11).
 */
public class PushdownAutomatonTest {

    private PushdownAutomaton m4;
    private PushdownAutomaton m5;

    @BeforeClass
    public void createTestAutomata(){
        m4 = new PushdownAutomaton();
        m4.setStartState(98);
        m4.addAcceptState(98);
        m4.addAcceptState(99);

        m4.addTransition(98,'-', '-', '$', 01);
        m4.addTransition(01,'a', '-', 'a', 01);
        m4.addTransition(01,'b', 'a', '-', 02);
        m4.addTransition(02,'b', 'a', '-', 02);
        m4.addTransition(02,'-', '$', '-', 99);

        m5 = new PushdownAutomaton();
        m5.setStartState(98);
        m5.addAcceptState(98);
        m5.addAcceptState(99);

        m5.addTransition(98,'-', '-', '$', 01);
        m5.addTransition(01,'a', '-', 'a', 01);
        m5.addTransition(01,'b', '-', 'b', 01);
        m5.addTransition(01,'-', '-', '-', 02);
        m5.addTransition(02,'a', 'a', '-', 02);
        m5.addTransition(02,'b', 'b', '-', 02);
        m5.addTransition(02,'-', '$', '-', 99);

    }

    @Test
    public void testThatM4AcceptsWord() throws Exception {
        //arrange
        String inputWord = "aaabbb";

        //act
        boolean accepted = m4.emulate(inputWord);

        //assert
        assertTrue(accepted);
    }

    @Test
    public void testThatM4DoesAcceptEmptyWord() throws Exception {
        //arrange
        String inputWord = "";

        //act
        boolean accepted = m4.emulate(inputWord);

        //assert
        assertTrue(accepted);
    }

    @Test
    public void testThatM4DoesNotAcceptWord() throws Exception {
        //arrange
        String inputWord = "abbb";

        //act
        boolean accepted = m4.emulate(inputWord);

        //assert
        assertFalse(accepted);
    }

    @Test
    public void testThatM5AcceptsWord() throws Exception {
        //arrange
        String inputWord = "aaabbbbaaa";

        //act
        boolean accepted = m5.emulate(inputWord);

        //assert
        assertTrue(accepted);
    }

    @Test
    public void testThatM5DoesNotAcceptWord() throws Exception {
        //arrange
        String inputWord = "abbb";

        //act
        boolean accepted = m5.emulate(inputWord);

        //assert
        assertFalse(accepted);
    }

}
