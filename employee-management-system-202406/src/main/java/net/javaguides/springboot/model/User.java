package net.javaguides.springboot.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import java.util.Collection;
import java.util.Set;

@Entity
@Table(name =  "user", uniqueConstraints = @UniqueConstraint(columnNames = "idNumber"))
public class User {
    
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String idNumber;
    
    @Column(nullable = false)
    private String password;
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
               joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;
    
    public User() {
        
    }
    
    public User(String idNumber, String password, Set<Role> roles) {
        super();
        this.idNumber = idNumber;
        this.password = password;
        this.roles = roles;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getIdNumber() {
        return idNumber;
    }
    
    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public Collection<Role> getRoles() {
        return roles;
    }
    
    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
}
