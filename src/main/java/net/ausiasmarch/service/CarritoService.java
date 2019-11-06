package net.ausiasmarch.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import net.ausiasmarch.bean.CarritoBean;
import net.ausiasmarch.bean.ResponseBean;
import net.ausiasmarch.connection.ConnectionInterface;
import net.ausiasmarch.dao.PostDao;
import net.ausiasmarch.factory.ConnectionFactory;
import net.ausiasmarch.factory.GsonFactory;
import net.ausiasmarch.setting.ConnectionSettings;

public class CarritoService {

	HttpServletRequest oRequest = null;

	public CarritoService(HttpServletRequest oRequest) {
		this.oRequest = oRequest;
	}

	public String add() throws SQLException {
		ResponseBean oResponseBean;
		Gson oGson = GsonFactory.getGson();
		HttpSession oSession = oRequest.getSession();
		if (oSession.getAttribute("usuario") == null) {
			oResponseBean = new ResponseBean(500, "Inicie sesión para acceder a su carrito");
			return oGson.toJson(oResponseBean);
		}
		@SuppressWarnings("unchecked")
		ArrayList<CarritoBean> carrito = (ArrayList<CarritoBean>) oSession.getAttribute("carrito");
		if (carrito == null) {
			carrito = new ArrayList<CarritoBean>();
		}
		CarritoBean oCarrito = new CarritoBean();
		int sustituir = 0;
		for (int i = 0; i < carrito.size(); i++) {
			CarritoBean itemCarrito = carrito.get(i);
			if (itemCarrito.getProducto() == Integer.parseInt(oRequest.getParameter("id"))) {
				itemCarrito.setCantidad(itemCarrito.getCantidad()+Integer.parseInt(oRequest.getParameter("cantidad")));
				carrito.set(i, itemCarrito);
				sustituir = 1;
			}
		}
		if (sustituir == 0) {
		oCarrito.setProducto(Integer.parseInt(oRequest.getParameter("id")));
		oCarrito.setCantidad(Integer.parseInt(oRequest.getParameter("cantidad")));
		carrito.add(oCarrito);
		}
		oSession.setAttribute("carrito", carrito);

		return "{\"status\":200,\"message\": \"Añadido con exito\"}";
	}

	public String list() throws SQLException {
		ResponseBean oResponseBean;
		Gson oGson = GsonFactory.getGson();
		HttpSession oSession = oRequest.getSession();
		if (oSession.getAttribute("usuario") == null) {
			oResponseBean = new ResponseBean(500, "Inicie sesión para acceder a su carrito");
			return oGson.toJson(oResponseBean);
		}
		@SuppressWarnings("unchecked")
		ArrayList<CarritoBean> carrito = (ArrayList<CarritoBean>) oSession.getAttribute("carrito");
		if (carrito == null) {
			carrito = new ArrayList<CarritoBean>();
		}

		return "{\"status\":200,\"message\":" + carrito.toString() + "}";
	}

	public String empty() throws SQLException {
		ResponseBean oResponseBean;
		Gson oGson = GsonFactory.getGson();
		HttpSession oSession = oRequest.getSession();
		if (oSession.getAttribute("usuario") == null) {
			oResponseBean = new ResponseBean(500, "Inicie sesión para acceder a su carrito");
			return oGson.toJson(oResponseBean);
		}
		oSession.removeAttribute("carrito");

		return "{\"status\":200,\"message\": Carrito vaciado}";
	}
	
	public String remove() throws SQLException {
		ResponseBean oResponseBean;
		Gson oGson = GsonFactory.getGson();
		HttpSession oSession = oRequest.getSession();
		if (oSession.getAttribute("usuario") == null) {
			oResponseBean = new ResponseBean(500, "Inicie sesión para acceder a su carrito");
			return oGson.toJson(oResponseBean);
		}
		@SuppressWarnings("unchecked")
		ArrayList<CarritoBean> carrito = (ArrayList<CarritoBean>) oSession.getAttribute("carrito");
		if (carrito == null) {
			carrito = new ArrayList<CarritoBean>();
		}
		for (int i = 0; i < carrito.size(); i++) {
			CarritoBean itemCarrito = carrito.get(i);
			if (itemCarrito.getProducto() == Integer.parseInt(oRequest.getParameter("id"))) {
				carrito.remove(i);
			}
		}
		oSession.setAttribute("carrito", carrito);

		return "{\"status\":200,\"message\": Se ha quitado el producto}";
	}
}
