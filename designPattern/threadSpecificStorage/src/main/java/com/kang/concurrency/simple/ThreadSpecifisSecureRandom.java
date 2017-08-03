package com.kang.concurrency.simple;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/4/23.
 * @Author Healthy
 * @Version
 */
@NoArgsConstructor(access= AccessLevel.PRIVATE)
public class ThreadSpecifisSecureRandom {

    public static final  ThreadSpecifisSecureRandom
            threadSpecifisSecureRandom = new ThreadSpecifisSecureRandom();

    private  static final ThreadLocal<SecureRandom> SECURE_RANDOM = new ThreadLocal<SecureRandom>(){
        protected  SecureRandom initialValue(){
            SecureRandom secureRandom;
            try{
                secureRandom = SecureRandom.getInstance("SHA1PRNG");
            } catch (NoSuchAlgorithmException e) {
                secureRandom = new SecureRandom();
            }
            return secureRandom;
        };
    };

    public int nextInt(int upperBound){
        SecureRandom  decureRandom = SECURE_RANDOM.get();
        return decureRandom.nextInt(upperBound);
    }

    public void setSeed(long seed){
        SecureRandom  decureRandom = SECURE_RANDOM.get();
        decureRandom.setSeed(seed);
    }

}
