package net.ausiasmarch.service;

import javax.servlet.http.HttpServletRequest;

public class TipoProductoService extends GenericService {

	HttpServletRequest oRequest = null;

	public TipoProductoService(HttpServletRequest oRequest) {
		super(oRequest);
	}
}
