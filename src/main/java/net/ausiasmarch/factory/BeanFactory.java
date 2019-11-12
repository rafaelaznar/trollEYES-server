package net.ausiasmarch.factory;

import com.google.gson.Gson;
import net.ausiasmarch.bean.BeanInterface;
import net.ausiasmarch.bean.ProductoBean;

public class BeanFactory {

    public static BeanInterface getBean(String ob) {
        BeanInterface oBean = null;
        switch (ob) {
            case "producto":
                oBean = new ProductoBean();
                break;
        }
        return oBean;
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
