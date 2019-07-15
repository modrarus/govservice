package org.modrarus.govservice.model.repository;

import java.util.Optional;

import org.modrarus.govservice.model.GovService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий работы с услугами
 */
@Repository
public interface GovServiceRepository extends MongoRepository<GovService, String> {
	/**
	 * Перегрузка используется для подключения кэша
	 */
	@Cacheable(cacheNames = "govService", key = "#id")
	@Override
	public Optional<GovService> findById(String id);
	
	/**
	 * Перегрузка используется для подключения кэша
	 */
	@CachePut(cacheNames = "govService", key = "#entity.id")
	@Override
	<T extends GovService> T save(T entity);
	
	/**
	 * Перегрузка используется для подключения кэша
	 */
	@CacheEvict(cacheNames = "govService", allEntries = true)
	@Override
	void deleteAll(Iterable<? extends GovService> entities);
}