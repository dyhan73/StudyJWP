package spms.servlets;

import spms.dao.MemberDao;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 1001235 on 15. 5. 19..
 */
@WebServlet("/member/delete")
public class MemberDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ServletContext sc = this.getServletContext();

            MemberDao memberDao = (MemberDao) sc.getAttribute("memberDao");

            memberDao.delete(Integer.parseInt(request.getParameter("no")));

            request.setAttribute("viewUrl", "redirect:list.do");

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
