package net.ausiasmarch.bean;

import com.google.gson.annotations.Expose;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class ProductoBean implements BeanInterface {

    @Expose
    private Integer id;
    @Expose
    private String codigo;
    @Expose
    private Integer existencias;
    @Expose
    private Double precio;
    @Expose
    private String imagen;
    @Expose
    private String descripcion;
    @Expose
    private Integer tipo_producto_id;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getExistencias() {
        return existencias;
    }

    public void setExistencias(Integer existencias) {
        this.existencias = existencias;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getTipo_producto_id() {
        return tipo_producto_id;
    }

    public void setTipo_producto_id(Integer tipo_producto_id) {
        this.tipo_producto_id = tipo_producto_id;
    }

    public ProductoBean fill(ResultSet oResultSet) throws SQLException {
        this.setId(oResultSet.getInt("id"));
        this.setCodigo(oResultSet.getString("codigo"));
        this.setExistencias(oResultSet.getInt("existencias"));
        this.setPrecio(oResultSet.getDouble("precio"));
        this.setImagen(oResultSet.getString("imagen"));
        this.setDescripcion(oResultSet.getString("descripcion"));
        return this;
    }

}
