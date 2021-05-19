package com.zcrain.ipcunit.domain

import android.os.Parcel
import android.os.Parcelable

/**
 * Author：CWQ
 * Date：2021/5/15
 * Desc:
 */
class Person(var name: String?, var age: Int) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt()
    ) {
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel?, flags: Int) {
        parcel?.writeString(name)
        parcel?.writeInt(age)
    }

    companion object CREATOR : Parcelable.Creator<Person> {
        override fun createFromParcel(parcel: Parcel): Person {
            return Person(parcel)
        }

        override fun newArray(size: Int): Array<Person?> {
            return arrayOfNulls(size)
        }
    }

    override fun toString(): String {
        return "Person---name:$name,age:$age"
    }
}
