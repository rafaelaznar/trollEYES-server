package net.ausiasmarch.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface BeanInterface {

    Integer getId();

    void setId(Integer id);

    public BeanInterface fill(ResultSet oResultSet) throws SQLException;

}
