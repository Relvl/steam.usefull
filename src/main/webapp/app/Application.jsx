import * as React from "react";
import * as ReactDOM from "react-dom";
import {HashRouter, Route, Switch} from 'react-router-dom';

import TopMenu from "./components/TopMenu";
import Crutches from "./components/Crutches";

import Page_404 from "./pageController/pages/technical/404";
import PageMain from "./pageController/pages/main";
import PageProfile from "./pageController/pages/profile";
import PageGame from "./pageController/pages/game";

class Application extends React.Component {

    constructor() {
        super();
    }

    /** @param {HashStruct} hashStruct */
    onHashChanged(hashStruct) {

    }

    render() {
        return <div className="page-wrapper">
            <TopMenu/>

            <Switch>
                <Route exact path='/' component={PageMain}/>
                <Route exact path='/main' component={PageMain}/>
                <Route exact path='/profile' component={PageProfile}/>
                <Route path='/game' component={PageGame}/>
                <Route path='*' component={Page_404}/>
            </Switch>

            <Crutches/>
        </div>;
    }
}

ReactDOM.render(<HashRouter hashType="noslash"><Application/></HashRouter>, document.getElementById("application"));

export default Application;