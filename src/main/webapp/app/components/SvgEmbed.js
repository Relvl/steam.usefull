const React = require("react");
const AComponent = require("../AComponent");

class SvgEmbed extends AComponent {
    render() {
        return <svg fill={this.props.fill}>
            <use xlinkHref={this.props.src + "#" + this.props.id}/>
        </svg>
    }
}

module.exports = SvgEmbed;