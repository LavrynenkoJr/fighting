import java.util.Random;

/**
 * Created by java-1-04 on 01.03.2017.
 */
public class Arena extends Thread {

    Random random = new Random();

    Fighter fighter1;
    Fighter fighter2;

    Fighter win;
    Fighter los;

    public Arena(Fighter fighter1, Fighter fighter2){
        this.fighter1 = fighter1;
        this.fighter2 = fighter2;
    }

    @Override
    public void run() {

        System.out.println("FIGHT");
        whoFirst();

    }

    public void whoFirst(){

        if (random.nextBoolean())
            fighting(fighter1, fighter2);
        else
            fighting(fighter2, fighter1);
    }

    public synchronized void fighting(Fighter fighter1, Fighter fighter2){

        if (fighter2.bias()){
            System.out.println("увернулся " + fighter2.getId());
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
        System.out.println("fighter dead = " + deadFighter.getId());
        if (fighter1 == deadFighter) {
            win = fighter2;
            los = fighter1;

            fighter2.refreshHealth();
        }
        else {
            win = fighter1;
            los = fighter2;

            fighter1.refreshHealth();
        }
        notify();
    }

    public void setFighter(Fighter fighter1, Fighter fighter2){
        this.fighter1 = fighter1;
        this.fighter2 = fighter2;
        run();
    }
}
