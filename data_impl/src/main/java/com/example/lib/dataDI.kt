package com.example.lib

import com.example.lib.mapper.ActivityDtoToDomain
import com.example.lib.mapper.ActivityFromStoredToDomain
import com.example.lib.mapper.DomainToActivityDto
import com.example.lib.prefs.AuthPreferenceImpl
import com.example.lib.prefs.CustomDataStoreImpl
import com.example.lib.prefs.ThemPreferenceImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.gubatenko.data.Mapper
import ru.gubatenko.data.dto.IdeaDto
import ru.gubatenko.data.entity.IdeaStored
import ru.gubatenko.data.pref.AuthPreference
import ru.gubatenko.data.pref.CustomDataStore
import ru.gubatenko.data.pref.ThemPreference
import ru.gubatenko.data.service.IdeaSourceService
import ru.gubatenko.domain.*
import ru.gubatenko.domain.model.Idea

fun mapperActionImplModuleDI() = module {
    single<Mapper<IdeaDto, Idea>>(named(MAPPER_DTO_TO_DOMAIN_ACTION)) { ActivityDtoToDomain() }
    single<Mapper<Idea, IdeaDto>>(named(MAPPER_DOMAIN_TO_DTO_ACTION)) { DomainToActivityDto() }
    single<Mapper<IdeaStored, Idea>>(named(MAPPER_STORED_TO_DOMAIN_ACTION)) { ActivityFromStoredToDomain() }
}

fun serviceImplModuleDI() = module {
    single<AuthPreference> { AuthPreferenceImpl(get(named(SIMPLE_VALUE_DATA_STORE))) }
    single<ThemPreference> { ThemPreferenceImpl(get(named(SIMPLE_VALUE_DATA_STORE))) }
    single<CustomDataStore> { CustomDataStoreImpl(get(named(CUSTOM_DATA_STORE))) }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl("https://www.boredapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single<IdeaSourceService> { get<Retrofit>().create(IdeaSourceServiceRetrofit::class.java) }
}
