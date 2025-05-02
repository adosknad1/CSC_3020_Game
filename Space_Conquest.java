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

        // initializing attack
        Attack laserShot = new Attack("Laser Shot", 10); // Laser Shot does 10 dmg and hits one enemy
        Attack doubleLaser = new MultiAttack("Double Laser", 10, 2); // Multi Laser does 10 damage and hits one enemy twice
        Attack laserArray = new Attack("Laser Array", 10, true); // Laser Array does 10 damage and hits all enemies
       
        Attack snipe = new Attack("Snipe", 15);
        Attack ram = new RandomAttack("Ram", 5,  false, 7);
        Attack wildShot = new RandomAttack("Wild Shot", 10, false, 20);
        
        Attack deathBeam = new Attack("Death Beam", 30);
        Attack rapidLaser = new MultiAttack("Rapid Laser", 10, 6);
        Attack rapidBeam = new MultiAttack("Rapid Beam", 12, 5);
       
        Attack steelBash = new Attack("Steel Bash", 40); 
        Attack doubleBash = new MultiAttack("Double Bash", 25, 2);
        Attack bomb = new RandomAttack("Bomb", 20, true, 10);
        Attack superBomb = new RandomAttack("Bomb", 25, true, 25);
        Attack implosionBomb = new RandomAttack("Implosion Bomb", 100, false, 25);

        Attack waterBeam = new Attack("Water Beam", 50);
        Attack waterBomb = new Attack("Water Bomb", 30, true);
        Attack waterFall = new MultiAttack("Waterfall", 40, 2);
        Attack oceansWrath = new RandomAttack("Ocean's Wrath", 20, false, 80);


        Ship commander = new Ship("Commander", 150, laserShot);
        commander.add(doubleLaser);
        commander.add(laserArray);

        player.ships.add(commander);

        // planet 1 enemies
        Ship grunt = new Ship("Grunt", 25, laserShot);
        Ship doubleShotGrunt = new Ship("Double Shot Grunt", 30, doubleLaser);
        Ship laserArrayGrunt = new Ship("Laser Array Grunt", 30, laserArray);
        Ship armorGrunt = new Ship("Armor Grunt", 30, laserShot, 5);
        Ship bossGrunt = new Ship("Boss Grunt", 50, doubleLaser);
        Ship tank = new Ship("Tank", 100, laserArray, 10, 1);
        bossGrunt.add(laserShot);
        
        // planet 2 enemies
        Ship sniperGrunt = new Ship("Sniper Grunt", 30, snipe);
        Ship miniGrunt = new Ship("Mini Grunt", 15, ram);
        miniGrunt.add(laserShot);
        Ship wildGrunt = new Ship("Wild Grunt", 30, wildShot);
        Ship chiefGrunt = new Ship("Chief Grunt", 60, wildShot);
        chiefGrunt.add(ram);

        // planet 3 enemies
        Ship destroyer = new Ship("Destroyer", 40, deathBeam);        
        destroyer.add(doubleLaser);
        Ship bruiser = new Ship("Bruiser", 80, wildShot, 5);
        bruiser.add(deathBeam);
        Ship minigun = new Ship("Minigun", 60, rapidLaser);
        Ship deathStar = new Ship("Death Star", 100, rapidBeam);
        deathStar.add(deathBeam);

        // planet 4 enemies
        Ship steelHead = new Ship("Steelhead", 70, steelBash, 5);
        Ship doubleSteelHead = new Ship("Double Steelhead", 80, doubleBash, 5);
        Ship bomber = new Ship("Bomber", 50, bomb);
        Ship superBomber = new Ship("Super Bomber", 100, superBomb);
        superBomber.add(doubleBash);
        superBomber.add(implosionBomb);
        Ship steelCommander = new Ship("Steel commander", 120, bomb, 10);
        steelCommander.add(doubleBash);

        // planet 5 enemies
        Ship spacePirate = new Ship("Space Pirate", 90, waterBeam);
        Ship pirateBomber = new Ship("Pirate Bomber", 60, waterBomb);
        Ship waterDestroyer = new Ship("Water Destroyer", 80, waterFall, 5);
        waterDestroyer.add(waterBeam);
        Ship kraken = new Ship("Kraken", 350, oceansWrath, 20);
        Ship colossus = new Ship("Colossus", 280, oceansWrath, 10);

        // planet 6 enemies

        planets = new Planet[8];
        planets[0] = new Planet("Galaxia");
        planets[0].addReward(destroyer);
        Battle battle1 = new Battle("Galaxia small fleet");
        battle1.add(grunt);
        battle1.add(grunt);
        

        Battle battle2 = new Battle("Galaxia big fleet");
        battle2.add(doubleShotGrunt);
        battle2.add(laserArrayGrunt);
        battle2.add(grunt);
        battle2.add(grunt);

        Battle battle3 = new Battle("Galaxia boss fleet");
        battle3.add(bossGrunt);
        battle3.add(grunt);
        battle3.add(grunt);

        planets[0].add(battle1);
        planets[0].add(battle2);
        planets[0].add(battle3);

        planets[1] = new Planet("Gruntopia");
        planets[1].addReward(tank);
        Battle gruntopia1 = new Battle("Gruntopia small fleet");
        gruntopia1.add(sniperGrunt);
        gruntopia1.add(sniperGrunt);

        Battle gruntopia2 = new Battle("Gruntopia big fleet");
        gruntopia2.add(sniperGrunt);
        gruntopia2.add(miniGrunt);
        gruntopia2.add(miniGrunt);
        gruntopia2.add(miniGrunt);

        Battle gruntopia3 = new Battle("Gruntopia boss fleet");
        gruntopia3.add(chiefGrunt);
        gruntopia3.add(wildGrunt);
        gruntopia3.add(wildGrunt);
        gruntopia3.add(miniGrunt);

        planets[1].add(gruntopia1);
        planets[1].add(gruntopia2);
        planets[1].add(gruntopia3);

        planets[2] = new Planet("Zethlon");
        planets[2].addReward(deathStar);

        Battle zethlon1 = new Battle("Zethlon small fleet");
        zethlon1.add(destroyer);
        zethlon1.add(destroyer);

        Battle zethlon2 = new Battle("Zethlon big fleet");
        zethlon2.add(minigun);
        zethlon2.add(minigun);
        zethlon2.add(minigun);
        zethlon2.add(bruiser);
        
        Battle zethlon3 = new Battle("Zethlon Boss fleet");
        zethlon3.add(deathStar);
        zethlon3.add(minigun);
        zethlon3.add(minigun);

        planets[2].add(zethlon1);
        planets[2].add(zethlon2);
        planets[2].add(zethlon3);

        planets[3] = new Planet("Ironhand");
        planets[3].addReward(superBomber);

        Battle ironhand1 = new Battle("Ironhand small fleet");
        ironhand1.add(steelHead);
        ironhand1.add(steelHead);

        Battle ironhand2 = new Battle("Ironhand big fleet");
        ironhand2.add(doubleSteelHead);
        ironhand2.add(doubleSteelHead);
        ironhand2.add(bomber);
        
        Battle ironhand3 = new Battle("Ironhand boss fleet");
        ironhand3.add(steelCommander);
        ironhand3.add(doubleSteelHead);
        ironhand3.add(bomber);
        ironhand3.add(bomber);

        planets[3].add(ironhand1);
        planets[3].add(ironhand2);
        planets[3].add(ironhand3);
        
        planets[4] = new Planet("Aquaria");
        planets[4].addReward(colossus);

        Battle aquaria1 = new Battle("Aquaria small fleet");
        aquaria1.add(spacePirate);
        aquaria1.add(spacePirate);

        Battle aquaria2 = new Battle("Aquaria big fleet");
        aquaria2.add(pirateBomber);
        aquaria2.add(waterDestroyer);
        aquaria2.add(waterDestroyer);

        Battle aquaria3 = new Battle("Aquaria boss fleet");
        aquaria3.add(kraken);

        planets[4].add(aquaria1);
        planets[4].add(aquaria2);
        planets[4].add(aquaria3);

        planets[5] = new Planet("Volcania");
        Attack fireBeam = new MultiAttack("Fire Beam", 20, false, 5);
        Attack fireExplosion = new Attack("Fire Explosion", 70, true);
        Attack fireSnipe = new Attack("Fire Snipe", 120);
        Attack blazeOfGlory = new MultiAttack("Blaze of Glory", 50, true, 3);

        Ship fireKnight = new Ship("Fire Knight", 100, fireBeam);
        Ship fireSniper = new Ship("Fire Sniper", 150, fireSnipe);
        Ship fireBerzerker = new Ship("Fire Berzerker", 70, fireExplosion);
        fireBerzerker.add(fireBeam);
        Ship fireWarrior = new Ship("Fire Warrrior", 100, blazeOfGlory);
        fireWarrior.add(fireBeam);
        Ship fireCommander = new Ship("Fire Commander", 200, blazeOfGlory);
        fireCommander.add(fireSnipe);
        fireCommander.add(fireExplosion);

        planets[5].addReward(fireCommander);

        Battle volcania1 = new Battle("Volcania small fleet");
        volcania1.add(fireKnight);
        volcania1.add(fireSniper);
        
        Battle volcania2 = new Battle("Volcania big fleet");
        volcania2.add(fireSniper);
        volcania2.add(fireBerzerker);
        volcania2.add(fireBerzerker);


        Battle volcania3 = new Battle("Volcania boss fleet");
        volcania3.add(fireWarrior);
        volcania3.add(fireWarrior);
        volcania3.add(fireWarrior);
        volcania3.add(fireWarrior);

        planets[5].add(volcania1);
        planets[5].add(volcania2);
        planets[5].add(volcania3);

        planets[6] = new Planet("Kryptix");
        Attack kryptoBeam = new RandomAttack("Krypto-Beam", 50, false, 20);
        Attack kryptoExplosion = new RandomAttack("Krypto-Explosion", 40, true, 30);
        Attack kryptoSweep = new MultiAttack("Krypto-Sweep", 40, true, 3);
        Attack kryptoDestruction = new RandomAttack("Krypto-Destruction", 100, false, 50);


        Ship kryptoGrunt = new Ship("Krypto Grunt", 100, kryptoBeam);
        Ship kryptoBrute = new Ship("Krypto Brute", 150, kryptoExplosion, 10);
        kryptoBrute.add(kryptoBeam);
        Ship kryptoJet = new Ship("Krypto Jet", 150, kryptoSweep);
        Ship kryptor = new Ship("Kryptor", 200, kryptoDestruction, 15);
        kryptor.add(kryptoBeam);

        Battle kryptix1 = new Battle("Kryptix small fleet");
        kryptix1.add(kryptoGrunt);
        kryptix1.add(kryptoGrunt);


        Battle kryptix2 = new Battle("Kryptix big fleet");
        kryptix2.add(kryptoBrute);
        kryptix2.add(kryptoGrunt);
        kryptix2.add(kryptoJet);

        Battle kryptix3 = new Battle("Kryptix boss fleet");
        kryptix3.add(kryptor);
        kryptix3.add(kryptoJet);
        kryptix3.add(kryptoJet);

        planets[6].add(kryptix1);
        planets[6].add(kryptix2);
        planets[6].add(kryptix3);

        planets[6].addReward(kryptor);

        planets[7] = new Planet("Assassin's Capital");

        Attack weakPointShot = new Attack("Weak-Point Shot", 120);
        Attack silentBomb = new RandomAttack("Silent Bomb", 90, true, 10);
        Attack rapidSlash = new MultiAttack("Rapid Slash", 50, false, 5);
        Attack annihilate = new Attack("Annihilate", 200);

        Ship rogue = new Ship("Rogue", 160, weakPointShot);
        Ship bomberAssassin = new Ship("BomberAssassin", 120, silentBomb);
        bomberAssassin.add(weakPointShot);
        Ship mercenary = new Ship("Mercenary", 200, rapidSlash);
        Ship korg = new Ship("Korg the Commander", 700, annihilate);
        korg.add(weakPointShot);
        korg.add(rapidSlash);

        Battle capital1 = new Battle("Assassin's Capital small fleet");
        capital1.add(rogue);
        capital1.add(rogue);
        capital1.add(rogue);
        
        Battle capital2 = new Battle("Assassin's Capital big fleet");
        capital2.add(rogue);
        capital2.add(rogue);
        capital2.add(bomberAssassin);
        capital2.add(mercenary);


        Battle capital3 = new Battle("Assassin's Capital small fleet");
        capital3.add(korg);
        capital2.add(mercenary);

        planets[7].add(capital1);
        planets[7].add(capital2);
        planets[7].add(capital3);
        Ship victoryShip = new Ship("Victory Ship", 10, laserShot);
        planets[7].addReward(victoryShip);

        System.out.println("Welcome to Space Conquest, choose a planet to get started");

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
        System.out.println("Thank you for playing Space Conquest!");
        System.out.println("You have saved your fleets from the J4V4 galaxy");
    }
}

class Planet {
    String name;
    ArrayList<Battle> battles;
    int curBattle;
    Ship reward;
    Planet(String n) {
        name = n;
        battles = new ArrayList<>();
        curBattle = 0;
    }

    void add(Battle battle) {
        battles.add(battle);
    }

    void addReward(Ship r) {
        reward = r.copyShip();
    }

    void giveReward() {
        System.out.println("Congratulations! You got " + reward.name);
        reward.levelUp(World.player.level);
        World.player.ships.add(reward.copyShip());
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
        giveReward();
        return true;
        
    }

    boolean enterBattle() {
        System.out.println("Enter this battle? type 0 to cancel enter to continue");
        World.scanner.nextLine();
        String input = World.scanner.nextLine();
        if (input.equals("0")) {
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
    ArrayList<Ship> activeEnemies;

    Battle(String n) {
        name = n;
        enemies = new ArrayList<>();
        activeEnemies = new ArrayList<>();
        
    }

    void printEnemiesStatus() {
        System.out.println("Enemies Status:");
        for (int i = 0; i < activeEnemies.size(); i++) {
            System.out.println((i + 1) + ": " + activeEnemies.get(i).getInfoString());
        }
    }

    void printFriendlyStatus() {
        System.out.println("Your Status:");
        for (int i = 0; i < World.player.activeShips.size(); i++) {
            System.out.println((i + 1) + ": " + World.player.activeShips.get(i).getInfoString());
        }
    }

    void resetEnemies() {
        activeEnemies.clear();
        for (int i = 0; i < enemies.size(); i++) {
            activeEnemies.add(enemies.get(i).copyShip());
        }
    }

    boolean enter() {
        System.out.println("Now entering battle with " + name);
        World.player.resetActiveShips();
        resetEnemies();
        printEnemiesStatus();

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
            World.player.levelUp();
            return true;
        }
    }

    void enemyCheck() {
        for (int i = 0; i < activeEnemies.size(); i++) {
            if (activeEnemies.get(i).isDefeated()) {
                System.out.println(activeEnemies.get(i).name + " was defeated!");
                activeEnemies.remove(i);
                i--;
            }
        }
    }

    boolean friendlyTurn() {
        printFriendlyStatus();
        printEnemiesStatus();
        for (int i = 0; i < World.player.activeShips.size(); i++) {
            Ship curShip = World.player.activeShips.get(i);
            if (!curShip.isDefeated()) {
                curShip.performChosenAttack(activeEnemies);
                enemyCheck(); 
                System.out.println("Press enter to continue");
                World.scanner.nextLine();
                World.scanner.nextLine();
            }

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
            enemyCheck();
        }
        return true;
    }

    boolean enemiesDefeated() {
        return activeEnemies.size() == 0;
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
        enemies.add(s.copyShip());
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
        damage = Math.max(damage, 0);
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
        attacks.add(a.copyAttack());
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
    }

    boolean performChosenAttack(ArrayList<Ship> targets) {
        System.out.println("");
        System.out.println("Choose attack for " + name +": ");
        int attack = chooseAttack();

        if (attack == attacks.size()) {
            return false;
        }
        

        int target;
        if (attacks.get(attack).hitsAll) {
            System.out.println("");
            System.out.println(name +" uses " + attacks.get(attack).name);
            attacks.get(attack).hit(targets);
            return true;
        }

        System.out.println("");
        System.out.println("Choose target: ");
        target = chooseTarget(targets);

        if (target == targets.size()) {
            return false;
        }
        System.out.println(name +" uses " + attacks.get(attack).name);
        attacks.get(attack).hit(targets.get(target));
        return true;
    }

    boolean performRandomAttack(ArrayList<Ship> targets) {
        System.out.println("");
        int attack = chooseRandomAttack();
        System.out.println("");
        System.out.println(name + " uses " + attacks.get(attack).name);
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
        if (ship < 0 || ship >= targets.size()) {
            System.out.println("Invalid index, choose from the listed numbers");
            return chooseTarget(targets);
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
        s.attackWith(damage);
    }

    void hit(ArrayList<Ship> targets) {
        for (int i = 0; i < targets.size(); i++) {
            hit(targets.get(i));
        }
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
        String output = name + ": " + damage + "-" + (damage + randomFactor) + " damage";
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

    void levelUp() {
        for (int i = 0; i < ships.size(); i++) {
            ships.get(i).levelUp();
        }
    }

    void listShips() {
        System.out.println("Friendly Ships:");
        for (int i = 0; i < ships.size(); i++) {
            System.out.println((i + 1)+": " + ships.get(i).getInfoString());
        }
    }

    void listNonCommanderShips() {
        System.out.println("Choose an ally for this battle:");
        for (int i = 1; i < ships.size(); i++) {
            System.out.println((i)+": " + ships.get(i).getInfoString());
        }
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
        activeShips.add(ships.get(0).copyShip()); // adding commander
        if (ships.size() == 1) {
            return;
        }
        listNonCommanderShips();
        int secondShip = World.scanner.nextInt();
        if (secondShip >= ships.size() || secondShip < 1) {
            System.out.println("Index out of bounds, choose from the listed numbers");
            chooseActiveShips();
            return;
        }
        activeShips.add(ships.get(secondShip).copyShip());
        
    }

    void resetActiveShips() {
        for (int i = 0; i < ships.size(); i++) {
            ships.get(i).hp = ships.get(i).maxHp;
        }
        chooseActiveShips();
    }

    int chooseFriendlyShip() {
        listNonCommanderShips();
        int ship = World.scanner.nextInt();
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
