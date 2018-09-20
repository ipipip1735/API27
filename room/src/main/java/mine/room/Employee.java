package mine.room;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Random;

/**
 * Created by Administrator on 2018/9/20.
 */
@Entity
public class Employee {

    @PrimaryKey(autoGenerate = true)
    private int employee_id;
    private String employee_name;
    private String cname;

    public Employee() {
        employee_name = "employee_name" + new Random().nextInt(100);;
        cname = "cname" + new Random().nextInt(100);;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employee_id=" + employee_id +
                ", employee_name='" + employee_name + '\'' +
                ", cname='" + cname + '\'' +
                '}';
    }
}
