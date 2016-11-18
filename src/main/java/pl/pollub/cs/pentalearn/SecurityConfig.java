package pl.pollub.cs.pentalearn;

import org.springframework.context.annotation.Bean;
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
import pl.pollub.cs.pentalearn.service.handlers.LogoutSuccesHandler;
import pl.pollub.cs.pentalearn.service.handlers.MyAuthenticationFailureHandler;
import pl.pollub.cs.pentalearn.service.handlers.MyAuthenticationSuccessHandler;
import pl.pollub.cs.pentalearn.service.impl.UserDetailsServiceImpl;

import javax.inject.Inject;
import javax.sql.DataSource;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    final DataSource dataSource;



    @Inject
    SecurityConfig(final DataSource dataSource){
        this.dataSource=dataSource;
    }

    @Bean
    public MyAuthenticationSuccessHandler myAuthenticationSuccessHandler(){
        return new MyAuthenticationSuccessHandler();
    }

    @Bean
    public MyAuthenticationFailureHandler myAuthenticationFailureHandler(){
        return new MyAuthenticationFailureHandler();
    }

    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().ignoringAntMatchers("/auth/login_check**").ignoringAntMatchers("/api/registration**").csrfTokenRepository(csrfTokenRepository())

                .and()
                .httpBasic()

                .and()
                .logout()
                .logoutUrl("/j_spring_security_logout")
                .deleteCookies("JSESSIONID")
                .addLogoutHandler(new LogoutSuccesHandler("/logout"))

                .and()
                .rememberMe()
                .tokenRepository(jdbcTokenRepository())
                .tokenValiditySeconds(86400)


                .and()
                .authorizeRequests()
                .antMatchers("/api/**").permitAll()
                .antMatchers("/api/root/**").hasRole("ROOT")
                .antMatchers("/api/user/**").hasAnyRole("ROOT","TEACHER","USER")
                .antMatchers("/api/teacher/**").hasAnyRole("ROOT","TEACHER")

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
                .sessionRegistry(sessionRegistry());}

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }


    @Bean
    public PersistentTokenBasedRememberMeServices rememberMeAuthenticationProvider(){
        return new PersistentTokenBasedRememberMeServices("myAppKey",userDetailsService(),jdbcTokenRepository());
    }

    @Bean
    public HttpSessionCsrfTokenRepository csrfTokenRepository(){
        HttpSessionCsrfTokenRepository csrfTokenRepository=new HttpSessionCsrfTokenRepository();
        csrfTokenRepository.setHeaderName("X-XSRF-TOKEN");
        return new HttpSessionCsrfTokenRepository();
    }


    @Bean
    public JdbcTokenRepositoryImpl jdbcTokenRepository(){
        JdbcTokenRepositoryImpl jdbcTokenRepository=new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setCreateTableOnStartup(false);
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(11);
    }

    @Bean
    public ConcurrentSessionFilter concurrentSessionFilter(){
        ConcurrentSessionFilter concurrentSessionFilter=new ConcurrentSessionFilter(sessionRegistry(),"/session-expired.htm");
        return concurrentSessionFilter;
    }

    @Bean
    public SessionRegistryImpl sessionRegistry(){
        return new SessionRegistryImpl();
    }


    @Bean
    public UserDetailsServiceImpl userDetailsService(){
        return new UserDetailsServiceImpl();
    }

}
