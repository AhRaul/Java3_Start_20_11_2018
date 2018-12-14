import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Урок 7. Reflection API и аннотации
 *
 * Создать класс, который может выполнять «тесты». В качестве тестов выступают классы с наборами методов
 * с аннотациями @Test. Для этого у него должен быть статический метод start(), которому в качестве
 * параметра передается или объект типа Class, или имя класса. Из «класса-теста» вначале должен быть запущен
 * метод с аннотацией @BeforeSuite, если такой имеется. Далее запущены методы с аннотациями @Test, а по завершении
 * всех тестов – метод с аннотацией @AfterSuite. К каждому тесту необходимо добавить приоритеты (int числа от 1 до 10),
 * в соответствии с которыми будет выбираться порядок их выполнения.
 * Если приоритет одинаковый, то порядок не имеет значения. Методы с аннотациями @BeforeSuite и @AfterSuite
 * должны присутствовать в единственном экземпляре, иначе необходимо бросить RuntimeException при запуске
 * «тестирования».
 * Это домашнее задание никак не связано с темой тестирования через JUnit и использованием этой библиотеки:
 * проект пишется с нуля.
 */

// запуск теста MyTestClass (логика запуска)
public class Main {
    public static void main(String[] args) {
        start(MyTestClass.class);
    }

    public static void start(Class object) {
        MyTestClass mc = new MyTestClass();
        Method[] methods = object.getDeclaredMethods();        //что за класс Field ? Запуск тестов
        int countBeforeSuite =0;
        int countAfterSuite =0;
        for (Method o : methods) {
            if (o.isAnnotationPresent(BeforeSuite.class)) {
                countBeforeSuite++;
                if (countBeforeSuite > 1) {
                    throw new RuntimeException("BeforeSuite может быть только один");
                }
                try {
                    o.invoke(mc);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        for (Method o : methods) {
                if (o.isAnnotationPresent(Test.class)) {
                    try {
                        o.invoke(mc);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
        }
        for (Method o : methods) {
            if (o.isAnnotationPresent(AfterSuite.class)) {
                countAfterSuite++;
                if (countAfterSuite > 1) {
                    throw new RuntimeException("AfterSuite может быть только один");
                }
                try {
                    o.invoke(mc);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}