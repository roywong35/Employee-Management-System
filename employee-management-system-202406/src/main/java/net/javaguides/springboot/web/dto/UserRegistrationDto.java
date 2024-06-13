package net.javaguides.springboot.web.dto;

public class UserRegistrationDto {
    private String idNumber;
    private String password;
    
    public UserRegistrationDto() {
        
    }
    
    public UserRegistrationDto(String idNumber, String password) {
        super();
        this.idNumber = idNumber;
        this.password = password;
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
}
