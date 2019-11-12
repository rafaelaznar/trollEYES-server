package net.ausiasmarch.bean;

import com.google.gson.annotations.Expose;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class CompraBean implements BeanInterface {

    @Expose
    private Integer id;
    @Expose
    private Integer cantidad;
    @Expose
    private Integer producto_id;
    @Expose
    private Integer factura_id;    
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

    @Override
    public ProductoBean fill(ResultSet oResultSet) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

	


}
