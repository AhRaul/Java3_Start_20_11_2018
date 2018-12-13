/**
 * Урок 6. Обзор средств разработки
 *
 * 1.Написать метод, которому в качестве аргумента передается не пустой одномерный целочисленный массив.
 *   Метод должен вернуть новый массив, который получен путем вытаскивания из исходного массива элементов,
 *   идущих после последней четверки. Входной массив должен содержать хотя бы одну четверку,
 *   иначе в методе необходимо выбросить RuntimeException. Написать набор тестов для этого метода
 *  (по 3-4 варианта входных данных). Вх: [ 1 2 4 4 2 3 4 1 7 ] -> вых: [ 1 7 ].
 *
 * 2.Написать метод, который проверяет состав массива из чисел 1 и 4. Если в нем нет хоть одной четверки или единицы,
 * то метод вернет false; Написать набор тестов для этого метода (по 3-4 варианта входных данных).
 */

public class MainTestTry {

    public static void main(String[] args) {
        int[] massiv = {1, 4, 4, 1, 5};
        int[] vaitmassiv = after4push(massiv);
        for(int i = 0; i < vaitmassiv.length; i++) {
            System.out.print(vaitmassiv[i]);
        }
        System.out.println();
        System.out.print(look4or1(massiv));
    }

    //задача 1
    public static int[] after4push(int[] massiv) {
        int [] rmassiv;
        for (int i = massiv.length-1; i >= 0; i--) {    //првоерил
            if (massiv[i] == 4) {                       //проверил
                rmassiv = new int[massiv.length-i-1];
                for(int j = 0; j < (massiv.length-i-1); j++) {
                    if ((i+j+1) <= massiv.length-1) {             //проверка не закончился ли массив
                        rmassiv[j] = massiv[i + j + 1];
                    }
                }
                return rmassiv;
            }
        }
        throw new RuntimeException("не найдено ниодной четверки");
    }

    //задача 2
    public static boolean look4or1(int[] massiv) {
        for(int i = 0; i < massiv.length; i++) {
            if(massiv[i]!= 4 && massiv[i]!=1) {
                return false;
            }
        }
        return true;
    }
}