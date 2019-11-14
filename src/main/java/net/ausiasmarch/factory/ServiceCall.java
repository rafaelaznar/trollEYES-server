package net.ausiasmarch.factory;

import javax.servlet.http.HttpServletRequest;
import net.ausiasmarch.service.CarritoService;
import net.ausiasmarch.service.CompraService;
import net.ausiasmarch.service.FacturaService;
import net.ausiasmarch.service.ProductoService;
import net.ausiasmarch.service.TipoProductoService;
import net.ausiasmarch.service.TipoUsuarioService;
import net.ausiasmarch.service.UsuarioService;

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
                    strResult = oUsuarioService.get();
                    break;
                case "getpage":
                    strResult = oUsuarioService.getPage();
                    break;
                case "update":
                    strResult = oUsuarioService.update();
                    break;
                case "getcount":
                    strResult = oUsuarioService.getCount();
                    break;
                case "getall":
                    strResult = oUsuarioService.getAll();
                    break;
                case "insert":
                    strResult = oUsuarioService.insert();
                    break;
                case "remove":
                    strResult = oUsuarioService.remove();
                    break;
                case "fill":
                    strResult = oUsuarioService.fill();
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
        if (ob.equalsIgnoreCase("compra")) {
        	CompraService oCompraService = new CompraService(oRequest);
            switch (op) {
                case "get":
                    strResult = oCompraService.get();
                    break;
                case "getpage":
                    strResult = oCompraService.getPage();
                    break;
                case "update":
                    strResult = oCompraService.update();
                    break;
                case "getcount":
                    strResult = oCompraService.getCount();
                    break;
                case "getall":
                    strResult = oCompraService.getAll();
                    break;
                case "insert":
                    strResult = oCompraService.insert();
                    break;
                case "remove":
                    strResult = oCompraService.remove();
                    break;
                case "fill":
                    strResult = oCompraService.fill();
                    break;
                   
            }
        }  
        if (ob.equalsIgnoreCase("producto")) {
        	ProductoService oProductoService = new ProductoService(oRequest);
            switch (op) {
                case "get":
                    strResult = oProductoService.get();
                    break;
                case "getpage":
                    strResult = oProductoService.getPage();
                    break;
                case "update":
                    strResult = oProductoService.update();
                    break;
                case "getcount":
                    strResult = oProductoService.getCount();
                    break;
                case "getall":
                    strResult = oProductoService.getAll();
                    break;
                case "insert":
                    strResult = oProductoService.insert();
                    break;
                case "remove":
                    strResult = oProductoService.remove();
                    break;
                case "fill":
                    strResult = oProductoService.fill();
                    break;
                   
            }
        }  
        if (ob.equalsIgnoreCase("tipo_producto")) {
        	TipoProductoService oTipoProductoService = new TipoProductoService(oRequest);
            switch (op) {
                case "get":
                    strResult = oTipoProductoService.get();
                    break;
                case "getpage":
                    strResult = oTipoProductoService.getPage();
                    break;
                case "update":
                    strResult = oTipoProductoService.update();
                    break;
                case "getcount":
                    strResult = oTipoProductoService.getCount();
                    break;
                case "getall":
                    strResult = oTipoProductoService.getAll();
                    break;
                case "insert":
                    strResult = oTipoProductoService.insert();
                    break;
                case "remove":
                    strResult = oTipoProductoService.remove();
                    break;
                case "fill":
                    strResult = oTipoProductoService.fill();
                    break;
              
                   
            }
        }  
        if (ob.equalsIgnoreCase("tipo_usuario")) {
        	TipoUsuarioService oTipoUsuarioService = new TipoUsuarioService(oRequest);
            switch (op) {
                case "get":
                    strResult = oTipoUsuarioService.get();
                    break;
                case "getall":
                    strResult = oTipoUsuarioService.getAll();
                    break;
                case "getcount":
                    strResult = oTipoUsuarioService.getCount();
                    break;
                case "getpage":
                    strResult = oTipoUsuarioService.getPage();
                    break;
                case "insert":
                    strResult = oTipoUsuarioService.insert();
                    break;
                case "update":
                    strResult = oTipoUsuarioService.update();
                    break;
                case "remove":
                    strResult = oTipoUsuarioService.remove();
                    break;
            }
        }
        if (ob.equalsIgnoreCase("factura")) {
        	FacturaService oFacturaService = new FacturaService(oRequest);
            switch (op) {
                case "get":
                    strResult = oFacturaService.get();
                    break;
                case "getall":
                    strResult = oFacturaService.getAll();
                    break;
                case "getcount":
                    strResult = oFacturaService.getCount();
                    break;
                case "getpage":
                    strResult = oFacturaService.getPage();
                    break;
                case "insert":
                    strResult = oFacturaService.insert();
                    break;
                case "update":
                    strResult = oFacturaService.update();
                    break;
                case "remove":
                    strResult = oFacturaService.remove();
                    break;
                case "fill":
                    strResult = oFacturaService.fill();
                    break;
            }
        }
        
        
        return strResult;
    }
}
