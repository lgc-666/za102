package zhbit.za102.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import zhbit.za102.bean.Hot;
import zhbit.za102.bean.HotExample;
@Component
public interface HotMapper {
    long countByExample(HotExample example);

    int deleteByExample(HotExample example);

    int deleteByPrimaryKey(Integer hotid);

    int insert(Hot record);

    int insertSelective(Hot record);

    List<Hot> selectByExample(HotExample example);

    Hot selectByPrimaryKey(Integer hotid);

    int updateByExampleSelective(@Param("record") Hot record, @Param("example") HotExample example);

    int updateByExample(@Param("record") Hot record, @Param("example") HotExample example);

    int updateByPrimaryKeySelective(Hot record);

    int updateByPrimaryKey(Hot record);
}