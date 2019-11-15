package net.ausiasmarch.dao;

import java.sql.Connection;

public class TipoUsuarioDao extends GenericDao implements DaoInterface {

    public TipoUsuarioDao(Connection oConnection) {
		super(oConnection, "tipo_usuario");
	}
}
