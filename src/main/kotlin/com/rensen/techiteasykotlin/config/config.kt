import com.rensen.techiteasykotlin.config.JwtRequestFilter
import com.rensen.techiteasykotlin.config.JwtService
import com.rensen.techiteasykotlin.services.UserService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class Securityconfig{

    @Bean
    fun filterChain(http: HttpSecurity, jwtFilter: JwtRequestFilter, jwtservice: JwtService, userService: UserService){
        http
            .httpBasic{it.disable()}
            .authorizeHttpRequests { it
                .requestMatchers("/**").permitAll()
            }
            .sessionManagement{it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)}
            .addFilterBefore(JwtRequestFilter(jwtservice, userService), UsernamePasswordAuthenticationFilter::class.java)
    }

}