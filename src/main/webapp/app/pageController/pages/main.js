import * as React from "react";
import RequestExecutor from "../../lib/request-executor";
import APage from "../APage";
import Utils from "../../Utils";

class PageMain extends Utils.MultiClass(React.Component, APage) {

    static getPageNames() {
        return ["", "main"];
    }

    doRequests() {
        for (let obj = 0; obj < 2; obj++) {
            RequestExecutor.call("/api/game/test", {aaa: String(obj)}, "get")
                .then((r) => {
                    console.log("APP on response(", obj, "): ", r);
                })
                .catch((e) => {
                    console.error("APP on error: ", e, " args: ", arguments);
                });
        }
    }

    render() {
        return <div>
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
        </div>;
    }
}

export default PageMain;