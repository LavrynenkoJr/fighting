import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class Fighter extends Thread {

    private Random random = new Random();
    private final Arena arena;

    private int countWins;

    private int strength;
    private int dexterity;
    private int intuition;
    private int health = 100;

    private int id;

    public Fighter(int id, Arena arena){
        this.id = id;
        this.arena = arena;
    }

    @Override
    public void run() {
        initParams();
        joinToArena();
    }

    public void joinToArena(){
        synchronized (arena){
            if (!arena.joinFighter(this)) {
                try {
                    arena.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                joinToArena();
            }
        }
    }

    public void refreshHealth(){
        this.health = 100;
        countWins++;
    }

    public void initParams(){

        strength = random.nextInt(100)+1;
        dexterity = random.nextInt(100)+1;
        intuition = random.nextInt(100)+1;

        double sum = strength + dexterity + intuition;
        double koef = (50/sum);

        double str = strength*koef;
        double dex = dexterity*koef;
        double intu = intuition*koef;

        strength = (int) Math.round(str);
        dexterity = (int) Math.round(dex);
        intuition = (int) Math.round(intu);

        if (strength+dexterity+intuition > 50){
            System.out.println("shit");
            strength = (int) Math.floor(str);
            if (strength+dexterity+intuition > 50){
                System.out.println("shit");
                dexterity = (int) Math.floor(dex);
            }
        }

        System.out.println("fighter id = " + id + " strength = " + strength + " dexter = " + dexterity + " intui = " + intuition);

    }

    public int hit(){
        if ((random.nextInt(100)+1) < intuition)
            return strength*2;
        else {
            return strength;
        }
    }

    public boolean bias(){
        if ((random.nextInt(100)+1) < dexterity)
            return true;
        else
            return false;
    }

    public int getStrength() {
        if (strength<0){
            strength = 0;
        }
        return strength;
    }

    public boolean damage(int hit){

        System.out.println("hit = " + hit + " to fighter id = " + id);

        health -= hit;

        if (health > 0)
            return true;
        else if (health <= 0){
            return false;
        }else return false;
    }

    @Override
    public long getId() {
        return id;
    }

    public int getCountWins() {
        return countWins;
    }

    @Override
    public String toString() {
        return "Fighter id = " + id + " strength = " + strength + " dexterity = " + dexterity + " intuition = " + intuition;
    }
}
