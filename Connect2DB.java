package com.calculator_ii;
import java.sql.*;

public class Connect2DB {
public static Connection connector(){
    java.sql.Connection cnx = null;
    String driver = "com.mysql.cj.jdbc.Driver";
    //String url = "jdbc:mysql://db4free.net:3306/mydb4567";
    String url = "jdbc:mysql://localhost:3306";
    String user = "root";
    String password = "Durga@1234";

    try {
        Class.forName(driver);
        cnx = DriverManager.getConnection(url, user, password);
        System.out.println("xxxxx");
        return cnx;
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}
public static void connectDB(Connection con, String dbName)
  throws SQLException {

  Statement stmt = null;
  String query = "show databases";
                 //+ dbName + ".agents";
  try {
      stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery(query);
      while (rs.next()) {
          String firstName = rs.getString("Database");

          System.out.println(firstName);
      }
  } catch (SQLException e ) {
      System.out.println ("SQL Error");
  } finally {
      if (stmt != null) { stmt.close(); }
  }
}
public static void main (String[] args){
   Connection con = Connect2DB.connector();
   if (con == null) {
           String x = "Not Connected";
           System.out.println(x);
       }
   try {
   Connect2DB.connectDB(con,"sakila");}
   catch (Exception e){
       e.printStackTrace();
   }
}

}