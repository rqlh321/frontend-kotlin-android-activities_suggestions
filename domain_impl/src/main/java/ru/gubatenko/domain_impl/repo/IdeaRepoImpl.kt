package ru.gubatenko.domain_impl.repo

import kotlinx.coroutines.flow.map
import ru.gubatenko.data.Mapper
import ru.gubatenko.data.dao.IdeaDao
import ru.gubatenko.data.dto.IdeaDto
import ru.gubatenko.data.entity.IdeaStored
import ru.gubatenko.data.service.IdeaSourceService
import ru.gubatenko.data.service.UserService
import ru.gubatenko.domain.model.Idea
import ru.gubatenko.domain.repo.IdeaRepo

class IdeaRepoImpl(
    private val ideaService: IdeaSourceService,
    private val userService: UserService,
    private val ideaDao: IdeaDao<IdeaStored>,
    private val ideaDomainToStoredMapper: Mapper<Idea, IdeaStored>,
    private val ideaStoredToDomainMapper: Mapper<IdeaStored, Idea>,
    private val ideaDomainToDtoMapper: Mapper<Idea, IdeaDto>,
    private val ideaDtoToDomainMapper: Mapper<IdeaDto, Idea>,
) : IdeaRepo {
    override suspend fun create(query: IdeaRepo.CreateQuery) = when (query) {
        is IdeaRepo.CreateQuery.NewActivityToLocalStorage -> {
            ideaDao.save(query.ideas.map(ideaDomainToStoredMapper::map))
        }
        is IdeaRepo.CreateQuery.NewActivityToWebStorage   -> {
            userService.post(query.ideas.map(ideaDomainToDtoMapper::map))
        }
    }

    override suspend fun read(query: IdeaRepo.ReadQuery) = when (query) {
        is IdeaRepo.ReadQuery.NotSyncedActivityFromLocalStorageReadQuery -> {
            ideaDao.getNotSynced().map(ideaStoredToDomainMapper::map)
        }
        is IdeaRepo.ReadQuery.ActivityFromLocalStorageReadQuery          -> {
            ideaDao.all().map(ideaStoredToDomainMapper::map)
        }
        is IdeaRepo.ReadQuery.NewActivityFromSourceServerReadQuery       -> {
            listOf(ideaDtoToDomainMapper.map(ideaService.idea()))
        }
        is IdeaRepo.ReadQuery.GetUserActionsFromRemoteStorageReadQuery   -> {
            userService.get().map(ideaDtoToDomainMapper::map)
        }
    }

    override suspend fun subscribe(query: IdeaRepo.SubscribeQuery) = when(query){
        is IdeaRepo.SubscribeQuery.ActivityFromLocalStorage -> {
            ideaDao.subscribe().map { it.map(ideaStoredToDomainMapper::map) }
        }
    }

    override suspend fun update(query: IdeaRepo.UpdateQuery) = when (query) {
        is IdeaRepo.UpdateQuery.AllActivitiesSynced -> {
            ideaDao.updateAsSynced(query.ideas.mapNotNull { it.uid })
        }
    }

    override suspend fun delete(query: IdeaRepo.DeleteQuery) = when(query) {
        is IdeaRepo.DeleteQuery.ClearLocalStorage -> {
            ideaDao.delete()
        }
    }
}