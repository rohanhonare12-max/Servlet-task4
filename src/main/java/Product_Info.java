import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/ProductForm")
public class Product_Info extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        out.println("<h2>Product Feedback Form</h2>");
        out.println("<form action='FeedbackServlet' method='post'>");

        out.println("Product Name: <input type='text' name='p_name' required><br><br>");
        out.println("Product Prize: <input type='number' name='p_prize' required><br><br>");
        out.println("Product Quantity: <input type='text' name='p_quantity' required><br><br>");
        out.println("Product ID: <input type='number' name='p_id' required><br><br>");

        out.println("<input type='submit' value='Submit'>");
        out.println("</form>");
    }
}
