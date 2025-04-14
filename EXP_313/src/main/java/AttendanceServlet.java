import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AttendanceServlet")
public class AttendanceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String studentName = request.getParameter("student_name");
        String date = request.getParameter("date");
        String status = request.getParameter("status");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/PBLJ", "root", "1234");

            String query = "INSERT INTO attendance (student_name, date, status) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, studentName);
            stmt.setString(2, date);
            stmt.setString(3, status);
            stmt.executeUpdate();

            out.println("<html><body>");
            out.println("<h1>Attendance Details Saved Successfully</h1>");
            out.println("<a href='index.jsp'>Back to Home</a>");
            out.println("</body></html>");

            conn.close();
        } catch (Exception e) {
            out.println("<html><body>");
            out.println("<h1>Error: " + e.getMessage() + "</h1>");
            out.println("<a href='index.jsp'>Back to Home</a>");
            out.println("</body></html>");
        }
    }
}