package spms.controls;

import spms.annotation.Component;
import spms.dao.MemberDao;

import java.util.Map;

/**
 * Created by 1001235 on 15. 5. 26..
 */
@Component("/member/list.do")
public class MemberListController implements Controller {
    MemberDao memberDao;

    public MemberListController setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
        return this;
    }

    @Override
    public String execute(Map<String, Object> model) throws Exception {
        model.put("members", memberDao.selectList());
        return "/member/MemberList.jsp";
    }
}
