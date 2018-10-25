package xyz.oleryu.wallet.eth.observer.dao.impl;

import org.codehaus.groovy.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import xyz.oleryu.wallet.eth.observer.dao.IMongoDBDao;

import java.util.List;

/**
 * Created by hengpu on 2018/9/28.
 */
@Repository("iMongoDBDao")
public class MongoDBDaoImpl<T> implements IMongoDBDao<T>{
    @Autowired
    private MongoTemplate mongoTemplate;

    public void insert(T t){
        mongoTemplate.insert(t);
    }
    public T getTxRecordByHash(String txHash,Class<T> clazz){
        Query query=new Query(Criteria.where("txHash").is(txHash));
        T t =  mongoTemplate.findOne(query , clazz);
        mongoTemplate.find(query , clazz);
        return t;
    }

    /**
     *
     * @param clazz
     * @param address
     * @param flag
     * @return
     */
    public List<T> lstTxRecord(Class<T> clazz,String address,int flag){

        Query query =new Query();
        Criteria c1= null;
        Criteria c2= null;

        if(address != null) {
            if(flag == 1) {
                String fromAddress=address;
                c2= Criteria.where("fromAddress").is(fromAddress);
            } else if(flag == 2){
                String toAddress = address;
                c1= Criteria.where("toAddress").is(toAddress);
            } else {
                String fromAddress=address;
                String toAddress = address;
                c1= Criteria.where("toAddress").is(toAddress);
                c2= Criteria.where("fromAddress").is(fromAddress);
            }

        }

        Criteria cr = new Criteria();
        if(c1!=null&&c2!=null){
            query = new Query(cr.orOperator(c1,c2));
        }else{
            if(c1!=null){
                query = new Query(c1);
            }
            if(c2!=null){
                query = new Query(c2);
            }
        }

        query.with(new Sort(new Sort.Order(Sort.Direction.DESC,"_id")));
        List<T> li = this.mongoTemplate.find(query, clazz);

        return li;
    }
}
