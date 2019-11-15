package net.ausiasmarch.factory;

import com.google.gson.Gson;
import net.ausiasmarch.bean.BeanInterface;
import net.ausiasmarch.bean.CompraBean;
import net.ausiasmarch.bean.FacturaBean;
import net.ausiasmarch.bean.ProductoBean;
import net.ausiasmarch.bean.TipoProductoBean;
import net.ausiasmarch.bean.TipoUsuarioBean;
import net.ausiasmarch.bean.UsuarioBean;

public class BeanFactory {

    public static BeanInterface getBean(String ob) {
        BeanInterface oBean = null;
        switch (ob) {
            case "producto":
                oBean = new ProductoBean();
                break;
            case "compra":
            	oBean = new CompraBean();
            	break;
            case "factura":
            	oBean = new FacturaBean();
            	break;
            case "tipo_producto":
            	oBean = new TipoProductoBean();
            	break;
            case "tipo_usuario":
            	oBean = new TipoUsuarioBean();
            	break;
            case "usuario":
            	oBean = new UsuarioBean();
            	break;
        }
        return oBean ;
    }

    public static BeanInterface getBeanFromJson(String ob, String data) {
        BeanInterface oBean = null;
        Gson oGson = GsonFactory.getGson();
        switch (ob) {
            case "producto":
                oBean = oGson.fromJson(data, ProductoBean.class);
                break;
        }
        return oBean;
    }
    
}
