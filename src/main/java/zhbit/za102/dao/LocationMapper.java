package zhbit.za102.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import zhbit.za102.bean.Location;
import zhbit.za102.bean.LocationExample;
@Component
public interface LocationMapper {
    long countByExample(LocationExample example);

    int deleteByExample(LocationExample example);

    int deleteByPrimaryKey(Integer locationid);

    int insert(Location record);

    int insertSelective(Location record);

    List<Location> selectByExample(LocationExample example);

    Location selectByPrimaryKey(Integer locationid);

    int updateByExampleSelective(@Param("record") Location record, @Param("example") LocationExample example);

    int updateByExample(@Param("record") Location record, @Param("example") LocationExample example);

    int updateByPrimaryKeySelective(Location record);

    int updateByPrimaryKey(Location record);

    Location searchLocationleatMac(@Param("mac") String mac);
    String searchLocationX(@Param("mac") String mac);
    String searchLocationY(@Param("mac") String mac);
    void insertLocation(@Param("mac") String mac,@Param("address") String address,@Param("x") String x,@Param("y") String y);
    List<String> searchLocationMac();
}