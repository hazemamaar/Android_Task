package com.example.data.network.models

import android.os.Parcel
import android.os.Parcelable

data class Medication(
    val active_ingredient: String,
    val description: String,
    val dosage_instructions: String,
    val dose: String,
    val name: String,
    val strength: String
) : Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(active_ingredient)
        parcel.writeString(description)
        parcel.writeString(dosage_instructions)
        parcel.writeString(dose)
        parcel.writeString(name)
        parcel.writeString(strength)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Medication> {
        override fun createFromParcel(parcel: Parcel): Medication {
            return Medication(parcel)
        }

        override fun newArray(size: Int): Array<Medication?> {
            return arrayOfNulls(size)
        }
    }


}