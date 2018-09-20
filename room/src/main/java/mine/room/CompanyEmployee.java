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
    @Relation(parentColumn = "company_name", entityColumn = "cname")
    private List<Employee> employees;

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

    @Override
    public String toString() {
        return "CompanyEmployee{" +
                "company_name='" + company_name + '\'' +
                ", employees=" + employees +
                '}';
    }
}
