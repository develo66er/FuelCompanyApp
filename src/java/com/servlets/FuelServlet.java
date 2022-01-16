package com.servlets;

import com.dao.DispenserDAO;
import com.dao.DispenserDAOIml;
import com.dao.FuelDAO;
import com.dao.FuelDAOIml;
import com.dao.PetrolStationDAO;
import com.dao.PetrolStationDAOIml;
import com.models.Dispenser;
import com.models.Fuel;
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
public class FuelServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String id = request.getParameter("id");
        String delete = request.getParameter("delete");
        String update = request.getParameter("update");
        FuelDAO dao = new FuelDAOIml();
        PetrolStationDAO stationDAO = new PetrolStationDAOIml();
        Predicate<Fuel> filter = f -> true;
        if (id != null && delete != null) {
            dao.deletePetrolStationFuel(Long.parseLong(id), Long.parseLong(delete));
        } else if (id != null && update != null) {
            request.setAttribute("fuelId", update);
        }
        if (id != null && Long.parseLong(id) != -1) {
            filter = f -> f.getPetrolstationId() == Long.parseLong(id);
        }
        List<Fuel> fuels = dao.getAllFuels().stream().filter(filter).collect(Collectors.toList());
        request.setAttribute("fuels", fuels);
        List<PetrolStation> petrolStations = stationDAO.getAllPetrolStations();
        request.setAttribute("petrolStations", petrolStations);
        request.setAttribute("activeFilterStation", id);

        RequestDispatcher rd = request.getRequestDispatcher("fuel.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        FuelDAO dao = new FuelDAOIml();
        Fuel fuel = new Fuel();
        String id = request.getParameter("id");
        if (id != null && !id.isEmpty()) {
            fuel.setId(Integer.parseInt(id));
        }
        fuel.setType(request.getParameter("fuel_type"));
        fuel.setName(request.getParameter("fuel_name"));
        fuel.setPetrolstationId(Long.parseLong(request.getParameter("petrolstation_id")));
        fuel.setPrice(Double.parseDouble(request.getParameter("fuel_price")));

        long newId = -1;
        if (id == null || id.isEmpty()) {
            newId = dao.insertFuelByPetrolStation(fuel.getPetrolstationId(), fuel);
            fuel.setId(newId);
        } else {
            dao.updateFuelByPetrolStation(Long.parseLong(request.getParameter("old_petrolstation_id")), fuel);
        }
        List<PetrolStation> petrolStations = new PetrolStationDAOIml().getAllPetrolStations();

        List<Fuel> fuels = dao.getAllFuels().stream().filter(d -> d.getPetrolstationId() == fuel.getPetrolstationId()).collect(Collectors.toList());
        request.setAttribute("fuels", fuels);
        request.setAttribute("petrolStations", petrolStations);
        request.setAttribute("activeFilterStation", String.valueOf(fuel.getPetrolstationId()));

        RequestDispatcher rd = request.getRequestDispatcher("fuel.jsp");
        rd.forward(request, response);
    }

}
