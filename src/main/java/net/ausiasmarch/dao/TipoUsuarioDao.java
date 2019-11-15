package net.ausiasmarch.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import net.ausiasmarch.bean.BeanInterface;
import net.ausiasmarch.bean.TipoUsuarioBean;

public class TipoUsuarioDao implements DaoInterface {

    Connection oConnection = null;

    public TipoUsuarioDao(Connection oConnection) {
        this.oConnection = oConnection;
    }

    @Override
    public TipoUsuarioBean get(int id) throws SQLException {
        PreparedStatement oPreparedStatement;
        ResultSet oResultSet;
        String strSQL = "SELECT * FROM tipo_usuario WHERE id=?";
        oPreparedStatement = oConnection.prepareStatement(strSQL);
        oPreparedStatement.setInt(1, id);
        oResultSet = oPreparedStatement.executeQuery();
        TipoUsuarioBean oTipoUsuarioBean;
        if (oResultSet.next()) {
            oTipoUsuarioBean = new TipoUsuarioBean();
            oTipoUsuarioBean.setId(oResultSet.getInt("id"));
            oTipoUsuarioBean.setDescripcion(oResultSet.getString("descripcion"));
        } else {
            oTipoUsuarioBean = null;
        }
        return oTipoUsuarioBean;
    }

    @Override
    public int getCount() throws SQLException {
        PreparedStatement oPreparedStatement;
        ResultSet oResultSet;
        String strSQL = "SELECT count(*) FROM tipo_usuario";
        oPreparedStatement = oConnection.prepareStatement(strSQL);
        oResultSet = oPreparedStatement.executeQuery();
        if (oResultSet.next()) {
            return oResultSet.getInt(1);
        } else {
            return -1;
        }
    }

    @Override
    public Integer update(BeanInterface oTipoUsuarioBeanParam) throws SQLException {
        PreparedStatement oPreparedStatement = null;
        String strSQL = "UPDATE tipo_usuario SET descripcion = ? WHERE id = ?";
        int iResult;
        oPreparedStatement = oConnection.prepareStatement(strSQL, Statement.RETURN_GENERATED_KEYS);
        TipoUsuarioBean oTipoUsuarioBean = (TipoUsuarioBean) oTipoUsuarioBeanParam;
        oPreparedStatement.setString(1, oTipoUsuarioBean.getDescripcion());
        oPreparedStatement.setInt(2, oTipoUsuarioBean.getId());
        iResult = oPreparedStatement.executeUpdate();
        return iResult;
    }

    @Override
    public List<BeanInterface> getAll() throws SQLException {
        Statement stmt = oConnection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM tipo_usuario LIMIT 100");
        List<BeanInterface> listaTipoUsuarioBean = new ArrayList();
        while (rs.next()) {
            TipoUsuarioBean oTipoUsuarioBean = new TipoUsuarioBean();
            oTipoUsuarioBean.setId(rs.getInt("id"));
            oTipoUsuarioBean.setDescripcion(rs.getString("titulo"));
            listaTipoUsuarioBean.add(oTipoUsuarioBean);        
        }
        return listaTipoUsuarioBean;
    }

    @Override
    public Integer insert(BeanInterface oTipoUsuarioBeanParam) throws SQLException {
        PreparedStatement oPreparedStatement;
        String strsql = "INSERT INTO tipo_usuario (descripcion) VALUES(?)";
        oPreparedStatement = oConnection.prepareStatement(strsql);
        TipoUsuarioBean oTipoUsuarioBean = (TipoUsuarioBean) oTipoUsuarioBeanParam;
        oPreparedStatement.setString(1, oTipoUsuarioBean.getDescripcion());
        int iResult = oPreparedStatement.executeUpdate();
        return iResult;
    }

    @Override
    public Integer remove(int id) throws SQLException {
        PreparedStatement oPreparedStament = null;
        String strSQL = "";
        int iResult;
        strSQL = "DELETE ";
        strSQL += " FROM tipo_usuario ";
        strSQL += " WHERE id=?";
        oPreparedStament = oConnection.prepareStatement(strSQL, Statement.RETURN_GENERATED_KEYS);
        oPreparedStament.setInt(1, id);
        iResult = oPreparedStament.executeUpdate();
        return iResult;
    }

    @Override
    public ArrayList<TipoUsuarioBean> getPage(int page, int limit, List<String> orden) throws SQLException {

        PreparedStatement oPreparedStatement;
        ResultSet oResultSet;
        int offset;

        if (page == 1) {
            offset = 0;
        } else {
            offset = (limit * page) - limit;
        }

        if (orden == null) {
        	oPreparedStatement = oConnection.prepareStatement("SELECT * FROM tipo_usuario LIMIT ? OFFSET ?");
        	oPreparedStatement.setInt(1, limit);
            oPreparedStatement.setInt(2, offset);
        } else {
        	String sqlQuery = "SELECT * FROM tipo_usuario ";
        	sqlQuery += "ORDER BY ";
        	for (int i = 1; i <= orden.size(); i++) {
        		if (orden.get((i-1)).equalsIgnoreCase("asc")) {
        			sqlQuery += "ASC ";
        		} else if (orden.get((i-1)).equalsIgnoreCase("desc")) {
        			sqlQuery += "DESC ";
        		} else {
        			sqlQuery += "? ";
        		}
        	}
        	sqlQuery += "LIMIT ? OFFSET ?";
        	oPreparedStatement = oConnection.prepareStatement(sqlQuery);
        	for (int i = 1; i < orden.size(); i++) {
        		if (orden.get((i-1)).equalsIgnoreCase("id")) {
        			oPreparedStatement.setInt(i, 1);
        		} else if (orden.get((i-1)).equalsIgnoreCase("descripcion")) {
        			oPreparedStatement.setInt(i, 2);
        		}
        		
        	}
        	oPreparedStatement.setInt((orden.size()), limit);
            oPreparedStatement.setInt((orden.size()+1), offset);
        }
        
        oResultSet = oPreparedStatement.executeQuery();

        ArrayList<TipoUsuarioBean> oTipoUsuarioBeanList = new ArrayList<>();
        while (oResultSet.next()) {
            TipoUsuarioBean oTipoUsuarioBean = new TipoUsuarioBean();
            oTipoUsuarioBean.setId(oResultSet.getInt("id"));
            oTipoUsuarioBean.setDescripcion(oResultSet.getString("descripcion"));

            oTipoUsuarioBeanList.add(oTipoUsuarioBean);
        }

        return oTipoUsuarioBeanList;
    }

}
