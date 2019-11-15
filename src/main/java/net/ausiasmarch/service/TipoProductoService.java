package net.ausiasmarch.service;

import com.google.gson.Gson;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import net.ausiasmarch.bean.ResponseBean;
import net.ausiasmarch.bean.TipoProductoBean;
import net.ausiasmarch.connection.ConnectionInterface;
import net.ausiasmarch.dao.TipoProductoDao;
import net.ausiasmarch.factory.ConnectionFactory;
import net.ausiasmarch.factory.GsonFactory;
import net.ausiasmarch.setting.ConnectionSettings;

public class TipoProductoService extends GenericService {

    String[] frasesInicio = {"Alimentación ", "Juguetes ", "Electronica ", "Higiene "};
    String[] frasesFinal = {"del hogar. ", "de la empresa. ", "para centros educativos. ", "sanitaria. "};

    public TipoProductoService(HttpServletRequest oRequest) {
        super(oRequest);
    }

    public String fill() throws Exception {
        ConnectionInterface oConnectionImplementation = null;
        Connection oConnection = null;
        try {
            oConnectionImplementation = ConnectionFactory
                    .getConnection(ConnectionSettings.connectionPool);
            oConnection = oConnectionImplementation.newConnection();
            TipoProductoDao oTipoProductoDao = new TipoProductoDao(oConnection);
            Gson oGson = GsonFactory.getGson();
            int numProd = Integer.parseInt(oRequest.getParameter("number"));
            for (int i = 0; i < numProd; i++) {
                TipoProductoBean oTipoProductoBean = new TipoProductoBean();
                oTipoProductoBean.setDescripcion(this.generaTexto(1));
                oTipoProductoDao.insert(oTipoProductoBean);
            }
            ResponseBean oResponseBean = new ResponseBean(200, "Insertados los registros con exito");
            return oGson.toJson(oResponseBean);
        } catch (Exception ex) {
            String msg = this.getClass().getName() + " ob: " + ob + "; fill method : error: " + ex.getMessage();
            throw new Exception(msg, ex);
        } finally {
            if (oConnection != null) {
                oConnection.close();
            }
            if (oConnectionImplementation != null) {
                oConnectionImplementation.disposeConnection();
            }
        }

    }

    private String generaTexto(int longitud) {
        String fraseRandom = "";
        for (int i = 0; i < longitud; i++) {
            fraseRandom += frasesInicio[(int) (Math.random() * frasesInicio.length) + 0];
            fraseRandom += frasesFinal[(int) (Math.random() * frasesFinal.length) + 0];
        }
        return fraseRandom;
    }

}
