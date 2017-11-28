package johnson.steam.usefull.database.entities

import johnson.steam.usefull.database.DatabaseEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

/**
 * @author Johnson / 25.11.2017 */
@Entity
@Table(name = "games")
class DbGame() : DatabaseEntity() {

    constructor(steamId: Int, name: String) : this() {
        this.steamId = steamId
        this.name = name
    }

    @Id
    @Column(name = "steam_id")
    var steamId: Int = 0

    @Column(name = "name")
    var name: String = ""


}
