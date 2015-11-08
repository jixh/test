package com.jc.apps;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jc on 11/8/2015.
 */
public class TestBean implements Parcelable {


    /**
     * a : b
     */

    private String a;

    public void setA(String a) {
        this.a = a;
    }

    public String getA() {
        return a;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.a);
    }

    public TestBean() {
    }

    protected TestBean(Parcel in) {
        this.a = in.readString();
    }

    public static final Parcelable.Creator<TestBean> CREATOR = new Parcelable.Creator<TestBean>() {
        public TestBean createFromParcel(Parcel source) {
            return new TestBean(source);
        }

        public TestBean[] newArray(int size) {
            return new TestBean[size];
        }
    };
}
