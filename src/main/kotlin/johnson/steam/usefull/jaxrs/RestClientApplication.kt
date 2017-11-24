package johnson.steam.usefull.jaxrs

import javax.ws.rs.ApplicationPath
import javax.ws.rs.core.Application

/** @author karpov-em on 24.11.2017*/
@ApplicationPath("/client-api")
class RestClientApplication : Application()