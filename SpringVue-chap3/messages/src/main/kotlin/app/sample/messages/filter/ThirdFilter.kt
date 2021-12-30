package app.sample.messages.filter

import org.apache.logging.log4j.LogManager
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse

class ThirdFilter : Filter {
    private val logger = LogManager.getLogger(SecondFilter::class.java)

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        logger.info("Third Filter")
        chain?.doFilter(request, response)
    }
}