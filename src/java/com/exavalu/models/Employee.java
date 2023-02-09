/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exavalu.models;

import com.exavalu.services.DepartmentService;
import com.exavalu.services.EmployeeService;
import com.exavalu.services.GeoMapService;
import com.exavalu.services.RoleService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import org.apache.struts2.dispatcher.ApplicationMap;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author Avijit Chattopadhyay
 */
public class Employee extends ActionSupport implements ApplicationAware, SessionAware, Serializable {

    private int employeeId;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
    private String gender;
    private int age;
    private int departmentId;
    private int roleId;
    private double basicSalary;
    private double carAllowance;
    private String departmentName;
    private String roleName;

    private SessionMap<String, Object> sessionMap = (SessionMap) ActionContext.getContext().getSession();

    private ApplicationMap map = (ApplicationMap) ActionContext.getContext().getApplication();

    @Override
    public void setApplication(Map<String, Object> application) {
        map = (ApplicationMap) application;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        sessionMap = (SessionMap) session;
    }

    public String createEmployee() throws Exception {
        String result = "FAILURE";

        if (this.getFirstName() == null) {
            ArrayList deptList = DepartmentService.getAllDepartment();
            ArrayList roleList = RoleService.getAllRoles();
            sessionMap.put("deptList", deptList);
            sessionMap.put("roleList", roleList);
            ArrayList countryList = GeoMapService.getAllCountries();
//            ArrayList stateList = GeoMapService.getAllStates();
//            ArrayList districtList = GeoMapService.getAllDistricts();
            sessionMap.put("countryList", countryList);
//            sessionMap.put("stateList", stateList);
//            sessionMap.put("districtList", districtList);
            return result;
        }

        boolean success = EmployeeService.createEmployee(this);

        if (success) {
            ArrayList empList = EmployeeService.getAllEmployees();
            sessionMap.put("empList", empList);
            result = "SUCCESS";
            System.out.println("Returning Success from createEmployee method");
        } else {
            System.out.println("Returning Failure from createEmployee method");
        }

        return result;
    }

    public String preEditEmployee() throws Exception {
        String result = "SUCCESS";

        Employee emp = EmployeeService.getEmployee(this.employeeId);
        ArrayList deptList = DepartmentService.getAllDepartment();
        ArrayList roleList = RoleService.getAllRoles();

        sessionMap.put("emp", emp);
        sessionMap.put("deptList", deptList);
        sessionMap.put("roleList", roleList);

        return result;
    }

    public String editEmployee() throws Exception {
        String result = "FAILURE";

        boolean success = EmployeeService.updateEmployee(this);

        if (success) {
            ArrayList empList = EmployeeService.getAllEmployees();
            sessionMap.put("empList", empList);
            result = "SUCCESS";
            System.out.println("Returning Success from editEmployee method");
        } else {
            System.out.println("Returning Failure from editEmployee method");
        }

        return result;
    }

    public String searchEmployee() throws Exception {
        String result = "SUCCESS";

        ArrayList empList = EmployeeService.searchEmployee(this);
        ArrayList deptList = DepartmentService.getAllDepartment();
        ArrayList roleList = RoleService.getAllRoles();

        sessionMap.put("empList", empList);
        sessionMap.put("deptList", deptList);
        sessionMap.put("roleList", roleList);

        return result;
    }

    public String deleteEmployee() throws Exception {
        String result = "SUCCESS";

        boolean success = EmployeeService.deleteEmployee(this.employeeId);
        if (success) {
            ArrayList empList = EmployeeService.getAllEmployees();
            sessionMap.put("empList", empList);
            result = "SUCCESS";
            System.out.println("Returning Success from deleteEmployee method");
        } else {
            System.out.println("Returning Failure from deleteEmployee method");
        }

        return result;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public double getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(double basicSalary) {
        this.basicSalary = basicSalary;
    }

    public double getCarAllowance() {
        return carAllowance;
    }

    public void setCarAllowance(double carAllowance) {
        this.carAllowance = carAllowance;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

}
