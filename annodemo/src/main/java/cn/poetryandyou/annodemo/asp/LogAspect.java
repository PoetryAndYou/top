package cn.poetryandyou.annodemo.asp;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Modifier;

@Aspect
@Component
public class LogAspect {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 切入点，无需添加具体实现
     */
    @Pointcut("@annotation(cn.poetryandyou.annodemo.anno.Log)")
    public void declareJoinPointerExpression() {
    }

    /**
     * 前置方法,在目标方法执行前执行
     *
     * @param joinPoint 封装了代理方法信息的对象,若用不到则可以忽略不写
     */
    @Before("declareJoinPointerExpression()")
    public void beforeMethod(JoinPoint joinPoint) {
        System.out.println("目标方法名为:" + joinPoint.getSignature().getName());
        System.out.println("目标方法所属类的简单类名:" + joinPoint.getSignature().getDeclaringType().getSimpleName());
        System.out.println("目标方法所属类的类名:" + joinPoint.getSignature().getDeclaringTypeName());
        System.out.println("目标方法声明类型:" + Modifier.toString(joinPoint.getSignature().getModifiers()));
        //获取传入目标方法的参数
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            System.out.println("第" + (i + 1) + "个参数为:" + args[i]);
        }
        System.out.println("被代理的对象:" + joinPoint.getTarget());
        System.out.println("代理对象自己:" + joinPoint.getThis());
    }

    /**
     * 环绕通知
     *
     * @param proceedingJoinPoint
     * @return
     */
    @Around("declareJoinPointerExpression()")
    public Object aroundMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        Object result = null;
        //前置通知
        System.out.println("目标方法执行前...");
        //执行目标方法
        result = proceedingJoinPoint.proceed();
        //用新的参数值执行目标方法
        //result = proceedingJoinPoint.proceed(new Object[]{"newSpring","newAop"});
        //返回通知
        System.out.println("目标方法返回结果后...");
        //后置通知
        System.out.println("目标方法执行后...");
        return result;
    }

    @AfterThrowing(pointcut = "declareJoinPointerExpression()", throwing = "e")
    public void aroundMethod(JoinPoint proceedingJoinPoint, Throwable e) {
        System.out.println("发生异常");
        System.out.println("异常处理");
        System.out.println("被代理对象" + proceedingJoinPoint.getTarget());
        logger.info(e.getMessage());
        System.out.println("异常处理结束");
    }
}
