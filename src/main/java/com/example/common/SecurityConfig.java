package com.example.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * パスワードをハッシュ化する為のセキュリティーコンフィグクラス.
 * 
 * @author matsuokatoshiichi
 *
 */
@Configuration
public class SecurityConfig {

	/**
	 * 静的リソースに対してセキュリティの設定を無効にする.
	 * 
	 */
	@Bean
	WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().requestMatchers("/css/**", "/img_toy/**", "/js/**");
	}

	/**
	 * 認可の設定やログイン/ログアウトに関する設定.
	 * 
	 * @param http
	 * @return
	 * @throws Exception
	 */
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests()
				.requestMatchers("/", "/login-user","/login-user/**", "/insert-user", "/insert-user/insert","/item/**",
						"/item/showItemList/showDetail", "/shoppingcart/**", "/shoppingcart/cart",
						"/shoppingcart/to-cartlist", "/shoppingcart/delete", "/orderconfilm/**",
						"/orderconfilm/vieworder","/sort")
				.permitAll()

				.anyRequest().authenticated();

		http.formLogin().loginPage("/login-user").loginProcessingUrl("/login-user/login")
				.failureUrl("/login-user?error=true").defaultSuccessUrl("/", true).usernameParameter("email")
				.passwordParameter("password");

		http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/login-user/logout"))
				.logoutSuccessUrl("/login-user").deleteCookies("JSESSIONID").invalidateHttpSession(true);

		return http.build();
	}

	/**
	 * <pre>
	 * bcryptアルゴリズムでハッシュ化する実装を返します.
	 * これを指定することでパスワード暗号化やマッチ確認する際に
	 * @Autowired
	 * private PasswordEncoder passwordEncoder;
	 * と記載するとDIされるようになります。
	 * </pre>
	 * 
	 * @return bcryptアルゴリズムで暗号化する実装オブジェクト
	 */
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
