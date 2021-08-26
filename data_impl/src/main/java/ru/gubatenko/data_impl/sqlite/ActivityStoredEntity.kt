package ru.gubatenko.data_impl.sqlite

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.gubatenko.data.entity.ActivityStored

@Entity
data class ActivityStoredEntity(
    @ColumnInfo(name = "activity") override val activity: String,
) : ActivityStored {
    @PrimaryKey
    val uid: String? = null
}