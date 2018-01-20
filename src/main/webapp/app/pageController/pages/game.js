import React from "react";
import Utils from "../../Utils";
import APage from "../APage";

class PageGame extends Utils.MultiClass(React.Component, APage) {
    static getPageNames() {
        return ["game"];
    }

    componentWillMount() {
        this.state = {
            appId: this.getPathArgument()
        };
    }

    render() {
        console.log("props: ", this.props)/*FIXME! Remove log!*/;

        return <div>
            <section>
                <div className="flex-row">
                    <img className="game_main_image" src={"http://cdn.akamai.steamstatic.com/steam/apps/" + this.state.appId + "/header.jpg"}/>
                    <div className="flex-col">
                        <h3>Tales of Monkey Island Complete Pack: Chapter 4 - The Trial and Execution of Guybrush Threepwood</h3>
                        <div className="flex-row tag_container">
                            <a>Steam</a>
                            <a>Hard game</a>
                            <a>Rogue-like</a>
                            <a>Casual</a>
                            <a>For mature</a>
                            <a>18+</a>
                            <a>Violence</a>
                            <a>Achievements</a>
                            <a>Achievements</a>
                            <a>Achievements</a>
                            <a>Achievements</a>
                            <a>Achievements</a>
                            <a>Achievements</a>
                            <a>Achievements</a>
                            <a>Achievements</a>
                            <a>Achievements</a>
                            <a>Achievements</a>
                            <a>Achievements</a>
                            <a>Achievements</a>
                            <a>Achievements</a>
                            <a>Achievements</a>
                            <a>Achievements</a>
                            <a>Achievements</a>
                            <a>Achievements</a>
                            <a>Achievements</a>
                        </div>
                    </div>
                </div>
            </section>
        </div>;
    }
}

export default PageGame;