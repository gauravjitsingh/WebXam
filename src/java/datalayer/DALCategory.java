package datalayer;

import beans.Category;
import java.sql.*;
import java.util.ArrayList ;

public class DALCategory {

    private Connection cn;

    public DALCategory() {
        cn = DBOperations.getConnection();
    }

    public eStatus saveCategory(beans.Category category) {
        eStatus ret = null;

        try 
        {
            PreparedStatement ps = cn.prepareStatement("insert into categories values(?,?)") ;
            ps.setString(1, category.getName());
            ps.setString(2, category.getDescription());
            
            ps.executeUpdate();
            
            ret = eStatus.Suceeded ;
        } 
        catch (Exception ex) 
        {
            ret = eStatus.Fail ;
        }
        return ret;
    }

    public eStatus deleteCategory(int pCategoryId) {
        eStatus ret = null;

        try
        {
            PreparedStatement ps = cn.prepareStatement("Delete from Categories where CategoryId=?") ;
            ps.setInt(1, pCategoryId);
            
            ps.executeUpdate();
            
            ret = eStatus.Delete ;
        }
        catch(Exception ex)
        {
            ret = eStatus.Fail ;
        }
        return ret;
    }

    public eStatus update(beans.Category category) {
        eStatus ret = null;

        try
        {
            PreparedStatement ps = cn.prepareStatement("update categories set CategoryName=?, Description=? where CategoryId=?") ;
            ps.setString(1, category.getName());
            ps.setString(2, category.getDescription());
            ps.setInt(3, category.getCategoryId());
            
            ps.executeUpdate();
            
            ret = eStatus.Update ;
        }
        catch(Exception ex)
        {
            ret = eStatus.Fail ;
        }
        
        return ret;
    }
    
    public eStatus exsists(String name)
    {
        eStatus ret = null ;
        
        try
        {
            Statement st = cn.createStatement() ;
            ResultSet rs = st.executeQuery("Select CategoryName from categories") ;
            
                while (rs.next()) 
                {
                    if (name.equals(rs.getString("CategoryName"))) 
                    {
                        ret = eStatus.Duplicate;
                        break;
                    } 
                    else 
                    {
                        ret = eStatus.Suceeded;
                    }
                }
            
            rs.close();
        }
        catch(Exception ex)
        {
            ret = eStatus.Fail ;
        }
        
        return ret ;
    }
    
    public ArrayList<beans.Category> getCategories()
    {
        ArrayList<beans.Category> AllCategories = new ArrayList<Category>() ;

        try
        {
            Statement st = cn.createStatement() ;
            ResultSet rs = st.executeQuery("Select * from categories") ;

            beans.Category c ;

            while(rs.next())
            {
                c = new Category() ;
                c.setCategoryId(rs.getInt("CategoryId"));
                c.setName(rs.getString("CategoryName"));
                c.setDescription(rs.getString("Description"));
                
                AllCategories.add(c);
            }
            rs.close();
        }
        catch(Exception ex)
        {
            System.out.println();
        }
        return AllCategories ;
    }
    
}

