package datalayer;

import beans.Question;
import beans.TestCategory;
import java.sql.*;
import java.util.ArrayList;

public class DALQuestion {

    private Connection cn ;

    public DALQuestion() {
        cn = DBOperations.getConnection() ;        
    }
    
    public eStatus saveQuestion(beans.Question question)
    {
        eStatus ret = null ;

        try
        {
            PreparedStatement ps = cn.prepareStatement("insert into questions values(?,?,?,?,?,?,?,?)") ;
            
            ps.setString(1, question.getQuestionText());
            ps.setString(2, question.getOption1());
            ps.setString(3, question.getOption2());
            ps.setString(4, question.getOption3());
            ps.setString(5, question.getOption4());
            ps.setByte(6, question.getAnswer());
            ps.setByte(7, question.getLevel());
            ps.setInt(8, question.CategoryInfo.getCategoryId());
        
            ps.executeUpdate();
            ret = eStatus.Suceeded ;
        }
        catch(Exception ex)
        {
            ret = eStatus.Fail ;
        }
        
        return ret ;
    }
    
    public eStatus deleteQuestion(int pQuestionId)
    {
        eStatus ret = null ;

        try
        {
            PreparedStatement ps = cn.prepareStatement("Delete from Questions where QuestionId=?") ;
            
            ps.setInt(1, pQuestionId);
            
            ps.executeUpdate();
            
            ret = eStatus.Delete ;
        }
        catch(Exception ex)
        {
            ret = eStatus.Fail ;
        }
        
        return ret ;
    }
    
    public eStatus updateQuestion(beans.Question question)
    {
        eStatus ret = null ;

        try
        {
            PreparedStatement ps = cn.prepareStatement("update Questions set QuestionText=?,Option1=?,Option2=?,Option3=?,Option4=?,Answer=?,Level=?,CategoryId=? where QuestionId=?") ;

            ps.setString(1, question.getQuestionText());
            ps.setString(2, question.getOption1());
            ps.setString(3, question.getOption2());
            ps.setString(4, question.getOption3());
            ps.setString(5, question.getOption4());
            ps.setByte(6, question.getAnswer());
            ps.setByte(7, question.getLevel());
            ps.setInt(8, question.CategoryInfo.getCategoryId());
            ps.setInt(9, question.getQuestionId());
            
            ps.executeUpdate();
            ret = eStatus.Update ;
        }
        catch(Exception ex)
        {
            ret = eStatus.Fail ;
        }
        
        return ret ;
    }
    
    public beans.Question getQuestion(int pQuestionId)
    {
        beans.Question q = new Question() ;

        try
        {
            PreparedStatement ps = cn.prepareStatement("select q.QuestionId, q.QuestionText, q.Option1, q.Option2, q.Option3, q.Option4, q.Answer,"
                    + " q.Level, c.CategoryName, c.CategoryId from Questions as [q], Categories as [c] where q.CategoryId=c.CategoryId "
                    + "and QuestionId=?") ;
            ps.setInt(1, pQuestionId);
            
            ResultSet rs = ps.executeQuery() ;
            
            while(rs.next())
            {
                q.setQuestionId(rs.getInt("QuestionId"));
                q.setQuestionText(rs.getString("QuestionText"));
                q.setOption1(rs.getString("Option1"));
                q.setOption2(rs.getString("Option2"));
                q.setOption3(rs.getString("Option3"));
                q.setOption4(rs.getString("Option4"));
                q.setAnswer(rs.getByte("Answer"));
                q.setLevel(rs.getByte("Level"));
                q.CategoryInfo.setName(rs.getString("CategoryName"));
                q.CategoryInfo.setCategoryId(rs.getInt("CategoryId"));
            }
            
            rs.close();
        }
        catch(Exception ex)
        {
            System.out.println();
        }
        
        return q ;
    }
    
    public ArrayList<beans.Question> getQuestion(byte pLevel,int pCategoryId)
    {
        ArrayList<beans.Question> AllQuestions = new ArrayList<Question>() ;
        
        try
        {
            PreparedStatement ps = cn.prepareStatement("select * from Questions where Level=? and CategoryId=?") ;
            ps.setByte(1, pLevel);
            ps.setInt(2, pCategoryId);
            ResultSet rs = ps.executeQuery() ;
            
            beans.Question q ;
            
            while(rs.next())
            {
                q = new Question() ;
                
                q.setQuestionId(rs.getInt("QuestionId"));
                q.setQuestionText(rs.getString("QuestionText"));
                q.setOption1(rs.getString("Option1"));
                q.setOption2(rs.getString("Option2"));
                q.setOption3(rs.getString("Option3"));
                q.setOption4(rs.getString("Option4"));
                q.setAnswer(rs.getByte("Answer"));
                q.setLevel(rs.getByte("Level"));
                q.CategoryInfo.setCategoryId(rs.getInt("CategoryId"));
                
                AllQuestions.add(q);
            }
            
            rs.close();
        }
        catch(Exception ex)
        {
            System.out.println();
        }

        return AllQuestions ;
    }
    
    public ArrayList<beans.TestCategory> gettestcategory(int pTestId)
    {
        ArrayList<beans.TestCategory> AllTestCategory = new ArrayList<TestCategory>() ;
        
        try
        {
            PreparedStatement ps = cn.prepareStatement("select tc.TestCategoryId as [tcid], tc.TestId, tc.CategoryId, tc.NumberofQuestions,"
                    + " t.Level from TestCategories as [tc], Tests as [t] where tc.TestId=t.TestId and t.TestId=?") ;
            ps.setInt(1, pTestId);
            ResultSet rs = ps.executeQuery() ;
        
            beans.TestCategory tc ;
            
            while(rs.next())
            {
                tc = new TestCategory() ;
                
                tc.setTestCategoryId(rs.getInt("tcid"));
                tc.TestInfo.setTestId(rs.getInt("TestId"));
                tc.CategoryInfo.setCategoryId(rs.getInt("CategoryId"));
                tc.setNoofQuestions(rs.getInt("NumberofQuestions"));             
                tc.TestInfo.setLevel(rs.getByte("Level"));
                
                AllTestCategory.add(tc) ;
            }
            
            rs.close();
        }
        catch(Exception ex)
        {
            System.out.println();
        }
        
        return AllTestCategory;
    }
    
//    public int getcategorycount(int pTestId)
//    {
//        int count = 0 ;
//        try
//        {
//            PreparedStatement ps = cn.prepareStatement("select COUNT(CategoryId) as [Count] from TestCategories where TestId=?");
//            ps.setInt(1, pTestId);
//            
//            ResultSet rs = ps.executeQuery() ;
//            
//            count = rs.getInt("Count") ;
//        }
//        catch(Exception ex)
//        {
//            System.out.println();
//        }
//        return count ;
//    }
//    
    public ArrayList<beans.Question> getTestQuestions(int NoOfQuestions, int pCategoryId, byte pLevel)
    {
        ArrayList<beans.Question> AllQuestions = new ArrayList<Question>() ;
        
        try
        {
                PreparedStatement ps
                        = cn.prepareStatement("select top (?) * from questions where categoryid = ? and level=?");

                ps.setInt(1, NoOfQuestions);
                ps.setInt(2, pCategoryId);
                ps.setInt(3, pLevel);
                ResultSet rs = ps.executeQuery();

                beans.Question q;

                while (rs.next()) {
                    q = new Question();

                    q.setQuestionId(rs.getInt("QuestionId"));
                    q.setQuestionText(rs.getString("QuestionText"));
                    q.setOption1(rs.getString("Option1"));
                    q.setOption2(rs.getString("Option2"));
                    q.setOption3(rs.getString("Option3"));
                    q.setOption4(rs.getString("Option4"));
                    q.setAnswer(rs.getByte("Answer"));
                    q.setLevel(rs.getByte("Level"));
                    q.CategoryInfo.setCategoryId(rs.getInt("CategoryId"));

                    AllQuestions.add(q);
                }

                rs.close();
            
        }
        catch(Exception ex)
        {
            System.out.println();
        }

        return AllQuestions ;        
    }

    
    
}
