package net.ausiasmarch.bean;

import com.google.gson.annotations.Expose;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import net.ausiasmarch.dao.FacturaDao;
import net.ausiasmarch.dao.ProductoDao;

public class CompraBean implements BeanInterface {

    @Expose
    private Integer id;
    @Expose
    private Integer cantidad;
    @Expose(serialize = false)
    private Integer producto_id;
    @Expose(serialize = false)
    private Integer factura_id;
    @Expose(deserialize = false)
    private ProductoBean producto_obj;
    @Expose(deserialize = false)
    private FacturaBean factura_obj;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getProducto_id() {
        return producto_id;
    }

    public void setProducto_id(Integer producto_id) {
        this.producto_id = producto_id;
    }

    public Integer getFactura_id() {
        return factura_id;
    }

    public void setFactura_id(Integer factura_id) {
        this.factura_id = factura_id;
    }

    public ProductoBean getProducto_obj() {
        return producto_obj;
    }

    public void setProducto_obj(ProductoBean producto_obj) {
        this.producto_obj = producto_obj;
    }

    public FacturaBean getFactura_obj() {
        return factura_obj;
    }

    public void setFactura_obj(FacturaBean factura_obj) {
        this.factura_obj = factura_obj;
    }

    @Override
    public CompraBean fill(ResultSet oResultSet, Connection oConnection, int spread) throws SQLException {
        this.setId(oResultSet.getInt("id"));
        this.setCantidad(oResultSet.getInt("cantidad"));
        this.setFactura_id(oResultSet.getInt("factura_id"));
        this.setProducto_id(oResultSet.getInt("producto_id"));

        if (spread > 0) {
            spread--;
            ProductoDao oProductoDao = new ProductoDao(oConnection);
            ProductoBean oProductoBean = new ProductoBean();
            oProductoBean = (ProductoBean) oProductoDao.get(this.producto_id);
            this.producto_obj = oProductoBean;

            FacturaDao oFacturaDao = new FacturaDao(oConnection);
            FacturaBean oFacturaBean = new FacturaBean();
            oFacturaBean = (FacturaBean) oFacturaDao.get(this.factura_id);
            this.factura_obj = oFacturaBean;
        }
        return this;
    }

    @Override
    public PreparedStatement orderSQL(List<String> orden, PreparedStatement oPreparedStatement) throws SQLException {
        for (int i = 1; i < orden.size(); i++) {
            if (orden.get((i - 1)).equalsIgnoreCase("id")) {
                oPreparedStatement.setInt(i, 1);
            } else if (orden.get((i - 1)).equalsIgnoreCase("cantidad")) {
                oPreparedStatement.setInt(i, 2);
            } else if (orden.get((i - 1)).equalsIgnoreCase("factura_id")) {
                oPreparedStatement.setInt(i, 3);
            } else if (orden.get((i - 1)).equalsIgnoreCase("producto_id")) {
                oPreparedStatement.setInt(i, 4);
            }
        }
        return oPreparedStatement;
    }

    @Override
    public String getFieldInsert() {
        return " (cantidad,producto_id,factura_id) VALUES(?,?,?)";
    }

    @Override
    public PreparedStatement setFieldInsert(BeanInterface oBeanParam, PreparedStatement oPreparedStatement)
            throws SQLException {
        CompraBean oCompraBean = (CompraBean) oBeanParam;
        oPreparedStatement.setInt(1, oCompraBean.getCantidad());
        oPreparedStatement.setInt(2, oCompraBean.getProducto_id());
        oPreparedStatement.setInt(3, oCompraBean.getFactura_id());
        return oPreparedStatement;
    }

    @Override
    public String getFieldUpdate() {
        return " (cantidad,producto_id,factura_id) VALUES(?,?,?)";
    }

    @Override
    public PreparedStatement setFieldUpdate(BeanInterface oBeanParam, PreparedStatement oPreparedStatement)
            throws SQLException {
        CompraBean oCompraBean = (CompraBean) oBeanParam;
        oPreparedStatement.setInt(1, oCompraBean.getCantidad());
        oPreparedStatement.setInt(2, oCompraBean.getProducto_id());
        oPreparedStatement.setInt(3, oCompraBean.getFactura_id());
        oPreparedStatement.setInt(4, oCompraBean.getId());
        return oPreparedStatement;
    }
}