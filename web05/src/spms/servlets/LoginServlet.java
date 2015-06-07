package spms.servlets;

import spms.dao.MemberDao;
import spms.vo.Member;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by 1001235 on 15. 5. 21..
 */
@WebServlet("/auth/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ServletContext sc = this.getServletContext();

            MemberDao memberDao = (MemberDao) sc.getAttribute("memberDao");

            Member member = memberDao.exist(request.getParameter("email"), request.getParameter("password"));

            HttpSession session = request.getSession();
            session.setAttribute("member", member);
            request.setAttribute("viewUrl", "redirect:../member/list.do");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("viewUrl", "/auth/LoginForm.jsp");
    }
}
