import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.Connection;

@WebServlet("/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        out.println("<html><body>");
        out.println("<h2>Employee List</h2>");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/PBLJ", "root", "1234");
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM employees");
            ResultSet rs = stmt.executeQuery();
            
            out.println("<table border='1'><tr><th>ID</th><th>Name</th><th>Position</th></tr>");
            while (rs.next()) {
                out.println("<tr><td>" + rs.getInt("id") + "</td><td>" + rs.getString("name") + "</td><td>" + rs.getString("position") + "</td></tr>");
            }
            out.println("</table>");
            
            out.println("<h2>Search Employee</h2>");
            out.println("<form action='EmployeeServlet' method='post'>");
            out.println("Enter Employee ID: <input type='text' name='empId'>");
            out.println("<input type='submit' value='Search'>");
            out.println("</form>");
            
            conn.close();
        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
        }
        
        out.println("</body></html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String empId = request.getParameter("empId");
        
        out.println("<html><body>");
        out.println("<h2>Employee Details</h2>");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/PBLJ", "root", "1234");
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM employees WHERE id = ?");
            stmt.setInt(1, Integer.parseInt(empId));
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                out.println("<p>ID: " + rs.getInt("id") + "</p>");
                out.println("<p>Name: " + rs.getString("name") + "</p>");
                out.println("<p>Position: " + rs.getString("position") + "</p>");
            } else {
                out.println("<p>No employee found with ID: " + empId + "</p>");
            }
            
            conn.close();
        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
        }
        
        out.println("<a href='EmployeeServlet'>Back</a>");
        out.println("</body></html>");
    }
}