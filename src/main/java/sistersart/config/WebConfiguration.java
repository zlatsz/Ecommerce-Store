package sistersart.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import sistersart.web.interceptors.BackGroundImgInterceptor;
import sistersart.web.interceptors.FaviconInterceptor;
import sistersart.web.interceptors.TitleInterceptor;

@Configuration
@EnableWebSecurity
public class WebConfiguration implements WebMvcConfigurer {
    private final TitleInterceptor titleInterceptor;
    private final FaviconInterceptor faviconInterceptor;
    private final BackGroundImgInterceptor backGroundImgInterceptor;

    @Autowired
    public WebConfiguration(TitleInterceptor titleInterceptor, FaviconInterceptor faviconInterceptor, BackGroundImgInterceptor backGroundImgInterceptor) {
        this.titleInterceptor = titleInterceptor;
        this.faviconInterceptor = faviconInterceptor;
        this.backGroundImgInterceptor = backGroundImgInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.titleInterceptor);
        registry.addInterceptor(this.faviconInterceptor);
        registry.addInterceptor(this.backGroundImgInterceptor);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("*");
    }
}
