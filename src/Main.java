import java.util.LinkedList;

public class Main {

    private static LinkedList<Fighter> fighterList;
    private static Arena arena;
    private static Fighter fighter;

    public static void main(String[]args) throws InterruptedException {

        fighterList = new LinkedList<Fighter>();

        for (int i = 1; i < 101; i++){
            fighter = new Fighter(i);
            fighterList.add(fighter);
        }

        fighterList.get(0).start();
        fighterList.get(1).start();
        arena = new Arena(fighterList.get(0), fighterList.get(1));
        arena.start();

        resultFight();
    }

    public static void resultFight() throws InterruptedException {
        synchronized (arena) {

            arena.wait();

            fighterList.remove(arena.los);
            if (fighterList.getLast() != arena.win) {
                fighterList.remove(arena.win);
                fighterList.add(arena.win);
            }

            if (fighterList.size() > 1) {
                fighterList.getFirst().start();

                arena = new Arena(fighterList.getLast(), fighterList.getFirst());
                arena.start();
                resultFight();
            }
            else {
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\tWINNER : " +
                        fighterList.getLast().toString() +
                        " WINS = " + fighterList.getFirst().getCountWins());
            }
        }
    }
}
