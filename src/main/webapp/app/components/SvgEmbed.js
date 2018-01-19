const React = require("react");

class SvgEmbed extends React.Component {
    render() {
        return <svg fill={this.props.fill}>
            <use xlinkHref={this.props.src + "#" + this.props.id}/>
        </svg>;
    }
}

export default SvgEmbed;