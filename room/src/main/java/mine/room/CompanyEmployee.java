package mine.room;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Relation;

import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2018/9/20.
 */
public class CompanyEmployee {
    private String company_name;
    public String company_id;
    @Relation(parentColumn = "company_name", entityColumn = "cname")
    private List<Employee> employees;
    @Relation(parentColumn = "company_name", entityColumn = "cname",
            entity = Employee.class, projection = "employee_name")
    public List<String> employeeAparts;

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<String> getEmployeeAparts() {
        return employeeAparts;
    }

    public void setEmployeeAparts(List<String> employeeAparts) {
        this.employeeAparts = employeeAparts;
    }

    @Override
    public String toString() {
        return "CompanyEmployee{" +
                "company_name='" + company_name + '\'' +
                ", employees=" + employees +
                ", employeeAparts=" + employeeAparts +
                '}';
    }
}

class EmployeeApart{
    private int employee_id;
    private String cname;

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    @Override
    public String toString() {
        return "EmployeeApart{" +
                "employee_id=" + employee_id +
                ", cname='" + cname + '\'' +
                '}';
    }
}
