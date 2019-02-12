import java.util.ArrayList;
import java.util.List;

public class PianoFingeringGenerator {

    private static Keyboard keyboard;
    private static Hand hand;

    public static void main(String[] args) {

        keyboard = Keyboard.getInstance();
        hand = new Hand(keyboard);
        String[] noteStrings = {
                "C4", "D4", "E4"
        };

        List<Note> notes = new ArrayList<>();
        for(String str : noteStrings) {
            notes.add(new Note(str, keyboard));
        }

        int[] fingerPositions = new int[notes.size()];

        bruteForce(notes, 0, fingerPositions, -1);

        for(int finger : fingerPositions)
            System.out.println(finger);
    }

    /*
    at each step, calculate the cost play the piece
    if at this step, you used fingers 1 to 5 to play the note

    5 possible scenarios in THIS step, choose the best one and return the cost
    then the recursive stack will unwind
     */
    private static int bruteForce(List<Note> notes, int noteIndex, int[] fingerPositions, int prevFinger) {

        // if finish reading every note, return
        if(noteIndex >= notes.size())
            return 0;

        int minCost = Integer.MAX_VALUE;
        int minCostFinger = -1;
        for(int finger = 1; finger <= 5; finger++) {

            int playCost = 0;
            // there's only a cost for playing the note if we aren't on the first note
            if(noteIndex > 0) {
                playCost = hand.getCostBetweenNotes(
                        notes.get(noteIndex),
                        notes.get(noteIndex - 1),
                        finger,
                        prevFinger);
            }
            int cost = playCost + bruteForce(notes, noteIndex + 1, fingerPositions, finger);

            if(cost < minCost) {
                minCost = cost;
                minCostFinger = finger;
            }
        }

        // add the min cost finger into the answer
        fingerPositions[noteIndex] = minCostFinger;
        return minCost;
    }

}
