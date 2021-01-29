package com.example;

import com.example.demo.entity.E;
import com.example.demo.entity.EService;
import com.example.demo.SpecTools;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

/**
 * @author SortfGrowingup
 * @version 1.0
 * @date 2021/1/21 15:38
 */
@SpringBootTest
public class DemoTest {
    @Autowired
    private EService service;

    @Test
    public void run(){

        for (int i = 1;i < 101; i++) {
            E e = new E();
            e.setSort(0);
            e.setCreateTime(0);
            e.setUpdateTime(0);
            e.setCreateOper("超级管理员");
            e.setUpdateOper("超级管理员");
            e.setDelFlag("0");
            e.setName("小"+i);
            service.save(e);
        }
    }


    @Test
    public void run2(){
        Optional<E> one = service.findOne(SpecTools.<E>and()
                .eq("name", "小1")
                .eq("createOper", "超级管理员")
                .build());
        System.out.println(one.get());


        service.findAll(SpecTools.<E>and()
                .eq("name", "小1", "小2")
                .build()).forEach(System.out::println);

    }
}