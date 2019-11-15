package net.ausiasmarch.dao;

import java.sql.Connection;

public class TipoProductoDao extends GenericDao implements DaoInterface {

    public TipoProductoDao(Connection oConnection) {
        super(oConnection, "tipo_producto");
    }
}
