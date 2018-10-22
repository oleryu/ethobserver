package xyz.oleryu.wallet.eth.observer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.oleryu.wallet.eth.observer.TxInputRecord;
import xyz.oleryu.wallet.eth.observer.TxOutputRecord;
import xyz.oleryu.wallet.eth.observer.service.TxInputRecordService;
import xyz.oleryu.wallet.eth.observer.service.TxOutputRecordService;

import java.math.BigInteger;

/**
 * user控制器
 * 
 * 作者： 段浩杰 2017年7月30日
 */
@RestController
@RequestMapping("/text")
public class TextController {

	@Autowired
	private TxInputRecordService txInputRecordService;
	@Autowired
	private TxOutputRecordService txOutputRecordService;

	@RequestMapping("/add")
	public String insert() {
		TxInputRecord txInputRecord  = new TxInputRecord("12345678","345","667","111", BigInteger.ONE,"777",BigInteger.ONE,BigInteger.ONE,BigInteger.ONE,BigInteger.ONE);
//		User user =new User(16, ""+16, 16);
//		userService.insert(user);
		txInputRecordService.insert(txInputRecord);
		return "sucess";
	}

	@RequestMapping("/find")
	public TxInputRecord findAll() {
		TxInputRecord txInputRecord = txInputRecordService.findTxInputRecordByUserName("0xa4deedf8b9502167725f8be9fa652629a90eda925be1600824ba127d30dcd341");
		return txInputRecord;
	}
	@RequestMapping("/add2")
	public String insert2() {
		TxOutputRecord txOutputRecord  = new TxOutputRecord("1238","345","667","111", BigInteger.ONE,"777",BigInteger.ONE,BigInteger.ONE,BigInteger.ONE,BigInteger.ONE);
//		User user =new User(16, ""+16, 16);
//		userService.insert(user);
		txOutputRecordService.insert(txOutputRecord);
		return "sucess";
	}

	@RequestMapping("/find2")
	public TxOutputRecord findAll2() {
		TxOutputRecord txOutputRecord = txOutputRecordService.findTxOutputRecordByUserName("1238");
		return txOutputRecord;
	}
	

}
