package net.ausiasmarch.dao;

import java.sql.Connection;

public class UsuarioDao extends GenericDao implements DaoInterface {

    public UsuarioDao(Connection oConnection) {
        super(oConnection, "usuario");
    }
}
