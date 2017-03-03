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

    @Override
    public void run() {


        synchronized (this) {
            if (fighter1 != null && fighter2 != null) {
                System.out.println("FIGHT");
                whoFirst();
            } else{
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
        //run();

       /* else {
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            run();
        }*/


    }

    public synchronized boolean joinFighter(Fighter fighter){

        if (fighter1==null){
            fighter1 = fighter;
            System.out.println("зашел боец с ид = " + fighter.getId());
            run();
            return true;
        }else if (fighter2==null){
            fighter2 = fighter;
            System.out.println("зашел боец с ид = " + fighter.getId());
            run();
            return true;
        }
        return false;
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


      /* if (!fighter2.bias()){
           hitting(fighter1, fighter2);
       }else if (!fighter1.bias()){
           System.out.println("fighter Увернулся");
           hitting(fighter2, fighter1);
       }else
           fighting(fighter1, fighter2);*/

       //fighting(fighter1, fighter2);

    }

    public void hitting(Fighter whoHit, Fighter whoDamage){

        if (whoDamage.damage(whoHit.hit())){
            fighting(whoDamage, whoHit);
        }else {
            dead(whoDamage);
        }
    }

    public void dead(Fighter fighter){
        System.out.println("fighter dead = " + fighter.getId());
        if (fighter1==fighter) {
            //Main.fightResult(fighter2, fighter1);

            win = fighter2;
            los = fighter1;

            fighter1 = null;
            fighter2.refreshHealth();
        }
        else {
            //Main.fightResult(fighter1, fighter2);
            win = fighter1;
            los = fighter2;

            fighter2 = null;
            fighter1.refreshHealth();
        }
        notify();
    }

    public boolean isFight(){
        if (fighter1==null || fighter2==null){
            return false;
        }
        else
            return true;
    }
}
