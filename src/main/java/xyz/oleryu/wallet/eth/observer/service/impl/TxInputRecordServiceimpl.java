package xyz.oleryu.wallet.eth.observer.service.impl;

import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.oleryu.wallet.eth.observer.TxInputRecord;
import xyz.oleryu.wallet.eth.observer.dao.TxInputRecordDao;
import xyz.oleryu.wallet.eth.observer.dao.IMongoDBDao;
import xyz.oleryu.wallet.eth.observer.service.TxInputRecordService;

import java.util.List;

/**
 * Created by hengpu on 2018/9/27.
 */
@Service("txInputRecordService")
public class TxInputRecordServiceimpl implements TxInputRecordService {
    @Autowired
    private TxInputRecordDao txInputRecordDao;
    @Autowired
    private IMongoDBDao iMongoDBDao;

    public void insert(TxInputRecord txInputRecord){

//        txInputRecordDao.insert(txInputRecord);
        iMongoDBDao.insert(txInputRecord);
    }
    public TxInputRecord findTxInputRecordByUserName(String txHash){
//        return txInputRecordDao.findTxInputRecordByUserName(txHash);
        return (TxInputRecord)iMongoDBDao.findRecordByTxHash(txHash,TxInputRecord.class);
    }

    public List findTxInputRecordListByUserName(Integer page, Integer pageSize){

        List<TxInputRecord> list = iMongoDBDao.findRecordListByTxHash(TxInputRecord.class);
        List pageList = getPageList(list,page,pageSize);
        return pageList;
    }
    private List getPageList(List sourceList, int page, int pageSize) {
        int beginIndex = (page - 1) * pageSize;
        int endIndex = beginIndex + pageSize - 1;

        List result = Lists.newArrayList();
        if (sourceList.size() > 0) {
            //全部记录集合 小于等于分页大小
            if (sourceList.size() <= pageSize) {
                result = sourceList;
            } else {
                //取出对应元素添加至
                for (int i = beginIndex; i <= endIndex && i < sourceList.size(); i++) {
                    result.add(sourceList.get(i));
                }
            }
        }
        return result;
    }

}
