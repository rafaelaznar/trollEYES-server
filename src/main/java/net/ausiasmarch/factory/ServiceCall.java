package net.ausiasmarch.factory;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;

import net.ausiasmarch.service.CarritoService;
import net.ausiasmarch.service.PostService;
import net.ausiasmarch.service.UsuarioService;
import net.ausiasmarch.setting.ConfigurationSettings;

public class ServiceCall {

    HttpServletRequest oRequest;

    public static String executeService(HttpServletRequest oRequest) throws SQLException {
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
        return strResult;
    }
}
