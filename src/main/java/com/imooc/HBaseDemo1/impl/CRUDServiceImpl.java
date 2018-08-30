package com.imooc.HBaseDemo1.impl;

import com.imooc.HBaseDemo1.entity.User;
import com.imooc.HBaseDemo1.mapper.UserRowMapper;
import com.imooc.HBaseDemo1.service.ICRUDService;
import com.spring4all.spring.boot.starter.hbase.api.HbaseTemplate;
import org.apache.hadoop.hbase.client.Mutation;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CRUDServiceImpl implements ICRUDService {
    private final HbaseTemplate hbaseTemplate;

    @Autowired
    public CRUDServiceImpl(HbaseTemplate hbaseTemplate) {
        this.hbaseTemplate = hbaseTemplate;
    }

    @Override
    public User findByRow(String tableName, String row) {
        return hbaseTemplate.get(tableName,row,new UserRowMapper());
    }

    @Override
    public List<User> findBySERow(String tableName, String startRow, String endRow) {
        Scan scan=new Scan(Bytes.toBytes(startRow),Bytes.toBytes(endRow));
        return hbaseTemplate.find(tableName,scan,new UserRowMapper());
    }

    @Override
    public List<Mutation> saveOrUpdate(String tablename, List<Mutation> datas) {
         hbaseTemplate.saveOrUpdates(tablename,datas);
        return datas;
    }
}
