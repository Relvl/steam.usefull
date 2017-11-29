const React = require("react");
const ReactDOM = require("react-dom");
const AComponent = require("./AComponent.jsx");

const _ = require("underscore");

const TopMenu = require("./TopMenu.jsx");
const RequestExecutor = require("./lib/request-executor");

class Application extends AComponent {

    doRequests() {
        for (let obj = 0; obj < 2; obj++) {
            RequestExecutor.call("/api/game/test", {aaa: String(obj)}, "get")
                .then((r) => {console.log("APP on response(", obj, "): ", r);})
                .catch((e) => {console.error("APP on error: ", e, " args: ", arguments);});
        }
    }

    render() {
        return <div className="page-wrapper">
            <TopMenu/>
            <div>
                <section>
                    <h2>Header!</h2>
                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea
                        commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim
                        id est laborum.</p>
                    <p>Just paragraph!!</p>
                </section>
                <section>
                    <h2>Header!</h2>
                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea
                        commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim
                        id est laborum.</p>
                    <p>Just paragraph!!</p>

                    <p onClick={() => this.doRequests()}>CLICK ME!</p>
                </section>
            </div>
        </div>;
    }
}

ReactDOM.render(<Application/>, document.getElementById("application"));