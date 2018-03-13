package datalayer;

import beans.Candidate;
import beans.Test;
import java.sql.*;
import java.util.ArrayList;

public class DALTest {

    private Connection cn ;
    
    public DALTest() 
    {
        cn = DBOperations.getConnection() ;
    }
    
    public eStatus saveTest(beans.Test test)
    {
        eStatus ret = null ;

        try
        {
            PreparedStatement ps = cn.prepareStatement("insert into tests values(?,?,?,?,?,?,?,?)") ;
            
            ps.setInt(1, test.DepartmentInfo.getDepartmentId());
            ps.setInt(2, test.JobInfo.getJobId());
            ps.setString(3, test.getTestDate());
            ps.setInt(4, test.getVacancies());
            ps.setShort(5, test.getPassingMarks());
            ps.setShort(6, test.getDuration());
            ps.setString(7, test.getDescription());
            ps.setByte(8, test.getLevel());
        
            ps.executeUpdate();
            ret = eStatus.Suceeded ;
        }
        catch(Exception ex)
        {
            ret = eStatus.Fail ;
        }
        
        return ret ;
    }
    
    public eStatus deleteTest(int pTestId)
    {
        eStatus ret = null ;

        try
        {
            PreparedStatement ps = cn.prepareStatement("Delete from Tests where TestId=?") ;
            
            ps.setInt(1, pTestId);
            
            ps.executeUpdate();
            
            ret = eStatus.Delete ;
        }
        catch(Exception ex)
        {
            ret = eStatus.Fail ;
        }
        
        return ret ;
    }
    
    public eStatus updateTest(beans.Test test)
    {
        eStatus ret = null ;

        try
        {
            PreparedStatement ps = cn.prepareStatement("Update Tests set DepartmentId=?, JobId=?, TestDate=?, Vacancies=?, PassingMarks=?, Duration=?, Description=?, Level=? where TestId=?") ;

            ps.setInt(1, test.DepartmentInfo.getDepartmentId());
            ps.setInt(2, test.JobInfo.getJobId());
            ps.setString(3, test.getTestDate());
            ps.setInt(4, test.getVacancies());
            ps.setShort(5, test.getPassingMarks());
            ps.setShort(6, test.getDuration());
            ps.setString(7, test.getDescription());
            ps.setByte(8, test.getLevel());
            ps.setInt(9, test.getTestId());
            
            ps.executeUpdate();
            ret = eStatus.Update ;
        }
        catch(Exception ex)
        {
            ret = eStatus.Fail ;
        }
        
        return ret ;
    }
    
    public beans.Test getTest(int pTestId)
    {
        beans.Test t = new Test() ;

        try
        {
            PreparedStatement ps = cn.prepareStatement("select t.testid,t.DepartmentId, d.Name, t.JobId, j.name as [JobName], Convert(varchar, t.TestDate, 103) as [TestDate],t.vacancies, "
                    + "t.passingmarks, t.duration, t.description, t.level from Tests as [t], Jobs as [j], Departments as [d] where t.JobId = j.JobId and "
                    + "t.DepartmentId = d.DepartmentId and TestId= ?") ;
            ps.setInt(1, pTestId);
            
            ResultSet rs = ps.executeQuery() ;
            
            while(rs.next())
            {
                t.setTestId(rs.getInt("TestId"));
                t.DepartmentInfo.setDepartmentId(rs.getInt("DepartmentId"));
                t.DepartmentInfo.setName(rs.getString("Name"));
                t.JobInfo.setJobId(rs.getInt("JobId"));
                t.JobInfo.setName(rs.getString("JobName"));
                t.setTestDate(rs.getString("TestDate"));
                t.setVacancies(rs.getInt("Vacancies"));
                t.setPassingMarks(rs.getShort("PassingMarks"));
                t.setDuration(rs.getShort("Duration"));
                t.setDescription(rs.getString("Description"));
                t.setLevel(rs.getByte("Level"));
            }
            
            rs.close();
        }
        catch(Exception ex)
        {
            System.out.println();
        }
        
        return t ;
    }
        
    public eStatus exsists()
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
    
    public ArrayList<beans.Test> getDepartmentTest(int pDepartmentId)
    {
        ArrayList<beans.Test> AllTests = new ArrayList<Test>() ;
        
        try
        {
            PreparedStatement ps = cn.prepareStatement("select t.testid,t.DepartmentId, j.jobid, j.name, Convert(varchar, t.TestDate, 103) as [TestDate], "
                    + "t.vacancies, t.passingmarks, t.duration, t.description, t.level from Tests as [t], Jobs as [j] "
                    + "where t.JobId = j.JobId and t.DepartmentId = ?") ;
            ps.setInt(1, pDepartmentId);
            ResultSet rs = ps.executeQuery() ;
            
            beans.Test t ;
            
            while(rs.next())
            {
                t = new Test() ;
                
                t.setTestId(rs.getInt("TestId"));
                t.DepartmentInfo.setDepartmentId(rs.getInt("DepartmentId"));
                t.JobInfo.setJobId(rs.getInt("JobId"));
                t.JobInfo.setName(rs.getString("Name"));
                t.setTestDate(rs.getString("TestDate"));
                t.setVacancies(rs.getInt("Vacancies"));
                t.setPassingMarks(rs.getShort("PassingMarks"));
                t.setDuration(rs.getShort("Duration"));
                t.setDescription(rs.getString("Description"));
                t.setLevel(rs.getByte("Level"));
                
                AllTests.add(t);
            }
            
            rs.close();
        }
        catch(Exception ex)
        {
            System.out.println();
        }

        return AllTests ;
    }
    
    public ArrayList<beans.Candidate> getAppearedCandidates(int pTestId)
    {
        ArrayList<beans.Candidate> AllCandidates = new ArrayList<Candidate>();
        
        try
        {
            PreparedStatement ps = cn.prepareStatement("select c.CandidateId, c.Name, c.Gender,convert(varchar, c.DateOfBirth,"
                    + " 103) as [DateOfBirth], c.FatherName, c.ContactNo, c.EmailId, c.TestId from Candidates as [c] where CandidateId in\n" +
"(select CandidateId from ConductedTests) and testid=?") ;
            
            ps.setInt(1, pTestId);
            ResultSet rs = ps.executeQuery() ;

            beans.Candidate c;
                        
            while(rs.next())
            {
                c = new Candidate();
                c.setCandidateId(rs.getInt("CandidateId"));
                c.setName(rs.getString("Name"));
                c.setGender(rs.getString("Gender"));
                c.setDateofBirth(rs.getString("DateOfBirth"));
                c.setFatherName(rs.getString("FatherName"));
                c.setContactNo(rs.getString("ContactNo"));
                c.setEmail(rs.getString("EmailId"));
                c.TestInfo.setTestId(rs.getInt("TestId"));

                AllCandidates.add(c);
            }
            
            rs.close();
        }
        catch(Exception ex)
        {
            System.out.println();
        }
        
        return AllCandidates ;
    }
    
    public ArrayList<beans.Candidate> getMeritList(int pTestId)
    {
        ArrayList<beans.Candidate> AllCandidates = new ArrayList<Candidate>();
        
        try
        {
            PreparedStatement ps = cn.prepareStatement("Select c.CandidateId, c.Name, c.Gender,"
                    + "convert(varchar, c.DateOfBirth, 103) as [DateOfBirth], \n"
                    + "(Select COUNT(*) From Questions as [Q], AttemptedQuestions as [AQ] Where Q.QuestionId=AQ.QuestionId "
                    + "and Q.Answer=AQ.OptionSelected \n"
                    + "and AQ.ConductedTestId=CT.ConductedTestId) as [Marks] \n"
                    + "From Candidates as [C], ConductedTests as [CT] Where C.CandidateId=CT.CandidateId \n"
                    + "and C.TestId=?\n"
                    + "order by Marks desc");

            ps.setInt(1, pTestId);

            ResultSet rs = ps.executeQuery() ;

            beans.Candidate c;
                        
            while(rs.next())
            {
                c = new Candidate();
                c.setCandidateId(rs.getInt("CandidateId"));
                c.setName(rs.getString("Name"));
                c.setGender(rs.getString("Gender"));
                c.setDateofBirth(rs.getString("DateOfBirth"));
                c.setMarks(rs.getInt("marks"));
//                c.setFatherName(rs.getString("FatherName"));
//                c.setContactNo(rs.getString("ContactNo"));
//                c.setEmail(rs.getString("EmailId"));
//                c.TestInfo.setTestId(rs.getInt("TestId"));

                AllCandidates.add(c);
            }
            
            rs.close();
        }
        catch(Exception ex)
        {
            System.out.println();
        }
        
        return AllCandidates ;
    }
    
}