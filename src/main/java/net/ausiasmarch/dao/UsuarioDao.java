package net.ausiasmarch.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import net.ausiasmarch.bean.UsuarioBean;
import net.ausiasmarch.setting.ConfigurationSettings;

public class UsuarioDao extends GenericDao implements DaoInterface {

    public UsuarioDao(Connection oConnection) {
        super(oConnection, "usuario");
    }

    public UsuarioBean get(String username, String password) throws Exception {
        String strSQL = "SELECT * FROM usuario WHERE login=? AND password=?";
        UsuarioBean oUsuarioBean;
        ResultSet oResultSet = null;
        PreparedStatement oPreparedStatement = null;
        try {
            oPreparedStatement = oConnection.prepareStatement(strSQL);
            oPreparedStatement.setString(1, username);
            oPreparedStatement.setString(2, password);
            oResultSet = oPreparedStatement.executeQuery();
            if (oResultSet.next()) {
                oUsuarioBean = new UsuarioBean();
                oUsuarioBean.fill(oResultSet, oConnection, ConfigurationSettings.spread);
            } else {
                oUsuarioBean = null;
            }
        } catch (SQLException e) {
            throw new Exception("Error en Dao get de " + ob, e);
        } finally {
            if (oResultSet != null) {
                oResultSet.close();
            }
            if (oPreparedStatement != null) {
                oPreparedStatement.close();
            }
        }
        return oUsuarioBean;
    }

}
