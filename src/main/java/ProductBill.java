import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ProductBill")
public class ProductBill extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/mydatabase",
                    "root",
                    "Rohan@2005"
            );

            Statement st = con.createStatement();

            // ✅ Fetch the latest inserted row using AUTO_INCREMENT
            ResultSet rs = st.executeQuery(
                    "SELECT P_Name, P_Prize, P_quantity FROM Login_Info ORDER BY bill_id DESC LIMIT 1"
            );

            out.println("<h2>Product Bill</h2>");
            out.println("<table border='1'>");
            out.println("<tr><th>Name</th><th>Price</th><th>Quantity</th><th>Total</th></tr>");

            if (rs.next()) {
                String name = rs.getString("P_Name");
                int price = rs.getInt("P_Prize");
                int quantity = rs.getInt("P_quantity");
                int total = price * quantity;

                out.println("<tr>");
                out.println("<td>" + name + "</td>");
                out.println("<td>" + price + "</td>");
                out.println("<td>" + quantity + "</td>");
                out.println("<td>" + total + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");

            rs.close();
            st.close();
            con.close();

        } catch (Exception e) {
            out.println("<h3 style='color:red;'>Error</h3>");
            e.printStackTrace(out);
        }
    }
}
