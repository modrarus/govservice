package org.modrarus.govservice.controller;

import java.util.Collection;
import java.util.List;

import org.modrarus.govservice.model.GovService;
import org.modrarus.govservice.model.GovServiceNotExistException;
import org.modrarus.govservice.model.GovServiceRequest;
import org.modrarus.govservice.model.GovServiceRequestData;
import org.modrarus.govservice.model.GovServiceRequestSchemaField;
import org.modrarus.govservice.model.repository.GovServiceRepository;
import org.modrarus.govservice.model.repository.GovServiceRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер API 
 */
@RestController
public class GovServiceController {
	/**
	 * Репозиторий для работы с услугами
	 */
	private GovServiceRepository serviceRepository;
	/**
	 * Репозиторий для работы с заявками
	 */
	@SuppressWarnings("unused")
	private GovServiceRequestRepository requestRepository;
	
	@Autowired
	public GovServiceController(final GovServiceRepository _serviceRepository,
			final GovServiceRequestRepository _requestRepository) {
		serviceRepository = _serviceRepository;
		requestRepository = _requestRepository;
	}
	
	/**
	 * Получение списка всех услуг
	 * @return Список услуг
	 */
	@GetMapping("/api/list")
	public Collection<GovService> getList() {
		return serviceRepository.findAll();
	}
	
	/**
	 * Получение схемы запроса услуги
	 * @param _id Идентификатор услуги
	 * @return Схема
	 */
	@GetMapping("/api/{serviceId}/schema")
	public List<GovServiceRequestSchemaField> getServiceSchema(
			@PathVariable("serviceId") final String _id) {
		return serviceRepository.findById(_id)
				.orElseThrow(() -> new GovServiceNotExistException(_id))
				.getRequestSchema();
	}
	
	/**
	 * Построение запроса услуги
	 * @param _id Идентификатор услуги
	 * @param _requestData Данные по услуге
	 */
	@PostMapping("/api/{serviceId}/request")
	public void requestService(@PathVariable("serviceId") final String _id,
			@RequestBody final GovServiceRequestData _requestData) {
		GovService service = serviceRepository.findById(_id)
				.orElseThrow(() -> new GovServiceNotExistException(_id));
		
		GovServiceRequest request = new GovServiceRequest(service, _requestData);
		
		requestRepository.save(request);
	}
}