package com.example.martyrs.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Data(
    val age: Int,
    val bio: String?,
    val birthDate: String,
    val burialPlace: String,
    val fatherFirstName: String,
    val fullName: String,
    val job: String?,
    val marriage: String?,
    val martyrDate: String,
    val martyrId: Int,
    val martyrPlace: String,
    val missionName: String?,
    val photoUrl: String,
    val testamentFileUrl: String?,
    val testamentText: String?
):Parcelable