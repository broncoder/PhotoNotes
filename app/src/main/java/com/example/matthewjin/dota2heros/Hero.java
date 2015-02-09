package com.example.matthewjin.dota2heros;

import android.os.Parcel;
import android.os.Parcelable;

public class Hero implements Parcelable{
    private String name;
    private String filename;

    public Hero(Parcel source){
    	String[] data = new String[2];
        source.readStringArray(data);
        name = data[0];
        filename = data[1];
    }
    public Hero(String name, String filename) {
        this.name = name;
        this.filename = filename;
    }

    public String getName() {
        return name;
    }

    public String getFileName() {
        return filename;
    }
    public static final Creator CREATOR = new Creator(){

		@Override
		public Object createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new Hero(source);
		}

		@Override
		public Object[] newArray(int size) {
			// TODO Auto-generated method stub
			return new Hero[size];
		}
    };
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeStringArray(new String[]{ this.name, this.filename});
	}
}