package com.winUtils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.Serializable;

class Employee implements Serializable {


    String name;
    String qualification;

    public Employee(String name, String qualification){
        this.name=name;
        this.qualification=qualification;
    }
}

class EmployeeSalary{

    String salary;
    String curreny;

    public EmployeeSalary(String name, String qualification){
        this.salary=salary;
        this.curreny=curreny;
    }
}
 class EmployeeDetails{

    String companyName;
    String location;
    EmployeeSalary employeeSalary;
    Employee employee;

    public EmployeeDetails(String companyName, String location, EmployeeSalary employeeSalary,Employee employee){
        this.employeeSalary=employeeSalary;
        this.location=location;
        this.employee=employee;
        this.companyName=companyName;
    }
}

public class EmployeeData{

    public static void main(String[] args) {
        String compName="NCR";
        String location="Hyd";
        Employee emp=new Employee("raju", "btech");

        Gson gson = new Gson();
        String json = gson.toJson(emp);
//        JsonObject jsonObject=new JsonObject(json.t);
//        jsonObject.add("empDetails", json);
        JsonElement element = gson.fromJson(emp.toString(), JsonElement.class);
        JsonObject jsonObject=new JsonObject();
        jsonObject.add("empDetails", element);
        System.out.println(jsonObject.toString());
        EmployeeSalary employeeSalary=new EmployeeSalary("1000", "USD");
        EmployeeDetails empData=new EmployeeDetails(compName, location, employeeSalary, emp);
        System.out.println(empData);



    }
}
