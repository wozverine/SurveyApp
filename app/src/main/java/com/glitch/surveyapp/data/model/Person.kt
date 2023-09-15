package com.glitch.surveyapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Person(
    val name: String,
    val phoneNumber: Long,
    val eMail: String,
    val city: String,
    val answer1: String,
    val answer2: String,
    val answer3: String
) : Parcelable
