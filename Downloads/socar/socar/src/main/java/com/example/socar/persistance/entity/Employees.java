package com.example.socar.persistance.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "employees")
public class Employees {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long employeeId;


    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Authorities",
            joinColumns = { @JoinColumn(name = "employeeId") },
            inverseJoinColumns = { @JoinColumn(name = "roleId") }
    )
    Set<Roles> roles = new HashSet<>();

    private String fullname;

    private String mail;

    private String password;

    @OneToMany(mappedBy="employees")
    private Set<Permission> permission;

    public Employees() {
    }

    public Employees(Set<Roles> roles, String fullname, String mail, String password, Set<Permission> permission) {
        this.roles = roles;
        this.fullname = fullname;
        this.mail = mail;
        this.password = password;
        this.permission = permission;
    }
    public Employees(Long employeeId, String fullname, String mail, String password) {
        this.employeeId = employeeId;
        this.fullname = fullname;
        this.mail = mail;
        this.password = password;
    }

    public Long getId() {

        return employeeId;
    }

    public Set<Roles> getRoles() {
        return roles;
    }
//


    public void setRoles(Set<Roles> roles) {
        this.roles = roles;
    }

    public Set<Permission> getPermission() {
        return permission;
    }

    public void setPermission(Set<Permission> permission) {
        this.permission = permission;
    }

    public void setId(Long id) {
        this.employeeId = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Employees{" +
                "employeeId=" + employeeId +
                ", roles=" + roles +
                ", fullname='" + fullname + '\'' +
                ", mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                ", permission=" + permission +
                '}';
    }
}
