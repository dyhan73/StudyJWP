package spms.controls;

import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.MemberDao;

import java.util.Map;

/**
 * Created by 1001235 on 15. 5. 26..
 */
@Component("/member/delete.do")
public class MemberDeleteController implements Controller, DataBinding {
    MemberDao memberDao;

    public MemberDeleteController setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
        return this;
    }

    @Override
    public String execute(Map<String, Object> model) throws Exception {
        memberDao.delete((Integer) model.get("no"));

        return "redirect:list.do";
    }

    @Override
    public Object[] getDataBinders() {
        return new Object[] {
                "no", Integer.class
        };
    }
}
