package com.turntabl.testsystem.configuration;
import com.turntabl.testsystem.dao.*;
import com.turntabl.testsystem.model.ValidAnswer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();
        http.requiresChannel()
                .requestMatchers(r -> r.getHeader("X-Forwarded-Proto") != null)
                .requiresSecure();
    }
    //configuring CORS
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new
                UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
    //defining student dao bean
    @Bean
    public StudentDAO studentDAO(){return new StudentDAO();}
    @Bean
    public StudentFeedbackDAO studentFeedbackDAO(){return new StudentFeedbackDAO();}
    @Bean
    public TestDAO testDAO(){return new TestDAO();}
    @Bean
    public CourseDAO courseDAO(){return new CourseDAO();}
    @Bean
    public QuestionDAO questionDAO(){return new QuestionDAO();}
    @Bean
    public OptionDAO optionDAO(){return new OptionDAO();}
    @Bean
    public ValidAnswerDAO validAnswerDAO(){return new ValidAnswerDAO();}

}
