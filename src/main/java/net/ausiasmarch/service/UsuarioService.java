package net.ausiasmarch.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.ausiasmarch.bean.BeanInterface;
import net.ausiasmarch.bean.ResponseBean;
import net.ausiasmarch.bean.UsuarioBean;
import net.ausiasmarch.connection.ConnectionInterface;
import net.ausiasmarch.dao.UsuarioDao;
import net.ausiasmarch.factory.ConnectionFactory;
import net.ausiasmarch.factory.GsonFactory;
import net.ausiasmarch.setting.ConnectionSettings;

public class UsuarioService implements ServiceInterface{

    HttpServletRequest oRequest = null;
    String[] nombre = { "Marcel·li", "Pompeu", "Cirili","Paco",
			"Josepa", "Vidal","Domènec", "Maurici","Eudald", "Miqueleta", "Bernat", "Jaumet","Pepet" };
	String[] apellido1 = { "de Cal", "el de", "de la",
			"dels","de Can","de les", "Ca la","Pacoco"};
	String[] apellido2 = { "Pacoco", "Clapés",
			"Trencapins", "Palla","Cargols","Metge","Murallot","Porrons", "Cigrons", "Llobarro", "Faves","Cebes","Freda" };

    public UsuarioService(HttpServletRequest oRequest) {
        this.oRequest = oRequest;
    }

    public String login() {
        HttpSession oSession = oRequest.getSession();
        ResponseBean oResponseBean = null;
        if (oRequest.getParameter("username").equals("rafa") && oRequest.getParameter("password").equalsIgnoreCase("bitnami")) {
            oSession.setAttribute("usuario", oRequest.getParameter("username"));
            oResponseBean = new ResponseBean(200, "Welcome");
        } else {
            oResponseBean = new ResponseBean(500, "Wrong password");
        }
        Gson oGson = GsonFactory.getGson();
        return oGson.toJson(oResponseBean);
    }

    public String check() {
        HttpSession oSession = oRequest.getSession();
        ResponseBean oResponseBean = null;
        if (oSession.getAttribute("usuario") != null) {
            oResponseBean = new ResponseBean(200, (String) oSession.getAttribute("usuario"));
        } else {
            oResponseBean = new ResponseBean(500, "No active session");
        }
        Gson oGson = GsonFactory.getGson();
        return oGson.toJson(oResponseBean);
    }

    public String logout() {
        HttpSession oSession = oRequest.getSession();
        oSession.invalidate();
        ResponseBean oResponseBean = null;
        oResponseBean = new ResponseBean(200, "No active session");
        Gson oGson = GsonFactory.getGson();
        return oGson.toJson(oResponseBean);
    }
    
    @Override
	public String get() throws SQLException {
		ConnectionInterface oConnectionImplementation = ConnectionFactory
				.getConnection(ConnectionSettings.connectionPool);
		Connection oConnection = oConnectionImplementation.newConnection();
		int id = Integer.parseInt(oRequest.getParameter("id"));
		UsuarioDao oPostDao = new UsuarioDao(oConnection);
		UsuarioBean oUsuarioBean = oPostDao.get(id);
		Gson oGson = GsonFactory.getGson();
		String strJson = oGson.toJson(oUsuarioBean);
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
		UsuarioDao oPostDao = new UsuarioDao(oConnection);
		ArrayList alPostBean = oPostDao.getPage(iPage, iRpp, orden);
		Gson oGson = GsonFactory.getGson();
		String strJson = oGson.toJson(alPostBean);
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
		UsuarioDao oPostDao = new UsuarioDao(oConnection);
		int iCount = oPostDao.getCount();
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
		UsuarioBean oUsuarioBean = new UsuarioBean();
		String data = oRequest.getParameter("data");
		oUsuarioBean = oGson.fromJson(data, UsuarioBean.class);
		UsuarioDao oPostDao = new UsuarioDao(oConnection);
		if (oPostDao.update(oUsuarioBean) == 0) {
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
		UsuarioDao oPostDao = new UsuarioDao(oConnection);
		Gson oGson = GsonFactory.getGson();
		String message = "";
		List<BeanInterface> listaPostBean = oPostDao.getAll();
		if (listaPostBean == null) {
			message = "\"La lista está vacia\"";
		} else {
			// oGson = gh.getGson();
			message = oGson.toJson(listaPostBean);
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
		UsuarioBean oUsuarioBean = oGson.fromJson(oRequest.getParameter("data"), UsuarioBean.class);
		UsuarioDao oPostDao = new UsuarioDao(oConnection);
		if (oPostDao.insert(oUsuarioBean) == 0) {
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
		UsuarioDao oPostDao = new UsuarioDao(oConnection);
		int id = Integer.parseInt(oRequest.getParameter("id"));
		if (oPostDao.remove(id) == 0) {
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
		UsuarioDao oPostDao = new UsuarioDao(oConnection);
		Gson oGson = GsonFactory.getGson();
		int numPost = Integer.parseInt(oRequest.getParameter("number"));
		for (int i = 0; i < numPost; i++) {
			UsuarioBean oUsuarioBean = new UsuarioBean();
			oUsuarioBean.setDni((int)Math.floor(Math.random()*(10000000-99999999)+99999999)+"O");
			String nombrePersona = nombre[(int) (Math.random() * nombre.length) + 0];
			String apellido1Persona = apellido1[(int) (Math.random() * apellido1.length) + 0];
			String apellido2Persona = apellido2[(int) (Math.random() * apellido2.length) + 0];
			String username = nombrePersona.substring(0, 2).toLowerCase().trim() +
					apellido1Persona.substring(0,2).toLowerCase().trim() +
					apellido2Persona.substring(0, 2).toLowerCase().trim()+
					(int)Math.floor(Math.random()*(1000-9999)+9999);
			oUsuarioBean.setNombre(nombrePersona);
			oUsuarioBean.setApellido1(apellido1Persona);
			oUsuarioBean.setApellido2(apellido2Persona);
			oUsuarioBean.setEmail(username+"@trolleyes.com");
			oUsuarioBean.setLogin(username);
			oUsuarioBean.setPassword("da8ab09ab4889c6208116a675cad0b13e335943bd7fc418782d054b32fdfba04");
			oUsuarioBean.setTipo_usuario_id(2);
			oPostDao.insert(oUsuarioBean);
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
