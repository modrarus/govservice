package org.modrarus.govservice.controller;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.modrarus.govservice.model.AGovServiceException;
import org.modrarus.govservice.model.GovService;
import org.modrarus.govservice.model.GovServiceNotExistException;
import org.modrarus.govservice.model.GovServiceRequest;
import org.modrarus.govservice.model.GovServiceRequestData;
import org.modrarus.govservice.model.GovServiceRequestSchemaField;
import org.modrarus.govservice.model.repository.GovServiceRepository;
import org.modrarus.govservice.model.repository.GovServiceRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
	
	/**
	 * Обработка отсутствия запрошенной услуги
	 * @param _exception Исключение
	 * @return Ответ
	 */
	@ExceptionHandler(AGovServiceException.class)
	public ResponseEntity<AGovServiceException> handleGovServiceNotExistException(
			final AGovServiceException _exception) {
		return new ResponseEntity<AGovServiceException>(_exception, HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * Инициализация базы набором услуг по умолчанию
	 */
	@PostMapping("/api/internal/init")
	public ResponseEntity<String> initServicesList() {
		//Очистка существующей базы
		serviceRepository.deleteAll();
		requestRepository.deleteAll();
		
		List<GovServiceRequestSchemaField> fields = new LinkedList<GovServiceRequestSchemaField>();
		fields.add(new GovServiceRequestSchemaField("ITN", "ИНН", "Индивидуальный номер налогоплательщика", true));
		
		serviceRepository.save(new GovService("Проверка налоговых задолженностей",
				"Проверка налоговых задолженностей",
				fields));
		
		fields = new LinkedList<GovServiceRequestSchemaField>();
		fields.add(new GovServiceRequestSchemaField("carLicense",
				"Автомобильный номер", "Автомобильный номер", true));
		
		serviceRepository.save(new GovService("Проверка штрафов ГИБДД",
				"Проверка штрафов ГИБДД",
				fields));
		
		fields = new LinkedList<GovServiceRequestSchemaField>();
		fields.add(new GovServiceRequestSchemaField("passportSeries",
				"Серия старого паспорта", "Серия располагается в верхней части первой страницы паспорта и представляет собой 4 цифры", true));
		fields.add(new GovServiceRequestSchemaField("passportNumber",
				"Номер старого паспорта", "Номер располагается в верхней части первой страницы паспорта и представляет собой 6 цифр", true));
		fields.add(new GovServiceRequestSchemaField("name",
				"Имя", "Имя", true));
		fields.add(new GovServiceRequestSchemaField("surname",
				"Фамилия", "Фамилия", true));
		fields.add(new GovServiceRequestSchemaField("patronymic",
				"Отчество", "Отчество", true));
		
		serviceRepository.save(new GovService("Замена паспорта",
				"Замена паспорта осуществляется по истечении срока действия текущего паспорта",
				fields));
		
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}
}