package servlets;

import beans.Question;
import beans.Test;
import datalayer.DALQuestion;
import datalayer.DALTest;
import datalayer.eStatus;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QuestionServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if(request.getParameter("btnSave")!=null)
        {
            beans.Question q = new Question();
            
            q.CategoryInfo.setCategoryId(Integer.parseInt(request.getParameter("ddlCategory")));            
            q.setQuestionText(request.getParameter("txtQuestion"));
            q.setOption1(request.getParameter("txtOption1"));
            q.setOption2(request.getParameter("txtOption2"));
            q.setOption3(request.getParameter("txtOption3"));
            q.setOption4(request.getParameter("txtOption4"));
            q.setAnswer(Byte.parseByte(request.getParameter("ddlAnswer")));
            q.setLevel(Byte.parseByte(request.getParameter("radLevel")));
            
            datalayer.DALQuestion objDAL = new DALQuestion() ;
            eStatus ret = objDAL.saveQuestion(q) ;
            
            if(ret==eStatus.Suceeded)
            {
                response.sendRedirect("Operator/Question.jsp?flag=Suceeded");
            }
        }
        else if(request.getParameter("DQID")!=null)
        {
            datalayer.DALQuestion objDAL = new DALQuestion();
            objDAL.deleteQuestion(Integer.parseInt(request.getParameter("DQID")));
        
            response.sendRedirect("Operator/Questions.jsp");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
