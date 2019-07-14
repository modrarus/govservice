package org.modrarus.govservice.model;

import com.google.gson.annotations.Expose;

public abstract class AGovServiceException extends RuntimeException {
	private static final long serialVersionUID = -7795699139671462775L;
	
	/**
	 * Копия основного описания для сериализации в JSoN
	 * Сериализация исключения используется для исключения необходимости
	 * создавать отдельный класс сообщения о ошибке API
	 */
	@Expose
	private String error;
	
	AGovServiceException(final String _error) {
		super(_error);
		error = _error;
	}
}