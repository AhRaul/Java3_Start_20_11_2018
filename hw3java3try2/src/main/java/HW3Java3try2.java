
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *Урок 3. Средства ввода-вывода
 *
 * 1.Прочитать файл (около 50 байт) в байтовый массив и вывести этот массив в консоль;
 *
 * 2.Последовательно сшить 5 файлов в один (файлы примерно 100 байт). Может пригодиться следующая конструкция:
 * ArrayList<InputStream> al = new ArrayList<>(); ... Enumeration<InputStream> e = Collections.enumeration(al);
 *
 * 3.Написать консольное приложение, которое умеет постранично читать текстовые файлы (размером > 10 mb).
 * Вводим страницу (за страницу можно принять 1800 символов), программа выводит ее в консоль.
 * Контролируем время выполнения: программа не должна загружаться дольше 10 секунд, а чтение – занимать свыше 5 секунд.
 *
 */

public class HW3Java3try2 {
    public static void main(String[] args) {
        z1();
        z2();
        z3();

    }

    static void z1 () {
        try (FileInputStream in = new FileInputStream("txtpath/file1.txt")) {
            ArrayList<Byte> bytes = new ArrayList<>();
            int x;                                          //символ файла
            while ((x=in.read()) != -1) {                   //проход по символам пока не закончатся
                bytes.add((byte)x);
            }
            System.out.print(bytes + "\n");                    //вывод массив байт в консоль
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void z2 () {
        ArrayList<InputStream> bufferarray = new ArrayList<>();

        try {
            for(int i = 2; i <= 6; i++) {
                bufferarray.add(new FileInputStream("txtpath/file"+i+"_z2.txt"));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        try(FileWriter writer = new FileWriter("txtpath/file7sum_z2.txt")) {
            SequenceInputStream in = new SequenceInputStream(Collections.enumeration(bufferarray));
            int x;
            while ((x = in.read()) != -1) {                   //проход по символам пока не закончатся
                writer.write(x);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    static void z3 () {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите желаемую к выводу страницу: ");
        String s = in.nextLine();

        long page = Long.parseLong(s);                                              //номер нужной страницы
        long t = System.currentTimeMillis();
        try(RandomAccessFile raf = new RandomAccessFile("txtpath/file8_z3.txt", "r")) {
            for(long i = 1800*(page-1); i < 1800*page; i++) {
                raf.seek(i);
                System.out.print((char)raf.read());
            }
            System.out.println((char)raf.read());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis()-t);           // засекли миллисекунды
    }

}
