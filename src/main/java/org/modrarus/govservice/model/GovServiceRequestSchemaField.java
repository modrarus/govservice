package org.modrarus.govservice.model;

import com.google.gson.annotations.Expose;

/**
 * Поле схемы запроса госуслуги
 */
public class GovServiceRequestSchemaField {
	/**
	 * Имя
	 */
	@Expose
	private String name;
	/**
	 * Отображаемое имя
	 */
	@Expose
	private String displayName;
	/**
	 * Описание
	 */
	@Expose
	private String description;
	/**
	 * Обязательность заполнения
	 */
	private boolean required;
	
	/**
	 * Конструктор нового поля
	 * @param _name    Имя
	 * @param _disName Отображаемое имя
	 * @param _desc    Описание
	 * @param _req     Обязательность заполнения
	 */
	public GovServiceRequestSchemaField(final String _name, final String _disName, final String _desc,
			final boolean _req) {
		name        = _name;
		displayName = _disName;
		description = _desc;
		required = _req;
	}
	
	/**
	 * Получение имени поля (внутреннее, используется как ключ)
	 * @return Имя поля
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Получение отображаемого имени
	 * @return Отображаемое имя
	 */
	public String getDisplayName() {
		return displayName;
	}
	
	/**
	 * Получение описания поля
	 * @return Описание
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Получение флага обязательности поля
	 * @return Флаг
	 */
	public boolean isRequired() {
		return required;
	}
}