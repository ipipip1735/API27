package mine.hilt.data;

/**
 * Created by Administrator on 2020/12/27.
 */
public class Employee {
    Company company;

    public Employee(Company company) {
        System.out.println("~~Employeer.Employeer~~");
        System.out.println("company = " + company);
        this.company = company;
    }
}
