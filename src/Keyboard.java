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

    // maps from a set of pair of fingers to the index in fingerPairMatrix which
    // gets a min and max distance for finger pair
    private final Map<Set<Integer>, Integer> fingerPairToMinMaxIndex;

    // shows the min and max distance for each finger pair
    private final int[][] fingerPairMatrix;

    private static Keyboard instance;

    private Keyboard() {
        noteToIndex = new HashMap<>();
        fingerPairToMinMaxIndex = new HashMap<>();
        fingerPairMatrix = new int[][]{
                {-7, 8},  // 1,2
                {-5, 9},  // 1,3
                {1, 8},   // 1,4
                {1, 10},  // 1,5
                {1, 5},   // 2,3
                {1, 6},   // 2,4
                {1, 8},   // 2,5
                {1, 3},   // 3,4
                {1, 7},   // 3,5
                {1, 4}    // 4,5
        };
        fillKeys();
    }

    /* cost depends on distance b/w notes, and whether or not
     the distance is outside of the range of the given two fingers

     if the next note is ahead of the previous note, means the next finger
     will also be ahead of the previous finger
     if next finger is ahead of prev finger, use the second value in the array as the max distance
     if its out of range, return a very high cost to move hand (for now just return max value)

     for now, the cost will just be the distance b/w the notes

     if you're using the same finger, the cost will be the distance that your hand must move
        if its the same note twice, moves 0 distance, so 0 cost
        if its different notes, get distance b/w notes
    */
    public int getCostBetweenNotes(Note nextNote, Note prevNote, int nextFinger, int prevFinger) {

        Set<Integer> fingerPair = new HashSet<>();
        fingerPair.add(nextFinger);
        fingerPair.add(prevFinger);

        // get the min and max range
        int[] range = fingerPairMatrix[fingerPairToMinMaxIndex.get(fingerPair)];

        int noteDistance = nextNote.distanceFrom(prevNote);

        // next note is ahead of previous note
        if (noteDistance > 0) {

            // if its out of range, return max cost FOR NOW
            if (noteDistance > range[1])
                return Integer.MAX_VALUE;
            else
                return noteDistance;

        } else if (noteDistance < 0) {

            if (noteDistance < range[0])
                return Integer.MAX_VALUE;
            else
                return Math.abs(noteDistance);

        } else {

            // for now...
            return Integer.MAX_VALUE;
        }
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
