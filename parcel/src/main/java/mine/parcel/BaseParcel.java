package mine.parcel;

import android.os.Parcel;

/**
 * Created by Administrator on 2017/4/21.
 */

public class BaseParcel {

    Parcel mBaseParcel = Parcel.obtain();

    public BaseParcel() {
        System.out.println("my BaseParcel");
    }

    public void forValue() {

        //读同一数据类型
//        status();
//        mBaseParcel.writeInt(115);
//        status();
//        mBaseParcel.writeInt(1155);
//        status();
//        mBaseParcel.setDataPosition(0);
//        System.out.println(mBaseParcel.readInt());
//        status();
//        System.out.println(mBaseParcel.readInt());
//        status();

        //读不同数据类型
        status();
        mBaseParcel.writeInt(115);
        status();
        mBaseParcel.writeDouble(1155d);
        status();
        mBaseParcel.setDataPosition(0);
        System.out.println(mBaseParcel.readInt());
        status();
        System.out.println(mBaseParcel.readDouble());
        status();





    }

    private void status() {
        System.out.println("dataCapacity is " + mBaseParcel.dataCapacity());
        System.out.println("dataPosition is " + mBaseParcel.dataPosition());
        System.out.println("dataAvail is " + mBaseParcel.dataAvail());
        System.out.println("dataSize is " + mBaseParcel.dataSize());
        System.out.println("-------");
    }

    public void forArray() {
        mBaseParcel.writeInt(115);
        mBaseParcel.writeInt(1155);

        mBaseParcel.setDataPosition(0);
        System.out.println( mBaseParcel.readInt());
        mBaseParcel.setDataPosition(4);
        System.out.println( mBaseParcel.readInt());
    }

}
