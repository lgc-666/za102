package zhbit.za102.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zhbit.za102.bean.Visit;
import zhbit.za102.bean.VisitExample;
import zhbit.za102.dao.VisitMapper;

import java.util.List;

@Service
public class VisitService {
    @Autowired
    VisitMapper visitMapper;

    public void add(Visit u) {
        visitMapper.insert(u);
    }


    public void delete(Integer id) {
        visitMapper.deleteByPrimaryKey(id);
    }


    public void update(Visit u) {
        visitMapper.updateByPrimaryKeySelective(u);
    }


    public Visit get(Integer id) {
        return visitMapper.selectByPrimaryKey(id);
    }


    public List<Visit> list() {
        VisitExample example = new VisitExample();
        example.setOrderByClause("id desc");
        return visitMapper.selectByExample(example);
    }

}
