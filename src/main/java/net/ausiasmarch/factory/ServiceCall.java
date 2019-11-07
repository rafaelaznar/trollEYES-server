package net.ausiasmarch.factory;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;

import net.ausiasmarch.service.CarritoService;
import net.ausiasmarch.service.PostService;
import net.ausiasmarch.service.UsuarioService;
import net.ausiasmarch.setting.ConfigurationSettings;

public class ServiceCall {

    HttpServletRequest oRequest;

    public static String executeService(HttpServletRequest oRequest) throws Exception {
        String ob = oRequest.getParameter("ob");
        String op = oRequest.getParameter("op");
        String strResult = null;
        if (ob.equalsIgnoreCase("usuario")) {
            UsuarioService oUsuarioService = new UsuarioService(oRequest);
            switch (op) {
                case "login":
                    strResult = oUsuarioService.login();
                    break;
                case "check":
                    strResult = oUsuarioService.check();
                    break;
                case "logout":
                    strResult = oUsuarioService.logout();
                    break;
                case "get":
                    strResult = "Aun por hacer :P";
                    break;
                case "getall":
                    strResult = "Aun por hacer :P";
                    break;
                case "getcount":
                    strResult = "Aun por hacer :P";
                    break;
                case "getpage":
                    strResult = "Aun por hacer :P";
                    break;
                case "insert":
                    strResult = "Aun por hacer :P";
                    break;
                case "update":
                    strResult = "Aun por hacer :P";
                    break;
                case "remove":
                    strResult = "Aun por hacer :P";
                    break;
                case "fill":
                    strResult = "Aun por hacer :P";
                    break;
            }
        }
        if (ob.equalsIgnoreCase("carrito")) {
        	CarritoService oCarritoService = new CarritoService(oRequest);
            switch (op) {
                case "add":
                    strResult = oCarritoService.add();
                    break;
                case "list":
                    strResult = oCarritoService.list();
                    break;
                case "empty":
                    strResult = oCarritoService.empty();
                    break;
                case "remove":
                    strResult = oCarritoService.remove();
                    break;
                case "buy":
                    strResult = "Aun por hacer :P";
                    break;
            }
        }
        if (ob.equalsIgnoreCase("tipousuario")) {
        	CarritoService oCarritoService = new CarritoService(oRequest);
            switch (op) {
                case "get":
                    strResult = oCarritoService.add();
                    break;
                case "getall":
                    strResult = oCarritoService.list();
                    break;
                case "getcount":
                    strResult = oCarritoService.empty();
                    break;
                case "getpage":
                    strResult = oCarritoService.remove();
                    break;
                case "insert":
                    strResult = "Aun por hacer :P";
                    break;
                case "update":
                    strResult = "Aun por hacer :P";
                    break;
                case "remove":
                    strResult = "Aun por hacer :P";
                    break;
                case "fill":
                    strResult = "Aun por hacer :P";
                    break;
            }
        }
        if (ob.equalsIgnoreCase("factura")) {
        	CarritoService oCarritoService = new CarritoService(oRequest);
            switch (op) {
                case "get":
                    strResult = oCarritoService.add();
                    break;
                case "getall":
                    strResult = oCarritoService.list();
                    break;
                case "getcount":
                    strResult = oCarritoService.empty();
                    break;
                case "getpage":
                    strResult = oCarritoService.remove();
                    break;
                case "insert":
                    strResult = "Aun por hacer :P";
                    break;
                case "update":
                    strResult = "Aun por hacer :P";
                    break;
                case "remove":
                    strResult = "Aun por hacer :P";
                    break;
                case "fill":
                    strResult = "Aun por hacer :P";
                    break;
            }
        }
        return strResult;
    }
}
