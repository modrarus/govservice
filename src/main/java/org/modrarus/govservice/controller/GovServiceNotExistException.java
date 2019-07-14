package org.modrarus.govservice.controller;

/**
 * Услуга не существует
 */
public class GovServiceNotExistException extends RuntimeException {
	private static final long serialVersionUID = 6183301773614480325L;
	
	/**
	 * Основной констурктор
	 * @param _id Запрошеный идентификатор услуги
	 */
	public GovServiceNotExistException(final String _id) {
		super(new StringBuilder()
				.append("Ошибка получения государственной услуги. Услуги с идентификатором \"")
				.append(_id).append("\" не существует.").toString());
	}
}