package warmup.nikosstais.atcom.com.devtest2.remote.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.security.Timestamp;
import java.util.Date;

public class Speaker implements Serializable, Parcelable {

    private int rowID;
    private String status;
    private String name;
    private String surname;
    private String photo;
    private String category;
    private String presentationTitle;
    private String presentationLocation;
    private String companyName;
    private String profession;
    private String cV;
    private String presentationDescription;
    private Date presentationDatetime;

    private int mData;

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    private int order;
    private boolean isActive;

    public int getRowID() {
        return rowID;
    }

    public void setRowID(int rowID) {
        this.rowID = rowID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPresentationTitle() {
        return presentationTitle;
    }

    public void setPresentationTitle(String presentationTitle) {
        this.presentationTitle = presentationTitle;
    }

    public String getPresentationLocation() {
        return presentationLocation;
    }

    public void setPresentationLocation(String presentationLocation) {
        this.presentationLocation = presentationLocation;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getcV() {
        return cV;
    }

    public void setcV(String cV) {
        this.cV = cV;
    }

    public String getPresentationDescription() {
        return presentationDescription;
    }

    public void setPresentationDescription(String presentationDescription) {
        this.presentationDescription = presentationDescription;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Date getPresentationDatetime() {
        return presentationDatetime;
    }

    public void setPresentationDatetime(Date presentationDatetime) {
        this.presentationDatetime = presentationDatetime;
    }

    @Override
    public int describeContents() {
        return Parcelable.CONTENTS_FILE_DESCRIPTOR;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    public static final Parcelable.Creator<Speaker> CREATOR
            = new Parcelable.Creator<Speaker>() {
        public Speaker createFromParcel(Parcel in) {
            return new Speaker(in);
        }

        public Speaker[] newArray(int size) {
            return new Speaker[size];
        }
    };
    private Speaker(Parcel in) {
        mData = in.readInt();
    }
}
