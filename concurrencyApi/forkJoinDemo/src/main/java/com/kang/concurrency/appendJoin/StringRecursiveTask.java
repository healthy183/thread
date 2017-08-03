package com.kang.concurrency.appendJoin;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.RecursiveTask;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/3/14.
 * @Author Healthy
 * @Version
 */
@AllArgsConstructor
@Slf4j
public class StringRecursiveTask extends RecursiveTask<String> {

    private Integer begin;
    private Integer end;

    @Override
    protected String compute() {
        if(end - begin > 2 ){
            StringRecursiveTask stringRecursiveBegin = new StringRecursiveTask(begin,(begin+end)/2);
            StringRecursiveTask stringRecursiveEnd = new StringRecursiveTask((begin+end)/2+1,end);
            this.invokeAll(stringRecursiveBegin,stringRecursiveEnd);
            return stringRecursiveBegin.join()+stringRecursiveEnd.join();
        }else{
            String result = "";
            for(int i = begin;i<=end;i++){
                result = result + i;
            }
            log.info("[{}{}] begin[{}] end[{}]  result is[{}]",
                    Thread.currentThread().getName(),Thread.currentThread().getId(),
                    begin,end,result);
            return result;
        }
    }
}
