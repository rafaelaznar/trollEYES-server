package net.ausiasmarch.dao;

import java.sql.Connection;

public class CompraDao extends GenericDao implements DaoInterface {

    public CompraDao(Connection oConnection) {
		super(oConnection, "compra");
	}
}
