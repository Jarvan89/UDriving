package com.udriving.api.execute.base;

/**
 * api执行器基础类
 */
public abstract class BaseExecute {
    /**
     * 执行前置方法，用于进行参数校验
     */
    protected abstract void preExecute();

    /**
     * 执行逻辑方法，用于调用Service层接口
     */
    protected abstract void doExecute();

    /**
     * 执行器供外部调用的方法。
     */
    public final void execute() {
        preExecute();
        doExecute();
    }

}
