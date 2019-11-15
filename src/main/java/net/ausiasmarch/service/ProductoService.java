package net.ausiasmarch.service;

import com.google.gson.Gson;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import javax.servlet.http.HttpServletRequest;
import net.ausiasmarch.bean.ProductoBean;
import net.ausiasmarch.bean.ResponseBean;
import net.ausiasmarch.connection.ConnectionInterface;
import net.ausiasmarch.dao.ProductoDao;
import net.ausiasmarch.factory.ConnectionFactory;
import net.ausiasmarch.factory.GsonFactory;
import net.ausiasmarch.setting.ConnectionSettings;

public class ProductoService extends GenericService implements ServiceInterface {

    String[] frasesInicio = {"Maquina de ", "Interruptor para ", "Libro de ", "Bebida de  "};
    String[] frasesFinal = {"emparejar. ", "montar tubos. ", "manzana. ", "dientes. "};
    String[] imagesRandom = {"https://www.revistacambio.com.mx/wp-content/uploads/2019/10/istock-640143244-1-300x200.jpg", "http://www.entretantomagazine.com/wp-content/uploads/2018/02/salad-2756467_1920-300x200.jpg", "https://www.aimdigital.com.ar/wp-content/uploads/2018/07/alimentos-cifra-300x200.jpg", "https://blog.cofciudadreal.com/wp-content/uploads/2017/12/duck-2957809__340-300x200.jpg"};

    public ProductoService(HttpServletRequest oRequest) {
        super(oRequest);
    }

    public String fill() throws SQLException {
        ConnectionInterface oConnectionImplementation = ConnectionFactory
                .getConnection(ConnectionSettings.connectionPool);
        Connection oConnection = oConnectionImplementation.newConnection();
        ProductoDao oProductoDao = new ProductoDao(oConnection);
        Gson oGson = GsonFactory.getGson();
        int numProd = Integer.parseInt(oRequest.getParameter("number"));
        for (int i = 0; i < numProd; i++) {
            ProductoBean oProductoBean = new ProductoBean();
            int numAleatorio = (int) Math.floor(Math.random() * (100000 - 999999) + 999999);
            int numAleatorio1 = (int) Math.floor(Math.random() * (0 - 999) + 999);
            double numAleatorio2 = (double) Math.random() * (0 - 999) + 999;
            DecimalFormat format2 = new DecimalFormat("#,00");
            double precioAleatorio = Double.parseDouble(format2.format(numAleatorio2));
            int alTipoProducto_id = (int) Math.floor(Math.random() * 12) + 1;

            oProductoBean.setCodigo(numAleatorio + "");
            oProductoBean.setExistencias(numAleatorio1);
            oProductoBean.setPrecio(precioAleatorio);
            oProductoBean.setImagen(generaImage());
            oProductoBean.setDescripcion(generaTexto(1));
            oProductoBean.setTipo_producto_id(alTipoProducto_id);
            oProductoDao.insert(oProductoBean);
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
            fraseRandom += frasesFinal[(int) (Math.random() * frasesFinal.length) + 0];
        }
        return fraseRandom;
    }

    private String generaImage() {
        String imageRandom = "";
        imageRandom = imagesRandom[(int) (Math.random() * imagesRandom.length) + 0];
        return imageRandom;
    }

}
