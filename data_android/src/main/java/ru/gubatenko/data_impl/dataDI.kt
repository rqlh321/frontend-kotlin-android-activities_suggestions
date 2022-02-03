package ru.gubatenko.data_impl

import androidx.room.Room
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.gubatenko.data.Mapper
import ru.gubatenko.data.dao.IdeaDao
import ru.gubatenko.data.entity.IdeaStored
import ru.gubatenko.domain.pref.Preference
import ru.gubatenko.data.service.UserService
import ru.gubatenko.data.text.DynamicText
import ru.gubatenko.data.text.StaticText
import ru.gubatenko.data_impl.mapper.ActivityFromDomainToStoredRoom
import ru.gubatenko.data_impl.prefs.PreferenceDataStore
import ru.gubatenko.data_impl.service.UserServiceImpl
import ru.gubatenko.data_impl.sqlite.AppDatabase
import ru.gubatenko.data_impl.text.DynamicTextFirebase
import ru.gubatenko.data_impl.text.StaticTextAssets
import ru.gubatenko.domain.DAO_IDEA_SQLITE
import ru.gubatenko.domain.MAPPER_DOMAIN_TO_STORED_SQLITE_ACTION
import ru.gubatenko.domain.model.Idea

fun databaseSQLiteModuleDI() = module {
    single { Room.databaseBuilder(get(), AppDatabase::class.java, "database-name").build() }
    single<IdeaDao<*>>(named(DAO_IDEA_SQLITE)) { get<AppDatabase>().ideaDao() }
}

fun serviceAndroidModuleDI() = module {
    single<UserService> { UserServiceImpl() }
}

fun mapperActionAndroidModuleDI() = module {
    single<Mapper<Idea, IdeaStored>>(named(MAPPER_DOMAIN_TO_STORED_SQLITE_ACTION)) { ActivityFromDomainToStoredRoom() }
}

fun prefsAndroidModuleDI() = module {
    single<Preference> { PreferenceDataStore(get()) }
}

fun textImplModuleDI() = module {
    single<StaticText> { StaticTextAssets(get()) }
    single<DynamicText> { DynamicTextFirebase(get()) }
}
