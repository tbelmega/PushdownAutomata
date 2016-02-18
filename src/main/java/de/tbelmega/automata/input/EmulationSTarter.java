package de.tbelmega.automata.input;

import de.tbelmega.automata.PushdownAutomaton;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by Thiemo on 18.02.2016.
 */
public class EmulationStarter {
    final String input;
    final JSONArray automatonRepresentation;
    final JSONArray inputWords;
    private PushdownAutomaton automaton;

    public EmulationStarter(String filename) {
        input = FileUtil.readFile(filename);
        JSONObject content = new JSONObject(input);
        automatonRepresentation = content.getJSONArray("automaton");
        inputWords = content.getJSONArray("inputwords");
        automaton = createAutomaton(automatonRepresentation);
    }

    private PushdownAutomaton createAutomaton(JSONArray automatonRepresentation) {
        PushdownAutomaton theAutomaton = new PushdownAutomaton();

        for (int i = 0; i < automatonRepresentation.length(); i++){
            processStateRepresentation(automatonRepresentation.getJSONObject(i), theAutomaton);
        }

        return theAutomaton;
    }

    private void processStateRepresentation(JSONObject stateRepresentation, PushdownAutomaton theAutomaton) {
        addIfStartState(theAutomaton, stateRepresentation);
        addIfAcceptState(theAutomaton, stateRepresentation);
        addTransitions(theAutomaton, stateRepresentation);
    }

    private void addTransitions(PushdownAutomaton theAutomaton, JSONObject stateRepresentation) {
        JSONArray transitions = stateRepresentation.getJSONArray("transitions");
        for (int j =0; j < transitions.length(); j++){
            theAutomaton.addTransition(stateRepresentation.getString("name"), transitions.getString(j));
        }
    }

    private void addIfAcceptState(PushdownAutomaton theAutomaton, JSONObject stateRepresentation) {
        if (stateRepresentation.has("acceptingstate") &&
                stateRepresentation.getBoolean("acceptingstate")){
            theAutomaton.addAcceptState(stateRepresentation.getString("name"));
        }
    }

    private void addIfStartState(PushdownAutomaton theAutomaton, JSONObject stateRepresentation) {
        if (stateRepresentation.has("startstate") &&
                stateRepresentation.getBoolean("startstate")){
            theAutomaton.setStartState(stateRepresentation.getString("name"));
        }
    }

    public PushdownAutomaton getAutomaton() {
        return automaton;
    }

    public Map<String, Boolean> emulateAll() {
        Map<String, Boolean> myResults = new HashMap<>();

        for (int i =0; i < this.inputWords.length(); i++){
            String inputWord = this.inputWords.getString(i);
            boolean result = this.automaton.emulate(inputWord);
            myResults.put(inputWord, result);
        }

        return myResults;
    }

    /**
     * The main method takes as input parameter
     * the file name of a .json file with the specification of a pushdown automaton.
     * @param args
     */
    public static void main(String[] args) {
        EmulationStarter em = new EmulationStarter(args[0]);
        Map<String, Boolean> results = em.emulateAll();

        for (String inputWord: results.keySet()){
            System.out.println(inputWord + " accepted? -> " + results.get(inputWord));
        }
    }
}
