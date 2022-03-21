package com.example.mvpapplicaton.model.db.table

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = RoomGithubUser::class,
        parentColumns = ["id"],
        childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE
    )]
)
class RoomGithubRepository(
    @PrimaryKey var id: String,
    var name: String,
    var forksCount: Int,
    var userId: String
)