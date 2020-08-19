package AboutGuest;

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

    public String[][] getNewGuests(String corpus) {
        try {
            int id_cor = corpusNames.get(corpus);

            Statement statement = conn.createStatement();
            String sql = "select distinct id_постоялец from история where (ДАТА_ЗАСЕЛ between sysdate-7  and sysdate)  and id_корпус=" + id_cor;
            ResultSet resultSet = statement.executeQuery(sql);
            String[][] data = new String[resultSet.getFetchSize()][2];
            data[0][0] = "ФИО";
            data[0][1] = "организация";
            int i = 1;
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id_постоялец");
                String name = getName(id);
                String org = getOrg(id);
                data[i][0] = name;
                data[i][1] = org;
                i++;
            }
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return new String[][]{};
        }
    }

    public String getName(int id) {
        try {
            Statement statement = conn.createStatement();
            String sql = "select distinct name from постоялец where id_постоялец=" + id;
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            return resultSet.getString("name");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }


    public String getOrg(int id) {
        try {
            Statement statement = conn.createStatement();
            String sql = "select distinct id_организация from постоялец where id_постоялец=" + id;
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            int id_org = resultSet.getInt("id_организация");

            String sql2 = "select distinct название from организация where id_организация=" + id_org;
            ResultSet resultSet2 = statement.executeQuery(sql2);
            resultSet2.next();
            return resultSet2.getString("название");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public String[][] getFrequentGuests(String corpus, String start, String finish, Boolean allTime) {
        try {
            int id_cor = corpusNames.get(corpus);

            Statement statement = conn.createStatement();
            String sql;
            if (allTime) {
                sql = "select id_постоялец, count(id_постоялец) from история where (ID_КОРПУС=" +
                        id_cor +
                        ") and (rownum < 11)" +
                        " group by id_постоялец order by count(id_постоялец) desc";
            } else {
                sql = "select id_постоялец, count(id_постоялец) from история where (ID_КОРПУС=" +
                        id_cor +
                        ") and (rownum < 11) and (" +

                        "(date'" + start + "'<= ДАТА_ЗАСЕЛ and date'"+ finish +"' >= ДАТА_ВЫЕЗД)" +

                        "or (date'" + start + "' between ДАТА_ЗАСЕЛ  and ДАТА_ВЫЕЗД)" +

                        "or (date'" + finish + "' between ДАТА_ЗАСЕЛ  and ДАТА_ВЫЕЗД))" +

                        " group by id_постоялец order by count(id_постоялец) desc";
            }
            ResultSet resultSet = statement.executeQuery(sql);
            String[][] data = new String[resultSet.getFetchSize()][2];
            data[0][0] = "ФИО";
            data[0][1] = "организация";
            int i = 1;
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id_постоялец");
                String name = getName(id);
                String org = getOrg(id);
                data[i][0] = name;
                data[i][1] = org;
                i++;
            }
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return new String[][]{};
        }
    }
}

