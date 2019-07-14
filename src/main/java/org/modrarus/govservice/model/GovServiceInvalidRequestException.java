package org.modrarus.govservice.model;

import java.util.LinkedList;
import java.util.List;

import com.google.gson.annotations.Expose;

/**
 * Невалидный запрос предоставления услуги
 */
public class GovServiceInvalidRequestException extends AGovServiceException {
	private static final long serialVersionUID = -4211098686693324026L;
	/**
	 * Список незаполненных обязательных полей
	 */
	@Expose
	private List<String> emptyRequiredFields;
	
	/**
	 * Основной конструктор
	 * @param _error Сообщение о ошибке данных
	 */
	public GovServiceInvalidRequestException(final String _error) {
		super(_error);
	}
	
	/**
	 * Добавление информации о отсутствии обязательного поля
	 * @param _field Поле
	 */
	public void addEmptyRequiredField(final String _field) {
		if (emptyRequiredFields == null) {
			emptyRequiredFields = new LinkedList<String>();
		}
		emptyRequiredFields.add(_field);
	}
	
	/**
	 * Проверка наличия информации о отсутствии обязательного поля
	 * @return true/false
	 */
	public boolean hasEmptyRequiredFields() {
		return emptyRequiredFields != null && !emptyRequiredFields.isEmpty();
	}
}