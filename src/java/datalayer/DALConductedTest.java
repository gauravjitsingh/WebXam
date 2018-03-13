package datalayer;

import beans.ConductedTest;
import beans.Question;
import java.sql.*;
import java.util.ArrayList;

public class DALConductedTest {

    private Connection cn;

    public DALConductedTest() {
        cn = DBOperations.getConnection();
    }

    public int startTest(int CandidateId, int TimeRemaining, ArrayList<beans.Question> AllQuestions) {
        int ConductedTestId = 0;
        try {
            CallableStatement cs = cn.prepareCall("{call Add_ConductedTestQuestions(?,?,?)}");

            cs.registerOutParameter(1, java.sql.Types.INTEGER);
            cs.setInt(2, CandidateId);
            cs.setInt(3, TimeRemaining);

            cs.executeUpdate();

            ConductedTestId = cs.getInt(1);

            PreparedStatement ps = cn.prepareStatement("insert into AttemptedQuestions values(?,?,?)");

            for (beans.Question q : AllQuestions) {
                ps.setInt(1, q.getQuestionId());
                ps.setInt(2, 0);
                ps.setInt(3, ConductedTestId);

                ps.executeUpdate();
            }
        } catch (Exception ex) {
            System.out.println();
        }

        return ConductedTestId;
    }

    public boolean isStarted(int pCandidateId) {
        boolean ret = false;

        try {
            PreparedStatement ps = cn.prepareStatement("Select * from ConductedTests Where CandidateId=?");
            ps.setInt(1, pCandidateId);

            ResultSet rs = ps.executeQuery();
            ret = rs.next();

            rs.close();

        } catch (Exception ex) {
            System.out.println();
        }

        return ret;
    }

    public int getConductedTestId(int pCandidateId) {
        int CTID = 0;

        try 
        {
            PreparedStatement ps = cn.prepareStatement("Select * From ConductedTests Where CandidateId=?");
            ps.setInt(1, pCandidateId);

            ResultSet rs = ps.executeQuery();

            rs.next();
            CTID = rs.getInt("ConductedTestId");
            rs.close();
        } 
        catch (Exception ex) 
        {
            System.out.println(ex);
        }

        return CTID;
    }

    public ArrayList<beans.Question> getSavedQuestions(int pConductedTestId) {

        ArrayList<beans.Question> SavedQuestion = new ArrayList<Question>();

        try {
            PreparedStatement ps = cn.prepareStatement("Select AQ.AttemptedQuestionId, AQ.OptionSelected,  Q.QuestionId,"
                    + " Q.QuestionText,Q.Option1,Q.Option2,Q.Option3,Q.Option4, Q.Answer\n"
                    + "From AttemptedQuestions as [AQ], Questions as [Q] Where AQ.QuestionId=Q.QuestionId and AQ.ConductedTestId=?");

            ps.setInt(1, pConductedTestId);
            ResultSet rs = ps.executeQuery();

            beans.Question q;

            while (rs.next()) {
                q = new Question();

                q.setAttemptedQuestionId(rs.getInt("AttemptedQuestionId"));
                q.setOptionSelected(rs.getByte("OptionSelected"));
                q.setQuestionId(rs.getInt("QuestionId"));
                q.setQuestionText(rs.getString("QuestionText"));
                q.setOption1(rs.getString("Option1"));
                q.setOption2(rs.getString("Option2"));
                q.setOption3(rs.getString("Option3"));
                q.setOption4(rs.getString("Option4"));
                q.setAnswer(rs.getByte("Answer"));
                SavedQuestion.add(q);
            }
        } catch (Exception ex) {
            System.out.println();
        }
        return SavedQuestion;
    }

    public eStatus attemptQuestion(int pOptionSelected, int pAttemptedQuestionId) {
        eStatus ret = null;

        try {
            PreparedStatement ps = cn.prepareStatement("update AttemptedQuestions set OptionSelected=? where "
                    + " AttemptedQuestionId=?");

            ps.setInt(1, pOptionSelected);
            ps.setInt(2, pAttemptedQuestionId);

            ps.executeUpdate();
            ret = eStatus.Update;
        } catch (Exception ex) {
            ret = eStatus.Fail;
        }

        return ret;
    }

    public eStatus saveTime(int pTimeRemaining, int pConductedTestId) {
        eStatus ret = null;

        try {
            PreparedStatement ps = cn.prepareStatement("update ConductedTests set TimeRemaining=? where "
                    + " ConductedTestId=?");

            ps.setInt(1, pTimeRemaining);
            ps.setInt(2, pConductedTestId);

            ps.executeUpdate();
            ret = eStatus.Update;
        } catch (Exception ex) {
            ret = eStatus.Fail;
        }

        return ret;
    }

    public void submitTest(int pConductedTestId) {
        try {

            PreparedStatement ps = cn.prepareStatement("update ConductedTests set Submitted=1 where ConductedTestId=?");

            ps.setInt(1, pConductedTestId);

            ps.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public boolean isSubmitted(int pCandidateId) {
        boolean ret = false;

        try 
        {
            PreparedStatement ps = cn.prepareStatement("select submitted from ConductedTests where CandidateId=?");
            ps.setInt(1, pCandidateId);

            ResultSet rs = ps.executeQuery();
//            ret = rs.next() ;

            int submitted = 0 ;

            while (rs.next()) 
            {
                submitted = rs.getInt("Submitted");
            }

            if(submitted==1)
            {
                ret = true ;
            }

            rs.close();

        } 
        catch (Exception ex) 
        {
            System.out.println();
        }

        return ret;
    }

    public int getTimeRemaining(int pConductedTestId)
    {            
        int TimeRemaining = 0 ;

        try
        {
            PreparedStatement ps = cn.prepareStatement("Select TimeRemaining from ConductedTests where ConductedTestId=?");
            ps.setInt(1, pConductedTestId);

            ResultSet rs = ps.executeQuery();

            rs.next();
            TimeRemaining = rs.getShort("TimeRemaining");
            rs.close();
            
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
        return TimeRemaining;
    }
}
