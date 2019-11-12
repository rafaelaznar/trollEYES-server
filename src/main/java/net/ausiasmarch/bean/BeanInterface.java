package net.ausiasmarch.bean;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface BeanInterface {

    Integer getId();

    void setId(Integer id);

    public BeanInterface fill(ResultSet oResultSet) throws SQLException;
    
    public PreparedStatement orderSQL(List<String> orden, PreparedStatement oPreparedStatement) throws SQLException;
}
