import * as React from "react";

class APage {
    props = {};
    state = {};
    argumentPaths = null;

    getPathArgument(index = 0) {
        if (!this.props) return null;
        if (!this.props.location) return null;
        if (!this.props.location.pathname) return null;
        if (this.argumentPaths == null)
            this.argumentPaths = this.props.location.pathname.split("/").compact().rest();
        return this.argumentPaths[index];
    }

    static getPageNames() {
        return null;
    }
}

export default APage;