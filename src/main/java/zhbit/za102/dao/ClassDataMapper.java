package zhbit.za102.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import zhbit.za102.bean.ClassData;
import zhbit.za102.bean.ClassDataExample;
@Component
public interface ClassDataMapper {
    long countByExample(ClassDataExample example);

    int deleteByExample(ClassDataExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ClassData record);

    int insertSelective(ClassData record);

    List<ClassData> selectByExample(ClassDataExample example);

    ClassData selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ClassData record, @Param("example") ClassDataExample example);

    int updateByExample(@Param("record") ClassData record, @Param("example") ClassDataExample example);

    int updateByPrimaryKeySelective(ClassData record);

    int updateByPrimaryKey(ClassData record);

    Integer selectWithin1hour();
    Integer selectWithin1hourByClass();
    void updateWithin1hour(@Param("hours")Integer hours,@Param("num")Integer num);
    void insertClassData (@Param("address")String address,@Param("hours")Integer hours);
    void updateClassData(@Param("address")String address,@Param("newStudent")Integer newStudent,@Param("inClassNumber")Integer inClassNumber,@Param("hourInClassNumber")Integer hourInClassNumber);
    //查询当前小时进店量
    Integer searchNowHour_in_customer_number(@Param("address")String address);
    //存储跳出量、动态当前客流量和小时客流量
    void updateDataThread(@Param("address") String address,@Param("dynamic_customer")Integer dynamic_customer,@Param("jumpOut_customer")Integer jumpOut_customer,@Param("subHour_customer")Integer subHour_customer);

    void updateExtraJumpOut(@Param("address")String address);
    void deleteExpiredShop_data();
}