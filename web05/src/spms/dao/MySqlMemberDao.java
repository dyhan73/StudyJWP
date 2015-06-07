package spms.dao;

import spms.annotation.Component;
import spms.vo.Member;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1001235 on 15. 5. 24..
 */
@Component("memberDao")
public class MySqlMemberDao implements MemberDao {
    DataSource ds;

    public void setDataSource(DataSource ds) {
        this.ds = ds;
    }

    public List<Member> selectList() throws Exception {
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            connection = ds.getConnection();
            stmt = connection.createStatement();
            rs = stmt.executeQuery(
                    "SELECT mno, mname, email, cre_date, mod_date " +
                            "FROM members " +
                            "ORDER BY mno ASC");

            ArrayList<Member> members = new ArrayList<Member>();

            while (rs.next()) {
                members.add(new Member()
                                .setNo(rs.getInt("mno"))
                                .setName(rs.getString("mname"))
                        .setEmail(rs.getString("email"))
                        .setCreatedDate(rs.getDate("cre_date"))
                        .setModifiedDate(rs.getDate("mod_date"))
                );
            }

            return members;
        } catch (Exception e) {
            System.out.println(e.toString());
            throw e;
        } finally {
            try { if (rs != null) rs.close();} catch(Exception e) {}
            try { if (stmt != null) stmt.close();} catch(Exception e) {}
            try { if (connection != null) connection.close();} catch(Exception e) {}
        }
    }

    public int insert(Member member) throws Exception {
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = ds.getConnection();
            stmt = connection.prepareStatement(
                    "INSERT INTO members(email, pwd,mname, cre_date, mod_date)" +
                            " VALUES (?, ?, ?, now(), now())");
            stmt.setString(1, member.getEmail());
            stmt.setString(2, member.getPassword());
            stmt.setString(3, member.getName());
            stmt.executeUpdate();
            return stmt.getUpdateCount();

        } catch (Exception e) {
            System.out.println(e.toString());
            throw e;
        } finally {
            try { if (stmt != null) stmt.close();} catch(Exception e) {}
            try { if (connection != null) connection.close();} catch(Exception e) {}
        }
    }

    public int delete(int no) throws Exception {
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = ds.getConnection();
            stmt = connection.prepareStatement("DELETE FROM members WHERE mno=?");
            stmt.setInt(1, no);
            stmt.executeUpdate();
            return stmt.getUpdateCount();

        } catch (Exception e) {
            System.out.println(e.toString());
            throw e;
        } finally {
            try { if (stmt != null) stmt.close();} catch(Exception e) {}
            try { if (connection != null) connection.close();} catch(Exception e) {}
        }

    }

    public Member selectOne(int no) throws Exception {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = ds.getConnection();
            stmt = connection.prepareStatement(
                    "SELECT mno, mname, email, pwd, cre_date, mod_date " +
                    "FROM members " +
                    "WHERE mno=?");
            stmt.setInt(1, no);
            rs = stmt.executeQuery();

            rs.next();

            Member member = new Member()
                                .setNo(rs.getInt("mno"))
                                .setName(rs.getString("mname"))
                                .setEmail(rs.getString("email"))
                                .setPassword(rs.getString("pwd"))
                                .setCreatedDate(rs.getDate("cre_date"))
                                .setModifiedDate(rs.getDate("mod_date"));
            return member;
        } catch (Exception e) {
            System.out.println(e.toString());
            throw e;
        } finally {
            try { if (rs != null) rs.close();} catch(Exception e) {}
            try { if (stmt != null) stmt.close();} catch(Exception e) {}
            try { if (connection != null) connection.close();} catch(Exception e) {}
        }
    }

    public int update(Member member) throws Exception {
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = ds.getConnection();
            stmt = connection.prepareStatement(
                    "UPDATE members " +
                            "SET email=?, pwd=?, mname=?, mod_date=now() " +
                            "WHERE mno=?" );
            stmt.setString(1, member.getEmail());
            stmt.setString(2, member.getPassword());
            stmt.setString(3, member.getName());
            stmt.setInt(4, member.getNo());
            stmt.executeUpdate();
            return stmt.getUpdateCount();

        } catch (Exception e) {
            System.out.println(e.toString());
            throw e;
        } finally {
            try { if (stmt != null) stmt.close();} catch(Exception e) {}
            try { if (connection != null) connection.close();} catch(Exception e) {}
        }
    }

    public Member exist(String email, String password) throws Exception {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = ds.getConnection();
            stmt = connection.prepareStatement(
                    "SELECT mno, mname, email, cre_date, mod_date " +
                            "FROM members " +
                            "WHERE email=? AND pwd=? ");
            stmt.setString(1, email);
            stmt.setString(2, password);
            rs = stmt.executeQuery();

            rs.next();

            Member member = new Member()
                    .setNo(rs.getInt("mno"))
                    .setName(rs.getString("mname"))
                    .setEmail(rs.getString("email"))
                    .setCreatedDate(rs.getDate("cre_date"))
                    .setModifiedDate(rs.getDate("mod_date"));
            return member;
        } catch (Exception e) {
            System.out.println(e.toString());
            throw e;
        } finally {
            try { if (rs != null) rs.close();} catch(Exception e) {}
            try { if (stmt != null) stmt.close();} catch(Exception e) {}
            try { if (connection != null) connection.close();} catch(Exception e) {}
        }
    }
}
