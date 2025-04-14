import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Set response content type
        response.setContentType("text/html");

        // Get username and password from request
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Simple authentication (for demo purposes only, not secure!)
        if ("admin".equals(username) && "password123".equals(password)) {
            // Display a personalized welcome message
            PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html><head><title>Welcome</title></head><body>");
            out.println("<h1>Welcome, " + username + "!</h1>");
            out.println("<p>Login successful. Enjoy your session!</p>");
            out.println("</body></html>");
        } else {
            // Invalid credentials
            PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html><head><title>Login Failed</title></head><body>");
            out.println("<h1>Invalid username or password!</h1>");
            out.println("<p>Please <a href='index.html'>try again</a>.</p>");
            out.println("</body></html>");
        }
    }
}