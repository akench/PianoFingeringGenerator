import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Keyboard {

    private static final char FIRST_NOTE_LETTER = 'A';
    private static final int FIRST_NOTE_OCTAVE = 0;
    private static final int NUM_KEYS = 88;

    // maps from the note object to the note number (used for calculating distance b/w notes)
    private final Map<Note, Integer> noteToIndex;

    private static Keyboard instance;

    private Keyboard() {
        noteToIndex = new HashMap<>();



        fillKeys();
    }


    /*
    Singleton
     */
    public static Keyboard getInstance() {

        if (instance == null) {
            instance = new Keyboard();
        }

        return instance;
    }


    public int toInt(Note note) {
        return noteToIndex.get(note);
    }


    /*
    Fill the keyboard representation with note objects

     */
    private void fillKeys() {

        char curLetter = FIRST_NOTE_LETTER;
        int curOctave = FIRST_NOTE_OCTAVE;
        boolean isSharp = false;

        int i = 0;
        while (i < NUM_KEYS) {

            // if note is valid, add to keyboard
            if (isNoteValid(curLetter, isSharp)) {
                Note note = new Note(curLetter, curOctave, isSharp);
                noteToIndex.put(note, i);
                i++;
            }

            // go to next note
            // if not sharp, just make note sharp,
            if (!isSharp) {
                isSharp = true;
            } else {
                // else increment the note,
                isSharp = false;
                curLetter++;
                // if note is C after incrementing, increment octave
                if (curLetter == 'C') {
                    curOctave++;
                }
                // if note is ahead of G after incrementing, go back to A
                else if (curLetter > 'G') {
                    curLetter = 'A';
                }
            }
        }
    }

    /*
    A note is valid if it is not B# or E#, since these notes do not exist in
    my simple representation of a piano keyboard. doesn't include enharmonic notes
     */
    private boolean isNoteValid(char letter, boolean isSharp) {
        if (isSharp && (letter == 'B' || letter == 'E')) {
            return false;
        }
        return true;
    }

//    private void fillFingerRanges() {
//
//        FingerPair p12 = new FingerPair(1, 2);
//        fingerPairToMinMaxRange.put(p12, new int[]{-7, 8});
//
//        FingerPair p13 = new FingerPair(1, 3);
//        fingerPairToMinMaxRange.put(p13, new int[]{-5, 9});
//
//        FingerPair p14 = new FingerPair(1, 4);
//        fingerPairToMinMaxRange.put(p14, new int[]{1, 8});
//
//        FingerPair p15 = new FingerPair(1, 5);
//        fingerPairToMinMaxRange.put(p15, new int[]{1, 10});
//
//        FingerPair p23 = new FingerPair(2, 3);
//        fingerPairToMinMaxRange.put(p23, new int[]{1, 5});
//
//        FingerPair p24 = new FingerPair(2, 4);
//        fingerPairToMinMaxRange.put(p24, new int[]{1, 6});
//
//        FingerPair p25 = new FingerPair(2, 5);
//        fingerPairToMinMaxRange.put(p25, new int[]{1, 8});
//
//        FingerPair p34 = new FingerPair(3, 4);
//        fingerPairToMinMaxRange.put(p34, new int[]{1, 3});
//
//        FingerPair p35 = new FingerPair(3, 5);
//        fingerPairToMinMaxRange.put(p35, new int[]{1, 7});
//
//        FingerPair p45 = new FingerPair(4, 5);
//        fingerPairToMinMaxRange.put(p45, new int[]{});
//
//    }

}
