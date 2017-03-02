import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[]args) {

        Random random = new Random();
        Arena arena = new Arena();
        arena.start();

        Fighter fighter;

        for (int i = 1; i < 11;  i++){
            fighter = new Fighter(i, arena);
            fighter.start();
        }
        try {
            arena.join(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("arena live? " + arena.isAlive());

        /*Fighter fighter1 = new Fighter(1, arena);
        Fighter fighter2 = new Fighter(2, arena);
        Fighter fighter3 = new Fighter(3, arena);
        Fighter fighter4 = new Fighter(4, arena);
        Fighter fighter5 = new Fighter(5, arena);
        Fighter fighter6 = new Fighter(6, arena);
        fighter1.start();
        fighter2.start();
        fighter3.start();
        fighter4.start();
        fighter5.start();
        fighter6.start();*/

        /*Fighter fighter3 = new Fighter();
        Fighter fighter4 = new Fighter();
        Fighter fighter5 = new Fighter();*/

       /* Arena arena = new Arena();
        arena.start();*/

        List<Fighter> fighters = new ArrayList<Fighter>();





    }
}
