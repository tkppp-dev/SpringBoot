package app.sample.messages.filter

import org.apache.logging.log4j.LogManager
import org.springframework.core.annotation.Order
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.annotation.WebFilter

@WebFilter(urlPatterns = ["/*"])
@Order(0)
class FourthFilter: Filter {
    private val logger = LogManager.getLogger(FourthFilter::class.java)
    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        logger.info("Fourth Filter")
        chain?.doFilter(request, response)
    }
}