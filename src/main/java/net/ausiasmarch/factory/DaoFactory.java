package net.ausiasmarch.factory;

import java.sql.Connection;
import net.ausiasmarch.dao.CompraDao;
import net.ausiasmarch.dao.DaoInterface;
import net.ausiasmarch.dao.FacturaDao;
import net.ausiasmarch.dao.ProductoDao;
import net.ausiasmarch.dao.TipoProductoDao;
import net.ausiasmarch.dao.TipoUsuarioDao;
import net.ausiasmarch.dao.UsuarioDao;

public class DaoFactory {
      public static DaoInterface getDao(String ob,Connection oConnection) {
        DaoInterface oDao = null;
        switch (ob) {
            case "producto":
                oDao = new ProductoDao(oConnection, ob);
                break;
            case "compra":
                oDao = new CompraDao(oConnection, ob);
                break;
            case "factura":
                oDao = new FacturaDao(oConnection, ob);
                break;
            case "tipoproducto":
                oDao = new TipoProductoDao(oConnection, ob);
                break;
            case "tipousuario":
                oDao = new TipoUsuarioDao(oConnection, ob);
                break;
            case "usuario":
                oDao = new UsuarioDao(oConnection, ob);
                break;
        }
        return oDao ;
    }
}