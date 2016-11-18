/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pollub.cs.pentalearn.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "vtoken")
public class VerificationToken {
    private static final int EXPIRATION = 60 * 24;
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min=1,max=64)
    private String token;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    @NotNull
    private Long date;

    @NotNull
    private boolean verified;

    public VerificationToken() {

    }
    public VerificationToken(String token, User user) {
        super();
        this.token = token;
        this.user = user;
        this.date = calculateExpiryDate(EXPIRATION).getTime();
        this.verified = false;
    }
    private Date calculateExpiryDate(int expiryTimeInMinutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }

    public Long getId()
    {
        return id;
    }
    public void setId(Long id)
    {
        this.id=id;
    }
    @JsonIgnore
    public User getUser()
    {
        return user;
    }
    public void setUser(User user)
    {
        this.user=user;
    }
    public String getToken()
    {
        return token;
    }
    public void setToken(String token)
    {
        this.token=token;
    }
    public Long getDate()
    {
        return date;
    }
    public void setDate(Long date)
    {
        this.date=date;
    }
    public boolean getVerified()
    {
        return verified;
    }
    public void setVerified(boolean verified)
    {
        this.verified=verified;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", token=" + token +
                ", date=" + date +
                ", verified=" + verified + '\'' +
                '}';
    }
}
