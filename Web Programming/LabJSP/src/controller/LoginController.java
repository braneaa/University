package controller;


import model.DBManager;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginController")
public class LoginController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        RequestDispatcher rd = null;

        DBManager dbManager = new DBManager();
        User user = dbManager.authenticate(username, password);
        if (user != null){
            dbManager.clearUserData(user.getId());
            rd = request.getRequestDispatcher("selectStartingPoint.jsp");
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
        }
        else{
            rd = request.getRequestDispatcher("loginFailure.jsp");
        }
        rd.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}