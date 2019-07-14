package org.modrarus.govservice.model.repository;

import org.modrarus.govservice.model.GovServiceRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий работы с запросами услуг
 */
@Repository
public interface GovServiceRequestRepository extends MongoRepository<GovServiceRequest, String> {
}