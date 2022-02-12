package com.york.easywrite.service.impl;

import com.alibaba.fastjson.JSON;
import com.york.easywrite.config.RedisConfig;
import com.york.easywrite.model.Person;
import com.york.easywrite.mapper.PersonMapper;
import com.york.easywrite.service.PersonService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.york.easywrite.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author York
 * @since 2021-07-10
 */
@Service
public class PersonServiceImpl extends ServiceImpl<PersonMapper, Person> implements PersonService {

    private static final String key="person:";
    private static final String incrKey="incrKey";

    @Autowired
     private RedisUtils redisUtils;

    @Override
    public int insert(Person person) {
        int insert = baseMapper.insert(person);
        redisUtils.set(key+person.getId(),person);
        return insert;
    }

    @Override
    public Person getPerson(Integer id) {
        Object person = redisUtils.get(key + id);
        if(person!=null){
            System.out.println("从redis中获取:"+JSON.toJSONString(person));
            return (Person) person;
        }
        return baseMapper.selectById(id);
    }

    @Override
    public int getIncr() {
        return 0;
    }
}
