package net.ausiasmarch.factory;

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
        return oBean ;
    }
    
}
