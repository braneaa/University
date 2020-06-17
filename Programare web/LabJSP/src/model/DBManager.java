package model;


import javax.persistence.SqlResultSetMapping;
import javax.servlet.jsp.jstl.sql.SQLExecutionTag;
import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class DBManager {
    private Statement stmt;

    public DBManager() {
        connect();
    }

    public void connect(){
        try{
            Class.forName("org.gjt.mm.mysql.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jsp", "root", "");
            stmt = conn.createStatement();
        }
        catch (Exception ex){
            System.out.println("Error connecting to server:" + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public User authenticate(String username, String password){
        ResultSet rs;
        User u = null;
        try{
            rs = stmt.executeQuery("select * from Users where Username = '" + username + "' and Password = '" + password + "'");
            if (rs.next()){
                u = new User(rs.getInt("UserID"), rs.getString("Username"), rs.getString("Password"));
            }
            rs.close();
        }
        catch (SQLException sqle){
            System.out.println("Error authenticating!");
            sqle.printStackTrace();
        }
        return u;
    }

    public void clearUserData(int userId){
        try{
            System.out.println("entered clearUserData");
            stmt.execute("delete from usercities where UserID = " + userId);
            System.out.println("left clearUserData");
            System.out.println();
        }
        catch (SQLException sqle){
            sqle.printStackTrace();
        }
    }

    public void removeLastDestinationForUser(int userId){
        ResultSet rs;
        try{
            System.out.println("entered removeLastDestinationForUser: userId = " + userId);
            rs = stmt.executeQuery("select Nr from usercities where UserID = " + userId + " order by Nr desc limit 1");
            if (rs.next()){
                int nr = rs.getInt("Nr");
                stmt.execute("delete from usercities where Nr = " + nr);
                System.out.println("left removeLastDestinationForUser: removed nr = " + nr);
                System.out.println();
            }
        }
        catch (SQLException sqle){
            sqle.printStackTrace();
        }
    }

    public void addCityToRoute(int userId,String cityName){
        ResultSet rs;
        try{
            System.out.println("entered addCityToRoute: userId = " + userId + ", cityName = " + cityName);
            rs = stmt.executeQuery("select * from cities where Name = '" + cityName + "'");
            if (rs.next()){
                int id = rs.getInt("CityID");
                rs = stmt.executeQuery("SELECT Nr FROM usercities where UserID = " + userId + " ORDER BY Nr desc LIMIT 1");
                int nr = 0;
                if (rs.next())
                    nr = rs.getInt("Nr");
                boolean ok = stmt.execute("insert into usercities(UserID, CityID, Nr) values(" + userId + ", " + id + ", " + (nr+1) + ")");
                System.out.println("left addCityToRoute");
                System.out.println();
            }
        }
        catch (SQLException sqle){
            System.out.println("Error retrieving cities!");
            sqle.printStackTrace();
        }
    }

    public City getUserCurrent(int userId){
        ResultSet rs;
        try{
            System.out.println("entered getUserCurrent");
            rs = stmt.executeQuery("SELECT C.CityID, C.Name FROM usercities UC INNER JOIN cities C ON C.CityID = UC.CityID " +
                    "WHERE UC.UserID = " + userId + " ORDER BY UC.Nr DESC LIMIT 1");
            if (rs.next()) {
                City c = new City(rs.getInt("CityID"), rs.getString("Name"));
                rs.close();
                System.out.println("left getUserCurrent: result = " + c);
                System.out.println();
                return c;
            }
        }
        catch  (SQLException sqle){
            sqle.printStackTrace();
        }
        return null;
    }

    public Map<City, Integer> getDestinationCities(int userId){
        Map<City, Integer> cities = new HashMap<>();
        ResultSet rs;
        try{
            System.out.println("entered getDestinationCities");
            rs = stmt.executeQuery("select count(*) as count from usercities where UserID = " + userId);
            rs.next();
            System.out.println(rs.getInt("Count"));
            rs = stmt.executeQuery("select CityID from usercities where UserID = " + userId + " ORDER BY Nr DESC");
            int id = 0;
            if (rs.next())
                id = rs.getInt("CityID");
            System.out.println("Current city ID: " + id);
            String sqlCommand = "select C.CityID, C.Name, P.Distance from Paths P " +
                    "INNER JOIN Cities C ON P.DestinationCityID = C.CityID WHERE P.SourceCityID = " + id;
            rs = stmt.executeQuery(sqlCommand);
            while (rs.next()) {
                City city =  new City(rs.getInt("CityID"), rs.getString("Name"));
                System.out.println(city);
                cities.put(city, rs.getInt("Distance"));
            }
            System.out.println("left getDestinationCities");
            System.out.println();
        }
        catch (SQLException sqle){
            System.out.println("error retrieving destination cities");
            sqle.printStackTrace();
        }
        return cities;
    }

    public List<City> getFullRoute(int userId){
        ResultSet rs;
        List<City> cities = new ArrayList<>();
        try{
            System.out.println("entered getFullRoute");
            rs = stmt.executeQuery("SELECT C.CityID, C.Name FROM usercities UC inner join cities C on UC.CityID = C.CityID" +
                    " WHERE UC.UserID = " + userId + " ORDER BY UC.Nr");
            while (rs.next()){
                cities.add(new City(rs.getInt("CityID"), rs.getString("Name")));
            }
            System.out.println("left getFullRoute");
            System.out.println();
        }
        catch (SQLException sqle){
            sqle.printStackTrace();
        }
        return cities;
    }

    public void beginTran(){
        try {
            stmt.execute("BEGIN TRANSACTION");
        }
        catch (SQLException sqle){
            sqle.printStackTrace();
        }
    }

    public void endTran(){
        try {
            stmt.execute("COMMIT TRANSACTION");
        }
        catch (SQLException sqle){
            sqle.printStackTrace();
        }
    }

}
