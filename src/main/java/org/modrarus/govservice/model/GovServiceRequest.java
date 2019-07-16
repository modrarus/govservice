package org.modrarus.govservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.NonNull;

/**
 * Заявка на государственную услугу
 */
@Document("ServiceRequest")
public class GovServiceRequest {
	/**
	 * Идентификатор
	 */
	@Id
	private String id;
	/**
	 * Услуга
	 */
	@DBRef(lazy = true)
	private GovService service;
	/**
	 * Основные данные
	 */
	private GovServiceRequestData data;
	/**
	 * Статус услуги
	 */
	private GovServiceRequestState state = GovServiceRequestState.CREATED;
	
	/**
	 * Пустой конструктор, необходим для драйвера mongodb
	 */
	@SuppressWarnings("unused")
	private GovServiceRequest() {}
	
	/**
	 * Построение нового запроса услуги
	 * @param _service Услуга
	 * @param _data    Данные по улсуге
	 */
	public GovServiceRequest(final GovService _service, final GovServiceRequestData _data) {
		if (_service == null) {
			throw new GovServiceInvalidRequestException(
					"Ошибка выполнения запроса на предоставление государственной услуги. Услуга не указана.");
		}
		service = _service;
		parseDataBySchema(_data);
	}
	
	/**
	 * Получение идентификатора
	 * @return Идентификатор
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Получение данных услуги
	 * @return Данные
	 */
	public GovServiceRequestData getData() {
		return data;
	}
	
	/**
	 * Получение статуса услуги
	 * @return Статус
	 */
	public GovServiceRequestState getState() {
		return state;
	}
	
	/**
	 * Установка статуса услуги
	 * @param _state Статус
	 */
	public void setState(@NonNull final GovServiceRequestState _state) {
		state = _state;
	}
	
	/**
	 * Получение описания услуги
	 * @return Улуга
	 */
	public GovService getService() {
		return service;
	}
	
	/**
	 * Построение данных запроса по схеме, соответствующей услуге
	 * @param _data Исходные данные для запроса
	 */
	private void parseDataBySchema(final GovServiceRequestData _data) {
		GovServiceInvalidRequestException error = new GovServiceInvalidRequestException(
				"Ошибка выполнения запроса на предоставление государственной услуги. Заявка не содержит обязательных полей");
		if (_data == null) {
			for (GovServiceRequestSchemaField field : service.getRequestSchema()) {
				error.addEmptyRequiredField(field.getName());
			}
			throw error;
		}
		
		data = new GovServiceRequestData();
		for (GovServiceRequestSchemaField field : service.getRequestSchema()) {
			String value = _data.get(field.getName());
			if (value != null && !value.trim().isEmpty()) {
				data.put(field.getName(), value.trim());
			} else if (field.isRequired()) {
				error.addEmptyRequiredField(field.getName());
			}
		}
		
		if (error.hasEmptyRequiredFields()) {
			throw error;
		}
	}
}