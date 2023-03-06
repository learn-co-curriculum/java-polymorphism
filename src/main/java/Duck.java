public class Duck implements Swimmable, Flyable {

    private String breed;

    public Duck(String breed) {
        this.breed = breed;
    }

    @Override
    public void fly() {
        System.out.println("Duck flies by flapping wings");
    }

    @Override
    public void swim() {
        System.out.println("Duck swims by paddling webbed feet");
    }
}
