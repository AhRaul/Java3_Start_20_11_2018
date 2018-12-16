
public class TestTasks {
    public static void main(String[] args) {
        createSpiral(5);
        createSpiral(6);
        createSpiral(7);
    }

    /**
     *1 Задать размерность матрицы от 5 до 7
     * 2 Создать матрицу с указанной размерностью
     * 3 Заполнить матрицу по спирали (числа увеличиваются на 1) (число начинается с 1)
     *
     * @param size  размерность матрицы
     */
    public static void createSpiral(int size) {
        System.out.println("массив " + size + " на " + size + ":");
        int[][] massiv = new int[size][size];
        int i=0;
        int j=0;
        int per=1;
        int sdvigSL=1;//сдвиг стартовой точки отсчета
        int sdvigFL=0;//сдвиг финишной точки отсчета

        for (i=0; i < size; i++) {          //1
            massiv[i][j] = per++;
        }
        i--;

        while(true) {   //__________________________________________

            if(size*size >= per) {          //2
                for (j=sdvigSL; j < size-sdvigFL; j++) {
                    massiv[i][j] = per++;
                }
                j--;
            } else {
                break;
            }


            if(size*size >= per) {          //3
                for (i=size-sdvigSL-1; i > sdvigFL-1; i--) {
                    massiv[i][j] = per++;
                }
                i++;
            } else {
                break;
            }

            sdvigFL++;              //сдвиг финишной точки отсчета

            if(size*size >= per) {          //4
                for (j=size-sdvigSL-1; j > sdvigFL-1; j--) {
                    massiv[i][j] = per++;
                }
                j++;
            } else {
                break;
            }

            if(size*size >= per) {          //1
                for (i=sdvigSL; i < size-sdvigFL; i++) {
                    massiv[i][j] = per++;
                }
                i--;
            } else {
                break;
            }

            sdvigSL++;              //сдвиг стартовой точки отсчета
        }       //__________________________________________________

        for(int k = 0; k<size; k++) {
            for(int n=0; n<size; n++) {
                System.out.print(massiv[n][k] + "\t ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
