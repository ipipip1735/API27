package mine.databinding.data;

/**
 * Created by Administrator on 2020/11/4.
 */
public class Fish{
    String name;
    Integer age;

    public Fish(String name) {
        System.out.println("--- " + getClass().getSimpleName() + ".Constructor ---");
        this.name = name;
        age = 10;
    }

    public void setName(String name) {
        System.out.println("~~~ " + getClass().getSimpleName() + ".setName ~~~");
        this.name = name;
    }

    public void setAge(Integer age) {
        System.out.println("~~~ " + getClass().getSimpleName() + ".setAge ~~~");
        this.age = age;
    }

    public String getName() {
        System.out.println("~~~ " + getClass().getSimpleName() + ".getName ~~~");
        return name;
    }

    public Integer getAge() {
        System.out.println("~~~ " + getClass().getSimpleName() + ".getAge ~~~");
        return age;
    }

}
