package com.oyd.mall.tiny.nosql.mongodb.repository;

import com.oyd.mall.tiny.nosql.mongodb.document.MemberReadHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MemberReadHistoryRepository extends MongoRepository<MemberReadHistory, String> {

    /**
     * 根据会员id按时间倒叙获取浏览记录
     */
    List<MemberReadHistory> findByMemberIdOrderByCreateTimeDesc(Long memberID);
}
