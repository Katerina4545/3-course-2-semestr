package StatRoom;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Controller {
    Connection conn;
    public Controller(Connection conn_) {
        conn = conn_;
    }
    public int getSoldRooms(String st, String fn){
        try {
            Statement statement = conn.createStatement();
            String sql = "select sum(КОЛВО_КОМНАТ) from БРОНЬ where "
                    + "(date'" + st+
                    "'<= ДАТА_ЗАСЕЛ and date'" +
                    fn + "' >= ДАТА_ВЫЕЗДА)"
                    + " or (date'" + st
                    + "' between ДАТА_ЗАСЕЛ  and ДАТА_ВЫЕЗДА)"
                    + " or (date'" + fn
                    + "' between ДАТА_ЗАСЕЛ  and ДАТА_ВЫЕЗДА)"
                    ;
            System.out.println(sql);
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            return resultSet.getInt("sum(КОЛВО_КОМНАТ)");
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    public int getAllRooms(){
        try {
            Statement statement = conn.createStatement();
            String sql = "select count(ID_НОМЕР) from НОМЕР";
            System.out.println(sql);
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            return resultSet.getInt("count(ID_НОМЕР)");
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
}
