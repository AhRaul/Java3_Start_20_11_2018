public class HW6_massivs {

    public static void main(String[] args) {
        int[] massiv = {1, 4, 4, 1, 5};
        int[] vaitmassiv = after4push(massiv);
        for(int i = 0; i < vaitmassiv.length; i++) {
            System.out.print(vaitmassiv[i]);
        }
        System.out.println();
        System.out.print(look4or1(massiv));
    }

    //задача 1 вывод после последней 4 Вх: [ 1 2 4 4 2 3 4 1 7 ] -> вых: [ 1 7 ].
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

    //задача 2 возврат true если кроме 1 и 4 в массиве ничего нет, false если хоть одна посторонняя цифра
    public static boolean look4or1(int[] massiv) {
        for(int i = 0; i < massiv.length; i++) {
            if(massiv[i]!= 4 && massiv[i]!=1) {
                return false;
            }
        }
        return true;
    }

}
