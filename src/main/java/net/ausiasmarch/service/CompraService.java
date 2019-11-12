package net.ausiasmarch.service;

import com.google.gson.Gson;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import net.ausiasmarch.bean.CompraBean;
import net.ausiasmarch.bean.ResponseBean;
import net.ausiasmarch.connection.ConnectionInterface;
import net.ausiasmarch.dao.CompraDao;
import net.ausiasmarch.factory.ConnectionFactory;
import net.ausiasmarch.factory.GsonFactory;
import net.ausiasmarch.setting.ConnectionSettings;

public class CompraService extends GenericService {

    HttpServletRequest oRequest = null;
    String[] frasesInicio = {"El fin de la estructura ", "La agrupacion logica ", "El objetivo conjunto ",
        "Una dinámica apropiada "};
//	String[] frasesMitad = { "dirige los objetivos ", "garantiza el deseo ", "mejora la capacidad ",
//			"recupera el concepto " };
//	String[] frasesFinal = { "de la reestructuracion requerida. ", "en la aplicación. ",
//			"por sus innumerables beneficios. ", "contra la inficiencia. " };

    public CompraService(HttpServletRequest oRequest) {
        super(oRequest);
    }

    public String fill() throws SQLException {
        ConnectionInterface oConnectionImplementation = ConnectionFactory
                .getConnection(ConnectionSettings.connectionPool);
        Connection oConnection = oConnectionImplementation.newConnection();
        CompraDao oCompraDao = new CompraDao(oConnection);
        Gson oGson = GsonFactory.getGson();
//		Date date1 = new GregorianCalendar(2014, Calendar.JANUARY, 1).getTime();
//		Date date2 = new GregorianCalendar(2019, Calendar.DECEMBER, 31).getTime();
        int numCompra = Integer.parseInt(oRequest.getParameter("number"));
        for (int i = 0; i < numCompra; i++) {
            CompraBean oCompraBean = new CompraBean();
//			Date randomDate = new Date(ThreadLocalRandom.current().nextLong(date1.getTime(), date2.getTime()));
            int numAleatorio = (int) Math.floor(Math.random() * (1 - 50) + 50);
            int alProducto_id = (int) Math.floor(Math.random() * 25) + 1;
            int alFactura_id = (int) Math.floor(Math.random() * 25) + 1;

            oCompraBean.setCantidad(numAleatorio);
            oCompraBean.setProducto_id(alProducto_id);
            oCompraBean.setFactura_id(alFactura_id);
            oCompraDao.insert(oCompraBean);
        }
        ResponseBean oResponseBean = new ResponseBean(200, "Insertados los registros con exito");
        if (oConnection != null) {
            oConnection.close();
        }
        if (oConnectionImplementation != null) {
            oConnectionImplementation.disposeConnection();
        }
        return oGson.toJson(oResponseBean);
    }

    private String generaTexto(int longitud) {
        String fraseRandom = "";
        for (int i = 0; i < longitud; i++) {
            fraseRandom += frasesInicio[(int) (Math.random() * frasesInicio.length) + 0];
//			fraseRandom += frasesMitad[(int) (Math.random() * frasesMitad.length) + 0];
//			fraseRandom += frasesFinal[(int) (Math.random() * frasesFinal.length) + 0];
        }
        return fraseRandom;
    }
}
