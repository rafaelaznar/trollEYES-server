package net.ausiasmarch.bean;

import com.google.gson.annotations.Expose;
import java.util.Date;

public class FacturaBean implements BeanInterface {

    @Expose
    private Integer id;
    @Expose
    private Date fecha;
    @Expose
    private Integer iva;

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

    

}
