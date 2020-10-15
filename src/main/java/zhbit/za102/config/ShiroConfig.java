package zhbit.za102.config;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import zhbit.za102.Filter.ShiroLogoutFilter;
import zhbit.za102.Filter.URLPathMatchingFilter;
import zhbit.za102.realm.DatabaseRealm;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * 要用@Configuration 注解表示它是一个配置类;
 * 只要用@Bean 注解了，就表示是被spring管理起来的对象了;
 */


@Configuration
public class ShiroConfig {
    @Bean
    public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * ShiroFilterFactoryBean 处理拦截资源文件问题。
     * 注意：单独一个ShiroFilterFactoryBean配置是或报错的，因为在
     * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
     *
     Filter Chain定义说明
     1、一个URL可以配置多个Filter，使用逗号分隔
     2、当设置多个过滤器时，全部验证通过，才视为通过
     3、部分过滤器可指定参数，如perms，roles
     *
     */
    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager){
        System.out.println("ShiroConfiguration.shirFilter()");
        ShiroFilterFactoryBean shiroFilterFactoryBean  = new ShiroFilterFactoryBean();

        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面或/login
        shiroFilterFactoryBean.setLoginUrl("/login");
        // 登录成功后要跳转的链接
        //shiroFilterFactoryBean.setSuccessUrl("/index");
        //未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized");
        //拦截器.
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();
        //自定义拦截器
        Map<String, Filter> customisedFilter = new HashMap<>();
        customisedFilter.put("url", getURLPathMatchingFilter());
        //配置自定义登出, 覆盖掉 logout 之前默认的LogoutFilter
        customisedFilter.put("logout", shiroLogoutFilter());


        /**
         *配置映射关系
        anon: 无需认证即可访问
        authc: 需要认证才可访问(已登录状态)
        user: 点击“记住我”功能可访问
        perms: 拥有权限才可以访问
        role: 拥有某个角色权限才能访问
         logout：退出(其中的具体的退出代码Shiro已经替我们实现了,redirectUrl：退出成功后默认重定向的地址（/）,其对应的url可以是不存在的)
         url：对所有接口都使用自定义拦截器
        */
        filterChainDefinitionMap.put("/loginUser","anon");
        filterChainDefinitionMap.put("/login","anon");
        filterChainDefinitionMap.put("/register","anon");
        filterChainDefinitionMap.put("/doLogout", "logout");

        //开放其他接口(登录后可以访问)
        filterChainDefinitionMap.put("/menu","authc");

        filterChainDefinitionMap.put("/listUser","perms[listUser]");
        filterChainDefinitionMap.put("/updatePassword", "perms[updatePassword]");
        filterChainDefinitionMap.put("/updateUser", "perms[updateUser]");

        filterChainDefinitionMap.put("/listRole", "perms[listRole]");
        filterChainDefinitionMap.put("/editRole", "perms[editRole]");
        filterChainDefinitionMap.put("/updateRolePermission", "perms[updateRolePermission]");

        filterChainDefinitionMap.put("/listregisterApproval", "perms[listregisterApproval]");
        filterChainDefinitionMap.put("/agreeregisterApproval", "perms[agreeregisterApproval]");
        filterChainDefinitionMap.put("/deleteregisterApproval", "perms[deleteregisterApproval]");

        filterChainDefinitionMap.put("/listmachine", "perms[listmachine]");
        filterChainDefinitionMap.put("/deletemachine", "perms[deletemachine]");
        filterChainDefinitionMap.put("/updatemachine", "perms[updatemachine]");
        filterChainDefinitionMap.put("/addmachine", "perms[addmachine]");

        filterChainDefinitionMap.put("/listClass", "perms[listClass]");
        filterChainDefinitionMap.put("/deleteClass", "perms[deleteClass]");
        filterChainDefinitionMap.put("/updateClass", "perms[updateClass]");
        filterChainDefinitionMap.put("/addClass", "perms[addClass]");

        filterChainDefinitionMap.put("/getDBlocation", "perms[getDBlocation]");
        filterChainDefinitionMap.put("/getDBlocationNotRepeat", "perms[getDBlocationNotRepeat]");

        filterChainDefinitionMap.put("/**", "url");
        shiroFilterFactoryBean.setFilters(customisedFilter);
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 需要注意一点，URLPathMatchingFilter 并没有用@Bean管理起来。 原因是Shiro的bug, 这个也是过滤器，ShiroFilterFactoryBean 也是过滤器，
     * 当他们都出现的时候，默认的什么anno,authc,logout过滤器就失效了。所以不能把他声明为@Bean。
     */

    public URLPathMatchingFilter getURLPathMatchingFilter() {
        return new URLPathMatchingFilter();
    }

    /**
     * 配置LogoutFilter
     * @return
     */
    public ShiroLogoutFilter shiroLogoutFilter(){
        ShiroLogoutFilter shiroLogoutFilter = new ShiroLogoutFilter();
        //配置登出后重定向的地址，等出后配置跳转到登录接口
        //shiroLogoutFilter.setRedirectUrl("/login");
        return shiroLogoutFilter;
    }


    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
        //设置realm.
        securityManager.setRealm(getDatabaseRealm());
        return securityManager;
    }

    @Bean
    public DatabaseRealm getDatabaseRealm(){
        DatabaseRealm myShiroRealm = new DatabaseRealm();
        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return myShiroRealm;
    }

    /**
     * 凭证匹配器
     * （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
     *  所以我们需要修改下doGetAuthenticationInfo中的代码;
     * ）
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();

        hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(2);//散列的次数，比如散列两次，相当于 md5(md5(""));

        return hashedCredentialsMatcher;
    }

    /**
     *  开启shiro aop注解支持.
     *  使用代理方式;所以需要开启代码支持;
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
