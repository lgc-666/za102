package zhbit.za102.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zhbit.za102.bean.ClassData;
import zhbit.za102.bean.ClassDataExample;
import zhbit.za102.dao.ClassDataMapper;

import java.util.List;

@Service
public class ClassDataService {
    @Autowired
    ClassDataMapper classDataMapper;

    public void add(ClassData u) {
        classDataMapper.insert(u);
    }


    public void delete(Integer id) {
        classDataMapper.deleteByPrimaryKey(id);
    }


    public void update(ClassData u) {
        classDataMapper.updateByPrimaryKeySelective(u);
    }


    public ClassData get(Integer id) {
        return classDataMapper.selectByPrimaryKey(id);
    }


    public List<ClassData> list() {
        ClassDataExample example = new ClassDataExample();
        example.setOrderByClause("id desc");
        return classDataMapper.selectByExample(example);
    }

}
