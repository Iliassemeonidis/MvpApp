package com.example.mvpapplicaton.view.details

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserRepositories(@Expose val fullName: String? = null, @Expose val forks: Int? = 0) : Parcelable

