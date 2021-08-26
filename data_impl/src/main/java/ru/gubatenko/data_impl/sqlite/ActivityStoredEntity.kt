package ru.gubatenko.data_impl.sqlite

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.gubatenko.data.entity.ActivityStored

@Entity(tableName = "activity_table")
data class ActivityStoredEntity(
    @PrimaryKey(autoGenerate = true) val uid: Long = 0,
    @ColumnInfo(name = "activity") override val activity: String,
) : ActivityStored