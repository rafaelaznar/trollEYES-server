package net.ausiasmarch.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import net.ausiasmarch.bean.BeanInterface;
import net.ausiasmarch.factory.BeanFactory;
import net.ausiasmarch.setting.ConfigurationSettings;

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
            oBean = oBean.fill(oResultSet, oConnection, ConfigurationSettings.spread);
        } else {
            oBean = null;
        }
        return oBean;
    }

    @Override
    public List<BeanInterface> getAll() throws SQLException {
        Statement stmt = oConnection.createStatement();
        ResultSet oResultSet = stmt.executeQuery("SELECT * FROM producto LIMIT 100");
        List<BeanInterface> listaBean = new ArrayList<BeanInterface>();
        while (oResultSet.next()) {
            BeanInterface oBean = BeanFactory.getBean(ob);
            oBean = oBean.fill(oResultSet, oConnection, ConfigurationSettings.spread);
            listaBean.add(oBean);
        }
        return listaBean;
    }

    @Override
    public int getCount() throws SQLException {
        PreparedStatement oPreparedStatement;
        ResultSet oResultSet;
        String strSQL = "SELECT count(*) FROM " + ob;
        oPreparedStatement = oConnection.prepareStatement(strSQL);
        oResultSet = oPreparedStatement.executeQuery();
        if (oResultSet.next()) {
            return oResultSet.getInt(1);
        } else {
            return -1;
        }
    }

    @Override
    public ArrayList<BeanInterface> getPage(int page, int rpp, List<String> orden) throws SQLException {
        PreparedStatement oPreparedStatement;
        ResultSet oResultSet;
        int offset;

        if (page == 1) {
            offset = 0;
        } else {
            offset = (rpp * page) - rpp;
        }

        if (orden == null) {
            oPreparedStatement = oConnection.prepareStatement("SELECT * FROM " + ob + " LIMIT ? OFFSET ?");
            oPreparedStatement.setInt(1, rpp);
            oPreparedStatement.setInt(2, offset);
        } else {
            String sqlQuery = "SELECT * FROM producto ";
            sqlQuery += "ORDER BY ";
            for (int i = 1; i <= orden.size(); i++) {
                if (orden.get((i - 1)).equalsIgnoreCase("asc")) {
                    sqlQuery += "ASC ";
                } else if (orden.get((i - 1)).equalsIgnoreCase("desc")) {
                    sqlQuery += "DESC ";
                } else {
                    sqlQuery += "? ";
                }
            }
            sqlQuery += "LIMIT ? OFFSET ?";
            oPreparedStatement = oConnection.prepareStatement(sqlQuery);
            BeanInterface oBean = BeanFactory.getBean(ob);
            oPreparedStatement = oBean.orderSQL(orden, oPreparedStatement);
            oPreparedStatement.setInt((orden.size()), rpp);
            oPreparedStatement.setInt((orden.size() + 1), offset);
        }

        oResultSet = oPreparedStatement.executeQuery();

        ArrayList<BeanInterface> listaBean = new ArrayList<>();
        while (oResultSet.next()) {
            BeanInterface oBean = BeanFactory.getBean(ob);
            oBean = oBean.fill(oResultSet, oConnection, ConfigurationSettings.spread);
            listaBean.add(oBean);
        }

        return listaBean;
    }

    @Override
    public Integer insert(BeanInterface oBeanParam) throws SQLException {
        BeanInterface oBean = BeanFactory.getBean(ob);
        PreparedStatement oPreparedStatement;
        String strsql = "INSERT INTO " + ob + oBean.getFieldInsert();
        oPreparedStatement = oConnection.prepareStatement(strsql);
        oPreparedStatement = oBean.setFieldInsert(oBeanParam, oPreparedStatement);
        int iResult = oPreparedStatement.executeUpdate();
        return iResult;
    }

    @Override
    public Integer remove(int id) throws SQLException {
        PreparedStatement oPreparedStament = null;
        int iResult;
        String strSQL = "DELETE FROM " + ob + " WHERE id=?";
        oPreparedStament = oConnection.prepareStatement(strSQL, Statement.RETURN_GENERATED_KEYS);
        oPreparedStament.setInt(1, id);
        iResult = oPreparedStament.executeUpdate();
        return iResult;
    }

    @Override
    public Integer update(BeanInterface oBeanParam) throws SQLException {
        BeanInterface oBean = BeanFactory.getBean(ob);
        PreparedStatement oPreparedStatement = null;
        String strSQL = "UPDATE " + ob + " SET " + oBean.getFieldUpdate() + " WHERE id = ?";
        int iResult;
        oPreparedStatement = oConnection.prepareStatement(strSQL, Statement.RETURN_GENERATED_KEYS);
        oPreparedStatement = oBean.setFieldUpdate(oBeanParam, oPreparedStatement);
        iResult = oPreparedStatement.executeUpdate();
        return iResult;
    }

}
