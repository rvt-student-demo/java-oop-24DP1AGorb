package rvt;
public class biggerClassDiagram{
    public static void main(String[] args){
    Animal a = new Animal("Dzivnieks", 999999999, 10000.56);
    a.sound();
    a.info();
    Mammal m = new Mammal("Mammal", 234, 33.2);
    m.sound();
    m.info();
    Dog d = new Dog("Maksis", 8, 6.7);
    d.sound();
    d.info();
    Puppy p = new Puppy("Benjamins", 1, 2.3);
    p.info();
    }
}

interface IA{
    void eat();
}

interface IB{
    int getNumberofLegs();
}

interface IC{
    String getSuga();
}

class D implements IA{
    @Override
    public void eat(){
        System.out.println("Cilveks ed");
    }
}

class Animal implements IA{ //A
    String name;
    int age;
    double weight;
    Animal(String name, int age, double weight){
        this.name = name;
        this.age = age;
        this.weight = weight;
    }
    void sound(){
        System.out.println("Dzivnieks var taisiit daudzas visadas skanas");
    }
    void info(){
        System.out.println("vinam ir "+age+" gadu un sver loti daudz("+weight+"kg)");
    }

    @Override
    public void eat(){
        System.out.println("Dzivnieks ed");
    }
}

class Mammal extends Animal implements IB{ // B extends A

    Mammal(String name, int age, double weight){
        super(name, age, weight);

        
    }
    public int getNumberofLegs(){
        return 4;
    }
    @Override
        void sound(){
            System.out.println("Mammals taisa vairakas sakanas, piem, vau vau, mooo, mjau mjau");
        }
        void info(){
            System.out.println("Mammals ir kaajas, parasti bus " + getNumberofLegs());
        }
}

class Dog extends Mammal implements IC{ //class C extends B
    Dog(String name, int age, double weight){
        super(name, age, weight);
    }
    public String getSuga(){
        return "Suns ir vacu shpics";
    }
    @Override
    void sound(){
        System.out.println("VAU VAU");
    }
    void info(){
        System.out.println("Suni ir majdzivnieki un muusu draugi! "+getSuga());
    }
    
}

class Puppy extends Dog{ // class E extend C
    Puppy(String name, int age, double weight){
        super(name, age, weight);
    }

    @Override
    void info(){
        System.out.println("Puppy ir jaundzimis vai jaundzimusi suns");
    }
}
