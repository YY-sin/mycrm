package cn.xaut.crm.settings.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String id;//主键
    private String loginAct;//登录账号
    private String name;//用户真实姓名
    private String loginPwd;//用户密码
    private String email;//邮箱
    private String expireTime;//失效时间  yyyy-MM-dd HH-mm-ss  共19位
    private String lockState;//锁定状态 ，0：表示锁定，1：表示启用
    private String deptno;//部门标号
    private String allowIps;//允许访问的IP地址
    private String createTime;//创建时间 19
    private String createBy;//创建人
    private String editTime;//修改时间  19
    private String editBy;//修改人

}
