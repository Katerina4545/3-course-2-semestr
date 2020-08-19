package HistoryGuest;

import java.sql.Connection;
import java.sql.Date;
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

    public String[][] getGuestList(String corpus, String start, String finish){
        try {
            Integer id_cop = getCorpusId(corpus);

            Statement statement = conn.createStatement();
            String sql = "select ID_ПОСТОЯЛЕЦ, ID_НОМЕР from история where "
                    + "ID_КОРПУС=" + id_cop
                    + " and (date'" + start+
                    "'<= ДАТА_ЗАСЕЛ and date'" +
                    finish + "' >= ДАТА_ВЫЕЗД)"
                    + " or (date'" + start
                    + "' between ДАТА_ЗАСЕЛ  and ДАТА_ВЫЕЗД)"
                    + " or (date'" + finish
                    + "' between ДАТА_ЗАСЕЛ  and ДАТА_ВЫЕЗД)"
                    ;
            System.out.println(sql);
            ResultSet resultSet = statement.executeQuery(sql);

            String[][] data = new String[resultSet.getFetchSize()][2];
            data[0][0] = "ФИО";
            data[0][1] = "номер";
            int i = 1;

            //собрать data
            while(resultSet.next()){
                int id_guest = resultSet.getInt("ID_ПОСТОЯЛЕЦ");
                String name = getNameGuest(id_guest);
                data[i][0] = name;
                data[i][1] = String.valueOf(resultSet.getInt("ID_НОМЕР"));
                i++;
            }
            return data;
        }catch (Exception e){
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

    public String getNameGuest(int id_guest){
        try {
            Statement statement = conn.createStatement();
            String sql = "select NAME from постоялец where"
                    + " ID_ПОСТОЯЛЕЦ=" + id_guest;
            System.out.println(sql);
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            return resultSet.getString("NAME");
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }

}
