package spms.controls;

import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.MemberDao;
import spms.dao.ProjectDao;

import java.util.Map;

/**
 * Created by 1001235 on 15. 5. 31..
 */
@Component("/project/delete.do")
public class ProjectDeleteController implements Controller, DataBinding {
    ProjectDao projectDao;

    public ProjectDeleteController setProjectDao(ProjectDao projectDao) {
        this.projectDao = projectDao;
        return this;
    }

    @Override
    public String execute(Map<String, Object> model) throws Exception {
        projectDao.delete((Integer) model.get("no"));

        return "redirect:list.do";
    }

    @Override
    public Object[] getDataBinders() {
        return new Object[] {
                "no", Integer.class
        };
    }
}
