import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Keyboard {

    private static final char FIRST_NOTE_LETTER = 'A';
    private static final int FIRST_NOTE_OCTAVE = 0;
    private static final int NUM_KEYS = 88;

    // maps from the note object to the note number (used for calculating distance b/w notes)
    private final Map<Note, Integer> noteToIndex;

    // maps from a set of pair of fingers to the index in fingerPairMatrix which
    // gets a min and max distance for finger pair
    private final Map<Set<Integer>, Integer> fingerPairToMinMaxIndex;

    // shows the min and max distance for each finger pair
    private final int[][] fingerPairMatrix;

    private static Keyboard instance;

    private Keyboard() {
        noteToIndex = new HashMap<>();
        fingerPairToMinMaxIndex = new HashMap<>();
        fingerPairMatrix = new int[][] {
                {-7, 8},  // 1,2
                {-5, 9},  // 1,3
                {1, 8},   // 1,4
                {1, 10},  // 1,5
                {1, 5},   // 2,3
                {1, 6},   // 2,4
                {1, 8},   // 2,5
                {1, 3},   // 3,4
                {1, 7},   // 3,5
                {}
        };
        fillKeys();
        fillFingerRanges();
    }

    /*
    Singleton
     */
    public static Keyboard getInstance() {

        if(instance == null) {
            instance = new Keyboard();
        }

        return instance;
    }


    // fill array with note objects
    // assume sharps
    private void fillKeys() {

        char curLetter = FIRST_NOTE_LETTER;
        int curOctave = FIRST_NOTE_OCTAVE;
        boolean isSharp = false;

        int i = 0;
        while(i < NUM_KEYS) {

            // if note is valid, add to keyboard
            if(isNoteValid(curLetter, isSharp)) {
                Note note = new Note(curLetter, curOctave, isSharp);
                noteToIndex.put(note, i);
                i++;
            }

            // go to next note
            // if not sharp, just make note sharp,
            if(!isSharp) {
                isSharp = true;
            } else {
                // else increment the note,
                isSharp = false;
                curLetter++;
                // if note is C after incrementing, increment octave
                if(curLetter == 'C') {
                    curOctave++;
                }
                // if note is ahead of G after incrementing, go back to A
                else if(curLetter > 'G') {
                    curLetter = 'A';
                }
            }
        }
    }

    private void fillFingerRanges() {

        FingerPair p12 = new FingerPair(1, 2);
        fingerPairToMinMaxRange.put(p12, new int[]{-7, 8});

        FingerPair p13 = new FingerPair(1, 3);
        fingerPairToMinMaxRange.put(p13, new int[]{-5, 9});

        FingerPair p14 = new FingerPair(1, 4);
        fingerPairToMinMaxRange.put(p14, new int[]{1, 8});

        FingerPair p15 = new FingerPair(1, 5);
        fingerPairToMinMaxRange.put(p15, new int[]{1, 10});

        FingerPair p23 = new FingerPair(2, 3);
        fingerPairToMinMaxRange.put(p23, new int[]{1, 5});

        FingerPair p24 = new FingerPair(2, 4);
        fingerPairToMinMaxRange.put(p24, new int[]{1, 6});

        FingerPair p25 = new FingerPair(2, 5);
        fingerPairToMinMaxRange.put(p25, new int[]{1, 8});

        FingerPair p34 = new FingerPair(3, 4);
        fingerPairToMinMaxRange.put(p34, new int[]{1, 3});

        FingerPair p35 = new FingerPair(3, 5);
        fingerPairToMinMaxRange.put(p35, new int[]{1, 7});

        FingerPair p45 = new FingerPair(4, 5);
        fingerPairToMinMaxRange.put(p45, new int[]{});

    }


    private boolean isNoteValid(char letter, boolean isSharp) {
        if(isSharp && (letter == 'B' || letter == 'E')) {
            return false;
        }
        return true;
    }


    // cost depends on distance b/w notes, and whether or not
    // the distance is outside of the range of the given two fingers
    public int getCostBetweenNotes(Note curNote, Note prevNote, int curFinger, int prevFinger) {

        return 0;

    }


}
