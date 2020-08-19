package infCorpus;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Controller {
    Connection conn;
    HashMap<String, Integer> corpusNames = new HashMap<>();

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
                data[i] = name;
                i++;
            }
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return new String[]{""};
        }

    }

    public String[] getInfCorpus(String name) {
        try {
            Statement statement = conn.createStatement();
            String sql = "SELECT * FROM корпус WHERE ID_КОРПУС=" + corpusNames.get(name);
            ResultSet resultSet = statement.executeQuery(sql);
            String[] data = new String[resultSet.getFetchSize()];
            resultSet.next();
            data[0] = resultSet.getString("ID_КОРПУС");
            data[1] = resultSet.getString("название");
            data[2] = resultSet.getString("КЛАСС");
            data[3] = resultSet.getString("КОМН_ЭТАЖ");
            data[4] = resultSet.getString("КОЛВО_ЭТАЖЕЙ");
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return new String[]{""};
        }
    }

    public void createCorpus(String[] data) {
        try {
            Statement statement = conn.createStatement();
            String sql = "INSERT INTO корпус VALUES (" + Integer.valueOf(data[0]) + ',' + data[1] + ',' +
                    Integer.valueOf(data[2]) + ',' + Integer.valueOf(data[3]) + ',' + Integer.valueOf(data[4]) + ");";
            statement.executeQuery(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getMaxNumberCorpus(){
        try {
            Statement statement = conn.createStatement();
            String sql = "SELECT MAX(ID_КОРПУС) as maxCorpus FROM КОРПУС";
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            return resultSet.getInt("maxCorpus");
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    public String[][] getServiceCorpus(int Id) {
        try {
            //запросы1
            Statement statement = conn.createStatement();
            String sql = "select ID_УСЛУГА, ВОЗ_ПОЛ from ДОП_УСЛУГИ where ID_КОРПУС=" + String.valueOf(Id);
            ResultSet resultSet = statement.executeQuery(sql);
            HashMap<Integer, String> using = new HashMap<>();
            while (resultSet.next()) {
                int number = resultSet.getInt("ID_УСЛУГА");
                String value = resultSet.getString("ВОЗ_ПОЛ");
                using.put(number, value);
            }
            HashMap<Integer, String> description = new HashMap<>();
            HashMap<Integer, Double> price = new HashMap<>();
            //запросы2
            Iterator<HashMap.Entry<Integer, String>> entries = using.entrySet().iterator();
            while (entries.hasNext()) {
                HashMap.Entry<Integer, String> entry = entries.next();
                String sql2 = "select ОПИСАНИЕ, ЦЕНА from СЛУЖБЫ_БЫТА where ID_УСЛУГА=" + String.valueOf(entry.getKey());
                ResultSet resultSet2 = statement.executeQuery(sql2);
                resultSet2.next();
                String discr = resultSet2.getString("ОПИСАНИЕ");
                Double pr = resultSet2.getDouble("ЦЕНА");
                description.put(entry.getKey(), discr);
                price.put(entry.getKey(), pr);
            }
            //таблица
            String[][] data = new String[using.size()+1][4];
            data[0][0] = "номер"; data[0][1] = "описание"; data[0][2] = "цена"; data[0][3] = "наличие в корпусе";
            int i = 1;
            Iterator<HashMap.Entry<Integer, String>> entries2 = using.entrySet().iterator();
            while (entries2.hasNext()) {
                HashMap.Entry<Integer, String> entry2 = entries2.next();
                data[i][0] = String.valueOf(entry2.getKey());
                data[i][1] = description.get(entry2.getKey());
                data[i][2] = String.valueOf(price.get(entry2.getKey()));
                data[i][3] = entry2.getValue();
                i++;
            }
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return new String[][]{};
        }
    }

    public void saveChangesCorpus(String[] newData, int IdCorpus) {
        //String[класс отеля, комн_этаж, кол_этаж]
        try {
            Statement statement = conn.createStatement();
            String sql = "update корпус set ";
            boolean isCorrect = false;
            boolean comma1 = false, comma2 = false;
            if (correctChanges(newData[0])) {
                sql += "класс=" + newData[0].trim();
                isCorrect = true;
                comma1 = true;
            }
            if (correctChanges(newData[1])) {
                if (comma1) {
                    sql += ", КОМН_ЭТАЖ=" + newData[1].trim();
                } else {
                    sql += " КОМН_ЭТАЖ=" + newData[1].trim();
                }
                isCorrect = true;
                comma2 = true;
            }
            if (correctChanges(newData[2])) {
                if (comma1 || comma2) {
                    sql += ", КОЛВО_ЭТАЖЕЙ=" + newData[2].trim();
                } else {
                    sql += " КОЛВО_ЭТАЖЕЙ=" + newData[2].trim();
                }
                isCorrect = true;
            }
            if (isCorrect) {
                sql += " where ID_КОРПУС=" + String.valueOf(IdCorpus);
                statement.executeQuery(sql);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //TODO: ПРОВЕРЯЕТ ЧТО СТРОКА - ЦИФРА
    private boolean correctChanges(String ch) {
        if (!ch.trim().equals("")) {
            int b = Integer.parseInt(ch.trim());
            return true;
        } else
            return false;
    }

    public void saveChangesService(Integer IdCorpus, HashMap<Integer, String> newData) {
        try {
            Statement statement = conn.createStatement();
            Iterator<HashMap.Entry<Integer, String>> entries = newData.entrySet().iterator();
            while (entries.hasNext()) {
                HashMap.Entry<Integer, String> entry = entries.next();
                String sql = "update ДОП_УСЛУГИ set ВОЗ_ПОЛ='" + entry.getValue() + "' where ID_КОРПУС=" + String.valueOf(IdCorpus) + " and ID_УСЛУГА=" + entry.getKey();
                statement.executeQuery(sql);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}