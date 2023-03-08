public class Main {
    public static void main(String[] args) {
        // Declare polymorphic variables (interface types)
        Swimmable swimmer;
        Flyable flyer;

        // Create class instances
        Airplane plane = new Airplane("Boeing");
        Fish fish = new Fish("Walleye");
        Duck duck = new Duck("Mallard");

        // Assign Swimmable to Fish instance
        swimmer = fish;
        // Dynamic binding to Fish swim() method
        swimmer.swim();

        // Assign Swimmable to Duck instance
        swimmer = duck;
        // Dynamic binding to Duck swim() method
        swimmer.swim();

        // Assign Flyable to Airplane instance
        flyer = plane;
        // Dynamic binding to Airplane fly() method
        flyer.fly();

        // Assign Flyable to Duck instance
        flyer = duck;
        // Dynamic binding to Duck fly() method
        flyer.fly();

    }
}
