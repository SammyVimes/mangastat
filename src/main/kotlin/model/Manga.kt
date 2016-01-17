package model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

/**
 * Created by Semyon on 17.01.2016.
 */
@Entity data class Manga(val mangaTitle: String,
                         val repositoryTitle: String,
                         @Id @GeneratedValue val id: Long? = null)