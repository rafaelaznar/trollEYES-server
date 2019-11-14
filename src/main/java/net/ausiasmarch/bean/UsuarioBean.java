package net.ausiasmarch.bean;

import com.google.gson.annotations.Expose;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UsuarioBean implements BeanInterface {

    @Expose
    private Integer id;
    @Expose
    private String dni;
    @Expose
    private String nombre;
    @Expose
    private String apellido1;
    @Expose
    private String apellido2;
    @Expose
    private String email;
    @Expose
    private String login;
    private String password;
    @Expose
    private Integer tipo_usuario_id;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getTipo_usuario_id() {
        return tipo_usuario_id;
    }

    public void setTipo_usuario_id(Integer tipo_usuario_id) {
        this.tipo_usuario_id = tipo_usuario_id;
    }

    @Override
    public UsuarioBean fill(ResultSet oResultSet, Connection oConnection, int spread) throws SQLException {
        this.setId(oResultSet.getInt("id"));
        this.setDni(oResultSet.getString("dni"));
        this.setNombre(oResultSet.getString("nombre"));
        this.setApellido1(oResultSet.getString("apellido1"));
        this.setApellido2(oResultSet.getString("apellido2"));
        this.setEmail(oResultSet.getString("email"));
        this.setLogin(oResultSet.getString("login"));
        this.setPassword(oResultSet.getString("password"));
        return this;
    }

    @Override
    public PreparedStatement orderSQL(List<String> orden, PreparedStatement oPreparedStatement) throws SQLException {
        for (int i = 1; i < orden.size(); i++) {
            if (orden.get((i - 1)).equalsIgnoreCase("id")) {
                oPreparedStatement.setInt(i, 1);
            } else if (orden.get((i - 1)).equalsIgnoreCase("dni")) {
                oPreparedStatement.setInt(i, 2);
            } else if (orden.get((i - 1)).equalsIgnoreCase("nombre")) {
                oPreparedStatement.setInt(i, 3);
            } else if (orden.get((i - 1)).equalsIgnoreCase("apellido1")) {
                oPreparedStatement.setInt(i, 4);
            } else if (orden.get((i - 1)).equalsIgnoreCase("apellido2")) {
                oPreparedStatement.setInt(i, 5);
            } else if (orden.get((i - 1)).equalsIgnoreCase("email")) {
                oPreparedStatement.setInt(i, 6);
            } else if (orden.get((i - 1)).equalsIgnoreCase("login")) {
                oPreparedStatement.setInt(i, 7);
            } else if (orden.get((i - 1)).equalsIgnoreCase("password")) {
                oPreparedStatement.setInt(i, 8);
            }

        }
        return oPreparedStatement;
    }

    @Override
    public String getFieldInsert() {
        return "(dni,nombre,apellido1,apellido2,email,login,password,tipo_usuario_id) VALUES(?,?,?,?,?,?,?,?)";
    }

    @Override
    public PreparedStatement setFieldInsert(BeanInterface oBeanParam, PreparedStatement oPreparedStatement)
            throws SQLException {
        UsuarioBean oUsuarioBean = (UsuarioBean) oBeanParam;
        oPreparedStatement.setString(1, oUsuarioBean.getDni());
        oPreparedStatement.setString(2, oUsuarioBean.getNombre());
        oPreparedStatement.setString(3, oUsuarioBean.getApellido1());
        oPreparedStatement.setString(4, oUsuarioBean.getApellido2());
        oPreparedStatement.setString(5, oUsuarioBean.getEmail());
        oPreparedStatement.setString(6, oUsuarioBean.getLogin());
        oPreparedStatement.setString(7, oUsuarioBean.getPassword());
        oPreparedStatement.setInt(8, oUsuarioBean.getTipo_usuario_id());
        return oPreparedStatement;
    }

    @Override
    public String getFieldUpdate() {
        // TODO Auto-generated method stub
        return "dni = ?, nombre = ?, apellido1 = ?, apellido2 = ?, email = ?, login = ?, password = ?, tipo_usuario_id = ?";
    }

    @Override
    public PreparedStatement setFieldUpdate(BeanInterface oBeanParam, PreparedStatement oPreparedStatement)
            throws SQLException {
        UsuarioBean oUsuarioBean = (UsuarioBean) oBeanParam;
        oPreparedStatement.setString(1, oUsuarioBean.getDni());
        oPreparedStatement.setString(2, oUsuarioBean.getNombre());
        oPreparedStatement.setString(3, oUsuarioBean.getApellido1());
        oPreparedStatement.setString(4, oUsuarioBean.getApellido2());
        oPreparedStatement.setString(5, oUsuarioBean.getEmail());
        oPreparedStatement.setString(6, oUsuarioBean.getLogin());
        oPreparedStatement.setString(7, oUsuarioBean.getPassword());
        oPreparedStatement.setInt(8, oUsuarioBean.getTipo_usuario_id());
        oPreparedStatement.setInt(9, oUsuarioBean.getId());
        return oPreparedStatement;
    }

}
