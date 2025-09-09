package com.payments;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class PaymentController extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String userId = req.getParameter("user_id");
        String amount = req.getParameter("amount");
        String status = "FAILED";

        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO transactions(user_id, amount, status) VALUES (?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
            );
            ps.setString(1, userId);
            ps.setDouble(2, Double.parseDouble(amount));

            // Random success/failure for demo
            status = Math.random() > 0.5 ? "SUCCESS" : "FAILED";
            ps.setString(3, status);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html><body>");
        out.println("<h2>Transaction " + status + "</h2>");
        out.println("<a href='index.jsp'>Back</a>");
        out.println("</body></html>");
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("index.jsp");
    }
}
