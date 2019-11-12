package net.ausiasmarch.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import net.ausiasmarch.bean.BeanInterface;
import net.ausiasmarch.bean.ProductoBean;
import net.ausiasmarch.factory.BeanFactory;

public class GenericDao implements DaoInterface {

    Connection oConnection = null;
    String ob = null;

    public GenericDao(Connection oConnection, String ob) {
        this.oConnection = oConnection;
        this.ob = ob;
    }

    @Override
    public BeanInterface get(int id) throws SQLException {
        PreparedStatement oPreparedStatement;
        ResultSet oResultSet;
        String strSQL = "SELECT * FROM " + ob + " WHERE id=?";
        oPreparedStatement = oConnection.prepareStatement(strSQL);
        oPreparedStatement.setInt(1, id);
        oResultSet = oPreparedStatement.executeQuery();
        BeanInterface oBean;
        if (oResultSet.next()) {
            oBean = BeanFactory.getBean(ob);
            oBean = oBean.fill(oResultSet);
        } else {
            oBean = null;
        }
        return oBean;
    }

    @Override
    public List<BeanInterface> getAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getCount() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList getPage(int page, int rpp, List<String> orden) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer insert(BeanInterface oBean) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer remove(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer update(BeanInterface oBean) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
