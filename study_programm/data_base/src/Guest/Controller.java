package Guest;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

public class Controller {
    Connection conn;
    HashMap<String, Integer> nameIdOrg = new HashMap<>();
    HashMap<String, Integer> nameIdCorpus = new HashMap<>();
    HashMap<Integer, String> idNameCorpus = new HashMap<>();
    HashMap<String, Integer> nameIdService = new HashMap<>();
    HashMap<Integer, String> idNameService = new HashMap<>();
    HashMap<Integer, Double> idCostService = new HashMap<>();

    public Controller(Connection conn_) {
        conn = conn_;
    }

    //columnNames = {0"организация", 1"дата заселения", 2"дата выезда", 3"фио", 4"долг за доп.ус.", 5"корпус",
    //                6"номер"};
    //ID_ПОСТОЯЛЕЦ	ID_ОРГАНИЗАЦИЯ	NAME
    public void addGuest(String[] data) {
        try {
            int idOrg = nameIdOrg.get(data[0]);
            int idGuest = maxIdGuest() + 1;
            int idCorpus = nameIdCorpus.get(data[5]);
            Statement statement = conn.createStatement();
            Date start = Date.valueOf(data[1]);
            Date finish = Date.valueOf(data[2]);

            String sql = "insert into ПОСТОЯЛЕЦ values(" + idGuest + ", " + idOrg + ", '" + data[3] + "')";
            statement.executeQuery(sql);

            //ID_ЗАПИСЬ	ID_ПОСТОЯЛЕЦ	ID_КОРПУС	ID_НОМЕР	ДАТА_ЗАСЕЛ	ДАТА_ВЫЕЗД	ДОЛГ
            String sql2 = "insert into ИСТОРИЯ values(" + (maxHistoryId() + 1)+ ", " + idGuest + ", " + idCorpus + ", "
                    + data[6] + ", date'" + start.toString() + "', date'" + finish.toString() + "', "
                    + data[4] + ")";
            statement.executeQuery(sql2);

            //сказать что номер занят (если текущая дата подходит)
            Date now = new Date(System.currentTimeMillis());
            if(now.compareTo(start)>=0 && now.compareTo(finish)<=0) {
                String sql3 = "update номер set ЗАНЯТ='yes' where ID_НОМЕР=" + data[6] + " and ID_КОРПУС=" + idCorpus;
                statement.executeQuery(sql3);
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    private int maxIdGuest() {
        try {
            Statement statement = conn.createStatement();
            String sql = "select MAX(ID_ПОСТОЯЛЕЦ) from ПОСТОЯЛЕЦ";
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            return resultSet.getInt("MAX(ID_ПОСТОЯЛЕЦ)");
        } catch (Exception e) {
            e.printStackTrace();
            return -2;
        }
    }

    private int maxHistoryId() {
        try {
            Statement statement = conn.createStatement();
            String sql = "select MAX(ID_ЗАПИСЬ) from история";
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            return resultSet.getInt("MAX(ID_ЗАПИСЬ)");
        } catch (Exception e) {
            e.printStackTrace();
            return -2;
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

    public String[] getLastInf(String orgName, String name) {
        try {
            int idOrg = nameIdOrg.get(orgName);
            Statement statement = conn.createStatement();
            String sql = "select ID_ПОСТОЯЛЕЦ from ПОСТОЯЛЕЦ where ID_ОРГАНИЗАЦИЯ=" + idOrg
                    + " and NAME='" + name + "'";
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            int idGuest = resultSet.getInt("ID_ПОСТОЯЛЕЦ");

            String sql2 = "select * from ИСТОРИЯ where ID_ПОСТОЯЛЕЦ=" + idGuest
                    + " ORDER BY дата_засел"+" desc";
            //ID_ЗАПИСЬ	ID_ПОСТОЯЛЕЦ	ID_КОРПУС	ID_НОМЕР	ДАТА_ЗАСЕЛ	ДАТА_ВЫЕЗД	ДОЛГ
            ResultSet resultSet2 = statement.executeQuery(sql2);
            resultSet2.next();
            String[] resDate = new String[7];
            resDate[0] = String.valueOf(resultSet2.getInt("ID_ЗАПИСЬ"));
            resDate[1] = String.valueOf(resultSet2.getInt("ID_ПОСТОЯЛЕЦ"));
            resDate[2] = String.valueOf(resultSet2.getInt("ID_КОРПУС"));
            resDate[3] = String.valueOf(resultSet2.getInt("ID_НОМЕР"));
            Date st = resultSet2.getDate("ДАТА_ЗАСЕЛ");
            Date fn = resultSet2.getDate("ДАТА_ВЫЕЗД");
            resDate[4] = st.toString();
            resDate[5] = fn.toString();
            resDate[6] = String.valueOf(resultSet2.getDouble("ДОЛГ"));
            return resDate;

        } catch (Exception e) {
            e.printStackTrace();
            return new String[]{};
        }
    }

    public String[] getAllService() {
        try {
            Statement statement = conn.createStatement();
            String sql = "select * from СЛУЖБЫ_БЫТА";
            ResultSet resultSet = statement.executeQuery(sql);
            String[] discrAr = new String[resultSet.getFetchSize()];
            int i = 0;
            while (resultSet.next()) {
                int id = resultSet.getInt("ID_УСЛУГА");
                String discr = resultSet.getString("ОПИСАНИЕ");
                discrAr[i] = discr;
                i++;
                double cost = resultSet.getDouble("ЦЕНА");

                //заполнение словарей
                nameIdService.put(discr, id);
                idNameService.put(id, discr);
                idCostService.put(id, cost);
            }
            return discrAr;
        } catch (Exception e) {
            e.printStackTrace();
            return new String[]{};
        }
    }

    //0ID_ЗАПИСЬ	1ID_ПОСТОЯЛЕЦ	2ID_КОРПУС	3ID_НОМЕР	4ДАТА_ЗАСЕЛ	5ДАТА_ВЫЕЗД	6ДОЛГ
    public void addCredit(String serviceName, String[] dataNote) {
        int id_serv = nameIdService.get(serviceName);
        double cost = Double.valueOf(dataNote[6]) + idCostService.get(id_serv);
        try {

            //добавить/обновить запись в история_услуг
            Statement statement = conn.createStatement();
            String sql = "select * from история_услуг where id_запись=" + dataNote[0] + "and id_услуги=" + id_serv;
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                int count = resultSet.getInt("КОЛИЧЕСТВО");
                String sql2 = "update история_услуг set КОЛИЧЕСТВО=" + (count + 1)
                        + "where id_запись=" + dataNote[0] + "and id_услуги=" + id_serv;
                statement.executeQuery(sql2);
            } else {
                String sql2 = "insert into история_услуг values(" + dataNote[0] + ", " + id_serv + ", " + 1 + ")";
                statement.executeQuery(sql2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //обновить долг в история
        try {
            Statement statement = conn.createStatement();
            String sql = "update история set ДОЛГ=" + cost + " where id_запись=" + dataNote[0];
            statement.executeQuery(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //0ID_ЗАПИСЬ	1ID_ПОСТОЯЛЕЦ	2ID_КОРПУС	3ID_НОМЕР	4ДАТА_ЗАСЕЛ	5ДАТА_ВЫЕЗД	6ДОЛГ
    public String[] getGuestServ(String[] dataNote) {
        try {
            Statement statement = conn.createStatement();
            String sql = "select ID_УСЛУГИ from ИСТОРИЯ_УСЛУГ where id_запись=" + dataNote[0];
            ResultSet resultSet = statement.executeQuery(sql);
            Integer[] idArray = new Integer[resultSet.getFetchSize()];
            int i = 0, j;
            while (resultSet.next()) {
                idArray[i] = resultSet.getInt("ID_УСЛУГИ");
                i++;
            }
            String[] result = new String[i];
            getAllService();
            for (j = i, i = 0; i < j; i++) {
                result[i] = idNameService.get(idArray[i]);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return new String[]{};
        }
    }

    public String[] getGuestCompl(Integer idGuest) {
        try {
            Statement statement = conn.createStatement();
            String sql = "select ОПИСАНИЕ from ЖАЛОБА where ID_ПОСТОЯЛЕЦ=" + idGuest;
            ResultSet resultSet = statement.executeQuery(sql);
            String[] result = new String[resultSet.getFetchSize()];
            int i=0;
            while (resultSet.next()) {
                result[i] = resultSet.getString("ОПИСАНИЕ");
                i++;
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return new String[]{};
        }
    }

    public String[][] getAllHistory(Integer idGuest){
        try {
            Statement statement = conn.createStatement();
            String sql = "select * from ИСТОРИЯ where ID_ПОСТОЯЛЕЦ=" + idGuest;
            ResultSet resultSet = statement.executeQuery(sql);

            // String[] header = {"корпус", "номер", "заселение", "выезд", "счет"};
            String[][] data = new String[resultSet.getFetchSize()][5];
            data[0][0] = "корпус";
            data[0][1] = "номер";
            data[0][2] = "заселение";
            data[0][3] = "выезд";
            data[0][4] = "счет";
            int i = 1;

            setCorpusMap();
            //ID_ЗАПИСЬ	ID_ПОСТОЯЛЕЦ	ID_КОРПУС	ID_НОМЕР	ДАТА_ЗАСЕЛ	ДАТА_ВЫЕЗД	ДОЛГ
            while(resultSet.next()){
                data[i][0] = idNameCorpus.get(resultSet.getInt("ID_КОРПУС"));
                data[i][1] = String.valueOf(resultSet.getInt("ID_НОМЕР"));
                data[i][2] = resultSet.getDate("ДАТА_ЗАСЕЛ").toString();
                data[i][3] = resultSet.getDate("ДАТА_ВЫЕЗД").toString();
                data[i][4] = String.valueOf(resultSet.getDouble("ДОЛГ"));
                i++;
            }
            return data;
        }catch (Exception e){
            e.printStackTrace();
            return new String[][]{};
        }
    }

    private void setCorpusMap(){
        try {
            Statement statement = conn.createStatement();
            String sql = "select ID_КОРПУС, НАЗВАНИЕ from КОРПУС";
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                int id = resultSet.getInt("ID_КОРПУС");
                String name = resultSet.getString("НАЗВАНИЕ");
                idNameCorpus.put(id, name);
            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }


    //ID_ЗАПИСЬ	ID_ПОСТОЯЛЕЦ	ID_КОРПУС	ID_НОМЕР	ДАТА_ЗАСЕЛ	ДАТА_ВЫЕЗД	ДОЛГ
    //таблица
    //String[] columnNames = {"корпус", "номер", "заселение", "выезд", "долг"};
    public void addHistoryNote(String[] data, Integer idGuest){
        //новая запись в таблицу История
        try {
            Statement statement = conn.createStatement();
            int id_note = getmaxIdNote()+1;
            getMaps();
            String sql = "insert into история values("
                    + id_note +", "
                    + idGuest +", "
                    + nameIdCorpus.get(data[0]) +", "
                    + data[1] +", "
                    + "date'" + data[2] +"', "
                    + "date'" + data[3] +"', "
                    + data[4]
                    +")";
            statement.executeQuery(sql);
        }catch(Exception e){
            e.printStackTrace();
        }
        //помечаем, что номер теперь занят
        Date start = Date.valueOf(data[2]);
        Date finish = Date.valueOf(data[3]);
        Date now = new Date(System.currentTimeMillis());
        if(now.compareTo(start)>=0 && now.compareTo(finish)<=0) {
            try {
                Statement statement = conn.createStatement();
                String sql = "update номер set ЗАНЯТ='yes' where ID_НОМЕР=" + data[1] + " and ID_КОРПУС=" + nameIdCorpus.get(data[0]);
                statement.executeQuery(sql);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public int getmaxIdNote(){
        try {
            Statement statement = conn.createStatement();
            String sql = "select MAX(id_запись) from ИСТОРИЯ";
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            return resultSet.getInt("MAX(id_запись)");
        }catch(Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    public void evict(Integer id_corpus, Integer id_number){
        try {
            Statement statement = conn.createStatement();
            String sql = "update номер set ЗАНЯТ='no' where ID_НОМЕР="
                    + id_number +
                    "and ID_КОРПУС=" + id_corpus;
            statement.executeQuery(sql);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public boolean numberIsBusy(String corpus, String number){
        getMaps();
        int id_c = nameIdCorpus.get(corpus);
        try {
            Statement statement = conn.createStatement();
            String sql = "select занят from номер where ID_НОМЕР="
                    + number +
                    "and ID_КОРПУС=" + id_c;
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            String answer = resultSet.getString("занят");
            if(answer.equals("no")) return false;
            if(answer.equals("yes")) return true;
        }catch(Exception e){
            e.printStackTrace();
            return true;
        }
        return true;
    }
}
