package zhbit.za102.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClassDataExample implements Serializable{
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ClassDataExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andAdressIsNull() {
            addCriterion("adress is null");
            return (Criteria) this;
        }

        public Criteria andAdressIsNotNull() {
            addCriterion("adress is not null");
            return (Criteria) this;
        }

        public Criteria andAdressEqualTo(String value) {
            addCriterion("adress =", value, "adress");
            return (Criteria) this;
        }

        public Criteria andAdressNotEqualTo(String value) {
            addCriterion("adress <>", value, "adress");
            return (Criteria) this;
        }

        public Criteria andAdressGreaterThan(String value) {
            addCriterion("adress >", value, "adress");
            return (Criteria) this;
        }

        public Criteria andAdressGreaterThanOrEqualTo(String value) {
            addCriterion("adress >=", value, "adress");
            return (Criteria) this;
        }

        public Criteria andAdressLessThan(String value) {
            addCriterion("adress <", value, "adress");
            return (Criteria) this;
        }

        public Criteria andAdressLessThanOrEqualTo(String value) {
            addCriterion("adress <=", value, "adress");
            return (Criteria) this;
        }

        public Criteria andAdressLike(String value) {
            addCriterion("adress like", value, "adress");
            return (Criteria) this;
        }

        public Criteria andAdressNotLike(String value) {
            addCriterion("adress not like", value, "adress");
            return (Criteria) this;
        }

        public Criteria andAdressIn(List<String> values) {
            addCriterion("adress in", values, "adress");
            return (Criteria) this;
        }

        public Criteria andAdressNotIn(List<String> values) {
            addCriterion("adress not in", values, "adress");
            return (Criteria) this;
        }

        public Criteria andAdressBetween(String value1, String value2) {
            addCriterion("adress between", value1, value2, "adress");
            return (Criteria) this;
        }

        public Criteria andAdressNotBetween(String value1, String value2) {
            addCriterion("adress not between", value1, value2, "adress");
            return (Criteria) this;
        }

        public Criteria andNewStudentIsNull() {
            addCriterion("new_student is null");
            return (Criteria) this;
        }

        public Criteria andNewStudentIsNotNull() {
            addCriterion("new_student is not null");
            return (Criteria) this;
        }

        public Criteria andNewStudentEqualTo(Integer value) {
            addCriterion("new_student =", value, "newStudent");
            return (Criteria) this;
        }

        public Criteria andNewStudentNotEqualTo(Integer value) {
            addCriterion("new_student <>", value, "newStudent");
            return (Criteria) this;
        }

        public Criteria andNewStudentGreaterThan(Integer value) {
            addCriterion("new_student >", value, "newStudent");
            return (Criteria) this;
        }

        public Criteria andNewStudentGreaterThanOrEqualTo(Integer value) {
            addCriterion("new_student >=", value, "newStudent");
            return (Criteria) this;
        }

        public Criteria andNewStudentLessThan(Integer value) {
            addCriterion("new_student <", value, "newStudent");
            return (Criteria) this;
        }

        public Criteria andNewStudentLessThanOrEqualTo(Integer value) {
            addCriterion("new_student <=", value, "newStudent");
            return (Criteria) this;
        }

        public Criteria andNewStudentIn(List<Integer> values) {
            addCriterion("new_student in", values, "newStudent");
            return (Criteria) this;
        }

        public Criteria andNewStudentNotIn(List<Integer> values) {
            addCriterion("new_student not in", values, "newStudent");
            return (Criteria) this;
        }

        public Criteria andNewStudentBetween(Integer value1, Integer value2) {
            addCriterion("new_student between", value1, value2, "newStudent");
            return (Criteria) this;
        }

        public Criteria andNewStudentNotBetween(Integer value1, Integer value2) {
            addCriterion("new_student not between", value1, value2, "newStudent");
            return (Criteria) this;
        }

        public Criteria andInClassNumberIsNull() {
            addCriterion("in_class_number is null");
            return (Criteria) this;
        }

        public Criteria andInClassNumberIsNotNull() {
            addCriterion("in_class_number is not null");
            return (Criteria) this;
        }

        public Criteria andInClassNumberEqualTo(Integer value) {
            addCriterion("in_class_number =", value, "inClassNumber");
            return (Criteria) this;
        }

        public Criteria andInClassNumberNotEqualTo(Integer value) {
            addCriterion("in_class_number <>", value, "inClassNumber");
            return (Criteria) this;
        }

        public Criteria andInClassNumberGreaterThan(Integer value) {
            addCriterion("in_class_number >", value, "inClassNumber");
            return (Criteria) this;
        }

        public Criteria andInClassNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("in_class_number >=", value, "inClassNumber");
            return (Criteria) this;
        }

        public Criteria andInClassNumberLessThan(Integer value) {
            addCriterion("in_class_number <", value, "inClassNumber");
            return (Criteria) this;
        }

        public Criteria andInClassNumberLessThanOrEqualTo(Integer value) {
            addCriterion("in_class_number <=", value, "inClassNumber");
            return (Criteria) this;
        }

        public Criteria andInClassNumberIn(List<Integer> values) {
            addCriterion("in_class_number in", values, "inClassNumber");
            return (Criteria) this;
        }

        public Criteria andInClassNumberNotIn(List<Integer> values) {
            addCriterion("in_class_number not in", values, "inClassNumber");
            return (Criteria) this;
        }

        public Criteria andInClassNumberBetween(Integer value1, Integer value2) {
            addCriterion("in_class_number between", value1, value2, "inClassNumber");
            return (Criteria) this;
        }

        public Criteria andInClassNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("in_class_number not between", value1, value2, "inClassNumber");
            return (Criteria) this;
        }

        public Criteria andJumpOutIsNull() {
            addCriterion("jump_out is null");
            return (Criteria) this;
        }

        public Criteria andJumpOutIsNotNull() {
            addCriterion("jump_out is not null");
            return (Criteria) this;
        }

        public Criteria andJumpOutEqualTo(Integer value) {
            addCriterion("jump_out =", value, "jumpOut");
            return (Criteria) this;
        }

        public Criteria andJumpOutNotEqualTo(Integer value) {
            addCriterion("jump_out <>", value, "jumpOut");
            return (Criteria) this;
        }

        public Criteria andJumpOutGreaterThan(Integer value) {
            addCriterion("jump_out >", value, "jumpOut");
            return (Criteria) this;
        }

        public Criteria andJumpOutGreaterThanOrEqualTo(Integer value) {
            addCriterion("jump_out >=", value, "jumpOut");
            return (Criteria) this;
        }

        public Criteria andJumpOutLessThan(Integer value) {
            addCriterion("jump_out <", value, "jumpOut");
            return (Criteria) this;
        }

        public Criteria andJumpOutLessThanOrEqualTo(Integer value) {
            addCriterion("jump_out <=", value, "jumpOut");
            return (Criteria) this;
        }

        public Criteria andJumpOutIn(List<Integer> values) {
            addCriterion("jump_out in", values, "jumpOut");
            return (Criteria) this;
        }

        public Criteria andJumpOutNotIn(List<Integer> values) {
            addCriterion("jump_out not in", values, "jumpOut");
            return (Criteria) this;
        }

        public Criteria andJumpOutBetween(Integer value1, Integer value2) {
            addCriterion("jump_out between", value1, value2, "jumpOut");
            return (Criteria) this;
        }

        public Criteria andJumpOutNotBetween(Integer value1, Integer value2) {
            addCriterion("jump_out not between", value1, value2, "jumpOut");
            return (Criteria) this;
        }

        public Criteria andClassNowNumberIsNull() {
            addCriterion("class_now_number is null");
            return (Criteria) this;
        }

        public Criteria andClassNowNumberIsNotNull() {
            addCriterion("class_now_number is not null");
            return (Criteria) this;
        }

        public Criteria andClassNowNumberEqualTo(Integer value) {
            addCriterion("class_now_number =", value, "classNowNumber");
            return (Criteria) this;
        }

        public Criteria andClassNowNumberNotEqualTo(Integer value) {
            addCriterion("class_now_number <>", value, "classNowNumber");
            return (Criteria) this;
        }

        public Criteria andClassNowNumberGreaterThan(Integer value) {
            addCriterion("class_now_number >", value, "classNowNumber");
            return (Criteria) this;
        }

        public Criteria andClassNowNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("class_now_number >=", value, "classNowNumber");
            return (Criteria) this;
        }

        public Criteria andClassNowNumberLessThan(Integer value) {
            addCriterion("class_now_number <", value, "classNowNumber");
            return (Criteria) this;
        }

        public Criteria andClassNowNumberLessThanOrEqualTo(Integer value) {
            addCriterion("class_now_number <=", value, "classNowNumber");
            return (Criteria) this;
        }

        public Criteria andClassNowNumberIn(List<Integer> values) {
            addCriterion("class_now_number in", values, "classNowNumber");
            return (Criteria) this;
        }

        public Criteria andClassNowNumberNotIn(List<Integer> values) {
            addCriterion("class_now_number not in", values, "classNowNumber");
            return (Criteria) this;
        }

        public Criteria andClassNowNumberBetween(Integer value1, Integer value2) {
            addCriterion("class_now_number between", value1, value2, "classNowNumber");
            return (Criteria) this;
        }

        public Criteria andClassNowNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("class_now_number not between", value1, value2, "classNowNumber");
            return (Criteria) this;
        }

        public Criteria andHourClassNumberIsNull() {
            addCriterion("hour_class_number is null");
            return (Criteria) this;
        }

        public Criteria andHourClassNumberIsNotNull() {
            addCriterion("hour_class_number is not null");
            return (Criteria) this;
        }

        public Criteria andHourClassNumberEqualTo(Integer value) {
            addCriterion("hour_class_number =", value, "hourClassNumber");
            return (Criteria) this;
        }

        public Criteria andHourClassNumberNotEqualTo(Integer value) {
            addCriterion("hour_class_number <>", value, "hourClassNumber");
            return (Criteria) this;
        }

        public Criteria andHourClassNumberGreaterThan(Integer value) {
            addCriterion("hour_class_number >", value, "hourClassNumber");
            return (Criteria) this;
        }

        public Criteria andHourClassNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("hour_class_number >=", value, "hourClassNumber");
            return (Criteria) this;
        }

        public Criteria andHourClassNumberLessThan(Integer value) {
            addCriterion("hour_class_number <", value, "hourClassNumber");
            return (Criteria) this;
        }

        public Criteria andHourClassNumberLessThanOrEqualTo(Integer value) {
            addCriterion("hour_class_number <=", value, "hourClassNumber");
            return (Criteria) this;
        }

        public Criteria andHourClassNumberIn(List<Integer> values) {
            addCriterion("hour_class_number in", values, "hourClassNumber");
            return (Criteria) this;
        }

        public Criteria andHourClassNumberNotIn(List<Integer> values) {
            addCriterion("hour_class_number not in", values, "hourClassNumber");
            return (Criteria) this;
        }

        public Criteria andHourClassNumberBetween(Integer value1, Integer value2) {
            addCriterion("hour_class_number between", value1, value2, "hourClassNumber");
            return (Criteria) this;
        }

        public Criteria andHourClassNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("hour_class_number not between", value1, value2, "hourClassNumber");
            return (Criteria) this;
        }

        public Criteria andHourInClassNumberIsNull() {
            addCriterion("hour_in_class_number is null");
            return (Criteria) this;
        }

        public Criteria andHourInClassNumberIsNotNull() {
            addCriterion("hour_in_class_number is not null");
            return (Criteria) this;
        }

        public Criteria andHourInClassNumberEqualTo(Integer value) {
            addCriterion("hour_in_class_number =", value, "hourInClassNumber");
            return (Criteria) this;
        }

        public Criteria andHourInClassNumberNotEqualTo(Integer value) {
            addCriterion("hour_in_class_number <>", value, "hourInClassNumber");
            return (Criteria) this;
        }

        public Criteria andHourInClassNumberGreaterThan(Integer value) {
            addCriterion("hour_in_class_number >", value, "hourInClassNumber");
            return (Criteria) this;
        }

        public Criteria andHourInClassNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("hour_in_class_number >=", value, "hourInClassNumber");
            return (Criteria) this;
        }

        public Criteria andHourInClassNumberLessThan(Integer value) {
            addCriterion("hour_in_class_number <", value, "hourInClassNumber");
            return (Criteria) this;
        }

        public Criteria andHourInClassNumberLessThanOrEqualTo(Integer value) {
            addCriterion("hour_in_class_number <=", value, "hourInClassNumber");
            return (Criteria) this;
        }

        public Criteria andHourInClassNumberIn(List<Integer> values) {
            addCriterion("hour_in_class_number in", values, "hourInClassNumber");
            return (Criteria) this;
        }

        public Criteria andHourInClassNumberNotIn(List<Integer> values) {
            addCriterion("hour_in_class_number not in", values, "hourInClassNumber");
            return (Criteria) this;
        }

        public Criteria andHourInClassNumberBetween(Integer value1, Integer value2) {
            addCriterion("hour_in_class_number between", value1, value2, "hourInClassNumber");
            return (Criteria) this;
        }

        public Criteria andHourInClassNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("hour_in_class_number not between", value1, value2, "hourInClassNumber");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNull() {
            addCriterion("updatetime is null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNotNull() {
            addCriterion("updatetime is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeEqualTo(Date value) {
            addCriterion("updatetime =", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotEqualTo(Date value) {
            addCriterion("updatetime <>", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThan(Date value) {
            addCriterion("updatetime >", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("updatetime >=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThan(Date value) {
            addCriterion("updatetime <", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThanOrEqualTo(Date value) {
            addCriterion("updatetime <=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIn(List<Date> values) {
            addCriterion("updatetime in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotIn(List<Date> values) {
            addCriterion("updatetime not in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeBetween(Date value1, Date value2) {
            addCriterion("updatetime between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotBetween(Date value1, Date value2) {
            addCriterion("updatetime not between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andHoursIsNull() {
            addCriterion("hours is null");
            return (Criteria) this;
        }

        public Criteria andHoursIsNotNull() {
            addCriterion("hours is not null");
            return (Criteria) this;
        }

        public Criteria andHoursEqualTo(Integer value) {
            addCriterion("hours =", value, "hours");
            return (Criteria) this;
        }

        public Criteria andHoursNotEqualTo(Integer value) {
            addCriterion("hours <>", value, "hours");
            return (Criteria) this;
        }

        public Criteria andHoursGreaterThan(Integer value) {
            addCriterion("hours >", value, "hours");
            return (Criteria) this;
        }

        public Criteria andHoursGreaterThanOrEqualTo(Integer value) {
            addCriterion("hours >=", value, "hours");
            return (Criteria) this;
        }

        public Criteria andHoursLessThan(Integer value) {
            addCriterion("hours <", value, "hours");
            return (Criteria) this;
        }

        public Criteria andHoursLessThanOrEqualTo(Integer value) {
            addCriterion("hours <=", value, "hours");
            return (Criteria) this;
        }

        public Criteria andHoursIn(List<Integer> values) {
            addCriterion("hours in", values, "hours");
            return (Criteria) this;
        }

        public Criteria andHoursNotIn(List<Integer> values) {
            addCriterion("hours not in", values, "hours");
            return (Criteria) this;
        }

        public Criteria andHoursBetween(Integer value1, Integer value2) {
            addCriterion("hours between", value1, value2, "hours");
            return (Criteria) this;
        }

        public Criteria andHoursNotBetween(Integer value1, Integer value2) {
            addCriterion("hours not between", value1, value2, "hours");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria implements Serializable{

        protected Criteria() {
            super();
        }
    }

    public static class Criterion implements Serializable {
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