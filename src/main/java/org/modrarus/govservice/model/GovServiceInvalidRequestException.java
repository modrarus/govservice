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
	private List<EmptyRequiredField> emptyRequiredFields;
	
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
	public void addEmptyRequiredField(final GovServiceRequestSchemaField _field) {
		if (emptyRequiredFields == null) {
			emptyRequiredFields = new LinkedList<EmptyRequiredField>();
		}
		emptyRequiredFields.add(
				new EmptyRequiredField(_field.getName(), _field.getDisplayName()));
	}
	
	/**
	 * Проверка наличия информации о отсутствии обязательного поля
	 * @return true/false
	 */
	public boolean hasEmptyRequiredFields() {
		return emptyRequiredFields != null && !emptyRequiredFields.isEmpty();
	}
	
	/**
	 * Контейнер упрощенного описания незаполненного поля для простой обработки на фронтенде
	 */
	private static final class EmptyRequiredField {
		/**
		 * Имя поля
		 */
		@Expose
		private String name;
		/**
		 * Отображаемое имя
		 */
		@Expose
		private String displayName;
		
		/**
		 * Базовый конструктор
		 * @param _name Имя
		 * @param _displayName Отображаемое имя
		 */
		private EmptyRequiredField(final String _name, final String _displayName) {
			name = _name;
			displayName = _displayName;
		}
	}
}