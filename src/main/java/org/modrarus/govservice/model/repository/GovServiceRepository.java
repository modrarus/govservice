package org.modrarus.govservice.model.repository;

import org.modrarus.govservice.model.GovService;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий работы с услугами
 */
@Repository
public interface GovServiceRepository extends MongoRepository<GovService, String> {
}