package model

import java.util.*
import javax.persistence.*

/**
 * Created by Semyon on 17.01.2016.
 */
@Entity open class StatEvent(user: ReaderUser?, timestamp: Date) {

    @Id
    @GeneratedValue
    val id: Long? = null

    @Column
    var timestamp: Date = timestamp

    @Column
    var user: ReaderUser? = user

    constructor() : this(null, Date())
}

@Entity class AppLaunchEvent(user: ReaderUser?, timestamp: Date) : StatEvent(user, timestamp) {

    constructor() : this(null, Date())

}

@Entity class ReadEvent : StatEvent {

    @ManyToOne
    val manga: Manga?

    @Column
    val startedReadingTimestamp: Date;

    constructor(user: ReaderUser?, manga: Manga?, startedReadingTimestamp: Date, timestamp: Date) : super(user, timestamp) {
        this.manga = manga
        this.startedReadingTimestamp = startedReadingTimestamp
    }

    constructor() : this(null, null, Date(), Date())

}

