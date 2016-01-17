package model

import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne

/**
 * Created by Semyon on 17.01.2016.
 */
@Entity open class StatEvent(timestamp: Date) {

    @Id
    @GeneratedValue
    val id: Long? = null

    var timestamp: Date = timestamp;

    constructor() : this(Date())
}

@Entity class ReadEvent : StatEvent {

    @ManyToOne
    val manga: Manga?

    val startedReadingTimestamp: Date;

    constructor(manga: Manga, startedReadingTimestamp: Date, timestamp: Date) : super(timestamp) {
        this.manga = manga
        this.startedReadingTimestamp = startedReadingTimestamp
    }
}

