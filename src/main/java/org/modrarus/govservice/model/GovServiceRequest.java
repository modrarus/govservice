package org.modrarus.govservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.lang.NonNull;

/**
 * Заявка на государственную услугу
 */
public class GovServiceRequest {
	/**
	 * Идентификатор
	 */
	@Id
	private String id;
	/**
	 * Услуга
	 */
	@DBRef
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
	 * Построение нового запроса услуги
	 * @param _service Услуга
	 * @param _data    Данные по улсуге
	 */
	public GovServiceRequest(final GovService _service, final GovServiceRequestData _data) {
		service = _service;
		data    = _data;
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
}