package spms.servlets;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by 1001235 on 15. 5. 13..
 */
@WebServlet("/member/list")
//public class MemberListServlet extends GenericServlet {
    //@Override
    //public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
public class MemberListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            //DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            //conn = DriverManager.getConnection("jdbc:mysql://localhost/studydb", "study", "TestStudy00");
            ServletContext sc = this.getServletContext();
            Class.forName(sc.getInitParameter("driver"));
            conn = DriverManager.getConnection(sc.getInitParameter("url"), sc.getInitParameter("username"), sc.getInitParameter("password"));

            stmt = conn.createStatement();
            rs = stmt.executeQuery("select mno, mname, email, cre_date, mod_date from members order by mno asc");

            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<html><head><title>회원목록</title></head>");
            out.println("<body><h1>회원목록</h1>");
            out.println("<p><a href='add'>신규회원</a></p>");
            while (rs.next()) {
                out.println(
                        rs.getInt("mno") + ", " +
                                "<a href='update?no=" + rs.getInt("mno") + "'>" +
                                rs.getString("mname") + "</a>, " +
                                rs.getString("email") + ", " +
                                rs.getDate("cre_date") + ", " +
                                rs.getDate("mod_date") + " " +
                                "<a href='delete?no=" + rs.getInt("mno") + "'><삭제></a><br>");

            }
            out.println("</body></html>");

        } catch (Exception e) {
            throw new ServletException(e);
        } finally {
            try {if (rs != null) rs.close();} catch(Exception e) {}
            try {if (stmt != null) stmt.close();} catch(Exception e) {}
            try {if (conn != null) conn.close();} catch(Exception e) {}
        }

    }

}
