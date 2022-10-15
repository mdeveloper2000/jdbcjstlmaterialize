package mysoftwaresdatabasejstl.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mysoftwaresdatabasejstl.dao.SoftwareDAO;
import mysoftwaresdatabasejstl.pojos.Software;
import mysoftwaresdatabasejstl.util.Data;

@WebServlet(name = "SoftwareController", urlPatterns = {"/SoftwareController"})
public class SoftwareController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        String query = request.getParameter("query");
        if(query == null) {
            query = "list";
        }
        
        if(query.equals("new")) {
            RequestDispatcher rd = request.getRequestDispatcher("new.jsp");
            rd.forward(request, response);
        }
        else if(query.equals("save")) {
            Software software = new Software();
            software.setNome(request.getParameter("nome"));
            software.setVersao(request.getParameter("versao"));
            software.setData_lancamento(Data.stringToDate(request.getParameter("data_lancamento")));
            SoftwareDAO dao = new SoftwareDAO();
            int i = dao.save(software);
            if(i == 1) {                
                RequestDispatcher rd = request.getRequestDispatcher("SoftwareController?query=list");
                rd.forward(request, response);
            }
        }
        else if(query.equals("edit")) {
            Software software = new Software();
            int id = Integer.valueOf(request.getParameter("id"));
            String nome = request.getParameter("nome");
            String versao = request.getParameter("versao");
            Date data_lancamento = Data.stringToDate(request.getParameter("data_lancamento"));
            software.setId(id);
            software.setNome(nome);
            software.setVersao(versao);
            software.setData_lancamento(data_lancamento);
            SoftwareDAO dao = new SoftwareDAO();
            int i = dao.edit(software);
            if(i == 1) {                
                RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                rd.forward(request, response);
            }
        }
        else if(query.equals("read")) {
            int id = Integer.valueOf(request.getParameter("id"));
            SoftwareDAO dao = new SoftwareDAO();
            Software software = dao.read(id);
            RequestDispatcher rd = request.getRequestDispatcher("edit.jsp");
            request.setAttribute("software", software);
            rd.forward(request, response);
        }
        else if(query.equals("list")) {
            SoftwareDAO dao = new SoftwareDAO();
            List<Software> softwares = new ArrayList<>();
            softwares = dao.list();            
            RequestDispatcher rd = request.getRequestDispatcher("list.jsp");
            request.setAttribute("softwares", softwares);
            rd.forward(request, response);
        }
        else if(query.equals("delete")) {
            int id = Integer.valueOf(request.getParameter("id"));
            SoftwareDAO dao = new SoftwareDAO();
            dao.delete(id);
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);
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
