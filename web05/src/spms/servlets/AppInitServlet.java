package spms.servlets;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by 1001235 on 15. 5. 21..
 */
public class AppInitServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("AppInitServlet Ready...");
        super.init(config);

        try {
            ServletContext sc = this.getServletContext();
            Class.forName(sc.getInitParameter("driver"));
            Connection conn = DriverManager.getConnection(sc.getInitParameter("url"), sc.getInitParameter("username"), sc.getInitParameter("password"));
            sc.setAttribute("conn", conn);
        } catch (Throwable e) {
            throw new ServletException(e);
        }
    }

    @Override
    public void destroy() {
        System.out.println("AppInitServlet termination...");
        super.destroy();
        Connection conn = (Connection)this.getServletContext().getAttribute("conn");
        try {
            if (conn != null && conn.isClosed() == false) {
                conn.close();
            }
        } catch (Exception e) {}
    }
}
