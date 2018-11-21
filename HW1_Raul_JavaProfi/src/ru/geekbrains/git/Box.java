package ru.geekbrains.git;

import java.util.ArrayList;

/**
 * класс "Коробка" куда складываются фрукты
 */
public class Box <T extends Fruit> {
    private T fruitInBox;
    private ArrayList<T> list = new ArrayList<>();

    /**
     * Создание коробки с присвоением типа коробки, и определением первоначального количества фруктов
     * @param ob - какой фрукт
     * @param quantity - количество
     */
    public Box(T ob, int quantity) {
        this.fruitInBox = ob;
        for (int i = 0; i<quantity; i++) list.add(fruitInBox);

    }

    /**
     *
     * @return массу фруктов в корзине
     */
    public float getWeight(){
        return (float)list.size() * this.fruitInBox.weight;
    }

    /**
     * Сравнение 2х коробок.
     * @param b2 - сравниваемая коробка.
     * @return true - если масса совпадает, иначе false.
     */
    public boolean compare(Box b2) {
        return this.getWeight() == b2.getWeight();
    }

    /**
     *
     * @return возвращает список фруктов в корзине
     */
    public ArrayList<T> getFruits () {
        return this.list;
    }

    /**
     * опустошение коробки (не влияет на заданный тип коробки, т.е. в нее можно положить только то, что раньше)
     */
    public void removeFruits() {
        this.list.removeAll(this.list);
    }

    /**
     * Перекладывание из коробки в коробку фруктов того же типа
     * @param b2 - коробка, из которой перекладываем
     */
    public void replace(Box b2) {
        if (this.fruitInBox.type.equals(b2.fruitInBox.type)) {
            ArrayList<T> insideList = b2.getFruits();
            for (int i = 0; i<insideList.size(); i++) {
                this.list.add(insideList.get(i));
            }
            b2.removeFruits();
            System.out.println("получилось переложить " + b2.fruitInBox.type);
        } else {
            System.out.println("В эту коробку нельзя класть " + b2.fruitInBox.type);
        }
    }

    /**
     * Добавление 1 фрукта в корзину
     * @param fruitInBox - добавляемый фрукт
     */
    public void addFruit(T fruitInBox) {
        if(this.fruitInBox.type.equals(fruitInBox.type)) {
            list.add(fruitInBox);
        } else {
            System.out.println("В эту коробку нельзя класть " + fruitInBox.type);
        }
    }
}
