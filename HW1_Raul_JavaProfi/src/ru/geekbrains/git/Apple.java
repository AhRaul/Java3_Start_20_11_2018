package ru.geekbrains.git;

/**
 * класс "Яблоки"
 */
public class Apple extends Fruit {
    public static final float APPLE_WEIGHT = 1.0f;

    public Apple(){
        super.type = "Apple";
        super.weight = APPLE_WEIGHT;
    }
}