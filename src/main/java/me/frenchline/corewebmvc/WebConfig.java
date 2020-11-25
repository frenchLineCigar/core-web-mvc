package me.frenchline.corewebmvc;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UrlPathHelper;

import java.util.List;

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


    /**
     * 만약에 우리가 원하는 HttpMessageConverter가 기본설정으로 등록이 안되있다면
     * WebMvcConfigurer를 구현한 설정 클래스에서 원하는 컨버터를 추가할 수 있다.
     */
    /* HttpMessageConverter 추가 */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {

    }
    /* HttpMessageConverter 설정 : 기본 설정으로 등록되는 메세지 컨버터가 무효화된다.(가급적이면 사용 X) */
//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//    }
}
