package com.ruoyi.common.exception.user;

/**
 * 用户锁定异常类
 * 
 * @author ruoyi
 */
public class SysValidErrorException extends UserException
{
    private static final long serialVersionUID = 1L;

    public SysValidErrorException()
    {
        super("sys.valid.error", null);
    }
}
