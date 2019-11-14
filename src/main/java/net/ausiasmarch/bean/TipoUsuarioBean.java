package net.ausiasmarch.bean;

import com.google.gson.annotations.Expose;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TipoUsuarioBean implements BeanInterface {

    @Expose
    private Integer id;
    @Expose
    private String descripcion;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

    @Override
    public TipoUsuarioBean fill(ResultSet oResultSet) throws SQLException {
    	 this.setId(oResultSet.getInt("id"));
         this.setDescripcion(oResultSet.getString("descripcion"));
         return this;
    }

	@Override
	public PreparedStatement orderSQL(List<String> orden, PreparedStatement oPreparedStatement) throws SQLException {
		for (int i = 1; i < orden.size(); i++) {
    		if (orden.get((i-1)).equalsIgnoreCase("id")) {
    			oPreparedStatement.setInt(i, 1);
    		} else if (orden.get((i-1)).equalsIgnoreCase("descripcion")) {
    			oPreparedStatement.setInt(i, 2);
    		}
    		
    	}
		return oPreparedStatement;
	}

	@Override
	public String getFieldInsert() {
		return "tipo_usuario (descripcion) VALUES(?)";
	}

	@Override
	public PreparedStatement setFieldInsert(BeanInterface oBeanParam, PreparedStatement oPreparedStatement)
			throws SQLException {
		TipoUsuarioBean oTipoUsuarioBean = (TipoUsuarioBean) oBeanParam;
        oPreparedStatement.setString(1, oTipoUsuarioBean.getDescripcion());
		return oPreparedStatement;
	}

	@Override
	public String getFieldUpdate() {
		return "descripcion = ?";
	}

	@Override
	public PreparedStatement setFieldUpdate(BeanInterface oBeanParam, PreparedStatement oPreparedStatement)
			throws SQLException {
		TipoUsuarioBean oTipoUsuarioBean = (TipoUsuarioBean) oBeanParam;
        oPreparedStatement.setString(1, oTipoUsuarioBean.getDescripcion());
        oPreparedStatement.setInt(2, oTipoUsuarioBean.getId());
		return oPreparedStatement;
	}



}
