package net.ausiasmarch.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import net.ausiasmarch.bean.BeanInterface;
import net.ausiasmarch.bean.FacturaBean;

public class FacturaDao implements DaoInterface {

    Connection oConnection = null;

    public FacturaDao(Connection oConnection) {
        this.oConnection = oConnection;
    }

    @Override
    public FacturaBean get(int id) throws SQLException {
        PreparedStatement oPreparedStatement;
        ResultSet oResultSet;
        String strSQL = "SELECT * FROM factura WHERE id=?";
        oPreparedStatement = oConnection.prepareStatement(strSQL);
        oPreparedStatement.setInt(1, id);
        oResultSet = oPreparedStatement.executeQuery();
        FacturaBean oFacturaBean;
        if (oResultSet.next()) {
            oFacturaBean = new FacturaBean();
            oFacturaBean.setId(oResultSet.getInt("id"));
            oFacturaBean.setFecha(oResultSet.getDate("fecha"));
            oFacturaBean.setIva(oResultSet.getInt("iva"));
        } else {
            oFacturaBean = null;
        }
        return oFacturaBean;
    }

    @Override
    public int getCount() throws SQLException {
        PreparedStatement oPreparedStatement;
        ResultSet oResultSet;
        String strSQL = "SELECT count(*) FROM factura";
        oPreparedStatement = oConnection.prepareStatement(strSQL);
        oResultSet = oPreparedStatement.executeQuery();
        if (oResultSet.next()) {
            return oResultSet.getInt(1);
        } else {
            return -1;
        }
    }

    @Override
    public Integer update(BeanInterface oFacturaBeanParam) throws SQLException {
        PreparedStatement oPreparedStatement = null;
        String strSQL = "UPDATE factura SET fecha = ?, iva = ?, usuario_id= ? WHERE id = ?";
        int iResult;
        oPreparedStatement = oConnection.prepareStatement(strSQL, Statement.RETURN_GENERATED_KEYS);
        FacturaBean oFacturaBean = (FacturaBean) oFacturaBeanParam;
        oPreparedStatement.setDate(1, (Date) oFacturaBean.getFecha());
        oPreparedStatement.setInt(2, oFacturaBean.getIva());
        oPreparedStatement.setInt(3, oFacturaBean.getUsuario_id());
        oPreparedStatement.setInt(4, oFacturaBean.getId());
        iResult = oPreparedStatement.executeUpdate();
        return iResult;
    }

    @Override
    public List<BeanInterface> getAll() throws SQLException {
        Statement stmt = oConnection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM factura LIMIT 100");
        List<BeanInterface> listaFacturaBean = new ArrayList();
        while (rs.next()) {
            FacturaBean oFacturaBean = new FacturaBean();
            oFacturaBean.setId(rs.getInt("id"));
            oFacturaBean.setFecha(rs.getDate("fecha"));
            oFacturaBean.setIva(rs.getInt("iva"));
            listaFacturaBean.add(oFacturaBean);        
        }
        return listaFacturaBean;
    }

    @Override
    public Integer insert(BeanInterface oFacturaBeanParam) throws SQLException {
        PreparedStatement oPreparedStatement;
        String strsql = "INSERT INTO factura (fecha,iva,usuario_id) VALUES(?,?,?)";
        oPreparedStatement = oConnection.prepareStatement(strsql);   
        FacturaBean oFacturaBean = (FacturaBean) oFacturaBeanParam;
        oPreparedStatement.setDate(1, new java.sql.Date(oFacturaBean.getFecha().getTime()));
        oPreparedStatement.setInt(2, oFacturaBean.getIva());
        oPreparedStatement.setInt(3, oFacturaBean.getUsuario_id());
        int iResult = oPreparedStatement.executeUpdate();
        return iResult;
    }

    @Override
    public Integer remove(int id) throws SQLException {
        PreparedStatement oPreparedStament = null;
        String strSQL = "";
        int iResult;
        strSQL = "DELETE ";
        strSQL += " FROM factura ";
        strSQL += " WHERE id=?";
        oPreparedStament = oConnection.prepareStatement(strSQL, Statement.RETURN_GENERATED_KEYS);
        oPreparedStament.setInt(1, id);
        iResult = oPreparedStament.executeUpdate();
        return iResult;
    }

    @Override
    public ArrayList<FacturaBean> getPage(int page, int limit, List<String> orden) throws SQLException {

        PreparedStatement oPreparedStatement;
        ResultSet oResultSet;
        int offset;

        if (page == 1) {
            offset = 0;
        } else {
            offset = (limit * page) - limit;
        }

        if (orden == null) {
        	oPreparedStatement = oConnection.prepareStatement("SELECT * FROM factura LIMIT ? OFFSET ?");
        	oPreparedStatement.setInt(1, limit);
            oPreparedStatement.setInt(2, offset);
        } else {
        	String sqlQuery = "SELECT * FROM factura ";
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
        		} else if (orden.get((i-1)).equalsIgnoreCase("fecha")) {
        			oPreparedStatement.setInt(i, 2);
        		} else if (orden.get((i-1)).equalsIgnoreCase("iva")) {
        			oPreparedStatement.setInt(i, 3);
        		}
        		
        	}
        	oPreparedStatement.setInt((orden.size()), limit);
            oPreparedStatement.setInt((orden.size()+1), offset);
        }
        
        oResultSet = oPreparedStatement.executeQuery();

        ArrayList<FacturaBean> oFacturaBeanList = new ArrayList<>();
        while (oResultSet.next()) {
            FacturaBean oFacturaBean = new FacturaBean();
            oFacturaBean.setId(oResultSet.getInt("id"));
            oFacturaBean.setFecha(oResultSet.getDate("fecha"));
            oFacturaBean.setIva(oResultSet.getInt("iva"));

            oFacturaBeanList.add(oFacturaBean);
        }

        return oFacturaBeanList;
    }

}
