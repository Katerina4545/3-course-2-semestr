package AboutRoom;

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
        try {
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

    public String[] getDateList(String corpusName, String date) {
        try {
            int id = corpusNames.get(corpusName);
            Statement statement = conn.createStatement();
            String sql = "select ID_НОМЕР from ИСТОРИЯ where " +
                    "(ДАТА_ЗАСЕЛ < date'"+ date +"') " +
                    "and (ДАТА_ВЫЕЗД< date'"+ date +"')" +
                    " and (SYSDATE between ДАТА_ЗАСЕЛ and ДАТА_ВЫЕЗД)"+
                    " and (ID_КОРПУС ="+id+")";
            ResultSet resultSet = statement.executeQuery(sql);

            String[] data = new String[resultSet.getFetchSize()];
            int i = 0;
            //собрать data
            while(resultSet.next()) {
                data[i] = String.valueOf(resultSet.getInt("ID_НОМЕР"));
                i++;
            }
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return new String[]{};
        }
    }

    public String[][] getNowList(String corpusName) {
        try {
            int id = corpusNames.get(corpusName);
            Statement statement = conn.createStatement();
            String sql = "select * from номер where (занят='no') and (ID_КОРПУС = "+id+")";
            ResultSet resultSet = statement.executeQuery(sql);

            String[][] data = new String[resultSet.getFetchSize()][4];
            data[0][0] = "номер";
            data[0][1] = "популярность";
            data[0][2] = "местность";
            data[0][3] = "цена";
            int i = 1;

            //собрать data
            while(resultSet.next()) {
                data[i][0] = String.valueOf(resultSet.getInt("ID_НОМЕР"));
                data[i][1] = String.valueOf(resultSet.getDouble("ПОПУЛЯРНОСТЬ"));
                data[i][2] = String.valueOf(resultSet.getInt("МЕСТНОСТЬ"));
                data[i][3] = String.valueOf(resultSet.getDouble("ЦЕНА_СУТКИ"));
                i++;
            }
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return new String[][]{};
        }
    }

    public String[][] getParametersList(String corpusName, String popular, String count, String cost) {
        try {
            int id = corpusNames.get(corpusName);
            Statement statement = conn.createStatement();
            String sql = "select * from номер where (занят='no') and (ID_КОРПУС = "+id+") " +
                    "and (ПОПУЛЯРНОСТЬ >= "+popular+")" +
                    " and(МЕСТНОСТЬ = "+count+") " +
                    "and (ЦЕНА_СУТКИ <= "+cost+")";
            ResultSet resultSet = statement.executeQuery(sql);

            String[][] data = new String[resultSet.getFetchSize()][4];
            data[0][0] = "номер";
            data[0][1] = "популярность";
            data[0][2] = "местность";
            data[0][3] = "цена";
            int i = 1;

            //собрать data
            while(resultSet.next()) {
                data[i][0] = String.valueOf(resultSet.getInt("ID_НОМЕР"));
                data[i][1] = String.valueOf(resultSet.getDouble("ПОПУЛЯРНОСТЬ"));
                data[i][2] = String.valueOf(resultSet.getInt("МЕСТНОСТЬ"));
                data[i][3] = String.valueOf(resultSet.getDouble("ЦЕНА_СУТКИ"));
                i++;
            }
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return new String[][]{};
        }
    }
}


