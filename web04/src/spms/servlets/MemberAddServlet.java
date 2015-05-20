package spms.servlets;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 * Created by 1001235 on 15. 5. 15..
 */
@WebServlet("/member/add")
public class MemberAddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html><head><title>회원등록</title></head>");
        out.println("<body><h1>회원 등록</h1>");
        out.println("<form action='add' method='post'>");
        out.println("이름: <input type='text' name='name'><br>");
        out.println("이메일: <input type='text' name='email'><br>");
        out.println("암호: <input type='text' name='password'><br>");
        out.println("<input type='submit' value='추가'>");
        out.println("<input type='reset' value='취소' onclick='location.href=\"list\"'>");
        out.println("</form>");
        out.println("</body></html>");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //request.setCharacterEncoding("UTF-8");

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            /*
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            conn = DriverManager.getConnection("jdbc:mysql://localhost/studydb", "study", "TestStudy00");
            */
            ServletContext sc = this.getServletContext();
            Class.forName(sc.getInitParameter("driver"));
            conn = DriverManager.getConnection(sc.getInitParameter("url"), sc.getInitParameter("username"), sc.getInitParameter("password"));

            stmt = conn.prepareStatement("insert into members(email, pwd,mname, cre_date, mod_date)" + "values (?, ?, ?, now(), now())");
            stmt.setString(1, request.getParameter("email"));
            stmt.setString(2, request.getParameter("password"));
            stmt.setString(3, request.getParameter("name"));
            stmt.executeUpdate();

            response.sendRedirect("list");

            /*
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<html><head><title>회원등록결과</title></head>");
            out.println("<body>");
            out.println("<p>등록 성공입니다!</p>");
            out.println("</body></html>");

            // refresh
            response.addHeader("Refresh", "1;url=list");
            */
        } catch (Exception e) {
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<html><head><title>회원등록결과</title></head>");
            out.println("<body>");
            out.println("<p>등록 실패입니다!</p>");
            out.println("오류 상세 : " + e.toString());
            out.println("</body></html>");

            throw new ServletException(e);
        } finally {
            try { if (stmt != null) stmt.close();} catch(Exception e) {}
            try { if (conn != null) conn.close();} catch(Exception e) {}
        }

    }
}
