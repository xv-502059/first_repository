package com.mage.crm.util;

import com.mage.crm.base.exceptions.ParamsException;
import com.mage.crm.vo.User;

public class AssertUtil {
    public static void isTrue(Boolean flag,String msg){
        if(flag){
            throw new ParamsException(300,msg);
        }
    }
    public static void isTrue(Boolean flag,Integer code,String msg){
        if (flag){
            throw new ParamsException(code,msg);
        }
    }
}
