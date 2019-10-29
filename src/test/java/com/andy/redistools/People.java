package com.andy.redistools;

import lombok.Builder;
import lombok.Data;

/**
 * Description: 测试类
 * Author: Andy.wang
 * Date: 2019/10/29 09:20
 */
@Data
@Builder
public class People {
    private int id;

    private String name;

    private int age;
}
