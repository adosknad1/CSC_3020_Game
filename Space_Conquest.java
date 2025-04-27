import java.util.Scanner;
import java.util.ArrayList;
// world class
// contains all the elements of the world, including player, planets, etc.
class World {
    public static Scanner scanner = new Scanner(System.in); // global scanner
    Planet[] planets; // array of all planets in the game
    // constructor
    World () {
        // initializing battles
        Battle sampleBattle = new Battle("Test");
        Battle BattleArr[] = new Battle[3];
        BattleArr[0] = sampleBattle;
        BattleArr[1] = sampleBattle;
        BattleArr[2] = sampleBattle;

        planets = new Planet[8];
        planets[0] = new Planet("Planet 1", BattleArr);
        planets[1] = new Planet("Planet 2", BattleArr);
        planets[2] = new Planet("Planet 3", BattleArr);
        planets[3] = new Planet("Planet 4", BattleArr);
        planets[4] = new Planet("Planet 5", BattleArr);
        planets[5] = new Planet("Planet 6", BattleArr);
        planets[6] = new Planet("Planet 7", BattleArr);
        planets[7] = new Planet("Planet 8", BattleArr);
    }
    void printPlanetsInfo() {
        for (int i = 0; i < 8; i++) {
            System.out.println((i + 1) + ": " + planets[i].getDisplayInfo());
        }
    }

    int takePlanetInput() {
        printPlanetsInfo();
        System.out.println("Enter 1-8 to go to planet 1 through 8, 9 to exit");
        int planetChoice = scanner.nextInt() - 1;
        return planetChoice;
    }

    boolean allPlanetsDefeated() {
        for (int i = 0; i < 8; i++) {
            if (!planets[i].isDefeated()) {
                return false;
            }
        }
        return true;
    }
    public static void main(String args[]) {
        World world = new World();
        while (!world.allPlanetsDefeated()) {
        int planet = world.takePlanetInput();
            if (planet < 8 && !world.planets[planet].isDefeated()) {
                world.planets[planet].enter();
            }
            else if (planet == 8) {
                System.out.println("Exiting");
                return;
            }
            else if (world.planets[planet].isDefeated()) {
                System.out.println("You have already conquered "  + world.planets[planet].name + ", please choose a different planet");
            }  
        }
    }
}

class Planet {
    String name;
    Battle battles[];
    int curBattle;
    Planet(String n, Battle[] BattlesArray) {
        name = n;
        battles = new Battle[3];
        battles[0] = BattlesArray[0];
        battles[1] = BattlesArray[1];
        battles[2] = BattlesArray[2];
        curBattle = 0;
    }
    boolean enter() {
        System.out.println("Welcome to the planet " + name);
        while (!isDefeated()) {
            if (enterBattle()) {
                System.out.println("Battle won!");
                curBattle++;
            }
            else {
                System.out.println("Battle incomplete, type 0 to try again or 1 to go to a different planet");
                int input = World.scanner.nextInt();
                if (input == 0) {
                    enterBattle();
                }
                else {
                    return false;
                }
            }
        }
        System.out.println("Planet " + name + " defeated");
        return true;
        
    }

    boolean enterBattle() {
        System.out.println("Enter this battle? type 0 to cancel 1 to continue");
        int input = World.scanner.nextInt();
        if (input == 0) {
            return false;
        }
        else {
            return battles[curBattle].enter();
        }
    }

    boolean isDefeated() {
        return curBattle >= 3;
    }

    String getDisplayInfo() {
        if (isDefeated()) {
            return name + ": defeated";
        }
        return name + ": " + (3 - curBattle) + " battles left";
    }
}

class Battle {
    String name;
    ArrayList<Ship> Enemies;
    Battle(String n) {
        name = n;
    }
    boolean enter() {
        System.out.println("Now entering battle with " + name);
        return true;
    }
    void add(Ship s) {
        Enemies.add(s);
    }
}

class Player {
    String name;
    int level, hp;
    Player(String n) {
        name = n;
        level = 1;
        hp = 100;
    }
}

class Ship {
    String name;
    int hp, maxHp;
    ArrayList<Attack> attacks;

    Ship(String n, int h, Attack attack) {
        name = n;
        hp = maxHp = h;
        attacks = new ArrayList<>();
        attacks.add(attack);
    }

    void attackWith(int damage) {
        hp = Math.max(0, hp - damage);
    }

    void heal(int health) {
        hp = Math.min(maxHp, hp + health);
    }

    boolean isDefeated() {
        return hp == 0;
    }
}

class Attack {
    String name;
    int damage;
    boolean hitsAll;

    Attack(String n, int d, boolean h) {
        name = n;
        damage = d;
        hitsAll = h;
    }


    String getInfoString() {
        String output = name + ": " + damage + " damage";
        if (hitsAll) {
            return output + " hits all enemies";
        }
        else {
            return output + " hits 1 enemy";
        }
    }

    void hit(Ship s) {
        s.attackWith(damage);
    }
}