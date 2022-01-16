package com.servlets;

import com.dao.CompanyDAO;
import com.dao.CompanyDAOIml;
import com.models.Company;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet()
public class CompanyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String id = request.getParameter("id");
        String delete = request.getParameter("delete");
        CompanyDAO dao = new CompanyDAOIml();

        if (id != null) {
            Company bean = dao.findById(id);
            request.setAttribute("bean", bean);
            List<Company> beans = dao.findAll();
            request.setAttribute("beans", beans);
        } else if (delete != null) {
            boolean res = dao.deleteCompany(Integer.parseInt(delete));
            if (res == false) {
                Company bean = dao.findById(delete);
                request.setAttribute("bean", bean);
                List<Company> beans = dao.findAll();
                request.setAttribute("beans", beans);
                request.setAttribute("errorMessage", "Компания не была удалена");
            } else {
                List<Company> beans = dao.findAll();
                request.setAttribute("beans", beans);
            }
        } else {
            List<Company> beans = dao.findAll();
            request.setAttribute("beans", beans);
        }

        RequestDispatcher rd = request.getRequestDispatcher("company.jsp");
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String addr = request.getParameter("address"); 
        CompanyDAO dao = new CompanyDAOIml();
        Company company = new Company();
        String id = request.getParameter("id");
        if (id != null && !id.isEmpty()) {
            company.setId(Integer.parseInt(id));
        }
        company.setName(request.getParameter("name"));
        company.setAddress(request.getParameter("address"));
        company.setInn(request.getParameter("inn"));
        company.setPhone(request.getParameter("phone"));
        long newId;
        if (id == null || id.isEmpty()) {
            newId = dao.insertCompany(company);
            company.setId(newId);
        } else {
            dao.updateCompany(company);
        }
        List<Company> beans = dao.findAll();

        request.setAttribute("beans", beans);
        request.setAttribute("bean", company);
        //RequestDispatcher rd = request.getRequestDispatcher("company.jsp");
        response.sendRedirect("/FuelCompany/company?id="+company.getId()+"");
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
