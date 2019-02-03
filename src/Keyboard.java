public class Keyboard {

    private static final String FIRST_NOTE = "A0";
    private static final String LAST_NOTE = "C8";

    private final Note[] keyboardNotes;

    private static Keyboard instance;

    private Keyboard() {
        keyboardNotes = new Note[88];
        fillKeys();
    }

    // fill array with note objects
    private void fillKeys() {



        int i = 0;
        while(i < keyboardNotes.length) {



            i++;
        }

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
