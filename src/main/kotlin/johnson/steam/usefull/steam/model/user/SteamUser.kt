package johnson.steam.usefull.steam.model.user

import com.fasterxml.jackson.annotation.JsonProperty

/** @author karpov-em on 15.12.2017*/
class SteamUser {
    // http://localhost/test_steam
    // openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0
    // openid.mode=id_res | cancel
    // openid.op_endpoint=https%3A%2F%2Fsteamcommunity.com%2Fopenid%2Flogin
    // openid.claimed_id=http%3A%2F%2Fsteamcommunity.com%2Fopenid%2Fid%2F76561198087511877 <- Всё, что после http://steamcommunity.com/openid/id/ - это ид залогинившегося
    // openid.identity=http%3A%2F%2Fsteamcommunity.com%2Fopenid%2Fid%2F76561198087511877 <- Всё, что после http://steamcommunity.com/openid/id/ - это ид залогинившегося
    // openid.return_to=http%3A%2F%2Flocalhost%2Ftest_steam
    // openid.response_nonce=2017-11-28T18%3A03%3A50Zp7Y3FPGl7v5xUwl7TzGps6CfVlU%3D
    // openid.assoc_handle=1234567890
    // openid.signed=signed%2Cop_endpoint%2Cclaimed_id%2Cidentity%2Creturn_to%2Cresponse_nonce%2Cassoc_handle
    // openid.sig=OSVCewqd4bn9hQX%2BJiXbs4Bggsw%3D

    //http://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002/?key=612343986D22832CE43FD61C08CBF647&steamids=76561198087511877

    @JsonProperty("steamid")
    var steamId: String = ""

    @JsonProperty("communityvisibilitystate")
    var communityVisibilityState: Int = 0

    @JsonProperty("profilestate")
    var profileState: Int = 0

    @JsonProperty("personaname")
    var personName: String = ""

    @JsonProperty("lastlogoff")
    var lastLogoff: Long = 0L

    @JsonProperty("profileurl")
    var profileUrl: String = ""

    @JsonProperty("avatar")
    var avatarUrl: String = ""

    @JsonProperty("avatarmedium")
    var avatarMediumUrl: String = ""

    @JsonProperty("avatarfull")
    var avatarFullUrl: String = ""

    @JsonProperty("personastate")
    var personState: Int = 0

    @JsonProperty("realname")
    var realName: String = ""

    @JsonProperty("primaryclanid")
    var primaryClanId: String = ""

    @JsonProperty("timecreated")
    var timeCreated: Long = 0L

    @JsonProperty("personastateflags")
    var personStateFlags: Int = 0

    @JsonProperty("loccountrycode")
    var countryCode: String = "EN" // TODO ECountry

    @JsonProperty("locstatecode")
    var locStateCode: String = ""

}