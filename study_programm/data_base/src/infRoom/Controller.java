package infRoom;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

public class Controller {
    Connection conn;
    HashMap<String, Integer> corpusNames = new HashMap<>();

    public Controller(Connection conn_){conn=conn_;}

    public String[] getCorpusNames() {
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT ID_КОРПУС, название FROM корпус");
            String[] data = new String[resultSet.getFetchSize()];
            int i = 0;
            while (resultSet.next()) {
                Integer id = resultSet.getInt("ID_КОРПУС");
                String name = resultSet.getString("название");
                corpusNames.put(name, id);
                data[i] = name;
                i++;
            }
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return new String[]{""};
        }
    }

    //"номер", "занят", "оценка", "местность", "цена в сутки"
    //ID_НОМЕР	ID_КОРПУС	ЗАНЯТ	ПОПУЛЯРНОСТЬ	МЕСТНОСТЬ	ЦЕНА_СУТКИ
    public void createRoom(String[] data, String corpusName){
        try {
            Integer corId = corpusNames.get(corpusName);
            Statement statement = conn.createStatement();
            String sql = "INSERT INTO номер VALUES(" + data[0] + ", " + String.valueOf(corId)+ ", "
                    + "'" + data[1] + "'" + ", " + data[2] + ", " + data[3] + ", "
                    + data[4] + ")";
            statement.executeQuery(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Integer maxFloor(String corpusName){
        try {
            Integer corId = corpusNames.get(corpusName);
            Statement statement = conn.createStatement();
            String sql = "select КОЛВО_ЭТАЖЕЙ from КОРПУС where ID_КОРПУС=" + corId;
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            return resultSet.getInt("КОЛВО_ЭТАЖЕЙ");
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public Integer maxRoomFloor(String corpusName){
        try {
            Integer corId = corpusNames.get(corpusName);
            Statement statement = conn.createStatement();
            String sql = "select КОМН_ЭТАЖ from КОРПУС where ID_КОРПУС=" + corId;
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            return resultSet.getInt("КОМН_ЭТАЖ");
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    //"номер", "занят", "оценка", "местность", "цена в сутки"
    //ID_НОМЕР	ID_КОРПУС	ЗАНЯТ	ПОПУЛЯРНОСТЬ	МЕСТНОСТЬ	ЦЕНА_СУТКИ
    public String[] getInfRoom(String roomNumber, String corpusName){
        try{
            Integer corId = corpusNames.get(corpusName);
            Statement statement = conn.createStatement();
            String sql = "select ID_НОМЕР, ЗАНЯТ, ПОПУЛЯРНОСТЬ, МЕСТНОСТЬ, ЦЕНА_СУТКИ from НОМЕР "+
                    "where ID_КОРПУС=" + corId + "and ID_НОМЕР=" + roomNumber;
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            String[] result = new String[5];
            result[0] = resultSet.getString("ID_НОМЕР");
            result[1] = resultSet.getString("ЗАНЯТ");
            result[2] = resultSet.getString("ПОПУЛЯРНОСТЬ");
            result[3] = resultSet.getString("МЕСТНОСТЬ");
            result[4] = resultSet.getString("ЦЕНА_СУТКИ");
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return new String[]{"", "", "", "", ""};
        }
    }

    public String[] getRoomNumbers(String corpusName){
        try{
            Integer corId = corpusNames.get(corpusName);
            Statement statement = conn.createStatement();
            String sql = "select ID_НОМЕР from НОМЕР where ID_КОРПУС=" + corId;
            ResultSet resultSet = statement.executeQuery(sql);
            String[] result = new String[resultSet.getFetchSize()];
            int i=0;
            while(resultSet.next()){
                result[i] = resultSet.getString("ID_НОМЕР");
                i++;
            }
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return new String[]{};
        }
    }

    //"номер", "занят", "оценка", "местность", "цена в сутки"
    //ID_НОМЕР	ID_КОРПУС	ЗАНЯТ	ПОПУЛЯРНОСТЬ	МЕСТНОСТЬ	ЦЕНА_СУТКИ
    public void editRoom(String[] data, String corpusName, String roomNumber){
        try{
            Integer corId = corpusNames.get(corpusName);
            Statement statement = conn.createStatement();
            String sql = "update номер set ID_НОМЕР = " + data[0] + ", ЗАНЯТ='"
                    + data[1] + "', ПОПУЛЯРНОСТЬ=" + data[2] + ", МЕСТНОСТЬ=" + data[3] +
                    ", ЦЕНА_СУТКИ=" + data[4] + "where ID_НОМЕР=" + roomNumber + " and ID_КОРПУС=" + corId;
            statement.executeQuery(sql);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String[] getPeriodDate(String corpusName, String roomNumber, String start, String finish){
        try{
            Integer corId = corpusNames.get(corpusName);
            Statement statement = conn.createStatement();
            String sql = "select ID_ПОСТОЯЛЕЦ from ИСТОРИЯ where \n" +
                    "\n" +
                    "((date'"+start+"'<= ДАТА_ЗАСЕЛ and date'"+finish+"' >= ДАТА_ВЫЕЗД)\n" +
                    "\n" +
                    "or (date'"+start+"' between ДАТА_ЗАСЕЛ  and ДАТА_ВЫЕЗД)\n" +
                    "\n" +
                    "or (date'"+finish+"' between ДАТА_ЗАСЕЛ  and ДАТА_ВЫЕЗД))\n" +
                    "\n" +
                    "and (ID_НОМЕР="+roomNumber+") and (ID_КОРПУС="+corId+")";
            ResultSet resultSet = statement.executeQuery(sql);
            String[] guestSet = new String[resultSet.getFetchSize()];
            int i=0;
            while(resultSet.next()){
                guestSet[i] = resultSet.getString("ID_ПОСТОЯЛЕЦ");
                i++;
            }

            int j=0;
            String[] result = new String[i];
            while(j<i){
                String sql2 = "select NAME from ПОСТОЯЛЕЦ where ID_ПОСТОЯЛЕЦ=" + guestSet[j];
                ResultSet resultSet2 = statement.executeQuery(sql2);
                resultSet2.next();
                String name = resultSet2.getString("NAME");
                result[j] = name;
                j++;
            }
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return new String[]{};
        }
    }
}
