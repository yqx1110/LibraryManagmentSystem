package yqx1110.LibraryManagement.entity;

import java.util.Date;

public class Reader extends User{
    private String sex;
    private Date dateOfBirth;

    public Reader(int uuid, String name, String sex, Date dateOfBirth) {
        super(uuid, name);
        this.sex = sex;
        this.dateOfBirth = dateOfBirth;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
