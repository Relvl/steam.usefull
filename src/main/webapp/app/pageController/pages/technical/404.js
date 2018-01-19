import Utils from "../../../Utils";
import APage from "../../APage";
import React from "react";

class Page_404 extends Utils.MultiClass(React.Component, APage) {

    render() {
        return <div>404: Page not found</div>;
    }
}

export default Page_404;