package spms.controls;

import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.MemberDao;
import spms.dao.ProjectDao;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 1001235 on 15. 5. 26..
 */
@Component("/project/list.do")
public class ProjectListController implements Controller, DataBinding {
    ProjectDao projectDao;

    public ProjectListController setProjectDao(ProjectDao projectDao) {
        this.projectDao = projectDao;
        return this;
    }

    @Override
    public String execute(Map<String, Object> model) throws Exception {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("orderCond", model.get("orderCond"));
        model.put("projects", projectDao.selectList(paramMap));
        return "/project/ProjectList.jsp";
    }

    @Override
    public Object[] getDataBinders() {
        return new Object[] {
                "orderCond", String.class
        };
    }
}
