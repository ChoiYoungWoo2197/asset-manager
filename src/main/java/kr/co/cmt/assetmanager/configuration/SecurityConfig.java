package kr.co.cmt.assetmanager.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/js/**","/css/**","/img/**","/fonts/**", "*.ico","/html/**");
        // static 디렉터리의 하위 파일 목록은 인증 무시 ( = 항상통과 )
        web.ignoring().antMatchers("/resource/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // No session will be created or used by spring security
//                .and()
//                .httpBasic()
//                .and()
//                .authorizeRequests()
//                .antMatchers("/api/hello").permitAll()
//                .antMatchers("/api/user/**").permitAll() // allow every URI, that begins with '/api/user/'
//                .antMatchers("/api/secured").authenticated()
//                .anyRequest().authenticated() // protect all other requests
//                .and()
//                .formLogin()            //로그인 수행
//                .loginPage("/test")
//                .permitAll()
//                .and()
//                .logout()               //로그아웃 수행
//                .permitAll()
//                .and()
//                .csrf().disable(); // disable cross site request forgery, as we don't use cookies - otherwise ALL PUT, POST, DELETE will get HTTP 403!

//        http.authorizeRequests()
////                .antMatchers("/admin/**").hasRole("ADMIN")	//Admin 권한이 있는 경우 접근 허용
////                .antMatchers("/user/**").hasRole("USER")	//User 권한이 있는 경우 접근 허용
//                .antMatchers("/","/api/logins/signin", "/apoi/logins/singout").permitAll() //해당 URL은 전체 접근 허용
//                .anyRequest().authenticated()	//이외의 URL은 인증 절차를 수행하기 위해 login 페이지로 이동
//                .and().csrf().disable()
//                .formLogin()            //로그인 수행
//                .loginPage("/api/logins")
//                .permitAll()
//                .and()
//                .logout()               //로그아웃 수행
//                .permitAll();
        http.authorizeRequests()
//                .antMatchers("/admin/**").hasAnyAuthority("ADMIN")// 관리자페이지를 위한 곳
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()// 여기까지는 기본적으로 경로를 어떻게 사용할 것인가를 위한 곳이다
                .and().csrf().disable()
                .formLogin()            //로그인 수행
                .loginPage("/api/logins").permitAll()
                .and()
                .logout().permitAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

/*    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://127.0.0.1:8080")
                .allowedOrigins("http://127.0.0.1:8081")
                .allowedOrigins("http://localhost:8081")
                .allowedOrigins("http://localhost:8080");
    }*/
}
