package datalayer;

import beans.Test;
import beans.TestCategory;
import java.sql.*;
import java.util.ArrayList;

public class DALTestCategory {

            private Connection cn ;

    public DALTestCategory() {
        cn = DBOperations.getConnection() ;
    }
    
    public eStatus saveTestCategory(beans.TestCategory testCategory) 
    {

        eStatus ret = null;

        try 
        {

            PreparedStatement ps = cn.prepareStatement("insert into TestCategories values(?,?,?)");

            ps.setInt(1, testCategory.TestInfo.getTestId());
            ps.setInt(2, testCategory.CategoryInfo.getCategoryId());
            ps.setInt(3, testCategory.getNoofQuestions());

            ps.executeUpdate();
            ret = eStatus.Suceeded;
        } 
        catch (Exception ex) 
        {
            ret = eStatus.Fail;
        }

        return ret;
    }

    public eStatus deleteTestCategory(int pTestCategoryId) 
    {
        eStatus ret = null;

        try 
        {
            PreparedStatement ps = cn.prepareStatement("Delete from TestCategories where TestCategoryId=?");

            ps.setInt(1, pTestCategoryId);

            ps.executeUpdate();

            ret = eStatus.Delete;
        } 
        catch (Exception ex) 
        {
            ret = eStatus.Fail;
        }

        return ret;
    }

    public ArrayList<beans.TestCategory> getTestCategories(int pTestId)
    {
        ArrayList<beans.TestCategory> AllTestCategories = new ArrayList<TestCategory>() ;
        
        try
        {
            PreparedStatement ps = cn.prepareStatement("select tc.TestCategoryId as [tcid], c.CategoryName as [name], "
                    + "tc.NumberofQuestions as [noq] from TestCategories as [tc], Categories as [c] where tc.CategoryId=c.CategoryId and tc.TestId=?") ;
            ps.setInt(1, pTestId);
            ResultSet rs = ps.executeQuery() ;
            
            beans.TestCategory tc ;
            
            while(rs.next())
            {
                tc = new TestCategory() ;
                tc.setTestCategoryId(rs.getInt("tcid"));
                tc.CategoryInfo.setName(rs.getString("name"));
                tc.setNoofQuestions(rs.getInt("noq"));
                
                AllTestCategories.add(tc);
            }
            
            rs.close();
        }
        catch(Exception ex)
        {
            System.out.println();
        }

        return AllTestCategories ;
    }
}
