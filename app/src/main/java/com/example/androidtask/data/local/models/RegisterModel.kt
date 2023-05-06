package com.example.androidtask.data.local.models

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "register_table")
data class RegisterModel(
    val name:String,
    @PrimaryKey
    val email:String,
    val mobileNumber: String,
    val password:String
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(email)
        parcel.writeString(mobileNumber)
        parcel.writeString(password)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RegisterModel> {
        override fun createFromParcel(parcel: Parcel): RegisterModel {
            return RegisterModel(parcel)
        }

        override fun newArray(size: Int): Array<RegisterModel?> {
            return arrayOfNulls(size)
        }
    }
}
