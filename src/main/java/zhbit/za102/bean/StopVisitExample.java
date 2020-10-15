package zhbit.za102.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StopVisitExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public StopVisitExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria implements Serializable{
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andStopVisitIdIsNull() {
            addCriterion("stop_visit_id is null");
            return (Criteria) this;
        }

        public Criteria andStopVisitIdIsNotNull() {
            addCriterion("stop_visit_id is not null");
            return (Criteria) this;
        }

        public Criteria andStopVisitIdEqualTo(Integer value) {
            addCriterion("stop_visit_id =", value, "stopVisitId");
            return (Criteria) this;
        }

        public Criteria andStopVisitIdNotEqualTo(Integer value) {
            addCriterion("stop_visit_id <>", value, "stopVisitId");
            return (Criteria) this;
        }

        public Criteria andStopVisitIdGreaterThan(Integer value) {
            addCriterion("stop_visit_id >", value, "stopVisitId");
            return (Criteria) this;
        }

        public Criteria andStopVisitIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("stop_visit_id >=", value, "stopVisitId");
            return (Criteria) this;
        }

        public Criteria andStopVisitIdLessThan(Integer value) {
            addCriterion("stop_visit_id <", value, "stopVisitId");
            return (Criteria) this;
        }

        public Criteria andStopVisitIdLessThanOrEqualTo(Integer value) {
            addCriterion("stop_visit_id <=", value, "stopVisitId");
            return (Criteria) this;
        }

        public Criteria andStopVisitIdIn(List<Integer> values) {
            addCriterion("stop_visit_id in", values, "stopVisitId");
            return (Criteria) this;
        }

        public Criteria andStopVisitIdNotIn(List<Integer> values) {
            addCriterion("stop_visit_id not in", values, "stopVisitId");
            return (Criteria) this;
        }

        public Criteria andStopVisitIdBetween(Integer value1, Integer value2) {
            addCriterion("stop_visit_id between", value1, value2, "stopVisitId");
            return (Criteria) this;
        }

        public Criteria andStopVisitIdNotBetween(Integer value1, Integer value2) {
            addCriterion("stop_visit_id not between", value1, value2, "stopVisitId");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andInjudgeIsNull() {
            addCriterion("inJudge is null");
            return (Criteria) this;
        }

        public Criteria andInjudgeIsNotNull() {
            addCriterion("inJudge is not null");
            return (Criteria) this;
        }

        public Criteria andInjudgeEqualTo(Integer value) {
            addCriterion("inJudge =", value, "injudge");
            return (Criteria) this;
        }

        public Criteria andInjudgeNotEqualTo(Integer value) {
            addCriterion("inJudge <>", value, "injudge");
            return (Criteria) this;
        }

        public Criteria andInjudgeGreaterThan(Integer value) {
            addCriterion("inJudge >", value, "injudge");
            return (Criteria) this;
        }

        public Criteria andInjudgeGreaterThanOrEqualTo(Integer value) {
            addCriterion("inJudge >=", value, "injudge");
            return (Criteria) this;
        }

        public Criteria andInjudgeLessThan(Integer value) {
            addCriterion("inJudge <", value, "injudge");
            return (Criteria) this;
        }

        public Criteria andInjudgeLessThanOrEqualTo(Integer value) {
            addCriterion("inJudge <=", value, "injudge");
            return (Criteria) this;
        }

        public Criteria andInjudgeIn(List<Integer> values) {
            addCriterion("inJudge in", values, "injudge");
            return (Criteria) this;
        }

        public Criteria andInjudgeNotIn(List<Integer> values) {
            addCriterion("inJudge not in", values, "injudge");
            return (Criteria) this;
        }

        public Criteria andInjudgeBetween(Integer value1, Integer value2) {
            addCriterion("inJudge between", value1, value2, "injudge");
            return (Criteria) this;
        }

        public Criteria andInjudgeNotBetween(Integer value1, Integer value2) {
            addCriterion("inJudge not between", value1, value2, "injudge");
            return (Criteria) this;
        }

        public Criteria andInTimeIsNull() {
            addCriterion("in_time is null");
            return (Criteria) this;
        }

        public Criteria andInTimeIsNotNull() {
            addCriterion("in_time is not null");
            return (Criteria) this;
        }

        public Criteria andInTimeEqualTo(Date value) {
            addCriterion("in_time =", value, "inTime");
            return (Criteria) this;
        }

        public Criteria andInTimeNotEqualTo(Date value) {
            addCriterion("in_time <>", value, "inTime");
            return (Criteria) this;
        }

        public Criteria andInTimeGreaterThan(Date value) {
            addCriterion("in_time >", value, "inTime");
            return (Criteria) this;
        }

        public Criteria andInTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("in_time >=", value, "inTime");
            return (Criteria) this;
        }

        public Criteria andInTimeLessThan(Date value) {
            addCriterion("in_time <", value, "inTime");
            return (Criteria) this;
        }

        public Criteria andInTimeLessThanOrEqualTo(Date value) {
            addCriterion("in_time <=", value, "inTime");
            return (Criteria) this;
        }

        public Criteria andInTimeIn(List<Date> values) {
            addCriterion("in_time in", values, "inTime");
            return (Criteria) this;
        }

        public Criteria andInTimeNotIn(List<Date> values) {
            addCriterion("in_time not in", values, "inTime");
            return (Criteria) this;
        }

        public Criteria andInTimeBetween(Date value1, Date value2) {
            addCriterion("in_time between", value1, value2, "inTime");
            return (Criteria) this;
        }

        public Criteria andInTimeNotBetween(Date value1, Date value2) {
            addCriterion("in_time not between", value1, value2, "inTime");
            return (Criteria) this;
        }

        public Criteria andLeftTimeIsNull() {
            addCriterion("left_time is null");
            return (Criteria) this;
        }

        public Criteria andLeftTimeIsNotNull() {
            addCriterion("left_time is not null");
            return (Criteria) this;
        }

        public Criteria andLeftTimeEqualTo(Date value) {
            addCriterion("left_time =", value, "leftTime");
            return (Criteria) this;
        }

        public Criteria andLeftTimeNotEqualTo(Date value) {
            addCriterion("left_time <>", value, "leftTime");
            return (Criteria) this;
        }

        public Criteria andLeftTimeGreaterThan(Date value) {
            addCriterion("left_time >", value, "leftTime");
            return (Criteria) this;
        }

        public Criteria andLeftTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("left_time >=", value, "leftTime");
            return (Criteria) this;
        }

        public Criteria andLeftTimeLessThan(Date value) {
            addCriterion("left_time <", value, "leftTime");
            return (Criteria) this;
        }

        public Criteria andLeftTimeLessThanOrEqualTo(Date value) {
            addCriterion("left_time <=", value, "leftTime");
            return (Criteria) this;
        }

        public Criteria andLeftTimeIn(List<Date> values) {
            addCriterion("left_time in", values, "leftTime");
            return (Criteria) this;
        }

        public Criteria andLeftTimeNotIn(List<Date> values) {
            addCriterion("left_time not in", values, "leftTime");
            return (Criteria) this;
        }

        public Criteria andLeftTimeBetween(Date value1, Date value2) {
            addCriterion("left_time between", value1, value2, "leftTime");
            return (Criteria) this;
        }

        public Criteria andLeftTimeNotBetween(Date value1, Date value2) {
            addCriterion("left_time not between", value1, value2, "leftTime");
            return (Criteria) this;
        }

        public Criteria andRtIsNull() {
            addCriterion("rt is null");
            return (Criteria) this;
        }

        public Criteria andRtIsNotNull() {
            addCriterion("rt is not null");
            return (Criteria) this;
        }

        public Criteria andRtEqualTo(String value) {
            addCriterion("rt =", value, "rt");
            return (Criteria) this;
        }

        public Criteria andRtNotEqualTo(String value) {
            addCriterion("rt <>", value, "rt");
            return (Criteria) this;
        }

        public Criteria andRtGreaterThan(String value) {
            addCriterion("rt >", value, "rt");
            return (Criteria) this;
        }

        public Criteria andRtGreaterThanOrEqualTo(String value) {
            addCriterion("rt >=", value, "rt");
            return (Criteria) this;
        }

        public Criteria andRtLessThan(String value) {
            addCriterion("rt <", value, "rt");
            return (Criteria) this;
        }

        public Criteria andRtLessThanOrEqualTo(String value) {
            addCriterion("rt <=", value, "rt");
            return (Criteria) this;
        }

        public Criteria andRtLike(String value) {
            addCriterion("rt like", value, "rt");
            return (Criteria) this;
        }

        public Criteria andRtNotLike(String value) {
            addCriterion("rt not like", value, "rt");
            return (Criteria) this;
        }

        public Criteria andRtIn(List<String> values) {
            addCriterion("rt in", values, "rt");
            return (Criteria) this;
        }

        public Criteria andRtNotIn(List<String> values) {
            addCriterion("rt not in", values, "rt");
            return (Criteria) this;
        }

        public Criteria andRtBetween(String value1, String value2) {
            addCriterion("rt between", value1, value2, "rt");
            return (Criteria) this;
        }

        public Criteria andRtNotBetween(String value1, String value2) {
            addCriterion("rt not between", value1, value2, "rt");
            return (Criteria) this;
        }

        public Criteria andVisitedTimesIsNull() {
            addCriterion("visited_times is null");
            return (Criteria) this;
        }

        public Criteria andVisitedTimesIsNotNull() {
            addCriterion("visited_times is not null");
            return (Criteria) this;
        }

        public Criteria andVisitedTimesEqualTo(Integer value) {
            addCriterion("visited_times =", value, "visitedTimes");
            return (Criteria) this;
        }

        public Criteria andVisitedTimesNotEqualTo(Integer value) {
            addCriterion("visited_times <>", value, "visitedTimes");
            return (Criteria) this;
        }

        public Criteria andVisitedTimesGreaterThan(Integer value) {
            addCriterion("visited_times >", value, "visitedTimes");
            return (Criteria) this;
        }

        public Criteria andVisitedTimesGreaterThanOrEqualTo(Integer value) {
            addCriterion("visited_times >=", value, "visitedTimes");
            return (Criteria) this;
        }

        public Criteria andVisitedTimesLessThan(Integer value) {
            addCriterion("visited_times <", value, "visitedTimes");
            return (Criteria) this;
        }

        public Criteria andVisitedTimesLessThanOrEqualTo(Integer value) {
            addCriterion("visited_times <=", value, "visitedTimes");
            return (Criteria) this;
        }

        public Criteria andVisitedTimesIn(List<Integer> values) {
            addCriterion("visited_times in", values, "visitedTimes");
            return (Criteria) this;
        }

        public Criteria andVisitedTimesNotIn(List<Integer> values) {
            addCriterion("visited_times not in", values, "visitedTimes");
            return (Criteria) this;
        }

        public Criteria andVisitedTimesBetween(Integer value1, Integer value2) {
            addCriterion("visited_times between", value1, value2, "visitedTimes");
            return (Criteria) this;
        }

        public Criteria andVisitedTimesNotBetween(Integer value1, Integer value2) {
            addCriterion("visited_times not between", value1, value2, "visitedTimes");
            return (Criteria) this;
        }

        public Criteria andBeatIsNull() {
            addCriterion("beat is null");
            return (Criteria) this;
        }

        public Criteria andBeatIsNotNull() {
            addCriterion("beat is not null");
            return (Criteria) this;
        }

        public Criteria andBeatEqualTo(Date value) {
            addCriterion("beat =", value, "beat");
            return (Criteria) this;
        }

        public Criteria andBeatNotEqualTo(Date value) {
            addCriterion("beat <>", value, "beat");
            return (Criteria) this;
        }

        public Criteria andBeatGreaterThan(Date value) {
            addCriterion("beat >", value, "beat");
            return (Criteria) this;
        }

        public Criteria andBeatGreaterThanOrEqualTo(Date value) {
            addCriterion("beat >=", value, "beat");
            return (Criteria) this;
        }

        public Criteria andBeatLessThan(Date value) {
            addCriterion("beat <", value, "beat");
            return (Criteria) this;
        }

        public Criteria andBeatLessThanOrEqualTo(Date value) {
            addCriterion("beat <=", value, "beat");
            return (Criteria) this;
        }

        public Criteria andBeatIn(List<Date> values) {
            addCriterion("beat in", values, "beat");
            return (Criteria) this;
        }

        public Criteria andBeatNotIn(List<Date> values) {
            addCriterion("beat not in", values, "beat");
            return (Criteria) this;
        }

        public Criteria andBeatBetween(Date value1, Date value2) {
            addCriterion("beat between", value1, value2, "beat");
            return (Criteria) this;
        }

        public Criteria andBeatNotBetween(Date value1, Date value2) {
            addCriterion("beat not between", value1, value2, "beat");
            return (Criteria) this;
        }

        public Criteria andHandlejudgeIsNull() {
            addCriterion("handleJudge is null");
            return (Criteria) this;
        }

        public Criteria andHandlejudgeIsNotNull() {
            addCriterion("handleJudge is not null");
            return (Criteria) this;
        }

        public Criteria andHandlejudgeEqualTo(Integer value) {
            addCriterion("handleJudge =", value, "handlejudge");
            return (Criteria) this;
        }

        public Criteria andHandlejudgeNotEqualTo(Integer value) {
            addCriterion("handleJudge <>", value, "handlejudge");
            return (Criteria) this;
        }

        public Criteria andHandlejudgeGreaterThan(Integer value) {
            addCriterion("handleJudge >", value, "handlejudge");
            return (Criteria) this;
        }

        public Criteria andHandlejudgeGreaterThanOrEqualTo(Integer value) {
            addCriterion("handleJudge >=", value, "handlejudge");
            return (Criteria) this;
        }

        public Criteria andHandlejudgeLessThan(Integer value) {
            addCriterion("handleJudge <", value, "handlejudge");
            return (Criteria) this;
        }

        public Criteria andHandlejudgeLessThanOrEqualTo(Integer value) {
            addCriterion("handleJudge <=", value, "handlejudge");
            return (Criteria) this;
        }

        public Criteria andHandlejudgeIn(List<Integer> values) {
            addCriterion("handleJudge in", values, "handlejudge");
            return (Criteria) this;
        }

        public Criteria andHandlejudgeNotIn(List<Integer> values) {
            addCriterion("handleJudge not in", values, "handlejudge");
            return (Criteria) this;
        }

        public Criteria andHandlejudgeBetween(Integer value1, Integer value2) {
            addCriterion("handleJudge between", value1, value2, "handlejudge");
            return (Criteria) this;
        }

        public Criteria andHandlejudgeNotBetween(Integer value1, Integer value2) {
            addCriterion("handleJudge not between", value1, value2, "handlejudge");
            return (Criteria) this;
        }

        public Criteria andMacIsNull() {
            addCriterion("mac is null");
            return (Criteria) this;
        }

        public Criteria andMacIsNotNull() {
            addCriterion("mac is not null");
            return (Criteria) this;
        }

        public Criteria andMacEqualTo(String value) {
            addCriterion("mac =", value, "mac");
            return (Criteria) this;
        }

        public Criteria andMacNotEqualTo(String value) {
            addCriterion("mac <>", value, "mac");
            return (Criteria) this;
        }

        public Criteria andMacGreaterThan(String value) {
            addCriterion("mac >", value, "mac");
            return (Criteria) this;
        }

        public Criteria andMacGreaterThanOrEqualTo(String value) {
            addCriterion("mac >=", value, "mac");
            return (Criteria) this;
        }

        public Criteria andMacLessThan(String value) {
            addCriterion("mac <", value, "mac");
            return (Criteria) this;
        }

        public Criteria andMacLessThanOrEqualTo(String value) {
            addCriterion("mac <=", value, "mac");
            return (Criteria) this;
        }

        public Criteria andMacLike(String value) {
            addCriterion("mac like", value, "mac");
            return (Criteria) this;
        }

        public Criteria andMacNotLike(String value) {
            addCriterion("mac not like", value, "mac");
            return (Criteria) this;
        }

        public Criteria andMacIn(List<String> values) {
            addCriterion("mac in", values, "mac");
            return (Criteria) this;
        }

        public Criteria andMacNotIn(List<String> values) {
            addCriterion("mac not in", values, "mac");
            return (Criteria) this;
        }

        public Criteria andMacBetween(String value1, String value2) {
            addCriterion("mac between", value1, value2, "mac");
            return (Criteria) this;
        }

        public Criteria andMacNotBetween(String value1, String value2) {
            addCriterion("mac not between", value1, value2, "mac");
            return (Criteria) this;
        }

        public Criteria andRssiIsNull() {
            addCriterion("rssi is null");
            return (Criteria) this;
        }

        public Criteria andRssiIsNotNull() {
            addCriterion("rssi is not null");
            return (Criteria) this;
        }

        public Criteria andRssiEqualTo(Integer value) {
            addCriterion("rssi =", value, "rssi");
            return (Criteria) this;
        }

        public Criteria andRssiNotEqualTo(Integer value) {
            addCriterion("rssi <>", value, "rssi");
            return (Criteria) this;
        }

        public Criteria andRssiGreaterThan(Integer value) {
            addCriterion("rssi >", value, "rssi");
            return (Criteria) this;
        }

        public Criteria andRssiGreaterThanOrEqualTo(Integer value) {
            addCriterion("rssi >=", value, "rssi");
            return (Criteria) this;
        }

        public Criteria andRssiLessThan(Integer value) {
            addCriterion("rssi <", value, "rssi");
            return (Criteria) this;
        }

        public Criteria andRssiLessThanOrEqualTo(Integer value) {
            addCriterion("rssi <=", value, "rssi");
            return (Criteria) this;
        }

        public Criteria andRssiIn(List<Integer> values) {
            addCriterion("rssi in", values, "rssi");
            return (Criteria) this;
        }

        public Criteria andRssiNotIn(List<Integer> values) {
            addCriterion("rssi not in", values, "rssi");
            return (Criteria) this;
        }

        public Criteria andRssiBetween(Integer value1, Integer value2) {
            addCriterion("rssi between", value1, value2, "rssi");
            return (Criteria) this;
        }

        public Criteria andRssiNotBetween(Integer value1, Integer value2) {
            addCriterion("rssi not between", value1, value2, "rssi");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria implements Serializable{

        protected Criteria() {
            super();
        }
    }

    public static class Criterion implements Serializable{
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}