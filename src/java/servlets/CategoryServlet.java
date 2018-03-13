package servlets;

import beans.Category;
import datalayer.DALCategory;
import datalayer.eStatus;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CategoryServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("btnSave") != null) 
        {
            beans.Category c = new Category();
            c.setName(request.getParameter("txtName"));
            c.setDescription(request.getParameter("txtDescription"));

            datalayer.DALCategory objDAL = new DALCategory();
            eStatus ret = objDAL.exsists(c.getName());

            if (ret == eStatus.Suceeded) 
            {
                ret = objDAL.saveCategory(c);
                response.sendRedirect("Admin/Category.jsp?flag=Suceeded");
            } 
            else if (ret == eStatus.Duplicate) 
            {
                response.sendRedirect("Admin/Category.jsp?flag=Duplicate");
            } 
            else 
            {
                response.sendRedirect("Admin/Category.jsp?flag=Fail");
            }

        } 
        else if (request.getParameter("DCID") != null) 
        {
            DALCategory objDAL = new DALCategory();
            objDAL.deleteCategory(Integer.parseInt(request.getParameter("DCID")));

            response.sendRedirect("Admin/Categories.jsp");
        } 
        else if (request.getParameter("UCID") != null) 
        {
            
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
