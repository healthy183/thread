package com.kang.concurrency.producer;

import com.google.common.base.Throwables;
import com.kang.concurrency.channel.BlockingQueueChannel;
import com.kang.concurrency.channel.Channel;
import com.kang.concurrency.simple.AbstractTerminatableThread;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.InputStream;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/4/22.
 * @Author Healthy
 * @Version
 */
@Slf4j
public class AttachmentProcessor {

    private static final  String ATTACHMENT_STORE_BASE_DIR  = "D:\\test";
    private Channel<File> channel =
            new BlockingQueueChannel<File>(new ArrayBlockingQueue<File>(200));

    private final AbstractTerminatableThread indexingThread = new AbstractTerminatableThread() {
        @Override
        public void doRun() throws Exception {
            File file = null;
            file = channel.take();
            try{
                indexFile(file);
            }catch(Exception e){
                log.info(Throwables.getStackTraceAsString(e));
            }finally {
                terminationToken.reservation.decrementAndGet();
            }
        }

        private void indexFile(File file) throws Exception {
            Random random = new Random();
            try{
                Thread.sleep(random.nextInt());
            }catch(InterruptedException i){
            }
        }
        @Override
        public void docleanup(Exception e) {
        }
    };

    public void init(){
        indexingThread.start();
    }

    public void shutdown(){
        indexingThread.terminate();
    }

    public void saveAttachment(InputStream inputStream,String documentId,String originalFileName){
        File file =  saveAsFile( inputStream,documentId,originalFileName);
        try {
            channel.put(file);
        } catch (InterruptedException e) {
            log.info(Throwables.getStackTraceAsString(e));
        }

    }

    private File saveAsFile(InputStream inputStream, String documentId, String originalFileName) {
        return null;
    }
}
