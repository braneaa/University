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
import java.util.List;

@WebServlet(name = "FinishedController")
public class FinishedController extends HttpServlet {
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User)request.getSession().getAttribute("user");
        RequestDispatcher rd = null;
        if (user != null){
            DBManager dbManager = new DBManager();
            dbManager.clearUserData(user.getId());
            request.getRequestDispatcher("displayFullRoute.jsp");
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
        }
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User)req.getSession().getAttribute("user");
        RequestDispatcher rd = null;
        if (user != null){
            req.getRequestDispatcher("displayFullRoute.jsp");
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
        }
        rd.forward(req, resp);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User)request.getSession().getAttribute("user");
        RequestDispatcher rd = null;
        if (user != null){
            DBManager dbManager = new DBManager();
            List<City> cities = dbManager.getFullRoute(user.getId());
            JSONArray jsonArray = new JSONArray();
            for (City c : cities){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("CityID", c.getId());
                jsonObject.put("Name", c.getName());
                jsonArray.add(jsonObject);
            }
            PrintWriter out = new PrintWriter(response.getOutputStream());
            out.println(jsonArray.toJSONString());
            out.flush();
        }
    }
}