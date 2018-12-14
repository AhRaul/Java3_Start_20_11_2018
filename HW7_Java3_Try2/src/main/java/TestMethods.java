
//комплект методов для тестирования
public class TestMethods {

    //проверяет что условие истино
    @Test(priority = 5)
    public static void assertTrue (Boolean bool) {
        if(bool == true) {
            System.out.println("assertTrue - success");
        } else {
            System.out.println("assertTrue - fail");
        }
    }

    //проверяет что условие ложно
    @Test(priority = 5)
    public static void assertFalse (Boolean bool) {
        if(bool == false) {
            System.out.println("assertFalse - success");
        } else {
            System.out.println("assertFalse - fail");
        }
    }

    //принудительный провал теста
    @Test(priority = 5)
    public static void fail () {
            System.out.println("fail");
    }

    //проверяет, что для объектов, переданных в качестве параметров, метод
    //equals возвращает true
    @Test(priority = 5)
    public static void assertEquals (Object object, Object result) {
        System.out.println("assertEquals не рабочий, т.к. нужно переопределить hashCode и equals");
//        if(object.equals(result)) {
//            System.out.println("assertEquals - success");
//        } else {
//            System.out.println("assertEquals - fail");
//        }
    }

    //проверяет, что equals для объектов возвращает false
    @Test(priority = 5)
    public static void assertNotEquals (Object object, Object result) {
        System.out.println("assertNotEquals не рабочий, т.к. нужно переопределить hashCode и equals");
//        if(!object.equals(result)) {
//            System.out.println("assertNotEquals - success");
//        } else {
//            System.out.println("assertNotEquals - fail");
//        }
    }

    //проверяет, что переданный в качестве параметра объект равен null;
    @Test(priority = 5)
    public static void assertNull (Object object) {
        if(object==null) {
            System.out.println("assertNull - success");
        } else {
            System.out.println("assertNull - fail");
        }
    }

    //проверяет, что переданный в качестве параметра объект не равен null;
    @Test(priority = 5)
    public static void assertNotNull (Object object) {
        if(object!=null) {
            System.out.println("assertNotNull - success");
        } else {
            System.out.println("assertNotNull - fail");
        }
    }
}
