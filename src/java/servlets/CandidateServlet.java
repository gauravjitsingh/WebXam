package servlets;

import beans.Candidate;
import datalayer.DALCandidate;
import datalayer.eStatus;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CandidateServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {            
        
            if(request.getParameter("btnSave")!=null)
            {
                beans.Candidate c = new Candidate() ;
                
                c.setName(request.getParameter("txtName"));
                c.setGender(request.getParameter("radGender") );
                c.setDateofBirth(request.getParameter("txtDateofBirth") );
                c.setFatherName(request.getParameter("txtFatherName"));
                c.setAddress(request.getParameter("txtAddress"));
                c.setCity(request.getParameter("txtCity"));
                c.setContactNo(request.getParameter("txtContactNo"));
                c.setEmail(request.getParameter("txtEmailId"));
                c.setEducation(request.getParameter("txtEducation"));
                c.TestInfo.setTestId(Integer.parseInt(request.getParameter("txtTestId")));
                
                datalayer.DALCandidate objDAL = new DALCandidate() ;
                
                    eStatus ret = objDAL.saveCandidate(c) ;
                    if(ret==eStatus.Suceeded)
                    {
                        response.sendRedirect("Operator/AddCandidate.jsp?flag=Suceeded") ;
                    }
                    else
                    {
                        response.sendRedirect("Operator/AddCandidate.jsp?flag=Fail") ;                        
                    }

            }
            else if(request.getParameter("DCID")!=null)
            {
                int x = Integer.parseInt(request.getParameter("DCID")) ;
                //int TestID = Integer.parseInt(request.getParameter("txtTestId")) ;
                
                datalayer.DALCandidate objDAL = new  DALCandidate();
                 objDAL.deleteCandidate(x) ;
                
                 response.sendRedirect("Operator/Candidates.jsp");
            }
            else if(request.getParameter("btnUpdate")!=null)
            {
                beans.Candidate c = new Candidate() ;
                
                c.setCandidateId(Integer.parseInt(request.getParameter("txtCandidateId")));
                c.setName(request.getParameter("txtName"));
                c.setGender(request.getParameter("radGender") );
                c.setDateofBirth(request.getParameter("txtDateofBirth") );
                c.setFatherName(request.getParameter("txtFatherName"));
                c.setAddress(request.getParameter("txtAddress"));
                c.setCity(request.getParameter("txtCity"));
                c.setContactNo(request.getParameter("txtContactNo"));
                c.setEmail(request.getParameter("txtEmailId"));
                c.setEducation(request.getParameter("txtEducation"));
//                c.TestInfo.setTestId(Integer.parseInt(request.getParameter("txtTestId")));
                
                datalayer.DALCandidate objDAL = new DALCandidate() ;
                eStatus ret = objDAL.updateCandidate(c) ;

                if(ret==eStatus.Update)
                {
                    response.sendRedirect("Operator/AddCandidate.jsp") ;
                } 
                else
                {
                    response.sendRedirect("Operator/AddCandidate.jsp?flag=Fail") ;                    
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
