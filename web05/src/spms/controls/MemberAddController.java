package spms.controls;

import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.MemberDao;
import spms.vo.Member;

import java.util.Map;

/**
 * Created by 1001235 on 15. 5. 26..
 */
@Component("/member/add.do")
public class MemberAddController implements Controller, DataBinding {
    MemberDao memberDao;

    public MemberAddController setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
        return this;
    }

    @Override
    public String execute(Map<String, Object> model) throws Exception {
        Member member = (Member) model.get("member");
        if (member.getEmail() == null) { // Get
            return "/member/MemberForm.jsp";
        } else { // POST
            memberDao.insert(member);
            return "redirect:list.do";
        }
    }

    @Override
    public Object[] getDataBinders() {
        return new Object[] {
                "member", spms.vo.Member.class
        };
    }
}
