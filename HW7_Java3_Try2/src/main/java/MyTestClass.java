public class MyTestClass {

    //сами рассматривыемые аннотации, здесь изменяемый код
    int[] massiv;

    @AfterSuite
    public static void endMethod() {
        System.out.println("Последняя аннотация");
    }

    //з1
    @Test
    public void massTestChange2() {
        TestMethods.assertNotNull(HW6_massivs.after4push(massiv));
    }

    //з2
    @Test
    public void massTestBool1() {
        int[] massiv = {1, 4, 4, 1, 5};
        TestMethods.assertTrue(HW6_massivs.look4or1(massiv));
    }

    @BeforeSuite
    public void init() {
        System.out.println("Первая аннотация");
        massiv = new int[]{1, 4, 4, 1, 5};
    }

}
