import java.util.ArrayList;
import java.util.List;

public class PianoFingeringGenerator {

    private static Keyboard keyboard;

    public static void main(String[] args) {

        Keyboard keyboard = Keyboard.getInstance();
        String[] noteStrings = {
                "C4", "D4", "E4", "F4", "G4"
        };

        List<Note> notes = new ArrayList<>();
        for(String str : noteStrings) {
            notes.add(new Note(str, keyboard));
        }

        int[] fingerPositions = new int[notes.size()];

        bruteForce(notes, 0, fingerPositions, -1);
    }

    private static int bruteForce(List<Note> notes, int noteIndex, int[] fingerPositions, int prevFinger) {

        // if finish reading every note, return
        if(noteIndex > notes.size())
            return 0;

        int minCost = Integer.MAX_VALUE;
        for(int finger = 1; finger <= 5; finger++) {

        }

        return minCost;
    }

}
