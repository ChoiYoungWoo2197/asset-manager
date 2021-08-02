package kr.co.cmt.assetmanager.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    String admin = "admin";
    String user = "user";

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // static 디렉터리의 하위 파일 목록은 인증 무시 ( = 항상통과 )
        web.ignoring().antMatchers("/js/**","/css/**","/img/**","/fonts/**", "*.ico","/html/**", "/adminlte/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/**").permitAll()
                .anyRequest().permitAll();



/*        http.csrf().disable();		// 개발 시 에만
        http.authorizeRequests()
                .antMatchers("/api/authoritys/**", "/api/departments/**").access("hasRole('admin')")	// 관리자 전용 페이지
                .antMatchers("/api/categorys/**",  "/api/category-specifications/**", "/api/partner-companys/**", "/api/partner-company-members/**")
                    .access("hasRole('admin') or hasRole('asset')") //관리자, 자산담당자 전용 페이지
                .antMatchers("/", "/logins","/api/logins/sing-in", "/api/logins/sign-out", "/api/members/test-create","/logins/login-success").permitAll()
                .antMatchers("/**").authenticated();

        http.formLogin()
                .loginPage("/")
                .loginProcessingUrl("/logins/sign-in") //"/logins/sign-in"로 요청하면 시큐리티가 인터셉트 한다.(컨트롤러를 생성 안해도 되는거 같네..)
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/logins/login-success"); //로긴 성공하면 여기 페이지를 띄워라.
        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true);*/


/*        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/api/hello").permitAll()
                .antMatchers("/api/logins").authenticated()
                .anyRequest().authenticated()
                .and()
                .csrf().disable();*/
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("foo").password("{noop}bar").roles("USER");
    }

}
