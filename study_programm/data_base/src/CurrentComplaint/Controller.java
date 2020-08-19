package CurrentComplaint;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

public class Controller {
    Connection conn;
    HashMap<String, Integer> corpusNames = new HashMap<>();
    HashMap<Integer, String> idCorpus = new HashMap<>();
    HashMap<String, Integer> complId = new HashMap<>();

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

    public String[] getComplaints(String corpus) {
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT ID_ЖАЛОБА, ОПИСАНИЕ FROM жалоба");
            String[] data = new String[resultSet.getFetchSize()];
            int i = 0;
            while (resultSet.next()) {
                Integer id = resultSet.getInt("ID_ЖАЛОБА");
                String name = resultSet.getString("ОПИСАНИЕ");
                complId.put(name, id);
                data[i] = name;
                i++;
            }
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return new String[]{""};
        }
    }

    //    String[] columnNames = {0"постоялец", 1"корпус", 2"статус", 3"описание"};
    //      ID_ЖАЛОБА	ID_ПОСТОЯЛЕЦ	ID_КОРПУС	СТАТУС_ОБР	ОПИСАНИЕ
    public String[] getInf(String complaint) {
        try {
            int id_comp = complId.get(complaint);
            Statement statement = conn.createStatement();
            String sql = "SELECT * FROM жалоба where ID_ЖАЛОБА=" + id_comp;
            ResultSet resultSet = statement.executeQuery(sql);
            String[] data = new String[resultSet.getFetchSize()];
            int i = 0;
            resultSet.next();

            Integer id_guest = resultSet.getInt("ID_ПОСТОЯЛЕЦ");
            String name_guest = getNameGuest(id_guest);
            data[0] = name_guest;

            data[1] = idCorpus.get(resultSet.getInt("ID_КОРПУС"));

            Integer status = resultSet.getInt("СТАТУС_ОБР");
            switch (status){
                case 1:{
                    data[2] = "на рассмотрении";
                    break;
                }
                case 2:{
                    data[2] = "обрабатывается";
                    break;
                }
                case 3:{
                    data[2] = "исправлено";
                    break;
                }
            }

            data[3] = resultSet.getString("ОПИСАНИЕ");

            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return new String[]{};
        }
    }

    public  String getNameGuest(Integer id){
        try{
            Statement statement = conn.createStatement();
            String sql = "SELECT NAME FROM постоялец where ID_ПОСТОЯЛЕЦ=" + id;
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            return resultSet.getString("NAME");
        }catch(Exception e){
            e.printStackTrace();
            return "";
        }
    }

    public void changeCompl(String complaint, Integer stat){
        Integer id_comp = complId.get(complaint);
        try {
            Statement statement = conn.createStatement();
            String sql = "update ЖАЛОБА set СТАТУС_ОБР=" + stat
                    + " where ID_ЖАЛОБА=" + id_comp;
            statement.executeQuery(sql);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
