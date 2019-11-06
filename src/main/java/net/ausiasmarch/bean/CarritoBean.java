package net.ausiasmarch.bean;

public class CarritoBean {

	int producto;
	int cantidad;
	public int getProducto() {
		return producto;
	}
	public void setProducto(int producto) {
		this.producto = producto;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	@Override
	public String toString() {
		return "[producto=" + producto + ", cantidad=" + cantidad + "]";
	}
	
	
}
