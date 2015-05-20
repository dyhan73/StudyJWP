package spms.servlets;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * Created by 1001235 on 15. 5. 19..
 */
@WebServlet("/member/delete")
public class MemberDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            ServletContext sc = this.getServletContext();
            Class.forName(sc.getInitParameter("driver"));
            conn = DriverManager.getConnection(sc.getInitParameter("url"), sc.getInitParameter("username"), sc.getInitParameter("password"));
            stmt = conn.prepareStatement("delete from members where mno=?");
            stmt.setInt(1, Integer.parseInt(request.getParameter("no")));
            stmt.executeUpdate();

            response.sendRedirect("list");

        } catch (Exception e) {
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<html><head><title>회원삭제결과</title></head>");
            out.println("<body>");
            out.println("<p>삭제 실패입니다!</p>");
            out.println("오류 상세 : " + e.toString());
            out.println("</body></html>");
            throw new ServletException(e);
        } finally {
            try {if (stmt != null) stmt.close();} catch(Exception e) {}
            try {if (conn != null) conn.close();} catch(Exception e) {}
        }
    }
}
