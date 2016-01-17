package controller.api

import model.AuthToken
import model.StatEventType
import org.json.JSONArray
import org.json.JSONObject
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import repository.AppLaunchEventRepository
import repository.ReadEventRepository
import repository.TokenRepository
import java.util.*
import javax.inject.Inject
import javax.servlet.http.HttpServletRequest

/**
 * Created by Semyon on 17.01.2016.
 */

@RestController
@RequestMapping("/api/stats/")
class StatisticsController {

    val tokenRepository: TokenRepository;
    val readEventRepository: ReadEventRepository;
    val appLaunchEventRepository: AppLaunchEventRepository;


    @Inject
    constructor(tokenRepository: TokenRepository, readEventRepository: ReadEventRepository, appLaunchEventRepository: AppLaunchEventRepository) {
        this.tokenRepository = tokenRepository
        this.readEventRepository = readEventRepository
        this.appLaunchEventRepository = appLaunchEventRepository
    }

    @RequestMapping(value = "/post", method = arrayOf(RequestMethod.POST))
    fun postStats(request: HttpServletRequest, @RequestBody statsData: JSONObject): JSONObject {
        val response = JSONObject()

        val tokenValue: String = statsData.get("token") as String
        val tokenOptional = tokenRepository.findOneByToken(tokenValue)

        val authToken: AuthToken
        if (tokenOptional.isPresent) {
            authToken = tokenOptional.get()
        } else {
            response.put("success", false)
            response.put("message", "Token expired or not found")
            return response
        }
        val user = authToken.user

        val statsList = statsData.get("stats") as JSONArray
        for (statEvent in statsList) {
            val _statEvent = statEvent as JSONObject
            val statType = _statEvent.get("stat_type")
            when (statType) {
                StatEventType.APP_LAUNCH.toString() -> {
                    Date(_statEvent.get("timestamp") as Long)
                }
                StatEventType.READ.toString() -> {

                }
            }
        }

        return response
    }

}