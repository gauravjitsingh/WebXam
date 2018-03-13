package datalayer;

import beans.Candidate;
import beans.Test;
import java.sql.*;
import java.util.ArrayList;

public class DALCandidate {

    private Connection cn;
    
    public DALCandidate() {
        cn = DBOperations.getConnection();
    }
    
    public eStatus saveCandidate(beans.Candidate candidate) 
    {

        eStatus ret = null;

        try 
        {

            PreparedStatement ps = cn.prepareStatement("insert into Candidates values(?,?,?,?,?,?,?,?,?,?)");

            ps.setString(1, candidate.getName());
            ps.setString(2, candidate.getGender());
            ps.setString(3, candidate.getDateofBirth());
            ps.setString(4, candidate.getFatherName());
            ps.setString(5, candidate.getAddress());
            ps.setString(6, candidate.getCity());
            ps.setString(7, candidate.getContactNo());
            ps.setString(8, candidate.getEmail());
            ps.setString(9, candidate.getEducation());
            ps.setInt(10, candidate.TestInfo.getTestId());

            ps.executeUpdate();
            ret = eStatus.Suceeded;
        } 
        catch (Exception ex) 
        {
            ret = eStatus.Fail;
        }

        return ret;
    }

    public eStatus deleteCandidate(int pCandidateId) 
    {
        eStatus ret = null;

        try 
        {
            PreparedStatement ps = cn.prepareStatement("Delete from Candidates where CandidateId=?");

            ps.setInt(1, pCandidateId);

            ps.executeUpdate();

            ret = eStatus.Delete;
        } 
        catch (Exception ex) 
        {
            ret = eStatus.Fail;
        }

        return ret;
    }
    
    public eStatus updateCandidate(beans.Candidate candidate) 
    {
        eStatus ret = null;

        try 
        {
            PreparedStatement ps = cn.prepareStatement("update candidates set Name=?,Gender=?,DateOfBirth=?,FatherName=?,Address=?,City=?, ContactNo=?, EmailId=?, Education=? where CandidateId=?");
    
            ps.setString(1, candidate.getName());
            ps.setString(2, candidate.getGender());
            ps.setString(3, candidate.getDateofBirth());
            ps.setString(4, candidate.getFatherName());
            ps.setString(5, candidate.getAddress());
            ps.setString(6, candidate.getCity());
            ps.setString(7, candidate.getContactNo());
            ps.setString(8, candidate.getEmail());
            ps.setString(9, candidate.getEducation());
            ps.setInt(10, candidate.getCandidateId());

            ps.executeUpdate();
            ret = eStatus.Update;
        } 
        catch (Exception ex) 
        {
            ret = eStatus.Fail;
        }

        return ret;
    }
        
    public ArrayList<beans.Candidate> getCandidates(int pTestId)
    {
        ArrayList<beans.Candidate> AllCandidates = new ArrayList<Candidate>();
        
        try
        {
            PreparedStatement ps = cn.prepareStatement("select c.CandidateId, c.Name, c.Gender, "
                    + "convert(varchar, c.DateOfBirth, 103) as [DateOfBirth], c.FatherName, c.Address, c.City, c.ContactNo, c.EmailId, c.Education, c.TestId "
                    + "from Candidates as [c] where TestId=?") ;
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
                c.setAddress(rs.getString("Address"));
                c.setCity(rs.getString("City"));
                c.setContactNo(rs.getString("ContactNo"));
                c.setEmail(rs.getString("EmailId"));
                c.setEducation(rs.getString("Education"));
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
    
    public ArrayList<beans.Candidate> getCandidates() 
    {
        ArrayList<beans.Candidate> AllCandidates = new ArrayList<Candidate>();

        try 
        {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("Select * from candidates");

            beans.Candidate c;

            while (rs.next()) 
            {
                c = new Candidate();
                c.setCandidateId(rs.getInt("CandidateId"));
                c.setName(rs.getString("Name"));
                c.setGender(rs.getString("Gender"));
                c.setDateofBirth(rs.getString("DateOfBirth"));
                c.setFatherName(rs.getString("FatherName"));
                c.setAddress(rs.getString("Address"));
                c.setCity(rs.getString("City"));
                c.setContactNo(rs.getString("ContactNo"));
                c.setEmail(rs.getString("EmailId"));
                c.setEducation(rs.getString("Education"));
                c.TestInfo.setTestId(rs.getInt("TestId"));

                AllCandidates.add(c);
            }

            rs.close();
        } 
        catch (Exception ex) 
        {
            System.out.println(ex);
        }

        return AllCandidates;
    }

    public beans.Candidate verifyCandidate(int pCandidateId)
    {
        beans.Candidate c = new Candidate();

        try 
        {
            PreparedStatement ps = cn.prepareStatement("select c.CandidateId, c.Name, c.Gender,"
                    + " convert(varchar, c.DateOfBirth, 103) as [DateOfBirth], c.FatherName, c.Address, c.City, c.ContactNo, "
                    + "c.EmailId, c.Education, c.TestId from candidates as [c] where candidateid=?");
            ps.setInt(1, pCandidateId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) 
            {
                c.setCandidateId(rs.getInt("CandidateId"));
                c.setName(rs.getString("Name"));
                c.setGender(rs.getString("Gender"));
                c.setDateofBirth(rs.getString("DateOfBirth"));
                c.setFatherName(rs.getString("FatherName"));
                c.setAddress(rs.getString("Address"));
                c.setCity(rs.getString("City"));
                c.setContactNo(rs.getString("ContactNo"));
                c.setEmail(rs.getString("EmailId"));
                c.setEducation(rs.getString("Education"));
                c.TestInfo.setTestId(rs.getInt("TestId"));
            }

            rs.close();
        } 
        catch (Exception ex) 
        {
            System.out.println(ex);
        }

        return c;
        
    }
    
    public beans.Candidate getCandidate(int pCandidateId)
    {
        beans.Candidate c = new Candidate();

        try 
        {
            PreparedStatement ps = cn.prepareStatement("select c.CandidateId, c.Name, c.Gender, "
                    + "convert(varchar, c.DateOfBirth, 103) as [DateOfBirth], c.FatherName, c.Address, c.City, c.ContactNo,"
                    + " c.EmailId, c.Education, c.TestId, t.PassingMarks, t.Duration from candidates as [c], tests as [t] "
                    + "where c.TestId=t.TestId and candidateid=?");
            ps.setInt(1, pCandidateId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) 
            {
                c.setCandidateId(rs.getInt("CandidateId"));
                c.setName(rs.getString("Name"));
                c.setGender(rs.getString("Gender"));
                c.setDateofBirth(rs.getString("DateOfBirth"));
                c.setFatherName(rs.getString("FatherName"));
                c.setAddress(rs.getString("Address"));
                c.setCity(rs.getString("City"));
                c.setContactNo(rs.getString("ContactNo"));
                c.setEmail(rs.getString("EmailId"));
                c.setEducation(rs.getString("Education"));
                c.TestInfo.setTestId(rs.getInt("TestId"));
                c.TestInfo.setPassingMarks(rs.getShort("PassingMarks"));
                c.TestInfo.setDuration(rs.getShort("Duration"));
            }

            rs.close();
        } 
        catch (Exception ex) 
        {
            System.out.println(ex);
        }

        return c;
        
    }
    
}
