package spms.controls;

import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.MemberDao;
import spms.vo.Member;

import java.util.Map;

/**
 * Created by 1001235 on 15. 5. 26..
 */
@Component("/member/update.do")
public class MemberUpdateController implements Controller, DataBinding {
    MemberDao memberDao;

    public MemberUpdateController setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
        return this;
    }

    @Override
    public String execute(Map<String, Object> model) throws Exception {
        Member member = (Member)model.get("member");
        if (member.getEmail() == null) {
            member = memberDao.selectOne((Integer)model.get("no"));
            model.put("member", member);
            return "/member/MemberUpdateForm.jsp";
        } else {
            memberDao.update(member);

            return "redirect:list.do";
        }
    }

    @Override
    public Object[] getDataBinders() {
        return new Object[] {
                "no", Integer.class,
                "member", spms.vo.Member.class
        };
    }
}
