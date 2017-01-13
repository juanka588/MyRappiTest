package com.co.myrappitest.Data;

import android.os.Parcel;
import android.os.Parcelable;

import com.co.myrappitest.R;

/**
 * Created by JuanCamilo on 12/01/2017.
 */
public class DataT5 implements Parcelable {
    private String title;
    private String description;
    private String htmlDescription;
    private String headerImg;
    private String color;
    private String bannerImg;

    public DataT5(String title, String description, String htmlDescription, String headerImg, String color, String bannerImg) {
        this.title = title;
        this.description = description;
        this.htmlDescription = htmlDescription;
        this.headerImg = headerImg;
        this.color = color;
        this.bannerImg = bannerImg;
    }

    protected DataT5(Parcel in) {
        title = in.readString();
        description = in.readString();
        htmlDescription = in.readString();
        headerImg = in.readString();
        color = in.readString();
        bannerImg = in.readString();
    }

    public static final Creator<DataT5> CREATOR = new Creator<DataT5>() {
        @Override
        public DataT5 createFromParcel(Parcel in) {
            return new DataT5(in);
        }

        @Override
        public DataT5[] newArray(int size) {
            return new DataT5[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getHtmlDescription() {
        return htmlDescription;
    }

    public String getHeaderImg() {
        return headerImg;
    }

    public String getColor() {
        if (color.isEmpty()) {
            return "#5C5254";//gray color
        }
        return color;
    }

    public String getBannerImg() {
        if (bannerImg.isEmpty()) {
            return String.valueOf(R.mipmap.ic_launcher);
        }
        return bannerImg;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(htmlDescription);
        dest.writeString(headerImg);
        dest.writeString(color);
        dest.writeString(bannerImg);
    }
}
