package ru.gubatenko.data_impl

import androidx.room.Room
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.gubatenko.data.Mapper
import ru.gubatenko.data.dao.IdeaDao
import ru.gubatenko.data.entity.IdeaStored
import ru.gubatenko.data.service.UserService
import ru.gubatenko.data.text.DynamicText
import ru.gubatenko.data.text.StaticText
import ru.gubatenko.data_impl.mapper.ActivityFromDomainToStoredRoom
import ru.gubatenko.data_impl.prefs.PreferenceDataStore
import ru.gubatenko.data_impl.prefs.PreferenceSharedPrefs
import ru.gubatenko.data_impl.service.UserServiceImpl
import ru.gubatenko.data_impl.sqlite.AppDatabase
import ru.gubatenko.data_impl.text.DynamicTextFirebase
import ru.gubatenko.data_impl.text.StaticTextAssets
import ru.gubatenko.domain.DAO_IDEA_SQLITE
import ru.gubatenko.domain.DefinedPreference
import ru.gubatenko.domain.MAPPER_DOMAIN_TO_STORED_SQLITE_ACTION
import ru.gubatenko.domain.Preference
import ru.gubatenko.domain.model.Idea

val databaseSQLiteModuleDI = module {
    single { Room.databaseBuilder(get(), AppDatabase::class.java, "database-name").build() }
    single<IdeaDao<*>>(named(DAO_IDEA_SQLITE)) { get<AppDatabase>().ideaDao() }
}
val serviceAndroidModuleDI = module {
    single<UserService> { UserServiceImpl() }
}
val mapperActionAndroidModuleDI = module {
    single<Mapper<Idea, IdeaStored>>(named(MAPPER_DOMAIN_TO_STORED_SQLITE_ACTION)) { ActivityFromDomainToStoredRoom() }
}

val prefsImplModuleDI = module {
    single { PreferenceDataStore(get()) }
    single<Preference> { get<PreferenceDataStore>() }
    single<DefinedPreference> { get<PreferenceDataStore>() }
}
val textImplModuleDI = module {
    single<StaticText> { StaticTextAssets(get()) }
    single<DynamicText> { DynamicTextFirebase(get()) }
}