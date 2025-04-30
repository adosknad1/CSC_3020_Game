import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
// world class
// contains all the elements of the world, including player, planets, etc.
class World {
    public static Scanner scanner = new Scanner(System.in); // global scanner
    public static Random random = new Random();
    Planet[] planets; // array of all planets in the game
    static PlayerCharacter player;

    // constructor
    World () {
        player = new PlayerCharacter("Dakota");

        // initializing battles
        Battle sampleBattle = new Battle("Test");
        Battle BattleArr[] = new Battle[3];

        // initializing attack
        Attack laserShot = new Attack("Laser Shot", 10); // Laser Shot does 10 dmg and hits one enemy
        Attack doubleLaser = new MultiAttack("Multi Laser", 10, 2); // Multi Laser does 10 damage and hits one enemy twice
        Attack laserArray = new Attack("Laser Array", 10, true); // Laser Array does 10 damage and hits all enemies

        Ship commander = new Ship("Commander", 150, laserShot.copyAttack());
        commander.add(doubleLaser.copyAttack());
        commander.add(laserArray.copyAttack());

        player.activeShips.add(commander.copyShip());
        Ship grunt = new Ship("Grunt", 25, laserShot);
        Ship doubleShotGrunt = new Ship("Double Shot Grunt", 25, doubleLaser.copyAttack());
        Ship laserArrayGrunt = new Ship("Laser Array Grunt", 25, laserArray.copyAttack());
        Ship armorGrunt = new Ship("Armor Grunt", 25, laserShot.copyAttack(), 5);
        Ship bossGrunt = new Ship("Boss Grunt", 50, laserArray.copyAttack());
        bossGrunt.add(doubleLaser.copyAttack());

        
        Battle battle1 = new Battle("Sample Battle");
        battle1.add(grunt.copyShip());
        battle1.add(grunt.copyShip());
        
        planets = new Planet[8];
        planets[0] = new Planet("Planet 1");
        planets[0].add(battle1);

        planets[1] = new Planet("Planet 2");
        planets[2] = new Planet("Planet 3");
        planets[3] = new Planet("Planet 4");
        planets[4] = new Planet("Planet 5");
        planets[5] = new Planet("Planet 6");
        planets[6] = new Planet("Planet 7");
        planets[7] = new Planet("Planet 8");
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
    ArrayList<Battle> battles;
    int curBattle;
    Planet(String n) {
        name = n;
        battles = new ArrayList<>();
        curBattle = 0;
    }

    void add(Battle battle) {
        battles.add(battle);
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
            return battles.get(curBattle).enter();
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
    ArrayList<Ship> enemies;
    Battle(String n) {
        name = n;
        enemies = new ArrayList<>();
    }

    void resetEnemies() {
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).hp = enemies.get(i).maxHp;
        }
    }

    boolean enter() {
        System.out.println("Now entering battle with " + name);
        while (friendlyTurn()) {
            if (!enemyTurn()) {
                break;
            }
        }
        if (playerDefeated()) {
            System.out.println("You lost!");
            return false;
        }
        else {
            System.out.println("You won!");
            return true;
        }
    }

    boolean friendlyTurn() {
        for (int i = 0; i < World.player.activeShips.size(); i++) {
            Ship curShip = World.player.activeShips.get(i);
            curShip.performChosenAttack(enemies);
            if (playerDefeated() || enemiesDefeated()) {
                return false;
            }
        }
        return true;
    }

    boolean enemyTurn() {
        for (int i = 0; i < enemies.size(); i++) {
            Ship curShip = enemies.get(i);
            curShip.performRandomAttack(World.player.activeShips);
            if (playerDefeated() || enemiesDefeated()) {
                return false;
            }
        }
        return true;
    }

    boolean enemiesDefeated() {
        for (int i = 0; i < enemies.size(); i++) {
            if (!enemies.get(i).isDefeated()) {
                return false;
            } 
        }
        return true;
    }

    boolean playerDefeated() {

        for (int i = 0; i < World.player.activeShips.size(); i++) {
            if (!World.player.activeShips.get(i).isDefeated()) {
                return false;
            } 
        }
        return true;
    }

    void add(Ship s) {
        enemies.add(s);
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
    // ship name used in info string
    String name;

    // current hp, maximum hp, armor (flat resistance to all attacks)
    int hp, maxHp, armor, level;
    ArrayList<Attack> attacks;

    Ship(String n, int h, Attack attack) {
        name = n;
        hp = maxHp = h;
        attacks = new ArrayList<>();
        attacks.add(attack);
        armor = 0;
        level = 1;
    }

    Ship(String n, int h, Attack attack, int l) {
        name = n;
        hp = maxHp = h;
        attacks = new ArrayList<>();
        attacks.add(attack);
        armor = 0;
        level = l;
    }

    Ship(String n, int h, Attack attack, int a, int l) {
        name = n;
        hp = maxHp = h;
        attacks = new ArrayList<>();
        attacks.add(attack);
        armor = a;
        level = l;
    }

    void attackWith(int damage) {
        damage -= armor;
        hp = Math.max(0, hp - damage);
        System.out.println(name + " took " + damage + " damage (" + armor + " blocked with armor)");
    }

    void heal(int health) {
        hp = Math.min(maxHp, hp + health);
    }

    boolean isDefeated() {
        return hp == 0;
    }

    int chooseRandomAttack() {
        return (World.random.nextInt(attacks.size()));
    }

    int chooseRandomTarget(ArrayList<Ship> targets) {
        return World.random.nextInt(targets.size());
    }

    void add(Attack a) {
        attacks.add(a);
    }

    void levelUp() {
        level++;
        maxHp *= 1.1;
        for (int i = 0; i < attacks.size(); i++) {
            attacks.get(i).levelUp();
        }

        if (level % 3 == 0) {
            armor++;
        }
    }

    void levelUp(int levels) {
        
        maxHp *= Math.pow(1.1, levels);
        for (int i = 0; i < attacks.size(); i++) {
            attacks.get(i).levelUp(levels);
        }
    for (int i = 0; i < levels; i++) {}
        level++;
        if (level % 3 == 0) {
            armor++;
        }
    }

    Ship copyShip() {
        Ship newShip = new Ship(name, maxHp, attacks.get(0), armor, level);
        for (int i = 1; i < attacks.size(); i++) {
            newShip.add(attacks.get(i).copyAttack());
        }
        return newShip;   
    }

    String getInfoString(){
        String output = name + " hp: " + hp + " armor: " + armor + " level: " + level; 
        return output;
    }

    void listAttacks() {
        for (int i = 0; i < attacks.size(); i++) {
            System.out.println((i + 1) + ": " + attacks.get(i).getInfoString());
        }
        System.out.println((attacks.size() + 1) + ": cancel");
    }

    boolean performChosenAttack(ArrayList<Ship> targets) {
        System.out.println("Choose attack: ");
        int attack = chooseAttack();

        System.out.println("Choose target: ");
        int target = chooseTarget(targets);

        if (attack == attacks.size() || target == targets.size()) {
            return false;
        }

        attacks.get(attack).hit(targets.get(target));
        return true;
    }

    boolean performRandomAttack(ArrayList<Ship> targets) {
        int attack = chooseRandomAttack();
        int target = chooseRandomTarget(targets);

        if (attack == attacks.size() || target == targets.size()) {
            return false;
        }

        attacks.get(attack).hit(targets.get(target));
        return true;
    }

    int chooseAttack() {
        listAttacks();
        int attack = World.scanner.nextInt() - 1;
        if (attack < 0 || attack >= attacks.size()) {
            System.out.println("Index out of bounds, choose from the listed numbers");
            return chooseAttack();
        }
        return attack;
    }

    int chooseTarget(ArrayList<Ship> targets) {
        for (int i = 0; i < targets.size(); i++) {
            System.out.println((i + 1) + ": " + targets.get(i).getInfoString());
            
        }
        int ship = World.scanner.nextInt() - 1;
        if (ship < 0 || ship > targets.size()) {
            System.out.println("Invalid index, choose from the listed numbers");
        }
        return ship;
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

   Attack(String n, int d) {
        name = n;
        damage = d;
        hitsAll = false;
    }


    String getInfoString() {
        String output = name + ": " + damage + " damage";

        if (hitsAll) {
            return output + " (hits all enemies)";
        }
        else {
            return output + " (hits 1 enemy)";
        }
    }

    void hit(Ship s) {
        System.out.println(name + " attacks " + s.name + " for " + damage + " damage");
        s.attackWith(damage);
    }

    void levelUp() {
        damage *= 1.08;
    }

    void levelUp(int levels) {
        damage *= Math.pow(1.08, levels);
    }

    Attack copyAttack() {
        return new Attack(name, damage, hitsAll);
    }
}

class RandomAttack extends Attack {
    int randomFactor;
    RandomAttack(String n, int d, boolean h, int r) {
        super(n, d, h);
        randomFactor = r;
    }

    RandomAttack(String n, int d, int r) {
        super(n, d);
        randomFactor = r;
    }

    @Override
    String getInfoString() {
        String output = name + ": " + damage + "-" + damage + randomFactor + " damage";
        if (super.hitsAll) {
            return output + " (hits all enemies)";
        }
        else {
            return output + " (hits 1 enemy)";
        }
    }

    @Override
    void hit(Ship s) {
        int dmg = damage + World.random.nextInt(randomFactor + 1);
        System.out.println(name + " attacks " + s.name + " for " + dmg + " damage");
        s.attackWith(dmg);
    }

    @Override
    RandomAttack copyAttack() {
        return new RandomAttack(name, damage, hitsAll, randomFactor);
    }

}

class MultiAttack extends Attack {
    int repeat;
    MultiAttack(String n, int d, boolean h, int r) {
        super(n, d, h);
        repeat = r;
    }

    MultiAttack(String n, int d, int r) {
        super(n, d);
        repeat = r;
    }

    @Override
    String getInfoString() {
        String output = name + ": " + damage + " damage " + repeat + " times"; 
        if (super.hitsAll) {
            return output + " (hits all enemies)";
        }
        else {
            return output + " (hits 1 enemy)";
        }
    }

    @Override
    void hit(Ship s) {
        for (int i = 0; i < repeat; i++) {
            System.out.println(name + " attacks " + s.name + " for " + damage + " damage (" + i + " /" + repeat + ")");
            s.attackWith(damage);
        }
    }

    @Override
    MultiAttack copyAttack() {
        return new MultiAttack(name, damage, hitsAll, repeat);
    }
}

class PlayerCharacter {
    String name;
    int level;
    ArrayList<Ship> ships, activeShips;
    ArrayList<Item> items;

    PlayerCharacter(String n) {
        ships = new ArrayList<>();
        activeShips = new ArrayList<>();
        items = new ArrayList<>();

        name = n;
    }

    void listShips() {
        System.out.println("Friendly Ships:");
        for (int i = 0; i < ships.size(); i++) {
            System.out.println((i + 1)+": " + ships.get(i).getInfoString());
        }
        System.out.println((ships.size() + 1) + ": cancel");
    }

    void listNonCommanderShips() {
        System.out.println("Friendly Ships:");
        for (int i = 1; i < ships.size(); i++) {
            System.out.println((i)+": " + ships.get(i).getInfoString());
        }
        System.out.println((ships.size() + 1) + ": cancel");
    }

    void listActiveShips() {
        System.out.println("Active Ships:");
        for (int i = 0; i < activeShips.size(); i++) {
            System.out.println((i + 1)+": " + activeShips.get(i).getInfoString());
        }
        System.out.println((activeShips.size() + 1) + ": cancel");
    }

    void chooseActiveShips() {
       
        activeShips.clear();
        activeShips.add(ships.get(0)); // adding commander
        
    }

    int chooseFriendlyShip() {
        listShips();
        int ship = World.scanner.nextInt() - 1;
        if (ship < 0 || ship > ships.size() + 1) {
            System.out.println("Index out of bounds, choose from listed numbers");
            return chooseFriendlyShip();
        }
        return ship;
    }
}

abstract class Item {
    String name;
    Item(String n) {
        name = n;
    } 
    abstract void use();
}

class HealItem extends Item {
    int healAmount;
    boolean healsAll;
    
    HealItem(String n, int amount) {
        super(n);
        healAmount = amount;
    }

    void use() {
        if (healsAll) {
            for (int i = 0; i < World.player.ships.size(); i++) {
                World.player.ships.get(i).heal(healAmount);
            }
        }
        else {
            int ship = World.player.chooseFriendlyShip();

        }
    }
}
