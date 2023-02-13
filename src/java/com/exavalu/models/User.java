/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exavalu.models;

import com.exavalu.services.EmployeeService;
import com.exavalu.services.AuthService;
import com.exavalu.services.GeoMapService;
import com.exavalu.services.JSONService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.struts2.dispatcher.ApplicationMap;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author kumar
 */
public class User extends ActionSupport implements ApplicationAware, SessionAware, Serializable {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String countryCode;
    private String stateCode;
    private String districtCode;
    private List<JSONData> jsonData;

    public List<JSONData> getJsonData() {
        return jsonData;
    }

    public void setJsonData(List<JSONData> jsonData) {
        this.jsonData = jsonData;
    }

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

    public String doPreSignUp() throws Exception {
        String result = "SUCCESS";

        ArrayList countryList = GeoMapService.getAllCountries();
        ArrayList stateList = null;
        ArrayList districtList = null;

        sessionMap.put("countryList", countryList);

        if (this.countryCode != null) {
            stateList = GeoMapService.getStatesByCountryCode(this.countryCode);
            sessionMap.put("stateList", stateList);
            sessionMap.put("user", this);
            result = "STATE";
        }

        if (this.stateCode != null) {
            districtList = GeoMapService.getDistrictsByStateCode(this.stateCode);
            sessionMap.put("districtList", districtList);
            sessionMap.put("user", this);
            result = "DISTRICT";
        }

        return result;
    }

    public String doSignUp() throws Exception {
        String result = "FAILURE";

        boolean success = AuthService.getInstance().doSignUp(this);

        if (success) {
            System.out.println("Returning Success from doSignUp method");
            result = "SUCCESS";
        } else {
            System.out.println("Returning Failure from doSignUp method");
        }

        return result;
    }

    public String doLogin() throws Exception {
        String result = "FAILURE";

        boolean success = AuthService.getInstance().doLogin(this);

        if (success) {
            System.out.println("Returning Success from doLogin method");
            sessionMap.put("loggedIn", this);

            ArrayList empList = EmployeeService.getAllEmployees();
            sessionMap.put("empList", empList);

            result = "SUCCESS";

        } else {
            System.out.println("Returning Failure from doLogin method");
        }

        return result;
    }

    public String doLogOut() throws Exception {
        String result = "SUCCESS";

        sessionMap.clear();

        return result;
    }

    public String doSendDataToDB() {
        boolean success = JSONService.sendDataToDB(this.getJsonData());
        if (success) {
            System.out.println("Yayy! Data successfully inserted into DB");
            sessionMap.put("msg", "Data successfully entered into DB");
            return "SUCCESS";
        } else {
            System.err.println("Oops! Something went wrong during insertion of data into DB. Please try again!");
            sessionMap.put("msg", "Something went wrong!");
            return "FAILURE";
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

}
