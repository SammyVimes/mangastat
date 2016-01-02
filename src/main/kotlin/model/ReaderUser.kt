package model

import javax.persistence.*

/**
 * Created by Semyon on 02.01.2016.
 */

@Entity data class ReaderUser(@Id
                              @GeneratedValue
                              var id: Long?,

                              @Column
                              var name: String,

                              @Column
                              var androidId: String,

                              @Column
                              var gmail: String,

                              @Column
                              var vkId: String,

                              @Column
                              var extra: String) {

    public constructor() : this(null, "", "", "", "", "")
}

@Entity data class AuthToken(@Id @GeneratedValue var id: Long?,
                             @Column var token: String,
                             @ManyToOne var user: ReaderUser?) {

    public constructor() : this(null, "", null)

}
