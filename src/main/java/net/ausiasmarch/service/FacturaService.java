package net.ausiasmarch.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.sql.Connection;
import java.sql.SQLException;
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

import net.ausiasmarch.bean.FacturaBean;
import net.ausiasmarch.bean.ResponseBean;
import net.ausiasmarch.connection.ConnectionInterface;
import net.ausiasmarch.dao.FacturaDao;
import net.ausiasmarch.factory.ConnectionFactory;
import net.ausiasmarch.factory.GsonFactory;
import net.ausiasmarch.setting.ConnectionSettings;

public class FacturaService implements ServiceInterface {

	HttpServletRequest oRequest = null;

	public FacturaService(HttpServletRequest oRequest) {
		this.oRequest = oRequest;
	}

	@Override
	public String get() throws SQLException {
		ConnectionInterface oConnectionImplementation = ConnectionFactory
				.getConnection(ConnectionSettings.connectionPool);
		Connection oConnection = oConnectionImplementation.newConnection();
		int id = Integer.parseInt(oRequest.getParameter("id"));
		FacturaDao oFacturaDao = new FacturaDao(oConnection);
		FacturaBean oFacturaBean = oFacturaDao.get(id);
		Gson oGson = GsonFactory.getGson();
		String strJson = oGson.toJson(oFacturaBean);
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
		FacturaDao oFacturaDao = new FacturaDao(oConnection);
		ArrayList alFacturaBean = oFacturaDao.getPage(iPage, iRpp, orden);
		Gson oGson = GsonFactory.getGson();
		String strJson = oGson.toJson(alFacturaBean);
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
		FacturaDao oFacturaDao = new FacturaDao(oConnection);
		int iCount = oFacturaDao.getCount();
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
		FacturaBean oFacturaBean = new FacturaBean();
		String data = oRequest.getParameter("data");
		oFacturaBean = oGson.fromJson(data, FacturaBean.class);
		FacturaDao oFacturaDao = new FacturaDao(oConnection);
		if (oFacturaDao.update(oFacturaBean) == 0) {
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
		FacturaDao oFacturaDao = new FacturaDao(oConnection);
		Gson oGson = GsonFactory.getGson();
		String message = "";
		List<BeanInterface> listaFacturaBean = oFacturaDao.getAll();
		if (listaFacturaBean == null) {
			message = "\"La lista está vacia\"";
		} else {
			// oGson = gh.getGson();
			message = oGson.toJson(listaFacturaBean);
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
		FacturaBean oFacturaBean = oGson.fromJson(oRequest.getParameter("data"), FacturaBean.class);
		FacturaDao oFacturaDao = new FacturaDao(oConnection);
		if (oFacturaDao.insert(oFacturaBean) == 0) {
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
		FacturaDao oFacturaDao = new FacturaDao(oConnection);
		int id = Integer.parseInt(oRequest.getParameter("id"));
		if (oFacturaDao.remove(id) == 0) {
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
		FacturaDao oFacturaDao = new FacturaDao(oConnection);
		Gson oGson = GsonFactory.getGson();
		Date date1 = new GregorianCalendar(2014, Calendar.JANUARY, 1).getTime();
		Date date2 = new GregorianCalendar(2019, Calendar.DECEMBER, 31).getTime();
		int numFactura = Integer.parseInt(oRequest.getParameter("number"));
		for (int i = 0; i < numFactura; i++) {
			FacturaBean oFacturaBean = new FacturaBean();
			Date randomDate = new Date(ThreadLocalRandom.current().nextLong(date1.getTime(), date2.getTime()));
			oFacturaBean.setIva(21);
			oFacturaBean.setFecha(randomDate);
			oFacturaBean.setUsuario_id((int) (Math.random() * 25) + 2);
			oFacturaDao.insert(oFacturaBean);
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

}
