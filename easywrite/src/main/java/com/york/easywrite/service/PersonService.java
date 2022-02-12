package com.york.easywrite.service;

import com.york.easywrite.model.Person;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author York
 * @since 2021-07-10
 */
public interface PersonService extends IService<Person> {

    int insert(Person person);

    Person getPerson(Integer id);

    int getIncr();

}
