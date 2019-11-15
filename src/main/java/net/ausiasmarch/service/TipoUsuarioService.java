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

import net.ausiasmarch.bean.TipoUsuarioBean;
import net.ausiasmarch.bean.ResponseBean;
import net.ausiasmarch.connection.ConnectionInterface;
import net.ausiasmarch.dao.TipoUsuarioDao;
import net.ausiasmarch.factory.ConnectionFactory;
import net.ausiasmarch.factory.GsonFactory;
import net.ausiasmarch.setting.ConnectionSettings;

public class TipoUsuarioService implements ServiceInterface {

	HttpServletRequest oRequest = null;

	public TipoUsuarioService(HttpServletRequest oRequest) {
		this.oRequest = oRequest;
	}

	@Override
	public String get() throws SQLException {
		ConnectionInterface oConnectionImplementation = ConnectionFactory
				.getConnection(ConnectionSettings.connectionPool);
		Connection oConnection = oConnectionImplementation.newConnection();
		int id = Integer.parseInt(oRequest.getParameter("id"));
		TipoUsuarioDao oTipoUsuarioDao = new TipoUsuarioDao(oConnection);
		TipoUsuarioBean oTipoUsuarioBean = oTipoUsuarioDao.get(id);
		Gson oGson = GsonFactory.getGson();
		String strJson = oGson.toJson(oTipoUsuarioBean);
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
		TipoUsuarioDao oTipoUsuarioDao = new TipoUsuarioDao(oConnection);
		ArrayList alTipoUsuarioBean = oTipoUsuarioDao.getPage(iPage, iRpp, orden);
		Gson oGson = GsonFactory.getGson();
		String strJson = oGson.toJson(alTipoUsuarioBean);
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
		TipoUsuarioDao oTipoUsuarioDao = new TipoUsuarioDao(oConnection);
		int iCount = oTipoUsuarioDao.getCount();
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
		TipoUsuarioBean oTipoUsuarioBean = new TipoUsuarioBean();
		String data = oRequest.getParameter("data");
		oTipoUsuarioBean = oGson.fromJson(data, TipoUsuarioBean.class);
		TipoUsuarioDao oTipoUsuarioDao = new TipoUsuarioDao(oConnection);
		if (oTipoUsuarioDao.update(oTipoUsuarioBean) == 0) {
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
		TipoUsuarioDao oTipoUsuarioDao = new TipoUsuarioDao(oConnection);
		Gson oGson = GsonFactory.getGson();
		String message = "";
		List<BeanInterface> listaTipoUsuarioBean = oTipoUsuarioDao.getAll();
		if (listaTipoUsuarioBean == null) {
			message = "\"La lista está vacia\"";
		} else {
			// oGson = gh.getGson();
			message = oGson.toJson(listaTipoUsuarioBean);
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
		TipoUsuarioBean oTipoUsuarioBean = oGson.fromJson(oRequest.getParameter("data"), TipoUsuarioBean.class);
		TipoUsuarioDao oTipoUsuarioDao = new TipoUsuarioDao(oConnection);
		if (oTipoUsuarioDao.insert(oTipoUsuarioBean) == 0) {
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
		TipoUsuarioDao oTipoUsuarioDao = new TipoUsuarioDao(oConnection);
		int id = Integer.parseInt(oRequest.getParameter("id"));
		if (oTipoUsuarioDao.remove(id) == 0) {
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

}
