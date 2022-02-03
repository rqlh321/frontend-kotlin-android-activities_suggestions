package com.example.lib

import com.example.lib.mapper.ActivityDtoToDomain
import com.example.lib.mapper.ActivityFromStoredToDomain
import com.example.lib.mapper.DomainToActivityDto
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.gubatenko.data.Mapper
import ru.gubatenko.data.dto.IdeaDto
import ru.gubatenko.data.entity.IdeaStored
import ru.gubatenko.data.service.IdeaSourceService
import ru.gubatenko.domain.MAPPER_DOMAIN_TO_DTO_ACTION
import ru.gubatenko.domain.MAPPER_DTO_TO_DOMAIN_ACTION
import ru.gubatenko.domain.MAPPER_STORED_TO_DOMAIN_ACTION
import ru.gubatenko.domain.model.Idea

fun mapperActionImplModuleDI() = module {
    single<Mapper<IdeaDto, Idea>>(named(MAPPER_DTO_TO_DOMAIN_ACTION)) { ActivityDtoToDomain() }
    single<Mapper<Idea, IdeaDto>>(named(MAPPER_DOMAIN_TO_DTO_ACTION)) { DomainToActivityDto() }
    single<Mapper<IdeaStored, Idea>>(named(MAPPER_STORED_TO_DOMAIN_ACTION)) { ActivityFromStoredToDomain() }
}

fun serviceImplModuleDI() = module {
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl("https://www.boredapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single<IdeaSourceService> { get<Retrofit>().create(IdeaSourceServiceRetrofit::class.java) }
}
