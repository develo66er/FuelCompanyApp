package com.servlets;

import com.dao.DispenserDAO;
import com.dao.DispenserDAOIml;
import com.dao.PetrolStationDAO;
import com.dao.PetrolStationDAOIml;
import com.models.Dispenser;
import com.models.PetrolStation;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author natali
 */
public class DispenserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String id = request.getParameter("id");
        String delete = request.getParameter("delete");
        String update = request.getParameter("update");

        DispenserDAO dao = new DispenserDAOIml();
        PetrolStationDAO stationDAO = new PetrolStationDAOIml();
        Predicate<Dispenser> filter = d -> true;
        if (id != null && delete != null) {
            dao.deletePetrolStationDispenser(Long.parseLong(id), Long.parseLong(delete));
        } else if (id != null&&update != null) {
            request.setAttribute("dispenserId", update);
        } 
        if (id != null && Long.parseLong(id)!=-1) {
            filter = d->d.getPetrolstationId() == Long.parseLong(id);
        }
        List<Dispenser> dispensers = dao.getAllDispensers().stream().filter(filter).collect(Collectors.toList());
        request.setAttribute("dispensers", dispensers);
        List<PetrolStation> petrolStations = stationDAO.getAllPetrolStations();
        request.setAttribute("petrolStations", petrolStations);
        request.setAttribute("activeFilter", id);

        RequestDispatcher rd = request.getRequestDispatcher("dispenser.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DispenserDAO dao = new DispenserDAOIml();
        Dispenser dispenser = new Dispenser();
        String id = request.getParameter("id");
        if (id != null && !id.isEmpty()) {
            dispenser.setId(Integer.parseInt(id));
        }
        dispenser.setType(request.getParameter("dispenser_type"));
        dispenser.setCount(Integer.parseInt(request.getParameter("dispenser_count")));
        dispenser.setModel(request.getParameter("dispenser_model"));
        dispenser.setPetrolstationId(Long.parseLong(request.getParameter("petrolstation_id")));

        long newId = -1;
        if (id == null || id.isEmpty()) {
            newId = dao.insertDispenserByPetrolStation(dispenser.getPetrolstationId(), dispenser);
            dispenser.setId(newId);
        } else {
            dao.updateDispenserByPetrolStation(Long.parseLong(request.getParameter("old_petrolstation_id")), dispenser);
        }
        List<PetrolStation> petrolStations = new PetrolStationDAOIml().getAllPetrolStations();
        
        List<Dispenser> dispensers = dao.getAllDispensers().stream().filter(d->d.getPetrolstationId()==dispenser.getPetrolstationId()).collect(Collectors.toList());
        request.setAttribute("dispensers", dispensers);
        request.setAttribute("petrolStations", petrolStations);
                request.setAttribute("activeFilter", String.valueOf(dispenser.getPetrolstationId()));

        RequestDispatcher rd = request.getRequestDispatcher("dispenser.jsp");
        rd.forward(request, response);
    }

}
