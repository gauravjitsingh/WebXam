package datalayer;

import java.sql.*;

public class DBOperations {
    
    private static Connection con=null ;
    
    public static Connection getConnection()
    {
        try
        {
            if(con==null)
            {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver") ;   
                con = DriverManager.getConnection("jdbc:sqlserver://SANJAM_INFOTECH\\"
                        + "GAURAVJIT:50820;database=ExamDB;userName=sa;password=gauravjit") ;
                
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex) ;
        }
        return con ;
    }
}
//We have made DBOperations class so we don't have to write the syntax for making the connection