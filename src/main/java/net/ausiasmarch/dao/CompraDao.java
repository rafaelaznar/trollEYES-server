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
import net.ausiasmarch.bean.CompraBean;
import net.ausiasmarch.bean.UsuarioBean;

public class CompraDao implements DaoInterface {

    Connection oConnection = null;

    public CompraDao(Connection oConnection) {
        this.oConnection = oConnection;
    }

    @Override
    public CompraBean get(int id) throws SQLException {
        PreparedStatement oPreparedStatement;
        ResultSet oResultSet;
        String strSQL = "SELECT * FROM compra WHERE id=?";
        oPreparedStatement = oConnection.prepareStatement(strSQL);
        oPreparedStatement.setInt(1, id);
        oResultSet = oPreparedStatement.executeQuery();
        CompraBean oCompraBean;
        if (oResultSet.next()) {
        	oCompraBean = new CompraBean();
        	oCompraBean.setId(oResultSet.getInt("id"));
        	oCompraBean.setCantidad(oResultSet.getInt("cantidad"));
           
        } else {
        	oCompraBean = null;
        }
        return oCompraBean;
    }

    @Override
    public int getCount() throws SQLException {
        PreparedStatement oPreparedStatement;
        ResultSet oResultSet;
        String strSQL = "SELECT count(*) FROM compra";
        oPreparedStatement = oConnection.prepareStatement(strSQL);
        oResultSet = oPreparedStatement.executeQuery();
        if (oResultSet.next()) {
            return oResultSet.getInt(1);
        } else {
            return -1;
        }
    }

    @Override
    public Integer update(BeanInterface oCompraBeanParam) throws SQLException {
        PreparedStatement oPreparedStatement = null;
        String strSQL = "UPDATE compra SET cantidad = ?, producto_id=?, factura_id=? WHERE id = ?";
        int iResult;
        oPreparedStatement = oConnection.prepareStatement(strSQL, Statement.RETURN_GENERATED_KEYS);
        CompraBean oCompraBean = (CompraBean) oCompraBeanParam;
        oPreparedStatement.setInt(1, oCompraBean.getCantidad()); 
        oPreparedStatement.setInt(2, oCompraBean.getProducto_id());
        oPreparedStatement.setInt(3, oCompraBean.getFactura_id());
        oPreparedStatement.setInt(4, oCompraBean.getId());
        iResult = oPreparedStatement.executeUpdate();
        return iResult;
    }

    @Override
    public List<BeanInterface> getAll() throws SQLException {
        Statement stmt = oConnection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM compra LIMIT 100");
        List<BeanInterface> listaCompraBean = new ArrayList();
        while (rs.next()) {
            CompraBean oCompraBean = new CompraBean();
            oCompraBean.setId(rs.getInt("id"));
            oCompraBean.setCantidad(rs.getInt("cantidad"));
       
            listaCompraBean.add(oCompraBean);        
        }
        return listaCompraBean;
    }

    @Override
    public Integer insert(BeanInterface oCompraBeanParam) throws SQLException {
        PreparedStatement oPreparedStatement;
        String strsql = "INSERT INTO compra (cantidad,producto_id,factura_id) VALUES(?,?,?)";
        oPreparedStatement = oConnection.prepareStatement(strsql);
        CompraBean oCompraBean = (CompraBean) oCompraBeanParam;
        oPreparedStatement.setInt(1, oCompraBean.getCantidad());
        oPreparedStatement.setInt(2, oCompraBean.getProducto_id());
        oPreparedStatement.setInt(3, oCompraBean.getFactura_id());

        //oPreparedStatement.setDate(4, (Date) oPostBean.getFecha());
        int iResult = oPreparedStatement.executeUpdate();
        return iResult;
    }

    @Override
    public Integer remove(int id) throws SQLException {
        PreparedStatement oPreparedStament = null;
        String strSQL = "";
        int iResult;
        strSQL = "DELETE ";
        strSQL += " FROM compra ";
        strSQL += " WHERE id=?";
        oPreparedStament = oConnection.prepareStatement(strSQL, Statement.RETURN_GENERATED_KEYS);
        oPreparedStament.setInt(1, id);
        iResult = oPreparedStament.executeUpdate();
        return iResult;
    }

    @Override
    public ArrayList<CompraBean> getPage(int page, int limit, List<String> orden) throws SQLException {

        PreparedStatement oPreparedStatement;
        ResultSet oResultSet;
        int offset;

        if (page == 1) {
            offset = 0;
        } else {
            offset = (limit * page) - limit;
        }

        if (orden == null) {
        	oPreparedStatement = oConnection.prepareStatement("SELECT * FROM compra LIMIT ? OFFSET ?");
        	oPreparedStatement.setInt(1, limit);
            oPreparedStatement.setInt(2, offset);
        } else {
        	String sqlQuery = "SELECT * FROM compra ";
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
        		} else if (orden.get((i-1)).equalsIgnoreCase("cantidad")) {
        			oPreparedStatement.setInt(i, 2);
        		}
        		
        	}
        	oPreparedStatement.setInt((orden.size()), limit);
            oPreparedStatement.setInt((orden.size()+1), offset);
        }
        
        oResultSet = oPreparedStatement.executeQuery();

        ArrayList<CompraBean> oCompraBeanList = new ArrayList<>();
        while (oResultSet.next()) {
        	CompraBean oCompraBean = new CompraBean();
        	oCompraBean.setId(oResultSet.getInt("id"));
        	oCompraBean.setCantidad(oResultSet.getInt("cantidad"));
           

        	oCompraBeanList.add(oCompraBean);
        }

        return oCompraBeanList;
    }

}
