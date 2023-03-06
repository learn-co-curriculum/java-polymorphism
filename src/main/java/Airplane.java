public class Airplane implements Flyable {
    private String manufacturer;

    public Airplane(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public void fly() {
        System.out.println("Airplane flies using engine power");
    }
}
