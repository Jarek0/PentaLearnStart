
package pl.pollub.cs.pentalearn.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "users")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min=6,max=64)
    private String username;

    @NotNull
    @Size(min=8,max=64)
    private String password;

    @NotNull
    @Size(min=5,max=255)
    private String email;

    @NotNull
    private boolean enabled;

    @NotNull
    private boolean online;

    @NotNull
    private boolean banned;

    @Transient
    private String passwordConfirm;

    @OneToOne(mappedBy = "user")
    private VerificationToken token;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    public User(){

    }

    public User(String username,String password,String passwordConfirm,String email){
        this.username=username;
        this.password=password;
        this.passwordConfirm=passwordConfirm;
        this.email=email;

    }
    public User(String username, String password, String passwordConfirm, String email, boolean enabled, boolean online, boolean banned, VerificationToken token, List<Role> roles){
        this.username=username;
        this.password=password;
        this.passwordConfirm=passwordConfirm;
        this.email=email;
        this.enabled=enabled;
        this.online=online;
        this.banned=banned;
        this.token=token;
        this.roles=roles;
        
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    
    public boolean getOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }
    
    public boolean getBanned() {
        return banned;
    }
    
    public void setBanned(boolean banned) {
        
        this.banned = banned;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    @JsonIgnore
    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @JsonIgnore
    public VerificationToken getToken() {
        return token;
    }

    public void setToken(VerificationToken token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", username=" + username +
                ", email=" + email +
                ", enabled=" + enabled +
                ", online=" + online +
                ", banned=" + banned +
                ", token=" + token.getToken() + '\'' +
                '}';
    }
}
