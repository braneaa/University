package controller;


import model.City;
import model.DBManager;
import model.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@WebServlet(name = "RouteController")
public class RouteController extends HttpServlet {


    @Override
    public void init() throws ServletException {
        super.init();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cityName = request.getParameter("city");
        User user = (User)request.getSession().getAttribute("user");
        RequestDispatcher rd = null;
        if (user != null) {
            DBManager dbManager = new DBManager();
            dbManager.addCityToRoute(user.getId(), cityName);

            rd = request.getRequestDispatcher("displayDestinations.jsp");
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
        }
        else{
            rd = request.getRequestDispatcher("loginFailure.jsp");
        }
        rd.forward(request, response);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User)req.getSession().getAttribute("user");
        String all = req.getParameter("all");

        RequestDispatcher rd = null;
        if (user != null) {
            if (all.equals("false")) {
                DBManager dbManager = new DBManager();
                dbManager.removeLastDestinationForUser(user.getId());

                rd = req.getRequestDispatcher("displayDestinations.jsp");
                HttpSession session = req.getSession();
                session.setAttribute("user", user);
            }
            else{
                DBManager dbManager = new DBManager();
                dbManager.clearUserData(user.getId());

                rd = req.getRequestDispatcher("displayDestinations.jsp");
                HttpSession session = req.getSession();
                session.setAttribute("user", user);
            }
        }
        rd.forward(req, resp);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = ((User)request.getSession().getAttribute("user")).getId();
        String all = request.getParameter("all");

        JSONArray jsonArray = new JSONArray();
        if (all.equals("false")){
            DBManager dbManager = new DBManager();
            Map<City, Integer> cities = dbManager.getDestinationCities(userId);
            City currentCity = dbManager.getUserCurrent(userId);
            JSONObject curr = new JSONObject();
            curr.put("CityID", currentCity.getId());
            curr.put("Name", currentCity.getName());
            jsonArray.add(curr);
            for (City c : cities.keySet()){
                JSONObject obj = new JSONObject();
                obj.put("CityID" ,c.getId());
                obj.put("Name", c.getName());
                obj.put("Distance", cities.get(c));
                jsonArray.add(obj);
            }
        }
        else{
            DBManager dbManager = new DBManager();
            List<City> cities = dbManager.getFullRoute(userId);
            cities = dbManager.getFullRoute(userId);
            for (City c : cities) {
                JSONObject obj = new JSONObject();
                obj.put("CityID", c.getId());
                obj.put("Name", c.getName());
                System.out.println();
                jsonArray.add(obj);
            }
        }
        PrintWriter out = new PrintWriter(response.getOutputStream());
        out.println(jsonArray.toJSONString());
        out.flush();
    }
}