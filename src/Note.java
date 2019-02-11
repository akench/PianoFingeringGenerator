import Enums.Accidental;
import Enums.NoteType;

public class Note {

    // A B C D E F G
    private char noteLetter;

    // '#', 'b' or 'n'
    private Accidental accidental;

    // octave number of note
    private int noteOctave;

    // black or white key
    private NoteType noteType;

    // dependency injected
    private Keyboard keyboard;


    // noteString example A4#
    public Note(String noteString, Keyboard keyboard) {
        parseNoteString(noteString);
        this.keyboard = keyboard;
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

    @Override
    public int hashCode() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.accidental);
        sb.append(this.noteOctave);
        sb.append(this.noteLetter);
        return sb.toString().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || !(obj instanceof Note))
            return false;

        Note n = (Note)obj;
        return this.accidental == n.accidental
                && this.noteOctave == n.noteOctave
                && this.noteLetter == n.noteLetter;
    }

    public int distanceFrom(Note n) {
        return keyboard.toInt(this) - keyboard.toInt(n);
    }
}
