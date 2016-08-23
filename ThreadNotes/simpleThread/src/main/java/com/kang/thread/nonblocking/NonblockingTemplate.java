package com.kang.thread.nonblocking;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * Created by Administrator on 2016/6/17.
 */
public class NonblockingTemplate {

    public static class IntendedModification{
        public AtomicBoolean completed = new AtomicBoolean(false);
    }
    private AtomicStampedReference<IntendedModification> ongoinMod =
            new AtomicStampedReference<IntendedModification>(null, 0);

    public void modify(){
        while(!attemptModifyASR());
    }

    public boolean attemptModifyASR(){
        boolean modified = false;
        /*

        IntendedMOdification currentlyOngoingMod = ongoingMod.getReference();
        int stamp = ongoingMod.getStamp();

        if(currentlyOngoingMod == null){
            IntendedModification newMod = new IntendModification();
            boolean modSubmitted = ongoingMod.compareAndSet(null, newMod, stamp, stamp + 1);
            if(modSubmitted){
                modified = true;
            }
        }else{
            modified = false;
        }
        return modified;
        */
        return  modified;
    }
}
