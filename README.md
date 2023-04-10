# Polymorphism

## Learning Goals

- Demonstrate runtime polymorphism based on method overriding
- Demonstrate compile-time polymorphism based on method overloading

## Introduction

Polymorphism means "many forms".
In Java, polymorphism is the ability for an object or method to take on many forms.

Java supports **runtime/dynamic** polymorphism and **compile-time/static**
polymorphism.  Runtime polymorphism is achieved through **method overriding**,
while compile-time polymorphism is achieved through **method overloading**.

## Code Along 

Fork and clone this lesson.  The project defines two interfaces `Swimmable`
and `Flyable`, along with classes `Fish`, `Airplane` and `Duck`.
Since the majority of fish don't fly, `Fish` and `Duck` implement the `Swimmable` interface,
while `Duck` and `Airplane` implement `Flyable` .

![polymorphism interfaces uml](https://curriculum-content.s3.amazonaws.com/6677/pillars/polymorphism_interface_uml.png)

Abstract methods `swim()` and `fly()` are displayed with italic font in the UML diagram.

```java
public interface Swimmable {
    void swim();
}
```

```java
public interface Flyable {
    void fly();
}
```


```java
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
```

```java
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
```

`Duck` implements both `Swimmable` and `Flyable`, using a comma to separate
the two interfaces.

```java
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
```

## Runtime Polymorphism and Interfaces

A variable declared with an interface type can store a reference to
an object of any class that implements the interface.  This type of variable is
called **polymorphic**, since it can reference objects of different types.

```java
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
```


- The polymorphic variable `swimmer`  initially references a `Fish` and then is reassigned to reference a `Duck` object.
- The polymorphic variable `flyer` initially references an  `Airplane` and then is reassigned to reference a `Duck` object.

Runtime polymorphism means the behavior of a method invocation
can vary depending on the actual type of the object referenced.

Java uses **dynamic binding** for the method calls `swimmer.swim()`
and `flyer.fly()`. Dynamic binding will determine which
`swim()` and `fly()` method to execute based on the type
of object referenced by `swimmer` and `flyer`.

The program prints:

```text
Fish swims using fins
Duck swims by paddling webbed feet
Airplane flies using engine power
Duck flies by flapping wings
```

Let's set a breakpoint after creating the class instances
and use the debugger to watch dynamic binding in action.

![breakpoint multiple interfaces](https://curriculum-content.s3.amazonaws.com/6677/pillars/breakpoint_swim_fly.png)


Press "Step Over" to execute the assignment statement `swimmer = fish;`

![polymorphism step1](https://curriculum-content.s3.amazonaws.com/6677/pillars/poly_step1.png)


Press "Step Into" to execute the call `swimmer.swim()` and step into the
`swim()` method defined in the `Fish` class:

![polymorphism step2](https://curriculum-content.s3.amazonaws.com/6677/pillars/poly_step2.png)

Press "Step Over" to execute the print statement, then "Step Over" again
to return to the `main` method.

Press "Step Over" to execute the assignment statement `swimmer = duck;`.

![polymorphism step 3](https://curriculum-content.s3.amazonaws.com/6677/pillars/poly_step3.png)

Press "Step Into" to execute the call `swimmer.swim()` and step into the
`swim()` method defined in the `Duck` class:

![polymorphism step4](https://curriculum-content.s3.amazonaws.com/6677/pillars/poly_step4.png)

Continue stepping through the code.  Confirm the two method calls `flyable.fly()`
invoke different method implementations based on whether `flyable` references
an `Airplane` or a `Duck` object.

## Runtime Polymorphism and Inheritance

We also encounter polymorphic variable references
and dynamic method binding when working with class inheritance.

Recall the `Person` class:

```java
public class Person {

    private String name;
    private int age;

    //get/set methods
    ...
    
    public void celebrateBirthday() {age++;}
    public String getWeekendPlans() {return "sleep late and relax all day";}

    @Override
    public String toString() {return "name=" + name + ", age=" + age;}
}
```

`Teacher`  and `Student` are subclasses of `Person`:

```java
public class Teacher extends Person {

    @Override
    public String getWeekendPlans() {
        return "grade homework assignments";
    }

}
```

```java
public class Student extends Person {
    
    private String favoriteSubject;

    public String getFavoriteSubject() {return favoriteSubject;}

    public void setFavoriteSubject(String favoriteSubject) {this.favoriteSubject = favoriteSubject;}

    @Override
    public String getWeekendPlans() {
        return "wake up early and study all day";
    }

    @Override
    public String toString() {
        return super.toString() +
                ", favoriteSubject=" + favoriteSubject ;
    }
}
```

Since a subclass inherits the fields and methods of the superclass,
we can declare a variable having the superclass type and assign it to a
subclass instance.  

For example, a variable `person` declared with
the type `Person` can reference an instance of `Person`, `Teacher`,
or `Student`.  Java will use dynamic binding for the call `person.getWeekendPlans()`
to figure out which method implementation to execute.


```java
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

```

The program prints:

```text
sleep late and relax all day
grade homework assignments
wake up early and study all day
```


## Compile-time polymorphism 

**Compile-time polymorphism** occurs when a class defines two methods with the
same name but different parameter lists.  Java uses **static or compile-time binding**
to determine which method to execute based on the arguments specified in a method call.

Like method overriding, there are some rules that we must follow when we overload methods:

- The method names must be the same.
- The method names must have a different parameter list.
- The methods can have a different return type.
- The access level can vary.


For example, the `Product` class has two methods named `salesPrice`
but with different parameter lists.  The `salesPrice` method is overloaded.

```java
public class Product {
    private double price;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double salePrice() {
        // default 10% discount
        return price * 0.9;
    }

    public double salePrice(double discountRatio){
        return price * (1.0 - discountRatio);
    }

    public static void main(String[] args) {
        Product widget = new Product();
        widget.setPrice(20.00);
        System.out.println(widget.salePrice()); //10% off is 18.00
        System.out.println(widget.salePrice(0.3)); //30% off is 14.00
    }
}
```

The `main` method contains two calls `widget.salePrice()` and
`widget.salePrice(0.3)`.  Java can bind each call statically, i.e.
at compile-time, based on whether a double value  is passed as a parameter
or not.

The program prints:

```text
18.0
14.0
```


## Conclusion

Method overriding involves runtime polymorphism because the method to execute
depends on the object referenced by a variable, which may reference
objects of different types.

Method overloading is also known as compile-time polymorphism because the method is resolved when we compile the code.

## Resources

- [Java Tutorial - Polymorphism](https://docs.oracle.com/javase/tutorial/java/IandI/polymorphism.html)

