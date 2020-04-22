import bs.security.UserPasswordEncoderListener
import bs.security.CustomUserDetailsService
// Place your Spring DSL code here
beans = {
    userPasswordEncoderListener(UserPasswordEncoderListener)
    userDetailsService(CustomUserDetailsService)
}
