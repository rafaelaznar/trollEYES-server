package net.ausiasmarch.bean;

import com.google.gson.annotations.Expose;
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


    

}
