package com.novopay.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


data class DataSource(
    @SerializedName("status") val status: String,
    @SerializedName("sources") val sources: List<Sources>
)

data class Sources(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String?=null,
    @SerializedName("description") val description: String?,
    @SerializedName("url") val url: String?,
    @SerializedName("category") val category: String?,
    @SerializedName("language") val language: String?,
    @SerializedName("country") val country: String?
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
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
        parcel.writeString(description)
        parcel.writeString(url)
        parcel.writeString(category)
        parcel.writeString(language)
        parcel.writeString(country)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Sources> {
        override fun createFromParcel(parcel: Parcel): Sources {
            return Sources(parcel)
        }

        override fun newArray(size: Int): Array<Sources?> {
            return arrayOfNulls(size)
        }
    }
}