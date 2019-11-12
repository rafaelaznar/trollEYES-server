
package net.ausiasmarch.factory;

import java.sql.Connection;
import net.ausiasmarch.dao.DaoInterface;
import net.ausiasmarch.dao.ProductoDao;


public class DaoFactory {
      public static DaoInterface getDao(String ob,Connection oConnection) {
        DaoInterface oDao = null;
        switch (ob) {
            case "producto":
                oDao = new ProductoDao(oConnection);
                break;
        }
        return oDao ;
    }
}
