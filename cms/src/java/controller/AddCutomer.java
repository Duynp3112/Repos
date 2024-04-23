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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import model.Customer;

/**
 *
 * @author ADMIN
 */
public class AddCutomer extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddProject</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddProject at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        CustomerDao p = new CustomerDao();
        ArrayList<String> list = p.getAllGender();
        request.setAttribute("gender", list);
        request.getRequestDispatcher("addcustomer.jsp").forward(request, response);
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
        try {
            String userID = request.getParameter("uid");
            String fullName = request.getParameter("fname");
            String email = request.getParameter("email");
            String address = request.getParameter("address");
            String gender = request.getParameter("gender");
            String dobStr = request.getParameter("dob");
            String phone = request.getParameter("phone");
            SimpleDateFormat inputDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat dbDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date dob = inputDateFormat.parse(dobStr);
            String dbDate = dbDateFormat.format(dob);
            Customer customer = new Customer(userID, fullName, email, address, gender, dbDate, phone);
            CustomerDao customerDao = new CustomerDao();
            customerDao.addCustomerWithRole(customer, "Customer");
            request.getRequestDispatcher("customer.jsp").forward(request, response);
        } catch (ParseException ex) {
            ex.getMessage();
        }
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
