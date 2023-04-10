public class Driver {
    public static void main(String[] args) {
        Person person = new Person();
        System.out.println(person.getWeekendPlans());

        person = new Teacher();
        System.out.println(person.getWeekendPlans());

        person = new Student();
        System.out.println(person.getWeekendPlans());
    }
}
