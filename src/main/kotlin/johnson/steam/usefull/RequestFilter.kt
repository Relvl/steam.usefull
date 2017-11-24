package johnson.steam.usefull

import javax.servlet.*
import javax.servlet.annotation.WebFilter

/** @author karpov-em on 24.11.2017*/
@WebFilter(urlPatterns = arrayOf("/*"))
class RequestFilter : Filter {

    override fun init(filterConfig: FilterConfig?) {
    }

    override fun destroy() {
    }

    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        println("request filter: ${request.servletContext}")
        chain.doFilter(request, response);
    }

}