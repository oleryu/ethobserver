package xyz.oleryu.wallet.eth.observer;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ oleryu.xyz
 */

@RestController
@RequestMapping("/tx")
public class TxRestfullApi {

    @RequestMapping("index")
    public String index() {
        /* 返回 交易记录统计 账户（地址)数和总记录数*/
        return "Hello World!";
    }

    /**
     * 返回列表指定 地址 的交易记录集合
     * @param address
     * @return
     */
    @RequestMapping("records/{address}")
    public String records(@PathVariable String address) {
        return "";
    }

    /**
     * 根据交易HASH 值查询交易详情
     * @param txHash
     * @return
     */
    @RequestMapping("get/{txHash}")
    public String get(@PathVariable String txHash) {
        return "";
    }
}
