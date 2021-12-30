package app.sample.messages.config

import app.sample.messages.filter.FirstFilter
import app.sample.messages.filter.SecondFilter
import app.sample.messages.filter.ThirdFilter
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class FilterConfiguration : WebMvcConfigurer {

    @Bean
    fun firstFilterRegistrationBean(): FilterRegistrationBean<FirstFilter> {
        val registrationBean = FilterRegistrationBean(FirstFilter())
        registrationBean.order = 0
        registrationBean.urlPatterns = mutableListOf("/*")
        return registrationBean
    }

    @Bean
    fun secondFilterRegistrationBean(): FilterRegistrationBean<ThirdFilter>{
        val registrationBean = FilterRegistrationBean(ThirdFilter())
        registrationBean.order = 2
        registrationBean.urlPatterns = mutableListOf("/*")
        return registrationBean
    }
}