package servlets;

import datalayer.DALConductedTest;
import datalayer.eStatus;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConductedTestServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            if(request.getParameter("OptionSelected")!=null)
            {
                int optionselected = Integer.parseInt(request.getParameter("OptionSelected")) ;
                int AttemptedQuestionId = Integer.parseInt(request.getParameter("AttemptedQuestionId")) ;
                
                eStatus ret = null ;
                
                datalayer.DALConductedTest objDAL = new DALConductedTest() ;
                
                ret = objDAL.attemptQuestion(optionselected, AttemptedQuestionId) ;
                PrintWriter writer = response.getWriter();
                
                if(ret==eStatus.Update)
                    writer.write(AttemptedQuestionId);
                else
                   writer.write("0");
                
            }
            
            if(request.getParameter("TimeRemaining")!=null)
            {
                int Time = Integer.parseInt(request.getParameter("TimeRemaining"));
                int CTID = Integer.parseInt(request.getParameter("ConductedTestId"));
                
                datalayer.DALConductedTest objDAL = new DALConductedTest();
                objDAL.saveTime(Time, CTID);
            }
            
            if(request.getParameter("btnSubmit")!=null)
            {
                int ctid = Integer.parseInt(request.getParameter("ConductedTestId"));
                datalayer.DALConductedTest objDAL = new DALConductedTest();
                objDAL.submitTest(ctid);
                response.sendRedirect("Candidate/Thankyou.jsp?CTID="+ctid);
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
