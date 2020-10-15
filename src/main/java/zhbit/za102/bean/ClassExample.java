package zhbit.za102.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ClassExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ClassExample() {
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

        public Criteria andClassidIsNull() {
            addCriterion("classid is null");
            return (Criteria) this;
        }

        public Criteria andClassidIsNotNull() {
            addCriterion("classid is not null");
            return (Criteria) this;
        }

        public Criteria andClassidEqualTo(Integer value) {
            addCriterion("classid =", value, "classid");
            return (Criteria) this;
        }

        public Criteria andClassidNotEqualTo(Integer value) {
            addCriterion("classid <>", value, "classid");
            return (Criteria) this;
        }

        public Criteria andClassidGreaterThan(Integer value) {
            addCriterion("classid >", value, "classid");
            return (Criteria) this;
        }

        public Criteria andClassidGreaterThanOrEqualTo(Integer value) {
            addCriterion("classid >=", value, "classid");
            return (Criteria) this;
        }

        public Criteria andClassidLessThan(Integer value) {
            addCriterion("classid <", value, "classid");
            return (Criteria) this;
        }

        public Criteria andClassidLessThanOrEqualTo(Integer value) {
            addCriterion("classid <=", value, "classid");
            return (Criteria) this;
        }

        public Criteria andClassidIn(List<Integer> values) {
            addCriterion("classid in", values, "classid");
            return (Criteria) this;
        }

        public Criteria andClassidNotIn(List<Integer> values) {
            addCriterion("classid not in", values, "classid");
            return (Criteria) this;
        }

        public Criteria andClassidBetween(Integer value1, Integer value2) {
            addCriterion("classid between", value1, value2, "classid");
            return (Criteria) this;
        }

        public Criteria andClassidNotBetween(Integer value1, Integer value2) {
            addCriterion("classid not between", value1, value2, "classid");
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

        public Criteria andX1IsNull() {
            addCriterion("x1 is null");
            return (Criteria) this;
        }

        public Criteria andX1IsNotNull() {
            addCriterion("x1 is not null");
            return (Criteria) this;
        }

        public Criteria andX1EqualTo(String value) {
            addCriterion("x1 =", value, "x1");
            return (Criteria) this;
        }

        public Criteria andX1NotEqualTo(String value) {
            addCriterion("x1 <>", value, "x1");
            return (Criteria) this;
        }

        public Criteria andX1GreaterThan(String value) {
            addCriterion("x1 >", value, "x1");
            return (Criteria) this;
        }

        public Criteria andX1GreaterThanOrEqualTo(String value) {
            addCriterion("x1 >=", value, "x1");
            return (Criteria) this;
        }

        public Criteria andX1LessThan(String value) {
            addCriterion("x1 <", value, "x1");
            return (Criteria) this;
        }

        public Criteria andX1LessThanOrEqualTo(String value) {
            addCriterion("x1 <=", value, "x1");
            return (Criteria) this;
        }

        public Criteria andX1Like(String value) {
            addCriterion("x1 like", value, "x1");
            return (Criteria) this;
        }

        public Criteria andX1NotLike(String value) {
            addCriterion("x1 not like", value, "x1");
            return (Criteria) this;
        }

        public Criteria andX1In(List<String> values) {
            addCriterion("x1 in", values, "x1");
            return (Criteria) this;
        }

        public Criteria andX1NotIn(List<String> values) {
            addCriterion("x1 not in", values, "x1");
            return (Criteria) this;
        }

        public Criteria andX1Between(String value1, String value2) {
            addCriterion("x1 between", value1, value2, "x1");
            return (Criteria) this;
        }

        public Criteria andX1NotBetween(String value1, String value2) {
            addCriterion("x1 not between", value1, value2, "x1");
            return (Criteria) this;
        }

        public Criteria andY1IsNull() {
            addCriterion("y1 is null");
            return (Criteria) this;
        }

        public Criteria andY1IsNotNull() {
            addCriterion("y1 is not null");
            return (Criteria) this;
        }

        public Criteria andY1EqualTo(String value) {
            addCriterion("y1 =", value, "y1");
            return (Criteria) this;
        }

        public Criteria andY1NotEqualTo(String value) {
            addCriterion("y1 <>", value, "y1");
            return (Criteria) this;
        }

        public Criteria andY1GreaterThan(String value) {
            addCriterion("y1 >", value, "y1");
            return (Criteria) this;
        }

        public Criteria andY1GreaterThanOrEqualTo(String value) {
            addCriterion("y1 >=", value, "y1");
            return (Criteria) this;
        }

        public Criteria andY1LessThan(String value) {
            addCriterion("y1 <", value, "y1");
            return (Criteria) this;
        }

        public Criteria andY1LessThanOrEqualTo(String value) {
            addCriterion("y1 <=", value, "y1");
            return (Criteria) this;
        }

        public Criteria andY1Like(String value) {
            addCriterion("y1 like", value, "y1");
            return (Criteria) this;
        }

        public Criteria andY1NotLike(String value) {
            addCriterion("y1 not like", value, "y1");
            return (Criteria) this;
        }

        public Criteria andY1In(List<String> values) {
            addCriterion("y1 in", values, "y1");
            return (Criteria) this;
        }

        public Criteria andY1NotIn(List<String> values) {
            addCriterion("y1 not in", values, "y1");
            return (Criteria) this;
        }

        public Criteria andY1Between(String value1, String value2) {
            addCriterion("y1 between", value1, value2, "y1");
            return (Criteria) this;
        }

        public Criteria andY1NotBetween(String value1, String value2) {
            addCriterion("y1 not between", value1, value2, "y1");
            return (Criteria) this;
        }

        public Criteria andX2IsNull() {
            addCriterion("x2 is null");
            return (Criteria) this;
        }

        public Criteria andX2IsNotNull() {
            addCriterion("x2 is not null");
            return (Criteria) this;
        }

        public Criteria andX2EqualTo(String value) {
            addCriterion("x2 =", value, "x2");
            return (Criteria) this;
        }

        public Criteria andX2NotEqualTo(String value) {
            addCriterion("x2 <>", value, "x2");
            return (Criteria) this;
        }

        public Criteria andX2GreaterThan(String value) {
            addCriterion("x2 >", value, "x2");
            return (Criteria) this;
        }

        public Criteria andX2GreaterThanOrEqualTo(String value) {
            addCriterion("x2 >=", value, "x2");
            return (Criteria) this;
        }

        public Criteria andX2LessThan(String value) {
            addCriterion("x2 <", value, "x2");
            return (Criteria) this;
        }

        public Criteria andX2LessThanOrEqualTo(String value) {
            addCriterion("x2 <=", value, "x2");
            return (Criteria) this;
        }

        public Criteria andX2Like(String value) {
            addCriterion("x2 like", value, "x2");
            return (Criteria) this;
        }

        public Criteria andX2NotLike(String value) {
            addCriterion("x2 not like", value, "x2");
            return (Criteria) this;
        }

        public Criteria andX2In(List<String> values) {
            addCriterion("x2 in", values, "x2");
            return (Criteria) this;
        }

        public Criteria andX2NotIn(List<String> values) {
            addCriterion("x2 not in", values, "x2");
            return (Criteria) this;
        }

        public Criteria andX2Between(String value1, String value2) {
            addCriterion("x2 between", value1, value2, "x2");
            return (Criteria) this;
        }

        public Criteria andX2NotBetween(String value1, String value2) {
            addCriterion("x2 not between", value1, value2, "x2");
            return (Criteria) this;
        }

        public Criteria andY2IsNull() {
            addCriterion("y2 is null");
            return (Criteria) this;
        }

        public Criteria andY2IsNotNull() {
            addCriterion("y2 is not null");
            return (Criteria) this;
        }

        public Criteria andY2EqualTo(String value) {
            addCriterion("y2 =", value, "y2");
            return (Criteria) this;
        }

        public Criteria andY2NotEqualTo(String value) {
            addCriterion("y2 <>", value, "y2");
            return (Criteria) this;
        }

        public Criteria andY2GreaterThan(String value) {
            addCriterion("y2 >", value, "y2");
            return (Criteria) this;
        }

        public Criteria andY2GreaterThanOrEqualTo(String value) {
            addCriterion("y2 >=", value, "y2");
            return (Criteria) this;
        }

        public Criteria andY2LessThan(String value) {
            addCriterion("y2 <", value, "y2");
            return (Criteria) this;
        }

        public Criteria andY2LessThanOrEqualTo(String value) {
            addCriterion("y2 <=", value, "y2");
            return (Criteria) this;
        }

        public Criteria andY2Like(String value) {
            addCriterion("y2 like", value, "y2");
            return (Criteria) this;
        }

        public Criteria andY2NotLike(String value) {
            addCriterion("y2 not like", value, "y2");
            return (Criteria) this;
        }

        public Criteria andY2In(List<String> values) {
            addCriterion("y2 in", values, "y2");
            return (Criteria) this;
        }

        public Criteria andY2NotIn(List<String> values) {
            addCriterion("y2 not in", values, "y2");
            return (Criteria) this;
        }

        public Criteria andY2Between(String value1, String value2) {
            addCriterion("y2 between", value1, value2, "y2");
            return (Criteria) this;
        }

        public Criteria andY2NotBetween(String value1, String value2) {
            addCriterion("y2 not between", value1, value2, "y2");
            return (Criteria) this;
        }

        public Criteria andStopjudgeIsNull() {
            addCriterion("stopJudge is null");
            return (Criteria) this;
        }

        public Criteria andStopjudgeIsNotNull() {
            addCriterion("stopJudge is not null");
            return (Criteria) this;
        }

        public Criteria andStopjudgeEqualTo(Integer value) {
            addCriterion("stopJudge =", value, "stopjudge");
            return (Criteria) this;
        }

        public Criteria andStopjudgeNotEqualTo(Integer value) {
            addCriterion("stopJudge <>", value, "stopjudge");
            return (Criteria) this;
        }

        public Criteria andStopjudgeGreaterThan(Integer value) {
            addCriterion("stopJudge >", value, "stopjudge");
            return (Criteria) this;
        }

        public Criteria andStopjudgeGreaterThanOrEqualTo(Integer value) {
            addCriterion("stopJudge >=", value, "stopjudge");
            return (Criteria) this;
        }

        public Criteria andStopjudgeLessThan(Integer value) {
            addCriterion("stopJudge <", value, "stopjudge");
            return (Criteria) this;
        }

        public Criteria andStopjudgeLessThanOrEqualTo(Integer value) {
            addCriterion("stopJudge <=", value, "stopjudge");
            return (Criteria) this;
        }

        public Criteria andStopjudgeIn(List<Integer> values) {
            addCriterion("stopJudge in", values, "stopjudge");
            return (Criteria) this;
        }

        public Criteria andStopjudgeNotIn(List<Integer> values) {
            addCriterion("stopJudge not in", values, "stopjudge");
            return (Criteria) this;
        }

        public Criteria andStopjudgeBetween(Integer value1, Integer value2) {
            addCriterion("stopJudge between", value1, value2, "stopjudge");
            return (Criteria) this;
        }

        public Criteria andStopjudgeNotBetween(Integer value1, Integer value2) {
            addCriterion("stopJudge not between", value1, value2, "stopjudge");
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