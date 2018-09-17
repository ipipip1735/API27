package mine.room;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Administrator on 2018/9/6.
 */
//@Entity(tableName = "User",
////        primaryKeys= "uid",
////        indices = {
////            @Index(name = "indexOne", value = {"first_name", "last_name"}),
////            @Index(name = "indexTwo", value = {"first_name", "age"})
////        },
//        foreignKeys = {
////            @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "user_id"),
//            @ForeignKey(entity = Car.class, parentColumns = "cid", childColumns = "car_id")
//        }
//)
@Entity(tableName = "User")
public class User {
    public User(String firstName, String lastName, int age, int carId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.carId = carId;
    }

    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "car_id", index = true)
    private int carId;


    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    @ColumnInfo(name = "first_name", collate = ColumnInfo.BINARY)
    private String firstName;

    @ColumnInfo(name = "last_name", typeAffinity = ColumnInfo.TEXT)
    private String lastName;

    private int age;

    @Embedded(prefix = "user_")
    private Address address;





    public void setAge(int age) {
        this.age = age;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getUid() {
        return uid;
    }

    public int getAge() {
        return age;
    }

    public void setUid(int uid) {
        this.uid = uid;
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

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", carId=" + carId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", address=" + address +
                '}';
    }
}
