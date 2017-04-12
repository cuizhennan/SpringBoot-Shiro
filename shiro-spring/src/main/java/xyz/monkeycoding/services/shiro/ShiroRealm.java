package xyz.monkeycoding.services.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by CZN on 2017/4/12.
 */
public class ShiroRealm extends AuthorizingRealm {
    private static final Logger logger = LoggerFactory.getLogger(ShiroRealm.class);

    /**
     * 资源授权
     * 
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    /**
     * 登录认证
     * 
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        logger.info("INIT AuthenticationInfo USER: {}", token.getPrincipal());

        UsernamePasswordToken authToken = (UsernamePasswordToken) token;
        // check account
        if (authToken.getUsername().equals("admin")) {
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo("admin", "admin",
                    getName());
            return simpleAuthenticationInfo;
        } else {
            // 没有找到账号
            throw new UnknownAccountException("UnknownAccountException");
        }
    }
}
