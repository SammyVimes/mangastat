package controller

import model.ReaderUser
import org.json.JSONObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Required
import org.springframework.web.bind.annotation.*
import service.UserService
import javax.inject.Inject
import javax.json.JsonObject
import javax.servlet.http.HttpServletRequest
import kotlin.collections.getValue
import kotlin.text.isEmpty

/**
 * Created by Semyon on 02.01.2016.
 */


@RestController
@RequestMapping("/auth")
class AuthController {

    val userService: UserService;

    @Inject
    public constructor(userService: UserService) {
        this.userService = userService;
    }

    /**
     *Проверка валидности токена
     */
    @RequestMapping(value = "/submittoken", method = arrayOf(RequestMethod.POST))
    fun auth(request: HttpServletRequest, @RequestParam(value = "token", defaultValue = "") token: String) : JSONObject {
        val exists = userService.exists(token)
        val json = JSONObject()
        json.put("success", exists)
        return json
    }

}

public inline fun <T, R> T?.trycall(defValue: R, f: (T) -> R): R {
    if (this != null) {
        return f(this)
    }
    return defValue
}

@RestController
@RequestMapping("/registration")
class RegistrationController {

    val userService: UserService;

    @Inject
    public constructor(userService: UserService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/", method = arrayOf(RequestMethod.POST))
    fun register(request: HttpServletRequest, @RequestBody registrationData: Map<String, Any>) : JSONObject {
        val user = ReaderUser()
        user.androidId = registrationData.get("androidId") as String
        user.gmail = registrationData.get("gmail") as String //fails here beacuse of actual NPE in java side
        user.name = registrationData.get("name") as String
        user.vkId = registrationData.get("vkId") as String
        user.extra = registrationData.get("extra") as String
        userService.createUser(user)
        val token = userService.generateToken(user).trycall("") {
            it.token
        }
        var success = false
        if (!token.isEmpty()) {
            success = true
        }
        val jsonObject = JSONObject()
        jsonObject.put("success", success)
        jsonObject.put("token", token)
        return jsonObject
    }

}
