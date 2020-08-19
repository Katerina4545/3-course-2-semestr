package Booking;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Controller {
    Connection conn;
    HashMap<String, Integer> nameIdOrg = new HashMap<>();
    HashMap<String, Integer> dataIdBooking = new HashMap<>();
    HashMap<Integer, String> idNameOrg = new HashMap<>();
    HashMap<Integer, Integer> idOrgIdBooking = new HashMap<>();

    public Controller(Connection conn_) {
        conn = conn_;
    }

    //ID_БРОНЬ	ID_ОРГАНИЗАЦИЯ	СТОИМ_ЗАКАЗА	СКИДКА	КОЛВО_ЧЕЛ	КОЛВО_КОМНАТ	ЖЕЛАЕМЫЙ_ЭТАЖ	ДАТА_ЗАСЕЛ	ДАТА_ВЫЕЗДА
    //организация, кол_во человек, кол_во комнат, желаемый этаж, заселение, выезд, скидка, стоимость
    public String[][] getAllBooking() {
        try {
            Statement statement = conn.createStatement();
            String sql = "select * from БРОНЬ";
            ResultSet resultSet = statement.executeQuery(sql);

            String[][] data = new String[resultSet.getFetchSize()][8];
            data[0][0] = "организация";
            data[0][1] = "кол_во человек";
            data[0][2] = "кол_во комнат";
            data[0][3] = "желаемый этаж";
            data[0][4] = "заселение";
            data[0][5] = "выезд";
            data[0][6] = "скидка";
            data[0][7] = "стоимость";
            int i = 1;

            ArrayList<Integer> idsOrg = new ArrayList<>();
            ArrayList<String> idOrg_Start_Finish_idB_List = new ArrayList<>();

            while (resultSet.next()) {
                int id = resultSet.getInt("ID_ОРГАНИЗАЦИЯ");
                idsOrg.add(id);

                int countPeople = resultSet.getInt("КОЛВО_ЧЕЛ");
                data[i][1] = String.valueOf(countPeople);

                int countRoom = resultSet.getInt("КОЛВО_КОМНАТ");
                data[i][2] = String.valueOf(countRoom);

                int floor = resultSet.getInt("ЖЕЛАЕМЫЙ_ЭТАЖ");
                data[i][3] = String.valueOf(floor);

                Date settlement = resultSet.getDate("ДАТА_ЗАСЕЛ");
                data[i][4] = settlement.toString();

                Date exit = resultSet.getDate("ДАТА_ВЫЕЗДА");
                data[i][5] = exit.toString();

                Double sale = resultSet.getDouble("СКИДКА");
                data[i][6] = String.valueOf(sale);

                Double cost = resultSet.getDouble("СТОИМ_ЗАКАЗА");
                data[i][7] = String.valueOf(cost);

                String str = id + "_" + data[i][4] + "_" + data[i][5] + "_" + resultSet.getInt("ID_БРОНЬ");
                idOrg_Start_Finish_idB_List.add(str);

                i++;
            }

            int j = 1;
            ResultSet resultSet2;
            while (j <= idsOrg.size()) {
                String sql2 = "select НАЗВАНИЕ from ОРГАНИЗАЦИЯ where ID_ОРГАНИЗАЦИЯ=" + String.valueOf(idsOrg.get(j - 1));
                resultSet2 = statement.executeQuery(sql2);
                resultSet2.next();

                String nameOrg = resultSet2.getString("НАЗВАНИЕ");
                data[j][0] = nameOrg;
                nameIdOrg.put(nameOrg, idsOrg.get(j - 1));
                idNameOrg.put(idsOrg.get(j - 1), nameOrg);

                j++;
            }

            int k=0;
            String[] array = idOrg_Start_Finish_idB_List.toArray(new String[idOrg_Start_Finish_idB_List.size()]);
            while(k<idOrg_Start_Finish_idB_List.size()){
                String[] str = array[k].split("_");
                String name = idNameOrg.get(Integer.valueOf(str[0]));
                String newStr = name + "_" + str[1] + "_" + str[2];
                dataIdBooking.put(newStr, Integer.valueOf(str[3]));
                k++;
            }

            return data;

        } catch (Exception e) {
            e.printStackTrace();
            return new String[][]{};
        }
    }

    public boolean organizationExist(String name) {
        try {
            getAllBooking();
            int id = nameIdOrg.get(name);
            Statement statement = conn.createStatement();
            String sql = "select * from ОРГАНИЗАЦИЯ where ID_ОРГАНИЗАЦИЯ=" + String.valueOf(id);
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            if(!resultSet.getString("НАЗВАНИЕ").isEmpty()) return true;
            else return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    //ID_БРОНЬ	ID_ОРГАНИЗАЦИЯ	СТОИМ_ЗАКАЗА	СКИДКА	КОЛВО_ЧЕЛ	КОЛВО_КОМНАТ	ЖЕЛАЕМЫЙ_ЭТАЖ	ДАТА_ЗАСЕЛ	ДАТА_ВЫЕЗДА

    //"организация", "кол_во человек", "кол_во комнат", "желаемый этаж", "заселение", "выезд",
    //                "скидка", "стоимость"
    public void addBooking(String[] data){
        try{
            int id = nameIdOrg.get(data[0]);
            Statement statement = conn.createStatement();
            Date start = Date.valueOf(data[4]);
            Date finish = Date.valueOf(data[5]);
            String sql = "insert into БРОНЬ values(" + (getMaxIdBooking() + 1) + ", " + id + ", "
                    + data[7] + ", "+ data[6] + ", "+ data[1] + ", "+ data[2] + ", "
                    + data[3] + ", date'" + start.toString() + "', date'"+ finish.toString() + "')";
            statement.executeQuery(sql);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public int getMaxIdBooking(){
        try {
            Statement statement = conn.createStatement();
            String sql = "SELECT MAX(ID_БРОНЬ) as maxB FROM БРОНЬ";
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            return resultSet.getInt("maxB");
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    public String[] getSimpleBookingData(){
        getAllBooking();
        ArrayList<String> arrayList = new ArrayList<>();
        Iterator<HashMap.Entry<String, Integer>> entries = dataIdBooking.entrySet().iterator();
        while (entries.hasNext()) {
            HashMap.Entry<String, Integer> entry = entries.next();
            arrayList.add(entry.getKey());
        }
        return arrayList.toArray(new String[arrayList.size()]);
    }

    public void deleteBooking(String data){
        try {
            Integer idB = dataIdBooking.get(data);
            Statement statement = conn.createStatement();
            String sql = "DELETE FROM бронь WHERE id_бронь=" + idB;
            statement.executeQuery(sql);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
