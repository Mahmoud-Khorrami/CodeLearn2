package com.mahapp1397.codelearn2.models

import androidx.annotation.DrawableRes
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mahapp1397.codelearn2.utils.Constants.PROFILE_TABLE

@Entity(tableName = PROFILE_TABLE)
data class Profile(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val nationalCode: String,
    val phoneNumber: String,
    @DrawableRes val avatar: Int,
    val description: String
    )
