package com.servlets;

import com.dao.CompanyDAO;
import com.dao.CompanyDAOIml;
import com.dao.PetrolStationDAO;
import com.dao.PetrolStationDAOIml;
import com.models.Company;
import com.models.PetrolStation;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author natali
 */
public class PetrolStationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String id = request.getParameter("id");
        String delete = request.getParameter("delete");
        String update = request.getParameter("update");

        CompanyDAO dao = new CompanyDAOIml();
        PetrolStationDAO pdao = new PetrolStationDAOIml();
//удаление заправки
        if (delete != null && id != null) {
            int cnt = pdao.deleteCompanyPetrolStation(Long.parseLong(id), Long.parseLong(delete));
            if (cnt == 1) {
                Company bean = dao.findById(id);
                request.setAttribute("bean", bean);
                List<Company> beans = dao.findAll();
                request.setAttribute("beans", beans);
            } else {
                List<Company> beans = dao.findAll();
                request.setAttribute("beans", beans);
                request.setAttribute("bean", beans.get(0));
                request.setAttribute("errorMessage", " омпани€ не была удалена");

            }

            //редактирование заправки
        } else if (update != null && id != null) {
            PetrolStation ps = pdao.getPetrolStationByCompany(Long.parseLong(id), Long.parseLong(update));
            request.setAttribute("petrolStation", ps);

            List<Company> beans = dao.findAll();
            request.setAttribute("beans", beans);
        } else if (id != null) {
            Company bean = dao.findById(id);
            request.setAttribute("bean", bean);
            List<Company> beans = dao.findAll();
            request.setAttribute("beans", beans);
        } else {
            List<Company> beans = dao.findAll();
            request.setAttribute("beans", beans);
            //request.setAttribute("bean", beans.get(0));
        }

        RequestDispatcher rd = request.getRequestDispatcher("petrolstation.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PetrolStationDAO dao = new PetrolStationDAOIml();
        PetrolStation station = new PetrolStation();
        String id = request.getParameter("id");
        if (id != null && !id.isEmpty()) {
            station.setId(Integer.parseInt(id));
        }
        station.setAddress(request.getParameter("address"));
        station.setStartTime(request.getParameter("start_time"));
        station.setFinishTime(request.getParameter("finish_time"));
        long companyId = Long.parseLong(request.getParameter("company_id"));
        long newId;
        if (id == null || id.isEmpty()) {
            newId = dao.insertPetrolStationsByCompany(companyId, station);
            station.setId(newId);
        } else {
            dao.updatePetrolStationsByCompany(companyId, station);
        }
        List<Company> beans = new CompanyDAOIml().findAll();
        if (id != null) {
            request.setAttribute("petrolStation", station);
        }

        request.setAttribute("beans", beans);
        RequestDispatcher rd = request.getRequestDispatcher("petrolstation.jsp");
        rd.forward(request, response);
    }

}
