package org.modrarus.govservice.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.google.gson.annotations.Expose;

/**
 * Описание госуслуги
 */
@Document("Service")
public class GovService {
	/**
	 * Идентификатор
	 */
	@Expose
	@Id
	private String id;
	/**
	 * Наименование услуги
	 */
	@Expose
	private String name;
	/**
	 * Описание
	 */
	@Expose
	private String description;
	/**
	 * Схема запроса услуги
	 */
	private List<GovServiceRequestSchemaField> schema;
	
	/**
	 * Пустой конструктор, необходим для mongodb
	 */
	@SuppressWarnings("unused")
	private GovService() {}
	
	/**
	 * Создание новой услуги
	 * @param _name        Название
	 * @param _description Описание
	 * @param _schema      Схема запроса услуги
	 */
	public GovService(final String _name, final String _description,
			final List<GovServiceRequestSchemaField> _schema) {
		name        = _name;
		description = _description;
		schema      = _schema;
	}
	
	/**
	 * Получение идентификатора услуги
	 * @return Идентификатор
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Получение схемы запроса
	 * @return Схема
	 */
	public List<GovServiceRequestSchemaField> getRequestSchema() {
		return schema;
	}
	
	/**
	 * Получение названия услуги
	 * @return Название
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Получение описания услуги
	 * @return Описание
	 */
	public String getDescription() {
		return description;
	}
}