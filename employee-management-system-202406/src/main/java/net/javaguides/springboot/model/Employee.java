package net.javaguides.springboot.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "employees")
public class Employee {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 社員ID (Employee ID)
    
    @Column(name = "joining_date", nullable = false)
    private LocalDate joiningDate; // 入社年月日 (Date of Joining)
    
    @Column(name = "name", nullable = false)
    private String name; // 氏名 (Name)
    
    @Column(name = "gender", nullable = false)
    private String gender; // 性別 (Gender)
    
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate; // 生年月日 (Date of Birth)
    
    @Column(name = "department", nullable = false)
    private String department; // 所属 (Department)
    
    @Column(name = "email", nullable = false)
    private String email; // メールアドレス (Email Address)
    
    private int age;
    
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void calculateAge() {
        if (this.birthDate != null) {
            this.age = Period.between(this.birthDate, LocalDate.now()).getYears();
        } else {
            this.age = 0;
        }
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public LocalDate getJoiningDate() {
        return joiningDate;
    }
    
    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }
    
    public String getFormattedJoiningDate() {
        if (this.joiningDate != null) {
            return this.joiningDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
        return "";
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getGender() {
        return gender;
    }
    
    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public LocalDate getBirthDate() {
        return birthDate;
    }
    
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        calculateAge();
    }
    
    public String getDepartment() {
        return department;
    }
    
    public void setDepartment(String department) {
        this.department = department;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
}
