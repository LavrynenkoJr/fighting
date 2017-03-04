import java.util.Random;

public class Arena extends Thread {

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";

    private Random random = new Random();

    private Fighter fighter1;
    private Fighter fighter2;

    private Fighter winner;
    private Fighter loser;

    public Arena(Fighter fighter1, Fighter fighter2){
        this.fighter1 = fighter1;
        this.fighter2 = fighter2;
    }

    @Override
    public void run() {

        System.out.println("\t\t\tFIGHT");
        System.out.println("\tFighter " + fighter1.getId() + " vs " + "Fighter " + fighter2.getId());
        startFighting();
    }

    public void startFighting(){

        if (random.nextBoolean())
            fighting(fighter1, fighter2);
        else
            fighting(fighter2, fighter1);
    }

    public synchronized void fighting(Fighter fighter1, Fighter fighter2){

        if (fighter2.bias()){
            System.out.println("fighter " + fighter2.getId() + " - увернулся");
            fighting(fighter2, fighter1);
        }else {
            hitting(fighter1, fighter2);
        }
    }

    public void hitting(Fighter whoHit, Fighter whoDamage){

        if (whoDamage.damage(whoHit.hit())){
            fighting(whoDamage, whoHit);
        }else {
            dead(whoDamage);
        }
    }

    public void dead(Fighter deadFighter){
        System.out.println(ANSI_RED + "fighter dead = " + deadFighter.getId() + ANSI_RESET);
        if (fighter1 == deadFighter) {
            winner = fighter2;
            loser = fighter1;

            fighter2.refreshHealth();
        }
        else {
            winner = fighter1;
            loser = fighter2;

            fighter1.refreshHealth();
        }
        notify();
    }

    public Fighter getWinner() {
        return winner;
    }

    public Fighter getLoser() {
        return loser;
    }
}
