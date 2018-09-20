package mine.room;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Random;

/**
 * Created by Administrator on 2018/9/20.
 */
@Entity
public class Company {

    @PrimaryKey(autoGenerate = true)
    private int company_id;
    private String company_name;

    public Company() {
        this.company_name = "company" + new Random().nextInt(100);
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    @Override
    public String toString() {
        return "Company{" +
                "company_id=" + company_id +
                ", company_name='" + company_name + '\'' +
                '}';
    }
}
