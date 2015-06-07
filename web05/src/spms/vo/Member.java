package spms.vo;

import java.util.Date;

/**
 * Created by 1001235 on 15. 5. 21..
 */
public class Member {
    protected int no;
    protected String name;
    protected String email;
    protected String password;
    protected Date createdDate;
    protected Date modifiedDate;

    public int getNo() {
        return no;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public Member setNo(int no) {
        this.no = no;
        return this;
    }

    public Member setName(String name) {
        this.name = name;
        return this;
    }

    public Member setEmail(String email) {
        this.email = email;
        return this;
    }

    public Member setPassword(String password) {
        this.password = password;
        return this;
    }

    public Member setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public Member setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
        return this;
    }

}
