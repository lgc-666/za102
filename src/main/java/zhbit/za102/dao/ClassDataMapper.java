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
}