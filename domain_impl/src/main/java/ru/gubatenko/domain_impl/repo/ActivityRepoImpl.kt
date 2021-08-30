package ru.gubatenko.domain_impl.repo

import ru.gubatenko.data.Mapper
import ru.gubatenko.data.dao.ActivityDao
import ru.gubatenko.data.dto.ActivityDto
import ru.gubatenko.data.entity.ActivityStored
import ru.gubatenko.data.service.ActivitySourceService
import ru.gubatenko.data.service.UserService
import ru.gubatenko.domain.model.Activity
import ru.gubatenko.domain.repo.ActivityRepo

class ActivityRepoImpl(
    private val activitySourceService: ActivitySourceService,
    private val userService: UserService,
    private val dao: ActivityDao<ActivityStored>,
    private val domainToStored: Mapper<Activity, ActivityStored>,
    private val storedToDomain: Mapper<ActivityStored, Activity>,
    private val domainToDto: Mapper<Activity, ActivityDto>,
    private val dtoToDomain: Mapper<ActivityDto, Activity>,
) : ActivityRepo {
    override suspend fun create(query: ActivityRepo.CreateQuery) = when (query) {
        is ActivityRepo.CreateQuery.NewActivityToLocalStorage -> {
            dao.saveAll(query.activities.map(domainToStored::map))
        }
        is ActivityRepo.CreateQuery.NewActivityToWebStorage -> {
            userService.post(query.activities.map(domainToDto::map))
        }
    }

    override suspend fun read(query: ActivityRepo.ReadQuery) = when (query) {
        is ActivityRepo.ReadQuery.ActivityFromLocalStorageReadQuery -> {
            dao.activities().map(storedToDomain::map)
        }
        is ActivityRepo.ReadQuery.NewActivityFromSourceServerReadQuery -> {
            listOf(dtoToDomain.map(activitySourceService.activity()))
        }
    }

    override suspend fun update(query: ActivityRepo.UpdateQuery) = when (query) {
        else -> Unit
    }

    override suspend fun delete(query: ActivityRepo.DeleteQuery) = when (query) {
        else -> Unit
    }
}