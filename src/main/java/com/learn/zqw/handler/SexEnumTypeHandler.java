package com.learn.zqw.handler;

import com.learn.zqw.generator.domain.SexEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * //TODO
 *
 * @author zhuquanwen
 * @vesion 1.0
 * @date 2020/3/16 22:04
 * @since jdk1.8
 */
@MappedTypes(value = {SexEnum.class})
@MappedJdbcTypes(JdbcType.SMALLINT)
public class SexEnumTypeHandler extends BaseTypeHandler<SexEnum> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, SexEnum parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getVal());
    }

    @Override
    public SexEnum getNullableResult(ResultSet rs, String columnName) throws SQLException {
        int val = rs.getInt(columnName);
        return convert(val);
    }

    @Override
    public SexEnum getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        int val = rs.getInt(columnIndex);
        return convert(val);
    }

    @Override
    public SexEnum getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        int val = cs.getInt(columnIndex);
        return convert(val);
    }

    private SexEnum convert(int val) {
        SexEnum[] objs = SexEnum.values();
        for (SexEnum em : objs) {
            if (em.getVal() == val) {
                return em;
            }
        }
        return null;
    }

}
