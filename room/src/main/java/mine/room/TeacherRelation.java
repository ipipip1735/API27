package mine.room;

import android.arch.persistence.room.Relation;

import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2018/9/11.
 */
public class TeacherRelation {
    private int tId;
    @Relation(parentColumn = "tId", entityColumn = "tId")
    private List<Teacher> teachers;

//        private String tName;
//    private int tAge;
//        private int tSalary;

    public TeacherRelation() {
//        this.tName = "chris" + new Random().nextInt(1);
//        this.tAge = new Random().nextInt(100);
//        this.tSalary = new Random().nextInt(5000);
    }


    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }


    public void setTId(int tId) {
        this.tId = tId;
    }


//    public void setTName(String tName) {
//        this.tName = tName;
//    }
//
//    public void setTAge(int tAge) {
//        this.tAge = tAge;
//    }

    //
//    public void setTSalary(int tSalary) {
//        this.tSalary = tSalary;
//    }
//
    public int getTId() {
        return tId;
    }

    //
//    public String getTName() {
//        return tName;
//    }
//
//    public int getTAge() {
//        return tAge;
//    }
//
//    public int getTSalary() {
//        return tSalary;
//    }

//    @Override
//    public String toString() {
//        return "TeacherRelation{" +
//                "tId=" + tId +
//                ", tName='" + tName + '\'' +
//                ", tAge=" + tAge +
//                ", tSalary=" + tSalary +
//                '}';
//    }

}
