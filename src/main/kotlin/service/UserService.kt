package service

import model.AuthToken
import model.ReaderUser
import org.springframework.stereotype.Service
import repository.ReaderUserRepository
import repository.TokenRepository
import java.math.BigInteger
import java.security.SecureRandom
import javax.inject.Inject

/**
 * Created by Semyon on 02.01.2016.
 */
@Service
class UserService {

    @Inject
    private var readerUserRepository: ReaderUserRepository? = null
        public set

    @Inject
    var tokenRepository: TokenRepository? = null;

    fun createUser(user: ReaderUser) {
        readerUserRepository?.save(user)
    }

    fun exists(token: String): Boolean {
        val valByToken = tokenRepository?.findOneByToken(token);
        if (valByToken != null) {
            return valByToken.isPresent
        }
        return false
    }

    private val rand = SecureRandom()
    fun generateToken(user: ReaderUser): AuthToken? {
        val token = BigInteger(130, rand).toString(32)
        val authToken = AuthToken(token, user)
        return tokenRepository?.save(authToken)
    }

}