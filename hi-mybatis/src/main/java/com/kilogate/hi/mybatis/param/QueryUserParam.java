package com.kilogate.hi.mybatis.param;

import lombok.Builder;
import lombok.Data;

/**
 * QueryUserParam
 *
 * @author kilogate
 * @create 2023/7/21 10:29
 **/
@Data
@Builder
public class QueryUserParam {
    private String nameLike;
}
