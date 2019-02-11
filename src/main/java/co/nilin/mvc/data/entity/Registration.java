package co.nilin.mvc.data.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "REGISTERS")
public class Registration implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long regId;
    @Column
    String receptor;
    @Column
    String message;
    @Column
    Date date;

    @Column
    Boolean active;
    @ManyToOne
    @JoinColumn(name = "USER_ID",nullable = false)
    User user;

    public Registration() {
        super();
    }

    public Long getRegId() {
        return regId;
    }

    public void setRegId(Long regId) {
        this.regId = regId;
    }

    public String getReceptor() {
        return receptor;
    }

    public void setReceptor(String receptor) {
        this.receptor = receptor;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }


    @Override
    public String toString() {
        return "Registration{" +
                "regId=" + regId +
                ", receptor='" + receptor + '\'' +
                ", message='" + message + '\'' +
                ", user=" + user +
                '}';
    }
}
