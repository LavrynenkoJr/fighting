import java.util.LinkedList;

public class Main {

    private static LinkedList<Fighter> fighterList;
    private static Arena arena;
    private static Fighter fighter;

    public static void main(String[]args) throws InterruptedException {

        fighterList = new LinkedList<Fighter>();

        for (int i = 1; i < 11; i++){
            fighter = new Fighter(i);
            fighterList.add(fighter);
        }

        arena = new Arena(fighterList.get(0), fighterList.get(1));
        arena.start();

        resultFight();
    }

    public static void resultFight() throws InterruptedException {
        synchronized (arena) {

            arena.wait();

            fighterList.remove(arena.getLoser());
            if (fighterList.getLast() != arena.getWinner()) {
                fighterList.remove(arena.getWinner());
                fighterList.add(arena.getWinner());
            }

            if (fighterList.size() > 1) {
                arena = new Arena(fighterList.getFirst(), fighterList.getLast());
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
