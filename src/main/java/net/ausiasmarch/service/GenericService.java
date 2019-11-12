package net.ausiasmarch.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.sql.Connection;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import net.ausiasmarch.bean.BeanInterface;
import net.ausiasmarch.bean.ResponseBean;
import net.ausiasmarch.connection.ConnectionInterface;
import net.ausiasmarch.dao.DaoInterface;
import net.ausiasmarch.factory.BeanFactory;
import net.ausiasmarch.factory.ConnectionFactory;
import net.ausiasmarch.factory.DaoFactory;
import net.ausiasmarch.factory.GsonFactory;
import net.ausiasmarch.setting.ConnectionSettings;

public class GenericService implements ServiceInterface {

    HttpServletRequest oRequest = null;
    String ob = null;

    public GenericService(HttpServletRequest oRequest) {
        this.oRequest = oRequest;
        ob = oRequest.getParameter("ob");
    }

    @Override
    public String get() throws Exception {
        ConnectionInterface oConnectionImplementation = null;
        Connection oConnection = null;
        try {
            oConnectionImplementation = ConnectionFactory.getConnection(ConnectionSettings.connectionPool);
            oConnection = oConnectionImplementation.newConnection();
            int id = Integer.parseInt(oRequest.getParameter("id"));
            DaoInterface oDao = DaoFactory.getDao(ob, oConnection);
            BeanInterface oBean = oDao.get(id);
            Gson oGson = GsonFactory.getGson();
            String strJson = null;
            strJson = oGson.toJson(oBean);
            return "{\"status\":200,\"message\":" + strJson + "}";
        } catch (Exception ex) {
            String msg = this.getClass().getName() + " ob: " + ob + "; get method ";
            throw new Exception(msg, ex);
        } finally {
            if (oConnection != null) {
                oConnection.close();
            }
            if (oConnectionImplementation != null) {
                oConnectionImplementation.disposeConnection();
            }
        }
    }

    @Override
    public String getPage() throws Exception {
        ConnectionInterface oConnectionImplementation = null;
        Connection oConnection = null;
        try {
            oConnectionImplementation = ConnectionFactory.getConnection(ConnectionSettings.connectionPool);
            oConnection = oConnectionImplementation.newConnection();
            int iRpp = Integer.parseInt(oRequest.getParameter("rpp"));
            int iPage = Integer.parseInt(oRequest.getParameter("page"));
            List<String> orden = null;
            if (oRequest.getParameter("order") != null) {
                orden = Arrays.asList(oRequest.getParameter("order").split("\\s*,\\s*"));
            }
            DaoInterface oDao = DaoFactory.getDao(ob, oConnection);
            ArrayList alBean = oDao.getPage(iPage, iRpp, orden);
            Gson oGson = GsonFactory.getGson();
            String strJson = null;
            strJson = oGson.toJson(alBean);
            return "{\"status\":200,\"message\":" + strJson + "}";
        } catch (Exception ex) {
            String msg = this.getClass().getName() + " ob: " + ob + "; getPage method ";
            throw new Exception(msg, ex);
        } finally {
            if (oConnection != null) {
                oConnection.close();
            }
            if (oConnectionImplementation != null) {
                oConnectionImplementation.disposeConnection();
            }
        }
    }

    @Override
    public String getCount() throws Exception {
        ConnectionInterface oConnectionImplementation = null;
        Connection oConnection = null;
        try {
            oConnectionImplementation = ConnectionFactory.getConnection(ConnectionSettings.connectionPool);
            oConnection = oConnectionImplementation.newConnection();
            ResponseBean oResponseBean = null;
            Gson oGson = GsonFactory.getGson();
            DaoInterface oDao = DaoFactory.getDao(ob, oConnection);
            Integer iCount = oDao.getCount();
            if (iCount < 0) {
                oResponseBean = new ResponseBean(500, iCount.toString());
            } else {
                oResponseBean = new ResponseBean(200, iCount.toString());
            }
            return oGson.toJson(oResponseBean);
        } catch (Exception ex) {
            String msg = this.getClass().getName() + " ob: " + ob + "; getCount method ";
            throw new Exception(msg, ex);
        } finally {
            if (oConnection != null) {
                oConnection.close();
            }
            if (oConnectionImplementation != null) {
                oConnectionImplementation.disposeConnection();
            }
        }
    }

    @Override
    public String update() throws Exception {
        HttpSession oSession = oRequest.getSession();
        ResponseBean oResponseBean = null;
        Gson oGson = GsonFactory.getGson();
        if (oSession.getAttribute("usuario") != null) {
            ConnectionInterface oConnectionImplementation = null;
            Connection oConnection = null;
            try {
                oConnectionImplementation = ConnectionFactory.getConnection(ConnectionSettings.connectionPool);
                oConnection = oConnectionImplementation.newConnection();
                BeanInterface oBean = BeanFactory.getBean(ob);
                String data = oRequest.getParameter("data");
                oBean = BeanFactory.getBeanFromJson(ob, data);
                DaoInterface oDao = DaoFactory.getDao(ob, oConnection);
                if (oDao.update(oBean) == 0) {
                    oResponseBean = new ResponseBean(500, "KO");
                } else {
                    oResponseBean = new ResponseBean(200, "OK");
                }
                return oGson.toJson(oResponseBean);
            } catch (Exception ex) {
                String msg = this.getClass().getName() + " ob: " + ob + "; update method ";
                throw new Exception(msg, ex);
            } finally {
                if (oConnection != null) {
                    oConnection.close();
                }
                if (oConnectionImplementation != null) {
                    oConnectionImplementation.disposeConnection();
                }
            }
        } else {
            oResponseBean = new ResponseBean(401, "Error: No session");
            return oGson.toJson(oResponseBean);
        }

    }

    @Override
    public String getAll() throws Exception {
        ConnectionInterface oConnectionImplementation = null;
        Connection oConnection = null;
        try {
            oConnectionImplementation = ConnectionFactory.getConnection(ConnectionSettings.connectionPool);
            oConnection = oConnectionImplementation.newConnection();
            DaoInterface oDao = DaoFactory.getDao(ob, oConnection);
            Gson oGson = GsonFactory.getGson();
            String message = "";
            List<BeanInterface> listaBean = oDao.getAll();
            if (listaBean == null) {
                message = "\"La lista está vacia\"";
            } else {
                message = oGson.toJson(listaBean);
            }
            return "{\"status\":200,\"message\":" + message + "}";
        } catch (Exception ex) {
            String msg = this.getClass().getName() + " ob: " + ob + "; getAll method ";
            throw new Exception(msg, ex);
        } finally {
            if (oConnection != null) {
                oConnection.close();
            }
            if (oConnectionImplementation != null) {
                oConnectionImplementation.disposeConnection();
            }
        }

    }

    @Override
    public String insert() throws Exception {
        HttpSession oSession = oRequest.getSession();
        ResponseBean oResponseBean = null;
        Gson oGson = GsonFactory.getGson();
        if (oSession.getAttribute("usuario") != null) {
            ConnectionInterface oConnectionImplementation = null;
            Connection oConnection = null;
            try {
                oConnectionImplementation = ConnectionFactory.getConnection(ConnectionSettings.connectionPool);
                oConnection = oConnectionImplementation.newConnection();
                final GsonBuilder builder = new GsonBuilder();
                builder.excludeFieldsWithoutExposeAnnotation();
                BeanInterface oBean = BeanFactory.getBean(ob);
                oBean = BeanFactory.getBeanFromJson(ob, oRequest.getParameter("data"));
                DaoInterface oDao = DaoFactory.getDao(ob, oConnection);
                if (oDao.insert(oBean) == 0) {
                    oResponseBean = new ResponseBean(500, "KO");
                } else {
                    oResponseBean = new ResponseBean(200, "OK");
                };
                return oGson.toJson(oResponseBean);
            } catch (Exception ex) {
                String msg = this.getClass().getName() + " ob: " + ob + "; insert method ";
                throw new Exception(msg, ex);
            } finally {
                if (oConnection != null) {
                    oConnection.close();
                }
                if (oConnectionImplementation != null) {
                    oConnectionImplementation.disposeConnection();
                }
            }
        } else {
            oResponseBean = new ResponseBean(401, "Error: No session");
            return oGson.toJson(oResponseBean);
        }
    }

    @Override
    public String remove() throws Exception {
        HttpSession oSession = oRequest.getSession();
        ResponseBean oResponseBean = null;
        Gson oGson = GsonFactory.getGson();
        if (oSession.getAttribute("usuario") != null) {
            ConnectionInterface oConnectionImplementation = null;
            Connection oConnection = null;
            try {
                oConnectionImplementation = ConnectionFactory.getConnection(ConnectionSettings.connectionPool);
                oConnection = oConnectionImplementation.newConnection();
                DaoInterface oDao = DaoFactory.getDao(ob, oConnection);
                int id = Integer.parseInt(oRequest.getParameter("id"));
                if (oDao.remove(id) == 0) {
                    oResponseBean = new ResponseBean(500, "KO");
                } else {
                    oResponseBean = new ResponseBean(200, "OK");
                }
                return oGson.toJson(oResponseBean);
            } catch (Exception ex) {
                String msg = this.getClass().getName() + " ob: " + ob + "; remove method ";
                throw new Exception(msg, ex);
            } finally {
                if (oConnection != null) {
                    oConnection.close();
                }
                if (oConnectionImplementation != null) {
                    oConnectionImplementation.disposeConnection();
                }
            }
        } else {
            oResponseBean = new ResponseBean(401, "Error: No session");
            return oGson.toJson(oResponseBean);
        }

    }

}
