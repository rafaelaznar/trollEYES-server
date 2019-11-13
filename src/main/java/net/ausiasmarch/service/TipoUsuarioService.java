package net.ausiasmarch.service;

import javax.servlet.http.HttpServletRequest;

public class TipoUsuarioService extends GenericService {

	HttpServletRequest oRequest = null;

	public TipoUsuarioService(HttpServletRequest oRequest) {
		super(oRequest);
	}
}
