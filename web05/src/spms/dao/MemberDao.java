package spms.dao;

import spms.vo.Member;

import java.util.List;

/**
 * Created by 1001235 on 15. 5. 27..
 */
public interface MemberDao {
    List<Member> selectList() throws Exception;
    int insert(Member member) throws Exception;
    int delete(int no) throws Exception;
    Member selectOne(int no) throws Exception;
    int update(Member member) throws Exception;
    Member exist(String email, String password) throws Exception;
}
