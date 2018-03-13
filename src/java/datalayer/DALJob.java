package datalayer;

import beans.Job;
import java.sql.*;
import java.util.ArrayList ;

public class DALJob {

    private Connection cn ;
    
    public DALJob() 
    {
        cn = DBOperations.getConnection() ;
    }

    public eStatus saveJob(beans.Job job)
    {
        eStatus ret = null ;
        
        try
        {
            PreparedStatement ps = cn.prepareStatement("insert into jobs values(?,?)") ;
            ps.setString(1, job.getName());
            ps.setString(2, job.getDescription());
            
            ps.executeUpdate();
            ret = eStatus.Suceeded ;
        }
        catch(Exception ex)
        {
            ret = eStatus.Fail ;
        }
        
        return ret ;
    }
    
    public eStatus deleteJob(int DJID)
    {
        eStatus ret = null ;
        
        try
        {
            PreparedStatement ps = cn.prepareStatement("Delete from jobs where JobId=?") ;
            ps.setInt(1, DJID);
            
            ps.executeUpdate();
            
            ret = eStatus.Delete ;
        }
        catch(Exception ex)
        {
            ret = eStatus.Fail ; 
        }
        return ret ;
    }
    
    public eStatus updateJob(beans.Job job)
    {
        eStatus ret = null ;
        
        try
        {
            PreparedStatement ps = cn.prepareStatement("Update jobs set Name=?,Description=? where JobId=?") ;
            ps.setString(1, job.getName());
            ps.setString(2, job.getDescription());
            ps.setInt(3, job.getJobId());
            
            ps.executeUpdate();
            
            ret = eStatus.Update ;
        }
        catch(Exception ex)
        {
            ret = eStatus.Fail ;
        }
        
        return ret ;
    }
    
    public beans.Job getJob(int pJobId)
    {
        beans.Job j =new Job() ;
        
        try
        {
            PreparedStatement ps = cn.prepareStatement("Select * from jobs where JobId=?") ;
            ps.setInt(1, pJobId);
            
            ResultSet rs = ps.executeQuery() ;
            while(rs.next())
            {
                j.setJobId(pJobId);
                j.setName(rs.getString("Name"));
                j.setDescription(rs.getString("Description"));
            }
            
            rs.close();
        }
        catch(Exception ex)
        {
            System.out.println(ex) ;
        }
        
        return j ;
    }
    
    public ArrayList<beans.Job> getJobs()
    {
        ArrayList<beans.Job> AllJobs = new ArrayList<Job>() ;
        
        try
        {
            Statement st = cn.createStatement() ;
            ResultSet rs = st.executeQuery("Select * from jobs") ;
            
            beans.Job j ;
            while(rs.next())
            {
                j = new Job() ;
                
                j.setJobId(rs.getInt("JobId"));
                j.setName(rs.getString("Name"));
                j.setDescription(rs.getString("Description"));
                
                AllJobs.add(j);
            }
            
            rs.close();
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
        return AllJobs ;
    }
    
    public eStatus exsists(String name)
    {
        eStatus ret = null ;
        
        try
        {
            
        }
        catch(Exception ex)
        {
            ret = eStatus.Fail ;
        }
        
        return ret ;
    }
}
