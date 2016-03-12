package csomag;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by bathoryalex on 3/11/2016.
 */
public class WebServer extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String name = req.getParameter("username");
        String pass = req.getParameter("password");
        PrintWriter out = resp.getWriter();
        HttpSession session = req.getSession();

//        if ((name.equals("alex") && pass.equals("12345")) || (name.equals("coco") && pass.equals("kaka")))
        if (!name.equals(session.getAttribute("username")) && !pass.equals(session.getAttribute("password")))
        {
            if (name.equals("Alex") && pass.equals("12345"))
            {
                req.getRequestDispatcher("welcome.html").include(req, resp);
                out.println("Szevasz " + name + "!" + " I hope you don't have a shitty day!");
                session.setAttribute("username", name);
            } else
            {
                req.getRequestDispatcher("index.html").include(req, resp);
                out.println("The given username or password is incorrect! Permission denied!");
            }
        }
        else
        {
            out.println("You already logged in!");
            req.getRequestDispatcher("logout.html").include(req, resp);
        }
    }
}
