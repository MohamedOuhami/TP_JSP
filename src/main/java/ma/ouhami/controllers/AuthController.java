/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ma.ouhami.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import ma.ouhami.entities.Client;
import ma.ouhami.services.UserService;
import ma.ouhami.util.SendMailTLS;
// Import necessary JavaMail classes
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import java.util.Random;
import ma.ouhami.entities.User;
import ma.ouhami.test.test;

/**
 *
 * @author moham
 */
@WebServlet(
        name = "AuthServlet", urlPatterns = {
            "/signin", "/signup", "/resetpassword", "/sendactivation",
            "/user/create", "/user/signin", "/user/panel", "/recoverpassword",
            "/user/disconnect", "/user/recoverpassword", "/user/codegeneration",
            "/user/resetpassword", "/user/passwordcheck"
        }
)
public class AuthController extends HttpServlet {

    protected UserService us;

    @Override
    public void init() throws ServletException {
        super.init();
        us = new UserService();
    }

    // Utility method to compute MD5 hash
    public static String MD5(String s) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            BigInteger bi = new BigInteger(1, md.digest(s.getBytes()));
            return bi.toString(16);
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Show the appropriate page based on the URL
        String pathInfo = req.getRequestURI();

        if (pathInfo.equals("/signin")) {
            // Redirect authenticated users to the panel
            HttpSession session = req.getSession();
            String authenticatedUser = (String) session.getAttribute("login");
            if (authenticatedUser != null) {
                resp.sendRedirect(req.getContextPath() + "/user/panel");
            } else {
                req.getRequestDispatcher("/signin.jsp").forward(req, resp);
            }
        } else if (pathInfo.equals("/resetpassword")) {
            req.getRequestDispatcher("/recoverpassword.jsp").forward(req, resp);
        } else if (pathInfo.equals("/signup")) {
            req.getRequestDispatcher("/signup.jsp").forward(req, resp);
        } else if (pathInfo.equals("/user/panel")) {
            req.getRequestDispatcher("/userPanel.jsp").forward(req, resp);
        } else if (pathInfo.equals("/recoverpassword")) {
            req.getRequestDispatcher("/recoverpassword.jsp").forward(req, resp);
        } else if (pathInfo.equals("/user/codegeneration")) {
            // Generate a random code
            Random random = new Random();
            String randomNumber = Integer.toString(random.nextInt(40000 - 10000 + 1) + 10000);

            // Send the random number by email
            String recipient = "mohamed.ouhami2001@gmail.com";
            String subject = "test sendMailer";
            String messageBody = randomNumber;

            SendMailTLS.sendEmail(recipient, subject, messageBody);
            req.setAttribute("code", randomNumber);
            req.getRequestDispatcher("/codegeneration.jsp").forward(req, resp);
        } else if (pathInfo.equals("/user/resetpassword")) {
            req.getRequestDispatcher("/resetpassword.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getRequestURI();

        if (pathInfo.equals("/user/create")) {
            // Get user registration data from the form
            String nom = req.getParameter("nom");
            String prenom = req.getParameter("prenom");
            String dateNaissanceStr = req.getParameter("dateNaissance");
            String email = req.getParameter("email");
            String password = req.getParameter("password");

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date dateNaissance = null;

            try {
                dateNaissance = dateFormat.parse(dateNaissanceStr);
            } catch (java.text.ParseException e) {
                e.printStackTrace();
                // Handle date parsing error
                resp.sendRedirect(req.getContextPath() + "/signup?error=date");
                return;
            }

            // Create a new client
            Client client = new Client(nom, prenom, dateNaissance, email, MD5(password));
            us.create(client);

            // Redirect to sign-in page
            resp.sendRedirect(req.getContextPath() + "/signin");
        } else if (pathInfo.equals("/user/signin")) {
            // Handle user sign-in
            String login = req.getParameter("email");
            String password = req.getParameter("password");

            if (us.findByEmail(login).getPassword().equals(MD5(password))) {
                // Set the login session attribute
                HttpSession session = req.getSession();
                session.setAttribute("login", login);

                // Redirect to the user panel
                resp.sendRedirect(req.getContextPath() + "/user/panel");
            } else {
                // Redirect to the sign-up page (handle authentication failure)
                resp.sendRedirect(req.getContextPath() + "/signup");
            }
        } else if (pathInfo.equals(req.getContextPath() + "/user/disconnect")) {
            // Handle user disconnection
            HttpSession session = req.getSession();
            session.invalidate();
            resp.sendRedirect(req.getContextPath() + "/signin");
        } else if (pathInfo.equals(req.getContextPath() + "/user/recoverpassword")) {
            // Handle password recovery request
            String login = req.getParameter("email");
            HttpSession session = req.getSession();
            session.setAttribute("reset_login", login);
            resp.sendRedirect(req.getContextPath() + "/user/codegeneration");
        } else if (pathInfo.equals(req.getContextPath() + "/user/resetpassword")) {
            // Handle password reset request
            if (req.getParameter("gen_code").equals(req.getParameter("entered_code"))) {
                resp.sendRedirect(req.getContextPath() + "/user/resetpassword");
            } else {
                resp.sendRedirect(req.getContextPath() + "/user/codegeneration");
            }
        } else if (pathInfo.equals(req.getContextPath() + "/user/passwordcheck")) {
            // Handle password check and update
            HttpSession session = req.getSession();
            String login = (String) session.getAttribute("reset_login");
            String new_password = req.getParameter("password");

            // Find the user and update the password
            User user = us.findByEmail(login);
            user.setPassword(MD5(new_password));
            us.update(user);

            // Invalidate the session and redirect to the sign-in page
            session.invalidate();
            resp.sendRedirect(req.getContextPath() + "/signin");
        }
    }

// ...
}
