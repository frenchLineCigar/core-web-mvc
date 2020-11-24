package me.frenchline.corewebmvc;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UrlPathHelper;

/**
 * @author swlee
 * @contact frenchline707@gmail.com
 * @since 2019-11-23
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /* @MatrixVariable 바인딩을 위한 설정 */
    /* GET /events/1;name=frenchline */
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        UrlPathHelper urlPathHelper = new UrlPathHelper();
        urlPathHelper.setRemoveSemicolonContent(false); //세미콜론 컨텐츠를 제거하지 않도록 설정
        configurer.setUrlPathHelper(urlPathHelper);
    }

    /* 인터셉터 등록 */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new VisitTimeInterceptor()).addPathPatterns("/events/**"); //접속 시간 기록을 위한 인터셉터
    }
}
