package ru.gubatenko.domain_impl.repo

import kotlinx.coroutines.flow.map
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
            dao.save(query.activities.map(domainToStored::map))
        }
        is ActivityRepo.CreateQuery.NewActivityToWebStorage   -> {
            userService.post(query.activities.map(domainToDto::map))
        }
    }

    override suspend fun read(query: ActivityRepo.ReadQuery) = when (query) {
        is ActivityRepo.ReadQuery.NotSyncedActivityFromLocalStorageReadQuery -> {
            dao.getNotSynced().map(storedToDomain::map)
        }
        is ActivityRepo.ReadQuery.ActivityFromLocalStorageReadQuery          -> {
            dao.all().map(storedToDomain::map)
        }
        is ActivityRepo.ReadQuery.NewActivityFromSourceServerReadQuery       -> {
            listOf(dtoToDomain.map(activitySourceService.activity()))
        }
        is ActivityRepo.ReadQuery.GetUserActionsFromRemoteStorageReadQuery   -> {
            userService.get().map(dtoToDomain::map)
        }
    }

    override suspend fun subscribe(query: ActivityRepo.SubscribeQuery) = when(query){
        is ActivityRepo.SubscribeQuery.ActivityFromLocalStorage -> {
            dao.subscribe().map { it.map(storedToDomain::map) }
        }
    }

    override suspend fun update(query: ActivityRepo.UpdateQuery) = when (query) {
        is ActivityRepo.UpdateQuery.AllActivitiesSynced -> {
            dao.updateAsSynced(query.activities.mapNotNull { it.uid })
        }
    }

    override suspend fun delete(query: ActivityRepo.DeleteQuery) = when(query) {
        is ActivityRepo.DeleteQuery.ClearLocalStorage -> {
            dao.delete()
        }
    }
}