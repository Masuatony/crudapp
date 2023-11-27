package com.example.firstcrud.statics;

import lombok.Data;

@Data
public class Cat {
    //todo methods static and non static
    private static int catCount;
    private static final int MAX_LIVES = 9;
    private int lives;
    private String name;

    public String getName() {
        return name;
    }

    public int getLives(){
        return lives;
    }

    public Cat(){
        catCount++;
        lives = MAX_LIVES;
    }

    public void printName(){
        System.out.println("mmmeeeewww");
    }

    public static int getCatCount(){
        return catCount;
    }

    public static void main(String[] args) {
        System.out.println(Cat.getCatCount());
        Cat myCat = new Cat();
        myCat.lives = 8;
        myCat.setLives(12);
//        System.out.println(myCat.printName());
        myCat.printName();
//        Cat.name
//        int maxLives = Cat.MAX_LIVES;
        System.out.println(Cat.MAX_LIVES);
        myCat.name = "Kim";
        System.out.println(Cat.getCatCount());
    }
}
