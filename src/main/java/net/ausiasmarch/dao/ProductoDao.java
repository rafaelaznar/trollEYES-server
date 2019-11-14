package net.ausiasmarch.dao;

import java.sql.Connection;

public class ProductoDao  extends GenericDao implements DaoInterface{
    public ProductoDao(Connection oConnection, String ob) {
		super(oConnection, ob);
	}
}