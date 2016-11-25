package pl.pollub.cs.pentalearn;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.session.ConcurrentSessionFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.context.request.RequestContextListener;
import pl.pollub.cs.pentalearn.service.handlers.HttpLogoutSuccessHandler;
import pl.pollub.cs.pentalearn.service.handlers.MyAuthenticationFailureHandler;
import pl.pollub.cs.pentalearn.service.handlers.MyAuthenticationSuccessHandler;
import pl.pollub.cs.pentalearn.service.impl.UserDetailsServiceImpl;

import javax.inject.Inject;
import javax.sql.DataSource;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    final DataSource dataSource;


    @Inject
    SecurityConfig(final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MyAuthenticationSuccessHandler myAuthenticationSuccessHandler() {
        return new MyAuthenticationSuccessHandler();
    }

    @Bean
    public MyAuthenticationFailureHandler myAuthenticationFailureHandler() {
        return new MyAuthenticationFailureHandler();
    }

    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().ignoringAntMatchers("/auth/login_check**").ignoringAntMatchers("/api/registration**").csrfTokenRepository(csrfTokenRepository())
                .and()
                .authenticationProvider(authenticationProvider())
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint())
                .and()
                .httpBasic()

                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .deleteCookies("JSESSIONID")
                .logoutSuccessHandler(logoutSuccessHandler())

                .and()
                .rememberMe()
                .tokenRepository(jdbcTokenRepository())
                .tokenValiditySeconds(86400)


                .and()
                .authorizeRequests()
                .antMatchers("/api/**").permitAll()
                .antMatchers("/api/root/**").hasRole("ROOT")
                .antMatchers("/api/user/**").hasAnyRole("ROOT", "TEACHER", "USER")
                .antMatchers("/api/teacher/**").hasAnyRole("ROOT", "TEACHER")

                .and()
                .formLogin()
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/auth/login_check")
                .successHandler(myAuthenticationSuccessHandler())
                .failureHandler(myAuthenticationFailureHandler())

                .and()
                .sessionManagement()
                .maximumSessions(1)
                .expiredUrl("/api/youarebanned")
                .sessionRegistry(sessionRegistry());
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());

        return authenticationProvider;
    }

    @Bean
    public PersistentTokenBasedRememberMeServices rememberMeAuthenticationProvider() {
        return new PersistentTokenBasedRememberMeServices("myAppKey", userDetailsService(), jdbcTokenRepository());
    }

    @Bean
    public HttpAuthenticationEntryPoint authenticationEntryPoint() {
        return new HttpAuthenticationEntryPoint();
    }

    @Bean
    public HttpLogoutSuccessHandler logoutSuccessHandler() {
        return new HttpLogoutSuccessHandler();
    }

    @Bean
    public HttpSessionCsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository csrfTokenRepository = new HttpSessionCsrfTokenRepository();
        csrfTokenRepository.setHeaderName("X-XSRF-TOKEN");
        return new HttpSessionCsrfTokenRepository();
    }


    @Bean
    public JdbcTokenRepositoryImpl jdbcTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setCreateTableOnStartup(false);
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }

    @Bean
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }

    @Bean
    public ConcurrentSessionFilter concurrentSessionFilter() {
        ConcurrentSessionFilter concurrentSessionFilter = new ConcurrentSessionFilter(sessionRegistry(), "/session-expired.htm");
        return concurrentSessionFilter;
    }

    @Bean
    public SessionRegistryImpl sessionRegistry() {
        return new SessionRegistryImpl();
    }


    @Bean
    public UserDetailsServiceImpl userDetailsService() {
        return new UserDetailsServiceImpl();
    }

}
