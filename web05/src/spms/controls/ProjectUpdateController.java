package spms.controls;

import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.MemberDao;
import spms.dao.ProjectDao;
import spms.vo.Member;
import spms.vo.Project;

import java.util.Map;

/**
 * Created by 1001235 on 15. 5. 26..
 */
@Component("/project/update.do")
public class ProjectUpdateController implements Controller, DataBinding {
    ProjectDao projectDao;

    public ProjectUpdateController setProjectDao(ProjectDao projectDao) {
        this.projectDao = projectDao;
        return this;
    }

    @Override
    public String execute(Map<String, Object> model) throws Exception {
        Project project = (Project)model.get("project");
        if (project.getTitle() == null) {
            project = projectDao.selectOne((Integer)model.get("no"));
            model.put("project", project);
            return "/project/ProjectUpdateForm.jsp";
        } else {
            projectDao.update(project);

            return "redirect:list.do";
        }
    }

    @Override
    public Object[] getDataBinders() {
        return new Object[] {
                "no", Integer.class,
                "project", Project.class
        };
    }
}
