package spms.servlets;

import spms.dao.MemberDao;
import spms.vo.Member;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Created by 1001235 on 15. 5. 15..
 */
@WebServlet("/member/add")
public class MemberAddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("viewUrl", "/member/MemberForm.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ServletContext sc = this.getServletContext();

            Member member = (Member) request.getAttribute("member");
            MemberDao memberDao = (MemberDao) sc.getAttribute("memberDao");
            memberDao.insert(member);

            request.setAttribute("viewUrl", "redirect:list.do");

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
