package ru.geekbrains.git;

import java.util.ArrayList;

/**
 * Урок 1. Обобщения
 * 1. Написать метод, который меняет два элемента массива местами (массив может быть любого ссылочного типа);
 * 2. Написать метод, который преобразует массив в ArrayList;
 * 3. Большая задача:
 *
 * a. Есть классы Fruit -> Apple, Orange (больше фруктов не надо);
 *
 * b. Класс Box, в который можно складывать фрукты. Коробки условно сортируются по типу фрукта, поэтому в одну коробку
 *  нельзя сложить и яблоки, и апельсины;
 *
 * c. Для хранения фруктов внутри коробки можно использовать ArrayList;
 *
 * d. Сделать метод getWeight(), который высчитывает вес коробки, зная количество фруктов и вес одного фрукта
 *  (вес яблока – 1.0f, апельсина – 1.5f. Не важно, в каких это единицах);
 *
 * e. Внутри класса Коробка сделать метод compare, который позволяет сравнить текущую коробку с той, которую
 *  подадут в compare в качестве параметра, true – если она равны по весу, false – в противном случае (коробки
 *  с яблоками мы можем сравнивать с коробками с апельсинами);
 *
 * f. Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую (помним про сортировку фруктов:
 *  нельзя яблоки высыпать в коробку с апельсинами). Соответственно, в текущей коробке фруктов не остается, а в другую
 *  перекидываются объекты, которые были в этой коробке;
 *
 * g. Не забываем про метод добавления фрукта в коробку.
 */

public class Main {
    public static void main(String[] args) {
        //проверка з1
        String[] mas = {"s", "t", "r", "i", "n", "g"};
        System.out.print("з1. Был массив: ");
        for (int i = 0; i<mas.length; i++) System.out.print(mas[i] + " ");

        changePlace(mas, 2, 3);

        System.out.print("\nз.1 Стал массив: ");
        for (int i = 0; i<mas.length; i++) System.out.print(mas[i] + " ");
        System.out.println();

//        int[] masInt = {1, 2, 3, 4, 5, 6};
//        changePlace(masInt, 2, 3);                //ошибка, не ссылочный тип
//        for (int i = 0; i<masInt.length; i++) System.out.print(mas[i] + " ");

        //з2, вывод преобразованного массива в консоль
        System.out.print("з.2 Массив ArrayList: ");
        ArrayList aMas = convertToArrayList(mas);
        for (int i = 0; i<aMas.size(); i++) System.out.print(aMas.get(i) + " ");
        System.out.println();

        //з3 проверка добавления в корзину фруктов
        Apple a1 = new Apple();     //один обьект яблоко
        Orange o1 = new Orange();   //обьект апельсин
        Box fora = new Box(a1, 0); //сложено в коробку 15 яблок
        Box foro = new Box(o1, 10); //сложено 10 апельсинов
        Box foro2 = new Box(o1, 6); //сложено 10 апельсинов

        //fora.addFruit(a1);
        fora.addFruit(o1);          //попытка положить фрукт другого типа
        System.out.println(fora.getWeight());
        System.out.println(fora.compare(foro));
        //fora.addFruit(o1);

        System.out.println("Перекладываем из коробки в коробку. Масса до перекладывания:");
        System.out.println("коробка 1 весит: " + foro.getWeight());
        System.out.println("коробка 2 весит: " + foro2.getWeight());
        foro.replace(fora);     //попытка переложить фрукты другого типа
        foro.replace(foro2);    //переложили тогоже типа
        System.out.println("коробка 1 весит: " + foro.getWeight());
        System.out.println("коробка 2 весит: " + foro2.getWeight());
    }


    /**
     * changePlace(massiv, i1, i2) меняет два элемента массива местами.
     * Аргументы:
     * massiv - изменяемый массив;
     * i1 - интедкс первого элемента массива;
     * i2 - индекс второго элемента массива.
     */
    public static void changePlace(Object[] massiv, int i1, int i2) {
        Object obj1 = massiv[i1];
        Object obj2 = massiv[i2];
        massiv[i2] = obj1;
        massiv[i1] = obj2;
    }

    /**
     * Метод - который преобразует массив в ArrayList
     * Аргументы:
     * massiv - преобразуемый массив.
     */
    public static ArrayList convertToArrayList(Object[] massiv) {
        ArrayList<Object> list = new ArrayList<Object>();
        for (int i = 0; i<massiv.length; i++) list.add(massiv[i]);
        return list;
    }
}