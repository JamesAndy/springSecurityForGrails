package bs.security

import grails.plugin.springsecurity.userdetails.GrailsUser
import org.springframework.security.core.GrantedAuthority

/**
 * File Description/檔案描述:
 * @author JamesChang* @since 2020/4/12下午 01:47
 * @version 1.0*
 */
class CustomUserDetails extends GrailsUser {
    final String fullname

    CustomUserDetails(String username, String password, boolean enabled,
                      boolean accountNonExpired, boolean credentialsNonExpired,
                      boolean accountNonLocked,
                      Collection<GrantedAuthority> authorities,
                      UUID id, String fullname) {
        super(username, password, enabled, accountNonExpired,
                credentialsNonExpired, accountNonLocked, authorities, id)

        this.fullname = fullname
    }
}
