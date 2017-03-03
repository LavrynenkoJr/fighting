import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Main {

    private static LinkedList<Fighter> fighterList;
    private static Arena arena;
    private static Fighter fighter;

    public static void main(String[]args) throws InterruptedException {

        arena = new Arena();
        arena.start();

        fighterList = new LinkedList<Fighter>();

        for (int i = 1; i < 11; i++){
            fighter = new Fighter(i, arena);
            fighterList.add(fighter);
        }

        fighterList.get(0).start();
        fighterList.get(1).start();

        resultFight();

    }

    public static void resultFight(){
        synchronized (arena) {
            try {
                arena.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            fighterList.remove(arena.los);
            if (fighterList.getLast() != arena.win) {
                fighterList.remove(arena.win);
                fighterList.add(arena.win);
            }

            if (fighterList.size()>1) {
                fighterList.getFirst().start();
                resultFight();
            }
            else
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\tFINISH WINNER = " +
                        fighterList.getLast().toString() +
                        " WINS = " + fighterList.getFirst().getCountWins() );
        }
    }
}
