import java.sql.*;
import java.util.Scanner;

/**
 * Урок 2. Базы данных
 *
 * 1. Сформировать таблицу товаров (id, prodid, title, cost) запросом из Java-приложения:
 * id – порядковый номер записи, первичный ключ;
 * prodid – уникальный номер товара;
 * title – название товара;
 * cost – стоимость.
 *
 * 2. При запуске приложения очистить таблицу и заполнить 10000 товаров вида:
 * id_товара 1 товар1 10
 * id_товара 2 товар2 20
 * id_товара 3 товар3 30
 * id_товара 10000 товар10000 100000
 *
 * 3. Написать консольное приложение, которое позволяет узнать цену товара по его имени, либо
 * вывести сообщение «Такого товара нет», если товар не обнаружен в базе. Консольная
 * команда: «цена товар545».
 *
 * 4. Добавить возможность изменения цены товара. Указываем имя товара и новую цену.
 * Консольная команда: «сменитьцену товар10 10000».
 *
 * 5. Вывести товары в заданном ценовом диапазоне. Консольная команда: «товарыпоцене 100
 * 600»
 */

public class TestSQL {
    private static Connection connection;
    private static Statement stmt;

    public static void main(String[] args) {

        try {
            connect();                          //начало подключения
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //методы для работы с БД
//з.1. Сформировать таблицу товаров (id, prodid, title, cost) запросом из Java-приложения:
        try {
            stmt.execute("DROP TABLE IF EXISTS products_table");            //опустошение существующей таблицы (костыль, будет время, посмотреть команду очищения всей таблицы, без её удаления)
            stmt.execute("CREATE TABLE IF NOT EXISTS products_table (" +    //создание новой таблицы
                    "    id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "    prodid TEXT UNIQUE," +
                    "    title  TEXT," +
                    "    cost   DOUBLE)");

//з.2 Автозаполнение таблицы
            connection.setAutoCommit(false);
            for (int i = 0; i<10000; i++) {
                int prodid_i = i+1;
                int title_i = i+1;
                double cost_i = (i+1)*10;
                stmt.executeUpdate("INSERT INTO products_table (prodid, title, cost)"+
                "VALUES ('id_товара "+ prodid_i +"', 'товар"+ title_i +"', "+ cost_i +")");
            }
            connection.setAutoCommit(true);

            ResultSet rs = stmt.executeQuery("SELECT * FROM products_table");   //вывод полученной в з.2 таблицы
            while (rs.next()) {
                System.out.println(rs.getInt(1) + rs.getString("prodid") + " " + rs.getDouble("cost"));
            }


            //слушатель консоли
            while (true) {
                Scanner in = new Scanner(System.in);
                System.out.println("Введите команду в консоль\n" +
                "например: для получения цены: «цена товар545»\n" +
                "для изменения цены: «сменитьцену товар10 10000»\n" +
                "для вывода списка товара в диапазоне: «товарыпоцене 100 600»\n");
                String s = in.nextLine();

//з.3 Консольная команда: «цена товар545».
                if (s.startsWith("цена ")) {
                    String tovar = s.substring(5).trim();
                    if (stmt.execute("SELECT EXISTS(SELECT * FROM products_table WHERE title LIKE '" + tovar + "')")) { //разобраться с магией
                        rs = stmt.executeQuery("SELECT cost FROM products_table WHERE title LIKE '" + tovar + "'");     //разобраться с магией

                        while (rs.next()) {
                            System.out.println("Цена за " + tovar + ": " + rs.getString("cost"));
                        }
                    } else {
                        System.out.println("Такого товара нет");
                    }
                }

//з.4 Консольная команда: «сменитьцену товар10 10000».
                else if (s.startsWith("сменитьцену ")) {
                    String tovar[] = s.split(" ");
                    int result = stmt.executeUpdate("UPDATE products_table SET cost = " + tovar[2] + " WHERE title = '" + tovar[1] + "'");
                    if (result > 0) {
                        System.out.println("Цена изменилась у " + result + " товаров");
                    } else {
                        System.out.println("Такого товара нет");
                    }
                }

//з.5 Консольная команда: «товарыпоцене 100 600».
                else if (s.startsWith("товарыпоцене ")) {
                    String centerval[] = s.split(" ");
                    rs = stmt.executeQuery("SELECT * FROM products_table WHERE cost BETWEEN " + centerval[1] +
                            " AND " + centerval[2]);
                    while (rs.next()) {
                        System.out.println(rs.getInt(1) + " " + rs.getString("prodid") + " " + rs.getDouble("cost"));
                    }
                }

                //закрыть программу
                else if (s.startsWith("close")) {
                    System.out.printf("Завершение программы");
                    break;                  //выход из цикла прослушки консоли
                } else {
                    System.out.println("Команда не предусмотрена, повторите ввод");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        disconnect();

    }

    public static void connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");   //обращение к драйверу
        connection = DriverManager.getConnection("jdbc:sqlite:products.db");
        stmt = connection.createStatement();
    }

    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


//CREATE TABLE products_table (
//    id INTEGER PRIMARY KEY AUTOINCREMENT,
//    prodid INTEGER UNIQUE,
//    title  TEXT,
//    cost   DOUBLE
//);

//stmt.execute("DROP TABLE IF EXISTS products_table");