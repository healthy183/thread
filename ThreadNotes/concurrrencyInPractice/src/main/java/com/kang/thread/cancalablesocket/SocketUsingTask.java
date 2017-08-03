package com.kang.thread.cancalablesocket;

import lombok.AllArgsConstructor;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2017/7/24.
 * @Author Healthy
 * @Version
 */
@AllArgsConstructor
public abstract class SocketUsingTask<T> implements  CancellableTask<T> {

    private Socket socket;

    @Override
    public void cancel() {
        try {
            if(socket == null){
                socket.close();
            }
        } catch (IOException e) {
        }
    }

    @Override
    public RunnableFuture<T> newTask() {
        return new FutureTask<T>(this){
            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                boolean  cancelResult = false;
                try{
                    SocketUsingTask.this.cancel();
                }finally {
                    cancelResult = super.cancel(mayInterruptIfRunning);
                }
                return cancelResult;
            }
        };
    }
}
