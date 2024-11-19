package com.agrospread.agrospread.agrospread.Model

import android.os.Parcel
import android.os.Parcelable

data class ListData(val name: String, val image: Int, val price: String, var quantity: Int = 1) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(image)
        parcel.writeString(price)
        parcel.writeInt(quantity)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ListData> {
        override fun createFromParcel(parcel: Parcel): ListData {
            return ListData(parcel)
        }

        override fun newArray(size: Int): Array<ListData?> {
            return arrayOfNulls(size)
        }
    }
}

