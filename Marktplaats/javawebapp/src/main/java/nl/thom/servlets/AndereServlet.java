package nl.thom.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AndereNaam", urlPatterns = "/other")
public class AndereServlet extends HttpServlet {

    private String boodschap;

    public void init() {
        boodschap = "Andere!";
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        out.println("<html><head><title>:'(</title><body>");
        out.println("<h1 style='color: slategrey; font-family: Consolas; font-size: 72px;'>" + boodschap + "</h1>");
        out.println("</body></html>");
    }
}
