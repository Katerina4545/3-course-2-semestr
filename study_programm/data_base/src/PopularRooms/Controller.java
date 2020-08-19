package PopularRooms;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

public class Controller {
    Connection conn;
    HashMap<String, Integer> corpusNames = new HashMap<>();
    HashMap<Integer, String> idCorpus = new HashMap<>();

    public Controller(Connection conn_) {
        conn = conn_;
    }

    public String[] getCorpusNames() {
        try{
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT ID_КОРПУС, название FROM корпус");
            String[] data = new String[resultSet.getFetchSize()];
            int i = 0;
            while (resultSet.next()) {
                Integer id = resultSet.getInt("ID_КОРПУС");
                String name = resultSet.getString("название");
                corpusNames.put(name, id);
                idCorpus.put(id, name);
                data[i] = name;
                i++;
            }
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return new String[]{""};
        }
    }


    public String[][] getMostPopular(String corpus){
        try{
            Integer id_cop = getCorpusId(corpus);

            Statement statement = conn.createStatement();
            String sql = "select ID_НОМЕР, ПОПУЛЯРНОСТЬ from НОМЕР where rownum < 11 and ID_КОРПУС=" +
                    id_cop +
                    " order by ПОПУЛЯРНОСТЬ desc "
                    ;
            System.out.println(sql);
            ResultSet resultSet = statement.executeQuery(sql);

            String[][] data = new String[resultSet.getFetchSize()][2];
            data[0][0] = "номер";
            data[0][1] = "популярность";
            int i = 1;

            //собрать data
            while(resultSet.next()){
                data[i][0] = String.valueOf(resultSet.getInt("ID_НОМЕР"));
                data[i][1] = String.valueOf(resultSet.getDouble("ПОПУЛЯРНОСТЬ"));
                i++;
            }
            return data;
        }catch (Exception e) {
            e.printStackTrace();
            return new String[][]{};
        }
    }

    public int getCorpusId(String corpus){
        try {
            Statement statement = conn.createStatement();
            String sql = "select ID_КОРПУС from КОРПУС where"
                    + " НАЗВАНИЕ='" + corpus + "'";
            System.out.println(sql);
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            return resultSet.getInt("ID_КОРПУС");
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    public String getInfRoom(String corpus, String room){
        try {
            Integer id_cop = getCorpusId(corpus);
            Statement statement = conn.createStatement();
            String sql = "select ПОПУЛЯРНОСТЬ from НОМЕР where"
                    + " ID_КОРПУС=" + id_cop
                    + " and ID_НОМЕР="+room;
            System.out.println(sql);
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            return String.valueOf(resultSet.getDouble("ПОПУЛЯРНОСТЬ"));
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }

}
