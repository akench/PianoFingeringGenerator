public class PianoFingeringGenerator {

    public static void main(String[] args) {

        Keyboard keyboard = Keyboard.getInstance();
        for(Note n : keyboard.keys) {
            System.out.println(n.toString());
        }
    }
}
