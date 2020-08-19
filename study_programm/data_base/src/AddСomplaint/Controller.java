package AddСomplaint;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

public class Controller {
    Connection conn;
    HashMap<String, Integer> nameIdOrg = new HashMap<>();
    HashMap<String, Integer> nameIdCorpus = new HashMap<>();
    HashMap<Integer, String> idNameCorpus = new HashMap<>();

    public Controller(Connection conn_) {
        conn = conn_;
    }

    //  String[] columnNames = {"ФИО", "организация", "корпус", "номер", "описание"};
    //ID_ЖАЛОБА	ID_ПОСТОЯЛЕЦ	ID_КОРПУС	СТАТУС_ОБР	ОПИСАНИЕ
    public void addComplaint(String[] data){
        try {
            Statement statement = conn.createStatement();
            String sql = "insert into ЖАЛОБА values (" +
                    (getMaxId()+1) +", " +
                    getIdGuest(data[0], data[1])+", " +
                    nameIdCorpus.get(data[2])+", " +
                    "1, '" + data[4] + "')";
            System.out.println(sql);
            statement.executeQuery(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getMaxId(){
        try {
            Statement statement = conn.createStatement();
            String sql = "select MAX(ID_ЖАЛОБА) from ЖАЛОБА";
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            return Integer.valueOf(resultSet.getInt("MAX(ID_ЖАЛОБА)"));
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    public int getIdGuest(String name, String org){
        try {
            getMaps();
            int id_org = nameIdOrg.get(org);
            Statement statement = conn.createStatement();
            String sql = "select ID_ПОСТОЯЛЕЦ from ПОСТОЯЛЕЦ where ID_ОРГАНИЗАЦИЯ="
                    + id_org +
                    " and NAME='" +
                    name +
                    "'";
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            return Integer.valueOf(resultSet.getInt("ID_ПОСТОЯЛЕЦ"));
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    private void getMaps() {
        //map 1 nameIdOrg
        try {
            Statement statement = conn.createStatement();
            String sql = "select ID_ОРГАНИЗАЦИЯ, НАЗВАНИЕ from ОРГАНИЗАЦИЯ";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Integer id = resultSet.getInt("ID_ОРГАНИЗАЦИЯ");
                String name = resultSet.getString("НАЗВАНИЕ");
                nameIdOrg.put(name, id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //map 2 nameIdCorpus
        try {
            Statement statement = conn.createStatement();
            String sql = "select ID_КОРПУС, НАЗВАНИЕ from КОРПУС";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Integer id = resultSet.getInt("ID_КОРПУС");
                String name = resultSet.getString("НАЗВАНИЕ");
                nameIdCorpus.put(name, id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean organizationExist(String name) {
        try {
            getMaps();
            int id = nameIdOrg.get(name);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean corpusExist(String name) {
        getMaps();
        try {
            int id = nameIdCorpus.get(name);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean roomExist(Integer idRoom, String nameCorpus) {
        try {
            Integer idCorpus = nameIdCorpus.get(nameCorpus);
            Statement statement = conn.createStatement();
            String sql = "select ID_НОМЕР, ID_КОРПУС from НОМЕР where ID_НОМЕР=" + idRoom
                    + " and ID_КОРПУС=" + idCorpus;
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            resultSet.getInt("ID_НОМЕР");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
