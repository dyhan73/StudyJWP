package spms.dao;

import spms.vo.Project;

import java.util.HashMap;
import java.util.List;

/**
 * Created by 1001235 on 15. 5. 30..
 */
public interface ProjectDao {
    List<Project> selectList(HashMap<String, Object> paramMap) throws Exception;
    int insert(Project project) throws Exception;
    Project selectOne(int no) throws Exception;
    int update(Project project) throws Exception;
    int delete(int no) throws Exception;
}
