const React = require("react");
const AComponent = require("./AComponent.js");
const SvgEmbed= require("./components/SvgEmbed");

class TopMenu extends AComponent {

    getSteamLoginUrl = () => require("./lib/object-to-url.js")({
        "url": "https://steamcommunity.com/openid/login",
        "openid.ns": "http://specs.openid.net/auth/2.0",
        "openid.mode": "checkid_setup",
        "openid.return_to": "http://localhost/#steam_login",
        "openid.realm": "http://localhost/",
        "openid.identity": "http://specs.openid.net/auth/2.0/identifier_select",
        "openid.claimed_id": "http://specs.openid.net/auth/2.0/identifier_select",
    });

    render() {
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

        return <div className="top-menu">
            <div className="top-menu_holder">
                <div>asdfsdf</div>
                <a className="flex-push-right steam-login-link" href={this.getSteamLoginUrl()}>
                    <div className="link-text flex-col">
                        Войти
                        <div>через Steam</div>
                    </div>
                    <SvgEmbed src="/images/collection.svg" id="steam"/>
                </a>
            </div>

        </div>
    }
}

module.exports = TopMenu;