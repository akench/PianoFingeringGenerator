import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Hand {

    private Keyboard keyboard;

    // maps from a set of pair of fingers to an array with the min and max stretch distance
    // for that pair
    private final Map<Set<Integer>, int[]> fingerPairToRange;

    public Hand(Keyboard keyboard) {
        this.keyboard = keyboard;
        fingerPairToRange = new HashMap<>();

        addRangeForFingerPair(1,2, -7, 8);
        addRangeForFingerPair(1,3,-5,9);
        addRangeForFingerPair(1,4,1,8);
        addRangeForFingerPair(1,5,1,10);
        addRangeForFingerPair(2,3,1,5);
        addRangeForFingerPair(2,4,1,6);
        addRangeForFingerPair(2,5,1,8);
        addRangeForFingerPair(3,4,1,3);
        addRangeForFingerPair(3,5,1,7);
        addRangeForFingerPair(4,5,1,4);
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

        // get the min and max range
        int[] range = this.getRangeForFingerPair(nextFinger, prevFinger);

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
    Puts a given pair of fingers and its min max range into the Map
     */
    private void addRangeForFingerPair(int finger1, int finger2, int min, int max) {

        Set<Integer> pair = new HashSet<>();
        pair.add(finger1);
        pair.add(finger2);

        int[] range = {min, max};

        fingerPairToRange.put(pair, range);
    }

    private int[] getRangeForFingerPair(int finger1, int finger2) {

        int[] none = {0,0};
        // FOR NOW, if the fingers are the same, range is one
        if(finger1 == finger2)
            return none;

        Set<Integer> pair = new HashSet<>();
        pair.add(finger1);
        pair.add(finger2);

        return fingerPairToRange.get(pair);
    }
}
