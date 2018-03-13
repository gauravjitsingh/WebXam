package servlets;

import beans.TestCategory;
import datalayer.DALTestCategory;
import datalayer.eStatus;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestCategoryServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if(request.getParameter("btnSave")!=null)
        {
            beans.TestCategory tc = new TestCategory() ;
            int TestId = Integer.parseInt(request.getParameter("TID")) ;
            tc.TestInfo.setTestId(TestId);
            tc.CategoryInfo.setCategoryId(Integer.parseInt(request.getParameter("ddlCategories")));
            tc.setNoofQuestions(Integer.parseInt(request.getParameter("txtNoOfQuestions")));

            datalayer.DALTestCategory objDAL = new DALTestCategory() ;
            eStatus ret = objDAL.saveTestCategory(tc) ;
            
            if(ret==eStatus.Suceeded)
            {
                response.sendRedirect("Admin/TestCategory.jsp?TID="+TestId);
            }
        }
        else if(request.getParameter("DTCID")!=null)
        {
            datalayer.DALTestCategory objDAL = new DALTestCategory() ;
            eStatus ret = objDAL.deleteTestCategory(Integer.parseInt(request.getParameter("DTCID")));
            int TestId = Integer.parseInt(request.getParameter("TID")) ;
            
            if(ret==eStatus.Delete)
            {
                response.sendRedirect("Admin/TestCategory.jsp?TID="+TestId);
            }            
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
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
