package com.zcrain.ipcunit.domain

import android.os.Parcel
import android.os.Parcelable

/**
 * Author：CWQ
 * Date：2021/5/19
 * Desc:
 */
class Book(var bookId: Int, var bookName: String?) : Parcelable {

    constructor(parcel: Parcel) : this(parcel.readInt(), parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(bookId)
        parcel.writeString(bookName)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Book> {
        override fun createFromParcel(parcel: Parcel): Book {
            return Book(parcel)
        }

        override fun newArray(size: Int): Array<Book?> {
            return arrayOfNulls(size)
        }
    }

    override fun toString(): String {
        return "Book---id:$bookId,name:$bookName"
    }
}