
package net.ausiasmarch.bean;

import com.google.gson.annotations.Expose;

public class ItemBean {
    @Expose
    private int id;
    @Expose
    private int cantidad;

    public ItemBean(int id, int cantidad) {
        this.id = id;
        this.cantidad = cantidad;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
}
