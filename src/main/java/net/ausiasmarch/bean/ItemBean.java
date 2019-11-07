package net.ausiasmarch.bean;

import com.google.gson.annotations.Expose;

public class ItemBean implements BeanInterface{

	@Expose
	int id;
	@Expose
	int cantidad;
	public ItemBean(int id, int cantidad) {
        this.id = id;
        this.cantidad = cantidad;
    }

	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	@Override
	public Integer getId() {
		return id;
	}
	@Override
	public void setId(Integer id) {
		this.id = id;
		
	}
	
	
}
