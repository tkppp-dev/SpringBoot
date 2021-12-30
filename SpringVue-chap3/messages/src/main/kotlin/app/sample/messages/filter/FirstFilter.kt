package app.sample.messages.filter

import org.apache.logging.log4j.LogManager
import javax.servlet.*

class FirstFilter : Filter {
    private val logger = LogManager.getLogger(FirstFilter::class.java)

    override fun init(filtercCongig: FilterConfig){
        logger.info("First Filter Init")
    }

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        logger.info("First Filter")
        chain?.doFilter(request, response)
    }

    override fun destroy() {
        logger.info("First Filter Destroy")
    }
}