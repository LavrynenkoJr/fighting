import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Main {

    public static List<Fighter> fighterList;
    private static Arena arena;

    static int whoNext = 0;

    public static Object locker = new Object();

    public static void main(String[]args) throws InterruptedException {

        Random random = new Random();
        arena = new Arena();
        arena.start();

        Fighter fighter;
        fighterList = new LinkedList<Fighter>();

        for (int i = 1; i < 11; i++){
            fighter = new Fighter(i, arena);
            fighterList.add(fighter);
        }

        startFighter();


      /*  for (int i = 1; i < 11;  i++){
            fighter = new Fighter(i, arena);
            fighter.start();
        }
        try {
            arena.join(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
*/
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

    }

    public static void startFighter() throws InterruptedException {

       synchronized (arena) {
           while (fighterList.size() > 1 && whoNext < fighterList.size()) {

               fighterList.get(whoNext).start();
               whoNext++;
               startFighter();
                arena.wait();
           }
       }




        /*int whoNext = 0;

        fighterList.get(whoNext).start();
        synchronized (arena) {
            if (arena.isFight()) {
                arena.wait();
            }else {
                startFighter();
            }
        }
*/

       /* synchronized (arena){
            //while (fighterList.size()>1){

                //fighterList.get(whoNext).start();
                whoNext++;
                arena.wait();
                startFighter();

            //}
        }

        startFighter();
*/
    }

}
