package Model;

import Model.Tables.Unternehmen;

import java.sql.*;

public class DBcon {
    private Connection connection;
    private final String connectionUrl = "jdbc:mysql://sql11.freesqldatabase.com:3306/sql11495010";
    private final String userName = "sql11495010";
    private final String password = "SRwSuf13UR";


    public DBcon(){
        createConnection();
//        insertSchueler(1,2,"Lecker","Leo","m",7);
        printAllSchueler();
    }

    public static void main(String[] args) {
        new DBcon();
    }

    /**
     * Prints all "schueler" joined by "unternehmen" and "kurs"
     *
     */

    public void printAllSchueler(){
        ResultSet resultSet = getAllSchueler();
        try{
            while(resultSet.next()){
                System.out.print(resultSet.getString("vorname")+", ");
                System.out.print(resultSet.getString("nachname")+", ");
                System.out.print(resultSet.getString("geschlecht")+", ");
                System.out.print(resultSet.getInt("vorkenntnisse")+", ");
                System.out.print(resultSet.getString("kurs.bezeichnung")+", ");
                System.out.print(resultSet.getString("unternehmen.name"));
                System.out.println();
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    /**
     * Gets all "schueler" joined by "unternehmen" and "kurs"<br>
     * Select data with:<br>
     * String "vorname"<br>
     * String "nachname"<br>
     * String "geschlecht"<br>
     * String "vorkenntnisse"<br>
     * String "kurs.bezeichnung"<br>
     * String "unternehmen.name"
     *
     * @return  ResultSet
     */

    public ResultSet getAllSchueler(){
        ResultSet resultSet = null;
        try{
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(
                    "SELECT vorname,nachname,geschlecht,vorkenntnisse,kurs.bezeichnung,unternehmen.name " +
                            "FROM (schueler LEFT JOIN unternehmen ON schueler.uId = unternehmen.uId) " +
                            "LEFT JOIN kurs ON schueler.kId = kurs.kId");

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return resultSet;
    }

    /**
     * Inserts a schueler into table schueler
     *
     * @param uId   Foreign Key "Unternehmen"
     * @param kId   Foreign Key "Kurs"
     * @param nachname  Nachname Schueler
     * @param vorname   Vorname Schueler
     * @param geschlecht    Geschlecht Schueler
     * @param vorkenntnisse Vorkenntnisse Schueler
     */

    public void insertSchueler(int uId, int kId, String nachname, String vorname, String geschlecht, int vorkenntnisse){
        try{
            PreparedStatement preparedStatement ;
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO schueler (uId,kId,nachname,vorname,geschlecht,vorkenntnisse) "+
                            "Values(?,?,?,?,?,?);");
            preparedStatement.setInt(1,uId);
            preparedStatement.setInt(2,kId);
            preparedStatement.setString(3,nachname);
            preparedStatement.setString(4,vorname);
            preparedStatement.setString(5,geschlecht);
            preparedStatement.setInt(6,vorkenntnisse);
            preparedStatement.execute();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    /**
     * Inserts a kurs into table kurs
     *
     * @param bezeichnung   name of "Kurs"
     * @param raum  Room of "Kurs"
     */

    public void insertKurs(String bezeichnung, String raum){
        try{
            PreparedStatement preparedStatement ;
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO kurs (bezeichnung,raum) "+
                            "Values(?,?);");
            preparedStatement.setString(1,bezeichnung);
            preparedStatement.setString(2,raum);
            preparedStatement.execute();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    /**
     * Inserts an unternehmen into table unternehmen
     *
     * @param name  name of "unternehmen"
     */

    public void insertUnternehmen(String name){
        try{
            PreparedStatement preparedStatement ;
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO unternehmen (name) "+
                            "Values(?);");
            preparedStatement.setString(1,name);
            preparedStatement.execute();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    /**
     * Deletes schueler by vorname and nachname
     *
     * @param vorname  Vorname of schueler to delete
     * @param nachname  Nachname of schueler to delete
     */

    public void deleteSchuelerByFullName(String vorname,String nachname){
        try{
            PreparedStatement preparedStatement ;
            preparedStatement = connection.prepareStatement(
                    "DELETE FROM schueler "+
                            "WHERE vorname LIKE ? AND nachname LIKE ?;");
            preparedStatement.setString(1,vorname);
            preparedStatement.setString(2,nachname);
            preparedStatement.execute();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    /**
     * Deletes unternehmen by name
     *
     * @param name  Name of unternehmen to delete
     */

    public void deleteUnternehmenByName(String name){
        try{
            PreparedStatement preparedStatement ;
            preparedStatement = connection.prepareStatement(
                    "DELETE FROM unternehmen "+
                            "WHERE name LIKE ?;");
            preparedStatement.setString(1,name);
            preparedStatement.execute();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    /**
     * Deletes kurs by name
     *
     * @param bezeichnung  Name of kurs to delete
     */

    public void deleteKursByBezeichnung(String bezeichnung){
        try{
            PreparedStatement preparedStatement ;
            preparedStatement = connection.prepareStatement(
                    "DELETE FROM kurs "+
                            "WHERE bezeichnung LIKE ?;");
            preparedStatement.setString(1,bezeichnung);
            preparedStatement.execute();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    /**
     * Creates a Connection to Database;
     * Uses values connectionUrl, userName & password;
     *
     */

    private void createConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(this.connectionUrl,this.userName,this.password);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
