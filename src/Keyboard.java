public class Keyboard {

    private final char FIRST_NOTE_LETTER = 'A';
    private final int FIRST_NOTE_OCTAVE = 0;
    private final char LAST_NOTE_LETTER = 'C';
    private final char LAST_NOTE_OCTAVE = 8;

    public final Note[] keys;

    private static Keyboard instance;

    private Keyboard() {
        keys = new Note[88];
        fillKeys();
    }

    // fill array with note objects
    // assume sharps
    private void fillKeys() {

        char curLetter = FIRST_NOTE_LETTER;
        int curOctave = FIRST_NOTE_OCTAVE;
        boolean isSharp = false;

        int i = 0;
        while(i < keys.length) {

            // if note is valid, add to keyboard
            if(isNoteValid(curLetter, isSharp)) {
                Note note = new Note(curLetter, curOctave, isSharp);
                keys[i] = note;
                i++;
            }

            // go to next note
            // if not sharp, just make note sharp,
            // else increment the note,
            // if note is C after incrementing, increment octave
            // if note is ahead of G after incrementing, go back to A
            if(!isSharp) {
                isSharp = true;
            } else {
                isSharp = false;
                curLetter++;
                if(curLetter == 'C') {
                    curOctave++;
                } else if(curLetter > 'G') {
                    curLetter = 'A';
                }
            }
        }

    }


    private boolean isNoteValid(char letter, boolean isSharp) {

        if(isSharp && (letter == 'B' || letter == 'E')) {
            return false;
        }
        return true;
    }

    public static Keyboard getInstance() {

        if(instance == null) {
            instance = new Keyboard();
        }

        return instance;
    }

    public int getDistanceTo(Note note) {
        return 0;
    }

}
