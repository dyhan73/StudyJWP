package spms.servlets;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * Created by 1001235 on 15. 5. 18..
 */
/*
@WebServlet(
        urlPatterns = {"/member/update"},
        initParams = {
                @WebInitParam(name="driver", value="com.mysql.jdbc.Driver"),
                @WebInitParam(name="url", value="jdbc:mysql://localhost/studydb"),
                @WebInitParam(name="username", value="study"),
                @WebInitParam(name="password", value="TestStudy00")
        }
)
*/
@SuppressWarnings("serial")
@WebServlet("/member/update")
public class MemberUpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            /*
            Class.forName(this.getInitParameter("driver"));
            conn = DriverManager.getConnection(this.getInitParameter("url"), this.getInitParameter("username"), this.getInitParameter("password"));
            */
            ServletContext sc = this.getServletContext();
            Class.forName(sc.getInitParameter("driver"));
            conn = DriverManager.getConnection(sc.getInitParameter("url"), sc.getInitParameter("username"), sc.getInitParameter("password"));

            stmt = conn.createStatement();
            rs = stmt.executeQuery("select mno, email, mname, cre_date from members " +
                                    "where mno=" + request.getParameter("no"));
            rs.next();

            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<html><head><title>회원정보</title></head>");
            out.println("<body><h1>회원정보</h1>");
            out.println("<form action='update' method='post'>");
            out.println("번호 : <input type='text' name='no' value='" + request.getParameter("no") + "' readonly><br>");
            out.println("이름 : <input type='text' name='name' value='" + rs.getString("mname") + "'><br>");
            out.println("이메일 : <input type='text' name='email' value='" + rs.getString("email") + "'><br>");
            out.println("가입일 : " + rs.getString("cre_date") + "<br>");
            out.println("<input type='submit' value='저장'>");
            out.println("<input type='button' value='삭제'" + " onclick='location.href=\"delete?no=" + request.getParameter("no") + "\"'>");
            out.println("<input type='button' value='취소'" + " onclick='location.href=\"list\"'>");
            out.println("</form>");
            out.println("</body></html>");

        } catch (SQLException e) {
            throw new ServletException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close();} catch(Exception e) {}
            try { if (stmt != null) stmt.close();} catch(Exception e) {}
            try { if (conn != null) conn.close();} catch(Exception e) {}
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //request.setCharacterEncoding("UTF-8");
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            response.setContentType("text/html; charset=UTF-8");

            //Class.forName(this.getInitParameter("driver"));
            //conn = DriverManager.getConnection(this.getInitParameter("url"), this.getInitParameter("username"), this.getInitParameter("password"));
            ServletContext sc = this.getServletContext();
            Class.forName(sc.getInitParameter("driver"));
            conn = DriverManager.getConnection(sc.getInitParameter("url"), sc.getInitParameter("username"), sc.getInitParameter("password"));

            stmt = conn.prepareStatement("update members set mname=?, email=?, mod_date=now() where mno=" + request.getParameter("no"));
            stmt.setString(1, request.getParameter("name"));
            stmt.setString(2, request.getParameter("email"));
            stmt.executeUpdate();

            response.sendRedirect("list");

        } catch (Exception e) {
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<html><head><title>회원수정결과</title></head>");
            out.println("<body>");
            out.println("<p>수정 실패입니다!</p>");
            out.println("오류 상세 : " + e.toString());
            out.println("</body></html>");
            throw new ServletException(e);
        } finally {
            try { if (stmt != null) stmt.close();} catch(Exception e) {}
            try { if (conn != null) conn.close();} catch(Exception e) {}
        }
    }
}
