import java.util.Scanner;
class World {
    public static Scanner scanner = new Scanner(System.in);
    private Planet[] planets;
    World () {
        planets = new Planet[8];
        planets[0] = new Planet("Planet 1");
        planets[1] = new Planet("Planet 2");
        planets[2] = new Planet("Planet 3");
        planets[3] = new Planet("Planet 4");
        planets[4] = new Planet("Planet 5");
        planets[5] = new Planet("Planet 6");
        planets[6] = new Planet("Planet 7");
        planets[7] = new Planet("Planet 8");
    }
    int takePlanetInput() {
        System.out.println("Enter 1-8 to go to planet 1 through 8, 9 to exit");
        int planetChoice = scanner.nextInt() - 1;
        return planetChoice;
    }
    public static void main(String args[]) {
        World world = new World();
        int planet = world.takePlanetInput();
        if (planet != 8) {
            world.planets[planet].enter();
        }
        
    }
    
}

class Planet {
    String name;
    Planet(String n) {
        name = n;
    }
    void enter() {
        System.out.println("Welcome to the planet " + name);
        
    }
}