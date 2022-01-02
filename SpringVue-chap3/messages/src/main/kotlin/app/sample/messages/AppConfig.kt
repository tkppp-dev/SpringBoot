package app.sample.messages

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.orm.hibernate5.LocalSessionFactoryBean
import javax.sql.DataSource

@Configuration
class AppConfig(val dataSource: DataSource) {

    @Bean
    fun sessionFactory(): LocalSessionFactoryBean {
        val sessionFactoryBean = LocalSessionFactoryBean()
        sessionFactoryBean.setDataSource(dataSource)
        sessionFactoryBean.setPackagesToScan("app.sample.messages")
        return sessionFactoryBean
    }

    @Bean
    fun registerBeanTest(): BeanTest {
        return BeanTest()
    }
}