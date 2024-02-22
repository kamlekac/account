package com.learning;

public class EmployeeDetails {
   static int employeeSalary=1000;
   static int employeeIncentive=200;
    int employeeAge=24;
   static  String employeeName="Ramu";
    String employeeId;

   static int totalSalary(){
      int salary=  employeeSalary+employeeIncentive;
      return salary;
    }

}
