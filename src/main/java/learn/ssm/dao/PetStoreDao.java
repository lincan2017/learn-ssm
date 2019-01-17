package learn.ssm.dao;

import learn.ssm.config.DataBaseConnect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author : Lin Can
 * @date : 2019/1/17 9:37
 */
@Repository
public class PetStoreDao {
    @Autowired
    private DataBaseConnect connect;

    public List<Map> query(Class clazz) {
        String queryCatSql = "SELECT name, owner FROM " + clazz.getSimpleName();
        return connect.executeQuery(queryCatSql,new String[]{"name","owner"});
    }

    public long count(Class clazz) {
        String queryCatSql = "SELECT COUNT(*) FROM " + clazz.getSimpleName();
        return connect.count(queryCatSql);
    }

    public int updateById(Class clazz, Object object) throws IllegalAccessException {
        if (object == null) {
            return 0;
        }
        if (!clazz.isInstance(object)) {
            return 0;
        }

        StringBuilder updateSqlBuilder = new StringBuilder();
        updateSqlBuilder.append("UPDATE ");
        updateSqlBuilder.append(clazz.getSimpleName());
        updateSqlBuilder.append(" SET ");

        Field[] declaredFields = clazz.getDeclaredFields();
        Number id = null;
        for (Field field : declaredFields) {
            field.setAccessible(true);
            if ("id".equals(field.getName())) {
                id = (Number)field.get(object);
                if (id == null) {
                    throw new RuntimeException("id=null is not access when updateById");
                }
                continue;
            }
            updateSqlBuilder.append(field.getName());
            if (Number.class.isAssignableFrom(field.getType())) {
                updateSqlBuilder.append("=");
                updateSqlBuilder.append(field.get(object));
                updateSqlBuilder.append(", ");
            } else {
                updateSqlBuilder.append("='");
                updateSqlBuilder.append(field.get(object));
                updateSqlBuilder.append("', ");
            }


        }

        String updateSql = updateSqlBuilder.substring(0,updateSqlBuilder.lastIndexOf(","));

        updateSql += (" WHERE id=" + id);
        return connect.executeUpdate(updateSql);

    }
}
