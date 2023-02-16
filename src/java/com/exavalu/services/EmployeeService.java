/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exavalu.services;

import com.exavalu.models.Employee;
import com.exavalu.utils.JDBCConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.log4j.Logger;

/**
 *
 * @author Avijit Chattopadhyay
 */
public class EmployeeService {

    public static EmployeeService employeeService = null;
    private static final Logger logger = Logger.getLogger(EmployeeService.class);

    public static EmployeeService getInstance() {
        if (employeeService == null) {
            return new EmployeeService();
        } else {
            return employeeService;
        }
    }

    public static Employee getEmployee(int employeeId) {
        Employee emp = new Employee();
        try {
            Connection con = JDBCConnectionManager.getMySQLConnection();
            String sql = "Select * from employees e, departments d, roles r "
                    + "where e.departmentId=d.departmentId and e.roleId=r.roleId and e.employeeId =?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, employeeId);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                emp.setAddress(rs.getString("address"));
                emp.setEmployeeId(rs.getInt("employeeId"));
                emp.setFirstName(rs.getString("firstName"));
                emp.setLastName(rs.getString("lastName"));
                emp.setPhone(rs.getString("phone"));
                emp.setGender(rs.getString("gender"));
                emp.setAge(rs.getInt("age"));
                emp.setDepartmentId(rs.getInt("departmentId"));
                emp.setRoleId(rs.getInt("roleId"));
                emp.setBasicSalary(rs.getDouble("basicSalary"));
                emp.setCarAllowance(rs.getDouble("carAllowance"));
            }
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }
        return emp;
    }

    public static boolean deleteEmployee(int employeeId) {
        boolean result = false;
        try {
            Connection con = JDBCConnectionManager.getMySQLConnection();
            String sql = "UPDATE employees SET status=0 WHERE employeeId =?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, employeeId);
            int row = preparedStatement.executeUpdate();
            if (row == 1) {
                result = true;
            }
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }
        return result;
    }

    public static ArrayList getAllEmployees() {

        ArrayList empList = new ArrayList();
        try {
            Connection con = JDBCConnectionManager.getMySQLConnection();
            String sql = "Select * from employees e, departments d, roles r "
                    + "where e.departmentId=d.departmentId and e.roleId=r.roleId and e.status=1 "
                    + "ORDER By e.employeeId;";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Employee emp = new Employee();
                emp.setEmployeeId(rs.getInt("employeeId"));
                emp.setFirstName(rs.getString("firstName"));
                emp.setLastName(rs.getString("lastName"));
                emp.setPhone(rs.getString("phone"));
                emp.setGender(rs.getString("gender"));
                emp.setAge(rs.getInt("age"));
                emp.setAddress(rs.getString("address"));
                emp.setDepartmentName(rs.getString("departmentName"));
                emp.setRoleName(rs.getString("roleName"));
                emp.setBasicSalary(rs.getDouble("basicSalary"));
                emp.setCarAllowance(rs.getDouble("carAllowance"));

                empList.add(emp);

            }

        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }
        System.out.println("Number of employees = " + empList.size());
        return empList;
    }

    public static boolean createEmployee(Employee emp) {
        boolean result = false;

        try {
            Connection con = JDBCConnectionManager.getMySQLConnection();
            String sql = "INSERT INTO employees "
                    + "(firstName, lastName, phone, address, gender, age, basicSalary, carAllowance, departmentId, roleId)"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement preparedStatement = con.prepareStatement(sql);

            preparedStatement.setString(1, emp.getFirstName());
            preparedStatement.setString(2, emp.getLastName());
            preparedStatement.setString(3, emp.getPhone());
            preparedStatement.setString(4, emp.getAddress());
            preparedStatement.setString(5, emp.getGender());
            preparedStatement.setInt(6, emp.getAge());
            preparedStatement.setDouble(7, emp.getBasicSalary());
            preparedStatement.setDouble(8, emp.getCarAllowance());
            preparedStatement.setInt(9, emp.getDepartmentId());
            preparedStatement.setInt(10, emp.getRoleId());

            int row = preparedStatement.executeUpdate();
            if (row == 1) {
                result = true;
            }
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }
        return result;
    }

    public static boolean updateEmployee(Employee emp) {
        boolean result = false;

        try {
            Connection con = JDBCConnectionManager.getMySQLConnection();
            String sql = "UPDATE employeedb.employees "
                    + "SET firstName = ? , lastName = ? , phone = ? , address = ? , gender = ? , age = ? , basicSalary = ?, carAllowance = ?, departmentId = ?, roleId = ? WHERE employeeId = ?;";
            PreparedStatement preparedStatement = con.prepareStatement(sql);

            preparedStatement.setString(1, emp.getFirstName());
            preparedStatement.setString(2, emp.getLastName());
            preparedStatement.setString(3, emp.getPhone());
            preparedStatement.setString(4, emp.getAddress());
            preparedStatement.setString(5, emp.getGender());
            preparedStatement.setInt(6, emp.getAge());
            preparedStatement.setDouble(7, emp.getBasicSalary());
            preparedStatement.setDouble(8, emp.getCarAllowance());
            preparedStatement.setInt(9, emp.getDepartmentId());
            preparedStatement.setInt(10, emp.getRoleId());

            preparedStatement.setInt(11, emp.getEmployeeId());

            int row = preparedStatement.executeUpdate();
            if (row == 1) {
                result = true;
            }
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }
        return result;
    }

    public static ArrayList searchEmployee(Employee emp) {

        ArrayList empList = new ArrayList();
        try {
            Connection con = JDBCConnectionManager.getMySQLConnection();
            String sql = "SELECT e.*, d.departmentName, r.roleName FROM employees "
                    + "e JOIN departments d ON e.departmentId=d.departmentId "
                    + "JOIN roles r ON e.roleId=r.roleId WHERE e.firstName LIKE ? and e.lastName LIKE ? and e.gender LIKE ? and d.departmentName LIKE ? and r.roleName LIKE ? ORDER By e.employeeId;";
            PreparedStatement preparedStatement = con.prepareStatement(sql);

            preparedStatement.setString(1, emp.getFirstName() + "%");
            preparedStatement.setString(2, emp.getLastName() + "%");
            preparedStatement.setString(3, emp.getGender() + "%");
            preparedStatement.setString(4, emp.getDepartmentName() + "%");
            preparedStatement.setString(5, emp.getRoleName() + "%");

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Employee dbEmp = new Employee();

                dbEmp.setEmployeeId(rs.getInt("employeeId"));
                dbEmp.setFirstName(rs.getString("firstName"));
                dbEmp.setLastName(rs.getString("lastName"));
                dbEmp.setPhone(rs.getString("phone"));
                dbEmp.setGender(rs.getString("gender"));
                dbEmp.setAddress(rs.getString("address"));
                dbEmp.setAge(rs.getInt("age"));
                dbEmp.setDepartmentName(rs.getString("departmentName"));
                dbEmp.setRoleName(rs.getString("roleName"));
                dbEmp.setBasicSalary(rs.getDouble("basicSalary"));
                dbEmp.setCarAllowance(rs.getDouble("carAllowance"));

                empList.add(dbEmp);

            }

        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }
//        System.out.println("Number of employees = " + empList.size());
        return empList;
    }

    public static ArrayList getLimitedEmployee(int limit, int page) {
        ArrayList empList = new ArrayList();
        try {
            Connection con = JDBCConnectionManager.getMySQLConnection();
            String sql = "SELECT e.*, d.departmentName, r.roleName FROM employees "
                    + "e JOIN departments d ON e.departmentId=d.departmentId "
                    + "JOIN roles r ON e.roleId=r.roleId ORDER BY e.employeeId LIMIT ? OFFSET ?;";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, limit);
            preparedStatement.setInt(2, (page - 1) * limit);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Employee emp = new Employee();
                emp.setAddress(rs.getString("address"));
                emp.setEmployeeId(rs.getInt("employeeId"));
                emp.setFirstName(rs.getString("firstName"));
                emp.setLastName(rs.getString("lastName"));
                emp.setPhone(rs.getString("phone"));
                emp.setGender(rs.getString("gender"));
                emp.setAge(rs.getInt("age"));
                emp.setDepartmentName(rs.getString("departmentName"));
                emp.setRoleName(rs.getString("roleName"));
                emp.setBasicSalary(rs.getDouble("basicSalary"));
                emp.setCarAllowance(rs.getDouble("carAllowance"));

                empList.add(emp);

            }

        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }
        return empList;
    }

}
