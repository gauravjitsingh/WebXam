package servlets;

import beans.Test;
import datalayer.DALTest;
import datalayer.eStatus;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if(request.getParameter("btnSave")!=null)
        {
            beans.Test t = new Test() ;
            
            t.DepartmentInfo.setDepartmentId(Integer.parseInt(request.getParameter("ddlDepartment")));
            t.JobInfo.setJobId(Integer.parseInt(request.getParameter("ddlJob")));
            t.setTestDate(request.getParameter("txtTestDate"));
            t.setVacancies(Integer.parseInt(request.getParameter("txtVacancies")));
            t.setPassingMarks(Short.parseShort(request.getParameter("txtPassingMarks")));
            t.setDuration(Short.parseShort(request.getParameter("txtDuration")));
            t.setDescription(request.getParameter("txtDescription"));
            t.setLevel(Byte.parseByte(request.getParameter("radLevel")));
            
            datalayer.DALTest objDAL = new DALTest() ;
            eStatus ret = objDAL.saveTest(t) ;
            
            if(ret==eStatus.Suceeded)
            {
                response.sendRedirect("Admin/Test.jsp?flag=Suceeded");
            }
        }
        else if(request.getParameter("DTID")!=null)
        {
            datalayer.DALTest objDAL = new DALTest() ;
            objDAL.deleteTest(Integer.parseInt(request.getParameter("DTID")));
        
            response.sendRedirect("Admin/TestList.jsp");
        }
        else if(request.getParameter("btnUpdate")!=null)
        {
            beans.Test t = new Test() ;

            t.setTestId(Integer.parseInt(request.getParameter("txtTestId")));
            t.DepartmentInfo.setDepartmentId(Integer.parseInt(request.getParameter("ddlDepartment")));
            t.JobInfo.setJobId(Integer.parseInt(request.getParameter("ddlJob")));
            t.setTestDate(request.getParameter("txtTestDate"));
            t.setVacancies(Integer.parseInt(request.getParameter("txtVacancies")));
            t.setPassingMarks(Short.parseShort(request.getParameter("txtPassingMarks")));
            t.setDuration(Short.parseShort(request.getParameter("txtDuration")));
            t.setDescription(request.getParameter("txtDescription"));
            t.setLevel(Byte.parseByte(request.getParameter("radLevel")));

            datalayer.DALTest objDAL = new DALTest() ;
            eStatus ret = objDAL.updateTest(t) ;
            
            if (ret == eStatus.Update) 
            {
                response.sendRedirect("Admin/Test.jsp");
            } 
            else 
            {
                response.sendRedirect("Admin/Test.jsp?flag=Fail");
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
