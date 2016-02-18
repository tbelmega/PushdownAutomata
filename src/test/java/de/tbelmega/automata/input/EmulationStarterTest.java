package de.tbelmega.automata.input;

import de.tbelmega.automata.PushdownAutomaton;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Map;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by Thiemo on 18.02.2016.
 */
public class EmulationStarterTest {

    private EmulationStarter starter;

    @BeforeTest
    public void readAutomatonFromFile(){
        starter = new EmulationStarter("example210.json");
    }


    @Test
    public void testThatInputIsParsed() throws Exception {
        //arrange
        JSONArray exampleAutomaton = starter.automatonRepresentation;
        JSONArray exampleInputs = starter.inputWords;

        //act
        JSONObject firstState = exampleAutomaton.getJSONObject(0);

        //assert
        assertTrue(firstState.has("name"));
        assertTrue(exampleInputs.length() > 0);
    }

    @Test
    public void testThatAutomatonHasStartState() throws Exception {
        //act
        PushdownAutomaton pda = starter.getAutomaton();

        //assert
        assertEquals("q1", pda.getStartState().getStateId());
    }

    @Test
    public void testThatAutomatonHasAcceptingStates() throws Exception {
        //act
        PushdownAutomaton pda = starter.getAutomaton();

        //assert
        assertTrue(pda.isAcceptingState("q4"));
        assertTrue(pda.isAcceptingState("q7"));
        assertFalse(pda.isAcceptingState("q1"));
    }

    @Test
    public void testThatAutomatonHasTransitions() throws Exception {
        //act
        PushdownAutomaton pda = starter.getAutomaton();

        //assert
        assertTrue(pda.hasTransition("q1", "-, -, $, q2"));
        assertFalse(pda.hasTransition("q1", "-, -, $, q1"));
    }

    @Test
    public void testThatAutomatonAccepts() throws Exception {
        //arrange
        PushdownAutomaton pda = starter.getAutomaton();

        //act & assert
        assertTrue(pda.emulate("aabb"));
        assertTrue(pda.emulate("aabbc"));
        assertTrue(pda.emulate("aabbcc"));
        assertTrue(pda.emulate("aaaabcccc"));
        assertTrue(pda.emulate(""));
    }

    @Test
    public void testThatAutomatonRejects() throws Exception {
        //arrange
        PushdownAutomaton pda = starter.getAutomaton();

        //act & assert
        assertFalse(pda.emulate("abb"));
        assertFalse(pda.emulate("aabc"));
        assertFalse(pda.emulate("bbcc"));
        assertFalse(pda.emulate("aaaabccc"));
        assertFalse(pda.emulate("a"));
    }

    @Test
    public void testThatAutomatonEmulatesAllInputWords() throws Exception {
        //arrange
        PushdownAutomaton pda = starter.getAutomaton();

        //act
        Map<String, Boolean> results = starter.emulateAll();

        //assert
        for (int i =0; i < starter.inputWords.length(); i++){
            String inputWord = starter.inputWords.getString(i);
            assertTrue(results.containsKey(inputWord));

            System.out.println(inputWord + " accepted? -> " + results.get(inputWord));
        }
    }

    @Test
    public void testMainMethodDoesNotThrowException() throws Exception {
        //arrange

        //act
        EmulationStarter.main(new String[]{"example210.json"});

        //assert
    }
}
