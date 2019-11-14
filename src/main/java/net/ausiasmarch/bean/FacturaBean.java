package net.ausiasmarch.bean;

import com.google.gson.annotations.Expose;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class FacturaBean implements BeanInterface {

    @Expose
    private Integer id;
    @Expose
    private Date fecha;
    @Expose
    private Integer iva;
    @Expose
    private Integer usuario_id;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Integer getIva() {
		return iva;
	}

	public void setIva(Integer iva) {
		this.iva = iva;
	}

	public Integer getUsuario_id() {
		return usuario_id;
	}

	public void setUsuario_id(Integer usuario_id) {
		this.usuario_id = usuario_id;
	}

    @Override
    public FacturaBean fill(ResultSet oResultSet) throws SQLException {
    	 this.setId(oResultSet.getInt("id"));
         this.setFecha(oResultSet.getDate("fecha"));
         this.setIva(oResultSet.getInt("iva"));
         return this;
    }

	@Override
	public PreparedStatement orderSQL(List<String> orden, PreparedStatement oPreparedStatement) throws SQLException {
		for (int i = 1; i < orden.size(); i++) {
    		if (orden.get((i-1)).equalsIgnoreCase("id")) {
    			oPreparedStatement.setInt(i, 1);
    		} else if (orden.get((i-1)).equalsIgnoreCase("fecha")) {
    			oPreparedStatement.setInt(i, 2);
    		} else if (orden.get((i-1)).equalsIgnoreCase("iva")) {
    			oPreparedStatement.setInt(i, 3);
    		}
		}
		return oPreparedStatement;
	}

	@Override
	public String getFieldInsert() {
		return "(fecha,iva,usuario_id) VALUES(?,?,?)";
	}

	@Override
	public PreparedStatement setFieldInsert(BeanInterface oBeanParam, PreparedStatement oPreparedStatement)
			throws SQLException {
		FacturaBean oFacturaBean = (FacturaBean) oBeanParam;
        oPreparedStatement.setDate(1, new java.sql.Date(oFacturaBean.getFecha().getTime()));
        oPreparedStatement.setInt(2, oFacturaBean.getIva());
        oPreparedStatement.setInt(3, oFacturaBean.getUsuario_id());
        return oPreparedStatement;
	}

	@Override
	public String getFieldUpdate() {
		return "fecha = ?, iva = ?, usuario_id= ?";
	}

	@Override
	public PreparedStatement setFieldUpdate(BeanInterface oBeanParam, PreparedStatement oPreparedStatement)
			throws SQLException {
		FacturaBean oFacturaBean = (FacturaBean) oBeanParam;
        oPreparedStatement.setDate(1, (java.sql.Date) oFacturaBean.getFecha());
        oPreparedStatement.setInt(2, oFacturaBean.getIva());
        oPreparedStatement.setInt(3, oFacturaBean.getUsuario_id());
        oPreparedStatement.setInt(4, oFacturaBean.getId());
        return oPreparedStatement;
	}

    

	}
