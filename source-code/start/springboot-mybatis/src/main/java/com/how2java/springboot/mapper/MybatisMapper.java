package com.how2java.springboot.mapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


@Mapper
public interface MybatisMapper<T> {
    @Insert(" insert into category_ ( name ) values (#{name}) ")
    void insert(T category);

    @Delete(" delete from category_ where id= #{id} ")
    void deleteById(int id);

    @Update("update category_ set name=#{name} where id=#{id} ")
    void update(T category);

    @Select("select * from category_ where id= #{id} ")
    T findById(int id);

    @Select("select * from category_ ")
    Page<T> findAll();
}