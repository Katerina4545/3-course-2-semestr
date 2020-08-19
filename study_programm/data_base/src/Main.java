import java.sql.*;
import java.util.Locale;
import java.util.Properties;
import java.util.TimeZone;

public class Main {
    public static void main(String[] args) throws SQLException {
        //подключение драйвера
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //устанавливаем свойства соединения
        String url = "jdbc:oracle:thin:@84.237.50.81:1521:";
        Properties props = new Properties();
        props.setProperty("user", "17210Temnikova");
        props.setProperty("password", "123");
        TimeZone timeZone = TimeZone.getTimeZone("GMT+7");
        TimeZone.setDefault(timeZone);
        Locale.setDefault(Locale.ENGLISH);

        //подключаемся
        Connection conn = DriverManager.getConnection(url, props);

        //создаем меню
        new Menu(conn);
//        conn.close();
    }
}
