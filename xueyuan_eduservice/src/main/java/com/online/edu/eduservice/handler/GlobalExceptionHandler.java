package com.online.edu.eduservice.handler;


import com.guli.common.R;
import com.online.edu.eduservice.entity.EduTeacher;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 统一异常处理类
 */
public class GlobalExceptionHandler {

    /**
     * 处理全局异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e){
        e.printStackTrace();
        return R.error().message("出现了异常");
    }

    //2 对某类异常进行处理ArithmeticException
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public R error(ArithmeticException e){
        e.printStackTrace();
        return R.error().message("被除数为整型时不可为零。");
    }

    //3.自定义异常
    @ExceptionHandler
    @ResponseBody
    public R error(EduException e){
        e.printStackTrace();
        return R.error().message(e.getMessage()).code(e.getCode());
    }
}
