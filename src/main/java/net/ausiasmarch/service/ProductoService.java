package net.ausiasmarch.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.ausiasmarch.bean.BeanInterface;

import net.ausiasmarch.bean.ProductoBean;
import net.ausiasmarch.bean.ResponseBean;
import net.ausiasmarch.connection.ConnectionInterface;
import net.ausiasmarch.dao.ProductoDao;
import net.ausiasmarch.dao.ProductoDao;
import net.ausiasmarch.factory.ConnectionFactory;
import net.ausiasmarch.factory.GsonFactory;
import net.ausiasmarch.setting.ConnectionSettings;

public class ProductoService implements ServiceInterface {

	HttpServletRequest oRequest = null;
	String[] frasesInicio = { "Maquina de ", "Interruptor para ", "Libro de ",
			"Bebida de  " };
//	String[] frasesMitad = { "dirige los objetivos ", "garantiza el deseo ", "mejora la capacidad ",
//			"recupera el concepto " };
	String[] frasesFinal = { "emparejar. ", "montar tubos. ",
			"manzana. ", "dientes. " };

	public ProductoService(HttpServletRequest oRequest) {
		this.oRequest = oRequest;
	}

	@Override
	public String get() throws SQLException {
		ConnectionInterface oConnectionImplementation = ConnectionFactory
				.getConnection(ConnectionSettings.connectionPool);
		Connection oConnection = oConnectionImplementation.newConnection();
		int id = Integer.parseInt(oRequest.getParameter("id"));
		ProductoDao oProductoDao = new ProductoDao(oConnection);
		ProductoBean oProductoBean = oProductoDao.get(id);
		Gson oGson = GsonFactory.getGson();
		String strJson = oGson.toJson(oProductoBean);
		if (oConnection != null) {
			oConnection.close();
		}
		if (oConnectionImplementation != null) {
			oConnectionImplementation.disposeConnection();
		}
		return "{\"status\":200,\"message\":" + strJson + "}";
	}

	@Override
	public String getPage() throws SQLException {
		ConnectionInterface oConnectionImplementation = ConnectionFactory
				.getConnection(ConnectionSettings.connectionPool);
		Connection oConnection = oConnectionImplementation.newConnection();
		int iRpp = Integer.parseInt(oRequest.getParameter("rpp"));
		int iPage = Integer.parseInt(oRequest.getParameter("page"));
		List<String> orden = null;
		if (oRequest.getParameter("order") != null) {
			orden = Arrays.asList(oRequest.getParameter("order").split("\\s*,\\s*"));
		}
		ProductoDao oProductoDao = new ProductoDao(oConnection);
		ArrayList alProductoBean = oProductoDao.getPage(iPage, iRpp, orden);
		Gson oGson = GsonFactory.getGson();
		String strJson = oGson.toJson(alProductoBean);
		if (oConnection != null) {
			oConnection.close();
		}
		if (oConnectionImplementation != null) {
			oConnectionImplementation.disposeConnection();
		}
		return "{\"status\":200,\"message\":" + strJson + "}";
	}

	@Override
	public String getCount() throws SQLException {
		ConnectionInterface oConnectionImplementation = ConnectionFactory
				.getConnection(ConnectionSettings.connectionPool);
		Connection oConnection = oConnectionImplementation.newConnection();
		ProductoDao oProductoDao = new ProductoDao(oConnection);
		int iCount = oProductoDao.getCount();
		if (oConnection != null) {
			oConnection.close();
		}
		if (oConnectionImplementation != null) {
			oConnectionImplementation.disposeConnection();
		}
		if (iCount < 0) {
			return "{\"status\":500,\"message\":" + iCount + "}";
		} else {
			return "{\"status\":200,\"message\":" + iCount + "}";
		}
	}

	@Override
	public String update() throws SQLException {
		ResponseBean oResponseBean;
		Gson oGson = GsonFactory.getGson();
		HttpSession oSession = oRequest.getSession();
		if (oSession.getAttribute("usuario") == null) {
			oResponseBean = new ResponseBean(500, "Inicie sesión para acceder a esta función");
			return oGson.toJson(oResponseBean);
		}
		ConnectionInterface oConnectionImplementation = ConnectionFactory
				.getConnection(ConnectionSettings.connectionPool);
		Connection oConnection = oConnectionImplementation.newConnection();
		ProductoBean oProductoBean = new ProductoBean();
		String data = oRequest.getParameter("data");
		oProductoBean = oGson.fromJson(data, ProductoBean.class);
		ProductoDao oProductoDao = new ProductoDao(oConnection);
		if (oProductoDao.update(oProductoBean) == 0) {
			oResponseBean = new ResponseBean(500, "KO");
		} else {
			oResponseBean = new ResponseBean(200, "OK");
		}
		if (oConnection != null) {
			oConnection.close();
		}
		if (oConnectionImplementation != null) {
			oConnectionImplementation.disposeConnection();
		}
		return oGson.toJson(oResponseBean);
	}

	@Override
	public String getAll() throws SQLException {
		ConnectionInterface oConnectionImplementation = ConnectionFactory
				.getConnection(ConnectionSettings.connectionPool);
		Connection oConnection = oConnectionImplementation.newConnection();
		ProductoDao oProductoDao = new ProductoDao(oConnection);
		Gson oGson = GsonFactory.getGson();
		String message = "";
		List<BeanInterface> listaProductoBean = oProductoDao.getAll();
		if (listaProductoBean == null) {
			message = "\"La lista está vacia\"";
		} else {
			// oGson = gh.getGson();
			message = oGson.toJson(listaProductoBean);
		}
		if (oConnection != null) {
			oConnection.close();
		}
		if (oConnectionImplementation != null) {
			oConnectionImplementation.disposeConnection();
		}
		return "{\"status\":200,\"message\":" + message + "}";
	}

	@Override
	public String insert() throws SQLException {
		ResponseBean oResponseBean;
		Gson oGson = GsonFactory.getGson();
		HttpSession oSession = oRequest.getSession();
		if (oSession.getAttribute("usuario") == null) {
			oResponseBean = new ResponseBean(500, "Inicie sesión para acceder a esta función");
			return oGson.toJson(oResponseBean);
		}
		ConnectionInterface oConnectionImplementation = ConnectionFactory
				.getConnection(ConnectionSettings.connectionPool);
		Connection oConnection = oConnectionImplementation.newConnection();
		final GsonBuilder builder = new GsonBuilder();
		builder.excludeFieldsWithoutExposeAnnotation();
		ProductoBean oProductoBean = oGson.fromJson(oRequest.getParameter("data"), ProductoBean.class);
		ProductoDao oProductoDao = new ProductoDao(oConnection);
		if (oProductoDao.insert(oProductoBean) == 0) {
			oResponseBean = new ResponseBean(500, "KO");
		} else {
			oResponseBean = new ResponseBean(200, "OK");
		}
		;
		if (oConnection != null) {
			oConnection.close();
		}
		if (oConnectionImplementation != null) {
			oConnectionImplementation.disposeConnection();
		}
		return oGson.toJson(oResponseBean);

	}

	@Override
	public String remove() throws SQLException {
		ResponseBean oResponseBean;
		Gson oGson = GsonFactory.getGson();
		HttpSession oSession = oRequest.getSession();
		if (oSession.getAttribute("usuario") == null) {
			oResponseBean = new ResponseBean(500, "Inicie sesión para acceder a esta función");
			return oGson.toJson(oResponseBean);
		}
		ConnectionInterface oConnectionImplementation = ConnectionFactory
				.getConnection(ConnectionSettings.connectionPool);
		Connection oConnection = oConnectionImplementation.newConnection();
		ProductoDao oProductoDao = new ProductoDao(oConnection);
		int id = Integer.parseInt(oRequest.getParameter("id"));
		if (oProductoDao.remove(id) == 0) {
			oResponseBean = new ResponseBean(500, "KO");
		} else {
			oResponseBean = new ResponseBean(200, "OK");
		}
		if (oConnection != null) {
			oConnection.close();
		}
		if (oConnectionImplementation != null) {
			oConnectionImplementation.disposeConnection();
		}
		return oGson.toJson(oResponseBean);
	}

	public String fill() throws SQLException {
		ConnectionInterface oConnectionImplementation = ConnectionFactory
				.getConnection(ConnectionSettings.connectionPool);
		Connection oConnection = oConnectionImplementation.newConnection();
		ProductoDao oProductoDao = new ProductoDao(oConnection);
		Gson oGson = GsonFactory.getGson();
//		Date date1 = new GregorianCalendar(2014, Calendar.JANUARY, 1).getTime();
//		Date date2 = new GregorianCalendar(2019, Calendar.DECEMBER, 31).getTime();
		int numProd = Integer.parseInt(oRequest.getParameter("number"));
		for (int i = 0; i < numProd; i++) {
			ProductoBean oProductoBean = new ProductoBean();
//			Date randomDate = new Date(ThreadLocalRandom.current().nextLong(date1.getTime(), date2.getTime()));
			 int numAleatorio=(int)Math.floor(Math.random()*(100000-999999)+999999);
			 int numAleatorio1=(int)Math.floor(Math.random()*(0-999)+999);
			 double numAleatorio2 =(double)Math.random()*(0-999)+999;
			 DecimalFormat format2 = new DecimalFormat("#,00");
			double precioAleatorio = Double.parseDouble(format2.format(numAleatorio2));
			int alTipoProducto_id = (int) Math.floor(Math.random()*12)+1;
			
			
			oProductoBean.setCodigo(numAleatorio+"");
			oProductoBean.setExistencias(numAleatorio1);
			oProductoBean.setPrecio(precioAleatorio);
			oProductoBean.setImagen("link a una imagen");
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
//			fraseRandom += frasesMitad[(int) (Math.random() * frasesMitad.length) + 0];
			fraseRandom += frasesFinal[(int) (Math.random() * frasesFinal.length) + 0];
		}
		return fraseRandom;
	}
}
