import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;


public class TestHW6Java3Try2 {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {     //не понимаю конструкцию (потренить класс Collection)
        return Arrays.asList(new Object[][]{
                {1, 2, 4, 4, 2, 3, 4, 1, 7},
                {4, 1, 1, 4, 1, 4, 1, 1, 1},
                {1, 2, 3, 5, 6, 7, 8, 9, 4},
                {4, 1, 2, 3, 5, 6, 7, 8, 9},
        });
    }
    private int[] testMas;       //не понятен пример с "a b c" т.к. не указано, какая именно переменная массива к какой переменной "a b c" относится
    //не понимаю, как работать с тест конструктором, на массивы ругается(Test class should have exactly one public zero-argument constructor)
    //делаю как в занятии
    public TestHW6Java3Try2(int[] testMas) {
        this.testMas = testMas;
    }

    //з1
    MainTestTry mainTest;
    @Before
    public void init() {
        mainTest = new MainTestTry();
    }

    //з1
    @Test(expected = RuntimeException.class)
    public void massTestChange1() {
        MainTestTry.after4push(testMas);
    }

    //з1
    @Test
    public void massTestChange2() {
        Assert.assertNotNull(MainTestTry.after4push(testMas));
    }

    //з2
    @Test
    public void massTestBool1() {
        Assert.assertTrue(MainTestTry.look4or1(testMas));
    }
}
