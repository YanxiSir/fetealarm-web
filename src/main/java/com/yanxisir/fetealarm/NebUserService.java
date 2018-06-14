package com.yanxisir.fetealarm;

import com.yanxisir.neb.bean.CallReq;
import com.yanxisir.neb.bean.Contract;
import com.yanxisir.neb.bean.enums.ETxType;
import com.yanxisir.neb.service.INebApiUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author YanxiSir
 * @since 2018/6/14
 */
@Service
public class NebUserService {

    @Autowired
    private INebApiUserService userService;

    public String callVoteContract(String contractAddress, String function, String args) {
        CallReq req = CallReq.builder()
                .from("n1JE2mf65ryVScatTsnP8M9U5c1VSFmzvaZ")
                .to(contractAddress)
                .value("0")
                .nonce(0)
                .gasPrice("100000")
                .gasLimit("20000")
                .type(ETxType.CALL.getValue())
                .contract(Contract.builder()
                        .function(function)
                        .args(args)
                        .build())
                .build();
        return userService.call(req).toBlocking().first().getResult().getResult();
    }
}
