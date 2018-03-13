package servlets;

import beans.Department;
import datalayer.DALDepartment;
import datalayer.eStatus;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DepartmentServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            if(request.getParameter("btnSave")!=null)
            {
                beans.Department d = new Department() ;
                
                d.setName(request.getParameter("txtName"));
                d.setAddress(request.getParameter("txtAddress") );
                d.setCity(request.getParameter("txtCity") );
                d.setContactNo(request.getParameter("txtContactNo"));
                d.setEmailId(request.getParameter("txtEmail"));
                d.setContactPerson(request.getParameter("txtContactPerson"));
                
                datalayer.DALDepartment objDAL = new DALDepartment() ;
                datalayer.eStatus ret = objDAL.exsists(d.getName()) ;
                
                if(ret==eStatus.Duplicate)
                {
                    response.sendRedirect("Admin/Department.jsp?flag=Duplicate") ;
                }
                else if(ret==eStatus.Suceeded)
                {
                    ret = objDAL.saveDepartment(d) ;
                    response.sendRedirect("Admin/Department.jsp?flag=Suceeded") ;
                } 
                else if(ret==eStatus.Fail)
                {
                    response.sendRedirect("Admin/Department.jsp?flag=Fail") ;                    
                }

            }
            else if(request.getParameter("DDID")!=null)
            {
                int x = Integer.parseInt(request.getParameter("DDID")) ;
                
                datalayer.DALDepartment objDAL = new DALDepartment() ;
                 objDAL.deleteDepartment(x) ;
                
                 response.sendRedirect("Admin/Departments.jsp");
            }
            else if(request.getParameter("btnUpdate")!=null)
            {
                beans.Department d = new Department() ;
                String id = request.getParameter("txtDepartmentId") ;
                d.setDepartmentId(Integer.parseInt(id));
                d.setName(request.getParameter("txtName"));
                d.setAddress(request.getParameter("txtAddress"));
                d.setCity(request.getParameter("txtCity"));
                d.setContactNo(request.getParameter("txtContactNo"));
                d.setEmailId(request.getParameter("txtEmailId"));
                d.setContactPerson(request.getParameter("txtContactPerson"));
                
                datalayer.DALDepartment objDAL = new DALDepartment() ;
                eStatus ret = objDAL.updateDepartment(d) ;

                if(ret==eStatus.Update)
                {
                    response.sendRedirect("Admin/Departments.jsp") ;
                } 
                else
                {
                    response.sendRedirect("Admin/Department.jsp?flag=Fail") ;                    
                }
                
            }
/*            else if(request.getParameter("UDID")!=null)
            {
                int x = Integer.parseInt(request.getParameter("UDID")) ;
                
                datalayer.DALDepartment objDAL = new DALDepartment() ;
                beans.Department dep = objDAL.updateDepartment(x) ;
                
                RequestDispatcher dispatcher = request.getRequestDispatcher("Admin/Department.jsp") ;
                
                request.setAttribute("update", dep);
                
                dispatcher.forward(request, response);
            } */
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
