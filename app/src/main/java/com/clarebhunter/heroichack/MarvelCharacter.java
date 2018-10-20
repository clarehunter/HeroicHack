package com.clarebhunter.heroichack;

import android.os.Parcel;
import android.os.Parcelable;

public class MarvelCharacter implements Parcelable {

    private String name;
    private int id;
    private boolean isSelected = false;

    public MarvelCharacter(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public boolean isSelected() {
        return isSelected;
    }

    /**
     * Constructor used by Parcel to make a new MarvelCharacter out of the
     * parceled information.
     *
     * @param in the parcel containing the MarvelCharacter information
     */
    private MarvelCharacter(Parcel in) {
        name = in.readString();
        id = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    /* *************************
       If you add new instance vars to MarvelCharacter, you will need to add
       them to the write. Be sure the order you write information matches
       the order that the constructor above reads them.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(id);
    }

    /**
     * Should not have to edit this method if the constructor and write method are
     * working correctly.
     */
    public static final Parcelable.Creator<MarvelCharacter> CREATOR
            = new Parcelable.Creator<MarvelCharacter>() {
        public MarvelCharacter createFromParcel(Parcel in) {
            return new MarvelCharacter(in);
        }

        public MarvelCharacter[] newArray(int size) {
            return new MarvelCharacter[size];
        }
    };
}
