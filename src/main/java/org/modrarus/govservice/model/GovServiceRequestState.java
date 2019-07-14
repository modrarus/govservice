package org.modrarus.govservice.model;

/**
 * Список статусов услуги
 */
public enum GovServiceRequestState {
	/**
	 * Услуга создана
	 */
	CREATED,
	/**
	 * Услуга отклонена
	 */
	REJECTED,
	/**
	 * Услуга выполнена
	 */
	FINISHED
}