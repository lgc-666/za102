package zhbit.za102.Utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 *因为URLPathMatchingFilter 没有被声明为@Bean, 换句话说 URLPathMatchingFilter就没有被Spring管理起来，那么也就无法在里面注入 PermissionService类了。
 * 但是在业务上URLPathMatchingFilter里面又必须使用PermissionService类，怎么办呢? 就借助SpringContextUtils这个工具类，来获取PermissionService的实例。
 */

@Component
public class SpringContextUtils implements ApplicationContextAware {
    private static ApplicationContext context;

    public void setApplicationContext(ApplicationContext context)
            throws BeansException {
        SpringContextUtils.context = context;
    }

    public static ApplicationContext getContext(){
        return context;
    }
}