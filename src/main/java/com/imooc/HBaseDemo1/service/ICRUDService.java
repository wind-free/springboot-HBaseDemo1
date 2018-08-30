package com.imooc.HBaseDemo1.service;

import com.imooc.HBaseDemo1.entity.User;
import org.apache.hadoop.hbase.client.Mutation;

import java.util.List;

public interface ICRUDService {
    User findByRow(String tableName,String row);
    List<User>findBySERow(String tableName,String startRow,String endRow);
    List<Mutation>saveOrUpdate(String tablename,List<Mutation> datas);
}
