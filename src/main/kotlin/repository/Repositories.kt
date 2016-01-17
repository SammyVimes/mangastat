package repository

import model.AppLaunchEvent
import model.AuthToken
import model.ReadEvent
import model.ReaderUser
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

/**
 * Created by Semyon on 02.01.2016.
 */
@Repository
interface ReaderUserRepository : JpaRepository<ReaderUser, Long> {


}

@Repository
interface TokenRepository : JpaRepository<AuthToken, Long> {

    fun findOneByUser(user: ReaderUser): Optional<AuthToken>;

    fun findOneByToken(token: String): Optional<AuthToken>;

}

interface StatRepository<T> : JpaRepository<T, Long> {

    fun findOneByUser(user: ReaderUser): Optional<T>;

}

@Repository
interface ReadEventRepository : StatRepository<ReadEvent> {


}

@Repository
interface AppLaunchEventRepository : StatRepository<AppLaunchEvent> {

}