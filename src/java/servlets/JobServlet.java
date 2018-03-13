package servlets;

import beans.Job;
import datalayer.DALJob;
import datalayer.eStatus;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JobServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if(request.getParameter("btnSave")!=null)
        {    
            beans.Job j= new Job();
            
            j.setName(request.getParameter("txtName"))  ;
            j.setDescription(request.getParameter("txtDescription"));
            
            datalayer.DALJob objDAL = new DALJob() ;
            eStatus ret = objDAL.saveJob(j) ;
            
            if(ret==eStatus.Update)
            {
                response.sendRedirect("Admin/Job.jsp?flag=Update");
            }
            else if(ret==eStatus.Suceeded)
            {
                response.sendRedirect("Admin/Job.jsp?flag=Suceeded");
            }
            else if(ret==eStatus.Fail)
            {
                response.sendRedirect("Admin/Job.jsp?flag=Fail");
            }
        }
        else if(request.getParameter("DJID")!=null)
        {
            DALJob objDAL = new DALJob() ;
            objDAL.deleteJob(Integer.parseInt(request.getParameter("DJID"))) ;
            
            response.sendRedirect("Admin/Jobs.jsp");
        }
        else if(request.getParameter("btnUpdate")!=null)
        {
            beans.Job j = new Job() ;
            
            j.setJobId(Integer.parseInt(request.getParameter("txtJobId") ));
            j.setName(request.getParameter("txtName"));
            j.setDescription(request.getParameter("txtDescription"));
            datalayer.DALJob objDAL = new DALJob() ;
            eStatus ret = objDAL.updateJob(j) ;
            
            if(ret==eStatus.Update)
            {
                response.sendRedirect("Admin/Jobs.jsp");
            }
            else
            {
                response.sendRedirect("Admin/Job.jsp?flag=Fail");
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
