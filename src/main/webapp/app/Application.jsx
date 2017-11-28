const React = require("react");

class Application extends React.Component {
    render() {
        let m = [1, 2, 3].map(value => String(value));

        return <div>Test!</div>;
    }
}

ReactDOM.render(<Application/>, document.getElementById("app-root"));