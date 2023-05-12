package com.example.madd_project.models

import android.os.Parcel
import android.os.Parcelable

data class Posts(val id :String?=null,
                 val name:String?=null,
                 val dueDate:String?=null,
                 val description:String?=null,
                 val quantity:String?=null,
                 val copyQuantity :String? = null,
                 val imageUrl:String?=null):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(dueDate)
        parcel.writeString(description)
        parcel.writeString(quantity)
        parcel.writeString(imageUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Posts> {
        override fun createFromParcel(parcel: Parcel): Posts {
            return Posts(parcel)
        }

        override fun newArray(size: Int): Array<Posts?> {
            return arrayOfNulls(size)
        }
    }
}
