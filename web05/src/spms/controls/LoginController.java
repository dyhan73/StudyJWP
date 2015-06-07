package spms.controls;

import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.MemberDao;
import spms.vo.Member;

import java.util.Map;

/**
 * Created by 1001235 on 15. 5. 26..
 */
@Component("/auth/login.do")
public class LoginController implements Controller, DataBinding {
    MemberDao memberDao;

    public LoginController setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
        return this;
    }

    @Override
    public String execute(Map<String, Object> model) throws Exception {
        Member member = (Member) model.get("loginInfo");
        if (member.getEmail() == null) {
            return "/auth/LoginForm.jsp";
        } else {
            memberDao.exist(member.getEmail(), member.getPassword());
            return "redirect:/member/list.do";
        }
    }

    @Override
    public Object[] getDataBinders() {
        return new Object[] {
                "loginInfo", spms.vo.Member.class
        };
    }
}
