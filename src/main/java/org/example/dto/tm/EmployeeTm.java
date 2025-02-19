package org.example.dto.tm;

public class EmployeeTm {

    private String employeeId;
    private String name;
    private String role;
    private String contact;

    public EmployeeTm(String employeeId, String name, String role, String contact) {
        this.employeeId = employeeId;
        this.name = name;
        this.role = role;
        this.contact = contact;
    }
    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
