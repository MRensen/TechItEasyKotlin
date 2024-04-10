import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer
import org.springframework.security.config.http.SessionCreationPolicy

@Configuration
@EnableWebSecurity
class Securityconfig{

    @Bean
    fun filterChain(http: HttpSecurity){
        http
            .httpBasic{it.disable()}
            .authorizeHttpRequests { it
                .requestMatchers("/**").permitAll()
            }
            .sessionManagement{it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)}
    }

}