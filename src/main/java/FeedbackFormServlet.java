import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.lang.NumberFormatException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/FeedbackServlet")
public class FeedbackFormServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        // 🔍 DEBUG OUTPUT (DO NOT REMOVE YET)
        out.println("p_name = " + req.getParameter("p_name") + "<br>");
        out.println("p_prize = " + req.getParameter("p_prize") + "<br>");
        out.println("p_quantity = " + req.getParameter("p_quantity") + "<br>");
        out.println("p_id = " + req.getParameter("p_id") + "<br><br>");

        try {
            String P_Name = req.getParameter("p_name");
            String P_Prize = req.getParameter("p_prize");
            String P_quantity = req.getParameter("p_quantity");
            String P_ID = req.getParameter("p_id");

            if (P_Prize == null || P_ID == null) {
                out.println("<h3 style='color:red;'>Form values not received</h3>");
                return;
            }

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/mydatabase",
                    "root",
                    "Rohan@2005"
            );

            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO Login_Info VALUES (?,?,?,?)"
            );

            ps.setString(1, P_Name);
            ps.setInt(2, Integer.parseInt(P_Prize));
            ps.setString(3, P_quantity);
            ps.setInt(4, Integer.parseInt(P_ID));

            ps.executeUpdate();

            out.println("<h2 style='color:green;'>Successfully Submitted!</h2>");

            ps.close();
            con.close();

        } catch (Exception e) {
            out.println("<h3 style='color:red;'>Error Occurred</h3>");
            e.printStackTrace(out);
        }
    }
}
