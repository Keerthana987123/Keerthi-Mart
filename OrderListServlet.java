```java
package com.elitemart.controller;

import com.elitemart.util.DBConnection;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/orders")
public class OrderListServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws IOException {

        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String sql = "SELECT o.order_id, u.name, o.total_amount, o.status, o.order_date " +
                     "FROM orders o JOIN users u ON o.buyer_id = u.user_id " +
                     "ORDER BY o.order_id DESC";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet result = statement.executeQuery()) {

            StringBuilder json = new StringBuilder("[");
            boolean first = true;

            while (result.next()) {

                if (!first) {
                    json.append(",");
                }
                first = false;

                json.append("{")
                    .append("\"storeName\":\"Keerthana Mart\",")
                    .append("\"orderId\":").append(result.getInt("order_id")).append(",")
                    .append("\"buyer\":\"")
                    .append(escapeJson(result.getString("name"))).append("\",")
                    .append("\"total\":").append(result.getDouble("total_amount")).append(",")
                    .append("\"status\":\"")
                    .append(escapeJson(result.getString("status"))).append("\"")
                    .append("}");
            }

            json.append("]");
            out.print(json.toString());

        } catch (Exception e) {
            response.sendError(
                HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                "Keerthana Mart: Could not load orders"
            );
        }
    }

    private String escapeJson(String value) {
        if (value == null) {
            return "";
        }

        return value.replace("\\", "\\\\")
                    .replace("\"", "\\\"")
                    .replace("\n", "\\n")
                    .replace("\r", "\\r")
                    .replace("\t", "\\t");
    }
}