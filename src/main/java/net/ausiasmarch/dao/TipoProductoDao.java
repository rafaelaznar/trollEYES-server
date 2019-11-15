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
import net.ausiasmarch.bean.TipoProductoBean;

public class TipoProductoDao implements DaoInterface {

    Connection oConnection = null;

    public TipoProductoDao(Connection oConnection) {
        this.oConnection = oConnection;
    }

    @Override
    public TipoProductoBean get(int id) throws SQLException {
        PreparedStatement oPreparedStatement;
        ResultSet oResultSet;
        String strSQL = "SELECT * FROM tipo_producto WHERE id=?";
        oPreparedStatement = oConnection.prepareStatement(strSQL);
        oPreparedStatement.setInt(1, id);
        oResultSet = oPreparedStatement.executeQuery();
        TipoProductoBean oTipoProductoBean;
        if (oResultSet.next()) {
            oTipoProductoBean = new TipoProductoBean();
            oTipoProductoBean.setId(oResultSet.getInt("id"));
            oTipoProductoBean.setDescripcion(oResultSet.getString("descripcion"));
        } else {
            oTipoProductoBean = null;
        }
        return oTipoProductoBean;
    }

    @Override
    public int getCount() throws SQLException {
        PreparedStatement oPreparedStatement;
        ResultSet oResultSet;
        String strSQL = "SELECT count(*) FROM tipo_producto";
        oPreparedStatement = oConnection.prepareStatement(strSQL);
        oResultSet = oPreparedStatement.executeQuery();
        if (oResultSet.next()) {
            return oResultSet.getInt(1);
        } else {
            return -1;
        }
    }

    @Override
    public Integer update(BeanInterface oTipoProductoBeanParam) throws SQLException {
        PreparedStatement oPreparedStatement = null;
        String strSQL = "UPDATE tipo_producto SET descripcion = ? WHERE id = ?";
        int iResult;
        oPreparedStatement = oConnection.prepareStatement(strSQL, Statement.RETURN_GENERATED_KEYS);
        TipoProductoBean oTipoProductoBean = (TipoProductoBean) oTipoProductoBeanParam;
        oPreparedStatement.setString(1, oTipoProductoBean.getDescripcion());
        oPreparedStatement.setInt(2, oTipoProductoBean.getId());
        iResult = oPreparedStatement.executeUpdate();
        return iResult;
    }

    @Override
    public List<BeanInterface> getAll() throws SQLException {
        Statement stmt = oConnection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM tipo_producto LIMIT 100");
        List<BeanInterface> listaTipoProductoBean = new ArrayList();
        while (rs.next()) {
            TipoProductoBean oTipoProductoBean = new TipoProductoBean();
            oTipoProductoBean.setId(rs.getInt("id"));
            oTipoProductoBean.setDescripcion(rs.getString("descripcion"));
            listaTipoProductoBean.add(oTipoProductoBean);        
        }
        return listaTipoProductoBean;
    }

    @Override
    public Integer insert(BeanInterface oTipoProductoBeanParam) throws SQLException {
        PreparedStatement oPreparedStatement;
        String strsql = "INSERT INTO tipo_producto (descripcion) VALUES(?)";
        oPreparedStatement = oConnection.prepareStatement(strsql);
        TipoProductoBean oTipoProductoBean = (TipoProductoBean) oTipoProductoBeanParam;
        oPreparedStatement.setString(1, oTipoProductoBean.getDescripcion());
      
        int iResult = oPreparedStatement.executeUpdate();
        return iResult;
    }

    @Override
    public Integer remove(int id) throws SQLException {
        PreparedStatement oPreparedStament = null;
        String strSQL = "";
        int iResult;
        strSQL = "DELETE ";
        strSQL += " FROM tipo_producto ";
        strSQL += " WHERE id=?";
        oPreparedStament = oConnection.prepareStatement(strSQL, Statement.RETURN_GENERATED_KEYS);
        oPreparedStament.setInt(1, id);
        iResult = oPreparedStament.executeUpdate();
        return iResult;
    }

    @Override
    public ArrayList<TipoProductoBean> getPage(int page, int limit, List<String> orden) throws SQLException {

        PreparedStatement oPreparedStatement;
        ResultSet oResultSet;
        int offset;

        if (page == 1) {
            offset = 0;
        } else {
            offset = (limit * page) - limit;
        }

        if (orden == null) {
        	oPreparedStatement = oConnection.prepareStatement("SELECT * FROM tipo_producto LIMIT ? OFFSET ?");
        	oPreparedStatement.setInt(1, limit);
            oPreparedStatement.setInt(2, offset);
        } else {
        	String sqlQuery = "SELECT * FROM tipo_producto ";
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

        ArrayList<TipoProductoBean> oTipoProductoBeanList = new ArrayList<>();
        while (oResultSet.next()) {
            TipoProductoBean oTipoProductoBean = new TipoProductoBean();
            oTipoProductoBean.setId(oResultSet.getInt("id"));
            oTipoProductoBean.setDescripcion(oResultSet.getString("descripcion"));
            oTipoProductoBeanList.add(oTipoProductoBean);
        }

        return oTipoProductoBeanList;
    }

}
