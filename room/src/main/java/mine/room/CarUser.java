package mine.room;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Administrator on 2018/9/18.
 */
@Entity(tableName = "Customer",
        indices = {
            @Index(name = "indexOne", value = {"first_name", "last_name"}),
            @Index(name = "indexTwo", value = {"first_name", "age"})
        },
        foreignKeys = {
            @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "user_id"),
            @ForeignKey(entity = Car.class, parentColumns = "cid", childColumns = "car_id")
        }
)
public class CarUser {

    @PrimaryKey(autoGenerate = true)
    private int cid;

    @ColumnInfo(name = "car_id", index = true)
    private int carId;

    @ColumnInfo(name = "user_id", index = true)
    private int userId;


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


    public CarUser(String firstName, String lastName, int age, int carId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.carId = carId;
    }




    public void setAge(int age) {
        this.age = age;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getCid() {
        return cid;
    }

    public int getAge() {
        return age;
    }

    public void setCid(int cid) {
        this.cid = cid;
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

}
