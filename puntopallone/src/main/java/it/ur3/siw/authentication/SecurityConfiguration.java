package it.ur3.siw.authentication;
/*
import it.ur3.siw.model.enums.UserRole;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final DataSource dataSource;

    public SecurityConfiguration(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
        manager.setUsersByUsernameQuery(
            "SELECT username, password, 1 as enabled FROM credentials WHERE username=?");
        manager.setAuthoritiesByUsernameQuery(
            "SELECT username, role FROM credentials WHERE username=?");
        return manager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected SecurityFilterChain configure(final HttpSecurity httpSecurity) throws Exception {

    	httpSecurity.authorizeHttpRequests(authorize -> {
			// Risorse statiche sempre pubbliche
			authorize.requestMatchers(HttpMethod.GET, "/", "/homepage", "/register", "/login", "/css/**", "/images/**").permitAll();
			authorize.requestMatchers(HttpMethod.POST, "/register", "/login").permitAll();
			// [SOLO ADMIN] funzionalità dell'amministratore (GET e POST)
			authorize.requestMatchers(HttpMethod.GET, "/???/new").hasAnyAuthority(UserRole.ROLE_ADMIN.getRole());
			authorize.requestMatchers(HttpMethod.POST, "/???").hasAnyAuthority(UserRole.ROLE_ADMIN.getRole());
			// [ALMENO USER] funzionalità dell'utente registrato (GET e POST)
			authorize.requestMatchers(HttpMethod.GET, "/???/new").hasAnyAuthority(UserRole.ROLE_USER.getRole());
			authorize.requestMatchers(HttpMethod.POST, "/???").hasAnyAuthority(UserRole.ROLE_USER.getRole());
			// [TUTTI] funzionalità dell'utente qualsiasi (GET)
			authorize.requestMatchers(HttpMethod.GET, "/arbitri", "/arbitri/**").permitAll();
			// Sezione admin generica
			authorize.requestMatchers("/admin/**").hasAnyAuthority(UserRole.ROLE_ADMIN.getRole());
			// Tutto il resto richiede autenticazione
			authorize.anyRequest().authenticated();
		});

        httpSecurity.formLogin(form -> {
            form.loginPage("/login").permitAll();
            form.defaultSuccessUrl("/", true);
            form.failureUrl("/login?error=true");
        });

        httpSecurity.logout(logout -> {
            logout.logoutUrl("/logout");
            logout.logoutSuccessUrl("/");
            logout.invalidateHttpSession(true);
            logout.deleteCookies("JSESSIONID");
            logout.clearAuthentication(true);
            logout.permitAll();
        });

        return httpSecurity.build();
    }
}
*/
