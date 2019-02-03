import Enums.Accidental;
import Enums.NoteType;

public class Note {

    // A, B, C ...
    private char noteLetter;

    // '#', 'b' or 'n'
    private Accidental accidental;

    private int noteOctave;

    // black or white key
    private NoteType noteType;

    // int representation of note
    private int noteNum;

    // number of beats for this note
    private int duration;


    // noteString example A4#:2
    public Note(String noteString) {
        String[] arr = noteString.split(":");
        parseNoteString(arr[0]);
        this.duration = Integer.parseInt(arr[1]);

        createNoteNum();
    }

    public Note(char letter, int octave, boolean isSharp) {

        noteLetter = letter;
        noteOctave = octave;
        if(isSharp) {
            accidental = Accidental.SHARP;
            noteType = NoteType.BLACK;
        }
        else {
            accidental = Accidental.NATURAL;
            noteType = NoteType.WHITE;
        }

        createNoteNum();
    }

    /**
     * Parse the String representation of a note A4# into noteLetter, Octave, Accidental
     * @param noteName string representation of note
     */
    private void parseNoteString(String noteName) {
        noteLetter = Character.toUpperCase(noteName.charAt(0));
        noteOctave = Character.getNumericValue(noteName.charAt(1));

        // may or may not be a sharp or flat after
        if(noteName.length() > 2) {

            if(noteName.charAt(3) == '#')
                accidental = Accidental.SHARP;
            else if(noteName.charAt(3) == 'b')
                accidental = Accidental.FLAT;

            // assume all notes with accidentals will be black (for now)
            // so user shouldn't be able to input something like Cb
            noteType = NoteType.BLACK;

        } else {
            // assume all natural notes are white
            accidental = Accidental.NATURAL;
            noteType = NoteType.WHITE;
        }
    }

    /**
     * Creates the integer representation of a note
     *
     * keep going down an noteOctave while our octaveInt is greater than one
     * then go down the keyboard one key at a time until we reach A0
     */
    private void createNoteNum() {
//        this.noteNum = Keyboard.getInstance().getDistanceTo(this);
    }

    public int getNoteNum() {
        return this.noteNum;
    }

    public int getDuration() {
        return this.duration;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(noteLetter);
        sb.append(noteOctave);

        if(accidental == Accidental.SHARP)
            sb.append('#');
        else if(accidental == Accidental.FLAT)
            sb.append('b');

        return sb.toString();
    }

}
