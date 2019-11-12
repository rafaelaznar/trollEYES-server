package net.ausiasmarch.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import net.ausiasmarch.bean.BeanInterface;
import net.ausiasmarch.bean.UsuarioBean;

public class UsuarioDao extends GenericDao implements DaoInterface {

    public UsuarioDao(Connection oConnection, String ob) {
		super(oConnection, ob);
		// TODO Auto-generated constructor stub
	}
}
