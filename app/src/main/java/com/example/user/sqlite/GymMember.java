package com.example.user.sqlite;

public class GymMember {
    private final String mName;
    private final String mAddress;
    private final String mPhoneNumber;
    private final float mAge;


    public GymMember(String mName, String mAddress, String mPhoneNumber, float mAge) {
        this.mName = mName;
        this.mAddress = mAddress;
        this.mPhoneNumber = mPhoneNumber;
        this.mAge = mAge;
    }

    public String getmName() {
        return mName;
    }

    public String getmAddress() {
        return mAddress;
    }

    public String getmPhoneNumber() {
        return mPhoneNumber;
    }

    public float getmAge() {
        return mAge;
    }
}
