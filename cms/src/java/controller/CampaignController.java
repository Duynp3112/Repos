/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dao.CustomerDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.ChienDich;


/**
 *
 * @author ADMIN
 */
public class CampaignController extends HttpServlet {

   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CampaignController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CampaignController at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        CustomerDao cdao = new CustomerDao();
        ArrayList<ChienDich> list = cdao.getListChienDich();
        request.setAttribute("listCp", list);
        request.getRequestDispatcher("chiendich.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String chiendichID = request.getParameter("chiendichID");
        String tenChiendich = request.getParameter("tenChiendich");
        String motaChitiet = request.getParameter("motaChitiet");
        String loaiChiendich = request.getParameter("loaiChiendich");
        String mucTieu = request.getParameter("mucTieu");
        double chiPhi = Double.parseDouble(request.getParameter("chiPhi"));
        String ngayBatDau = request.getParameter("ngayBatDau");
        String ngayKetThuc = request.getParameter("ngayKetThuc");
        String nhanVienFullName = request.getParameter("nhanVienFullName");
        CustomerDao customerDao = new CustomerDao();
        String nhanVienID = customerDao.getNhanVienIDByFullName(nhanVienFullName);
        if (nhanVienID != null) {
            ChienDich chiendich = new ChienDich(chiendichID, tenChiendich, motaChitiet, loaiChiendich, mucTieu, chiPhi, ngayBatDau, ngayKetThuc, nhanVienID);
            
            customerDao.addChienDich(chiendich);
            request.getRequestDispatcher("chiendich.jsp").forward(request, response);
        } else {
            System.out.println("Không tìm thấy ID của Nhân viên.");
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
