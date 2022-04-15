package com.github.krottv.tmstemp

import android.os.Parcel
import android.os.Parcelable

data class Student(
    val id: Int,
    val age: Int,
    val experience: Int,
    val name: String,
    var isSelected: Boolean = false,
): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString()!!
    )

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(p0: Parcel, p1: Int) {
        p0.writeInt(id)
        p0.writeInt(age)
        p0.writeInt(experience)
        p0.writeString(name)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Student

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id
    }


    companion object CREATOR : Parcelable.Creator<Student> {
        override fun createFromParcel(parcel: Parcel): Student {
            return Student(parcel)
        }

        override fun newArray(size: Int): Array<Student?> {
            return arrayOfNulls(size)
        }
    }
}