public class Fish implements Swimmable {

    private String species;

    public Fish(String species) {
        this.species = species;
    }

    @Override
    public void swim() {
        System.out.println("Fish swims using fins");
    }
}
