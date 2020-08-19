package Organization;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class Controller {
    Connection conn;
    public Integer count_room;
    public Integer floor;
    public Date lastDate;
    public boolean isData = true;

    //data for request
    private Date start;
    private Date finish;
    private Integer count;

    //data for second request
    private ArrayList<Integer> idOrg = new ArrayList<>();

    //some save date
    private HashMap<String, Integer> nameIdOrg = new HashMap<>();

    public Controller(Connection conn_) {
        conn = conn_;
    }

    public void addOrg(String[] data) {
        try {
            int id = getMaxIdOrg() + 1;
            Statement statement = conn.createStatement();
            Date start = Date.valueOf(data[1]);
            Date finish = Date.valueOf(data[2]);
            String sql = "insert into ОРГАНИЗАЦИЯ values(" + id + ", " + data[0]
                    + ", date'" + start.toString() + "', date'" + finish.toString() + "')";
            System.out.println(sql);
            statement.executeQuery(sql);
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }


    public int getMaxIdOrg() {
        try {
            Statement statement = conn.createStatement();
            String sql = "SELECT MAX(ID_ОРГАНИЗАЦИЯ) as maxO FROM ОРГАНИЗАЦИЯ";
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            return resultSet.getInt("maxO");
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void savePeriod(String st, String fin) {
        start = Date.valueOf(st);
        finish = Date.valueOf(fin);
    }

    public void saveCount(Integer c) {
        count = c;
    }

    /*
    SELECT *
FROM БРОНЬ
WHERE (ДАТА_ЗАСЕЛ BETWEEN TO_DATE ('2020/04/01', 'yyyy/mm/dd') AND TO_DATE ('2020/04/28', 'yyyy/mm/dd'))
and (ДАТА_ВЫЕЗДА BETWEEN TO_DATE ('2020/04/01', 'yyyy/mm/dd') AND TO_DATE ('2020/04/28', 'yyyy/mm/dd'))
and КОЛВО_ЧЕЛ>=10
     */

    public String[] getOrgList() {
        try {
            Statement statement = conn.createStatement();
            String sql = "SELECT DISTINCT ID_ОРГАНИЗАЦИЯ FROM БРОНЬ WHERE (ДАТА_ЗАСЕЛ BETWEEN TO_DATE ('"
                    + start.toString() + "', 'yyyy-mm-dd') AND TO_DATE ('" + finish.toString() +
                    "', 'yyyy-mm-dd')) and (ДАТА_ВЫЕЗДА BETWEEN TO_DATE ('" + start.toString() +
                    "', 'yyyy-mm-dd') AND TO_DATE ('" + finish.toString() + "', 'yyyy-mm-dd')) and КОЛВО_ЧЕЛ>=" + count.toString();
            System.out.println(sql);
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                idOrg.add(resultSet.getInt("ID_ОРГАНИЗАЦИЯ"));
            }
            return getNameOrg();
        } catch (Exception e) {
            e.printStackTrace();
            return new String[]{"no data"};
        }

    }

    private String[] getNameOrg() {
        try {
            Statement statement = conn.createStatement();
            int len = idOrg.size();
            int k = 0;
            ArrayList<String> names = new ArrayList<>();
            while (k < len) {
                String sql = "SELECT НАЗВАНИЕ from ОРГАНИЗАЦИЯ where ID_ОРГАНИЗАЦИЯ=" + idOrg.get(k);
                ResultSet resultSet = statement.executeQuery(sql);
                resultSet.next();
                names.add(resultSet.getString("НАЗВАНИЕ"));
                k++;
            }
            System.out.println(names);
            return names.toArray(new String[names.size()]);
        } catch (Exception e) {
            e.printStackTrace();
            return new String[]{"no data"};
        }
    }

    public String[] getAllNamesOrg() {
        try {
            Statement statement = conn.createStatement();
            ArrayList<String> names = new ArrayList<>();
            String sql = "SELECT НАЗВАНИЕ, ID_ОРГАНИЗАЦИЯ from ОРГАНИЗАЦИЯ";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                names.add(resultSet.getString("НАЗВАНИЕ"));
                nameIdOrg.put(resultSet.getString("НАЗВАНИЕ"), resultSet.getInt("ID_ОРГАНИЗАЦИЯ"));
            }
            return names.toArray(new String[names.size()]);
        } catch (Exception e) {
            e.printStackTrace();
            return new String[]{"no data"};
        }
    }

    public boolean orgExist(String str) {
        if (nameIdOrg.containsKey(str)) return true;
        else return false;
    }

    public void getOneStat(String start, String finish, String select) {
        try {
            Integer id = nameIdOrg.get(select);
            Statement statement = conn.createStatement();
            String sql = "SELECT count(КОЛВО_КОМНАТ) FROM БРОНЬ WHERE (ДАТА_ЗАСЕЛ BETWEEN TO_DATE ('"
                    + start + "', 'yyyy-mm-dd') AND TO_DATE ('" + finish +
                    "', 'yyyy-mm-dd')) and (ДАТА_ВЫЕЗДА BETWEEN TO_DATE ('" + start +
                    "', 'yyyy-mm-dd') AND TO_DATE ('" + finish + "', 'yyyy-mm-dd')) and ID_ОРГАНИЗАЦИЯ=" + id.toString();
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            try {
                count_room = resultSet.getInt("count(КОЛВО_КОМНАТ)");
            }catch (Exception e){
                e.printStackTrace();
                isData = false;
            }

            String sql2 = "SELECT ЖЕЛАЕМЫЙ_ЭТАЖ, COUNT(ЖЕЛАЕМЫЙ_ЭТАЖ ) FROM БРОНЬ WHERE (ДАТА_ЗАСЕЛ BETWEEN TO_DATE ('"
                    + start + "', 'yyyy-mm-dd') AND TO_DATE ('" + finish +
                    "', 'yyyy-mm-dd')) and (ДАТА_ВЫЕЗДА BETWEEN TO_DATE ('" + start +
                    "', 'yyyy-mm-dd') AND TO_DATE ('" + finish + "', 'yyyy-mm-dd')) and ID_ОРГАНИЗАЦИЯ=" + id.toString()
                    + "GROUP BY ЖЕЛАЕМЫЙ_ЭТАЖ ORDER BY COUNT(ЖЕЛАЕМЫЙ_ЭТАЖ ) DESC";
            ResultSet resultSet2 = statement.executeQuery(sql2);
            resultSet2.next();
            try {
                floor = resultSet2.getInt("ЖЕЛАЕМЫЙ_ЭТАЖ");
            }catch (Exception e){
                e.printStackTrace();
                isData = false;
            }

            String sql3 = "SELECT DISTINCT max(ДАТА_ВЫЕЗДА) FROM БРОНЬ where ID_ОРГАНИЗАЦИЯ=" + id.toString();
            ResultSet resultSet3 = statement.executeQuery(sql3);
            resultSet3.next();
            try {
                lastDate = resultSet3.getDate("max(ДАТА_ВЫЕЗДА)");
            }catch (Exception e){
                e.printStackTrace();
                isData = false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
