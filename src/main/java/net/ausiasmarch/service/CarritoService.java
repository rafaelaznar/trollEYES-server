package net.ausiasmarch.service;

import com.google.gson.Gson;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import net.ausiasmarch.bean.ItemBean;
import net.ausiasmarch.bean.ResponseBean;
import net.ausiasmarch.factory.GsonFactory;

public class CarritoService {

// API
//json?ob=carrito&op=add&id=??&cantidad=??
//json?ob=carrito&op=remove&id=??&cantidad=??
//json?ob=carrito&op=list
//json?ob=carrito&op=empty
//json?ob=carrito&op=buy

    HttpServletRequest oRequest = null;

    public CarritoService(HttpServletRequest oRequest) {
        this.oRequest = oRequest;
    }

    public String add() throws Exception {
        Gson oGson = GsonFactory.getGson();
        try {
            HttpSession oSession = oRequest.getSession();
            ArrayList<ItemBean> alCarrito = (ArrayList<ItemBean>) oSession.getAttribute("carrito");
            if (alCarrito == null) {
                alCarrito = new ArrayList<ItemBean>();
            }
            int id = Integer.parseInt(oRequest.getParameter("id"));
            int cantidad = Integer.parseInt(oRequest.getParameter("cantidad"));
            int resultadoFind = this.find(alCarrito, id);
            if (resultadoFind >= 0) {
                ItemBean oItemBean = alCarrito.get(resultadoFind);
                oItemBean.setCantidad(oItemBean.getCantidad() + cantidad);
                alCarrito.set(resultadoFind, oItemBean);
            } else {
                alCarrito.add(new ItemBean(id, cantidad));
            }
            oSession.setAttribute("carrito", alCarrito);
            return oGson.toJson(new ResponseBean(200, "OK"));
        } catch (Exception ex) {
            return oGson.toJson(new ResponseBean(500, ex.getMessage()));
        }
    }

    public String remove() throws Exception {
        Gson oGson = GsonFactory.getGson();
        try {
            HttpSession oSession = oRequest.getSession();
            ArrayList<ItemBean> alCarrito = (ArrayList<ItemBean>) oSession.getAttribute("carrito");
            if (alCarrito == null) {
                return oGson.toJson(new ResponseBean(200, "OK"));
            }
            int id = Integer.parseInt(oRequest.getParameter("id"));
            int cantidad = Integer.parseInt(oRequest.getParameter("cantidad"));
            int resultadoFind = this.find(alCarrito, id);
            if (resultadoFind >= 0) {
                ItemBean oItemBean = alCarrito.get(resultadoFind);
                oItemBean.setCantidad(oItemBean.getCantidad() - cantidad);
                if (oItemBean.getCantidad() > 0) {
                    alCarrito.set(resultadoFind, oItemBean);
                } else {
                    alCarrito.remove(resultadoFind);
                }
            } else {
                alCarrito.add(new ItemBean(id, cantidad));
            }
            oSession.setAttribute("carrito", alCarrito);
            return oGson.toJson(new ResponseBean(200, "OK"));
        } catch (Exception ex) {
            return oGson.toJson(new ResponseBean(500, ex.getMessage()));
        }
    }

    public String list() throws Exception {
        Gson oGson = GsonFactory.getGson();
        try {
            HttpSession oSession = oRequest.getSession();
            ArrayList<ItemBean> alCarrito = (ArrayList<ItemBean>) oSession.getAttribute("carrito");
            return "{\"status\":200,\"message\":" + oGson.toJson(alCarrito) + "}";
        } catch (Exception ex) {
            return oGson.toJson(new ResponseBean(500, ex.getMessage()));
        }
    }

    public String empty() throws Exception {
        Gson oGson = GsonFactory.getGson();
        try {
            HttpSession oSession = oRequest.getSession();
            oSession.setAttribute("carrito", null);
            return oGson.toJson(new ResponseBean(200, "OK"));
        } catch (Exception ex) {
            return oGson.toJson(new ResponseBean(500, ex.getMessage()));
        }
    }

    private int find(ArrayList<ItemBean> alCarrito, int id) throws Exception {
        for (int i = 0; i < alCarrito.size(); i++) {
            ItemBean oItemBean = alCarrito.get(i);
            if (oItemBean.getId() == id) {
                return i;
            }
        }
        return -1;
    }

}
