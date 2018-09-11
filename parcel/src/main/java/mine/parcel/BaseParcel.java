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

    public void opareteData() {
        mBaseParcel.writeInt(115);
        mBaseParcel.writeInt(1155);

        mBaseParcel.setDataPosition(0);
        System.out.println( mBaseParcel.readInt());
        mBaseParcel.setDataPosition(4);
        System.out.println( mBaseParcel.readInt());
    }

}
