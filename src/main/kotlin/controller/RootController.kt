package controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import javax.servlet.http.HttpServletRequest

/**
 * Created by Semyon on 02.01.2016.
 */
@Controller
class RootController {

    @RequestMapping("/index")
    @ResponseBody
    fun index(request: HttpServletRequest): String {
        return ""
    }

}