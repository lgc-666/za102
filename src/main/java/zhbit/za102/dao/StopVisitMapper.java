package zhbit.za102.dao;

import java.sql.Timestamp;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import zhbit.za102.bean.StopVisit;
import zhbit.za102.bean.StopVisitExample;
@Component
public interface StopVisitMapper {
    long countByExample(StopVisitExample example);

    int deleteByExample(StopVisitExample example);

    int deleteByPrimaryKey(Integer stopVisitId);

    int insert(StopVisit record);

    int insertSelective(StopVisit record);

    List<StopVisit> selectByExample(StopVisitExample example);

    StopVisit selectByPrimaryKey(Integer stopVisitId);

    int updateByExampleSelective(@Param("record") StopVisit record, @Param("example") StopVisitExample example);

    int updateByExample(@Param("record") StopVisit record, @Param("example") StopVisitExample example);

    int updateByPrimaryKeySelective(StopVisit record);

    int updateByPrimaryKey(StopVisit record);

    void updateCustomer(@Param("mac")String mac, @Param("rssi")Integer rssi, @Param("first_in_time") Timestamp first_in_time, @Param("latest_in_time")Timestamp latest_in_time, @Param("beat")Timestamp beat, @Param("inJudge")Integer inJudge, @Param("visited_times")Integer visited_times, @Param("handleJudge")Integer handleJudge, @Param("rt")String rt);

    List<String> searchExtraJumpOut();

    void updateInjudge();

    void deleteExpiredCustomer();
}