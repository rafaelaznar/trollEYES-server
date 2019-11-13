package net.ausiasmarch.dao;

import java.sql.Connection;

public class UsuarioDao extends GenericDao implements DaoInterface {

    public UsuarioDao(Connection oConnection, String ob) {
		super(oConnection, ob);
	}
}
