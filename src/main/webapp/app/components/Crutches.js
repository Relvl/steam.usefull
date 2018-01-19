import * as React from "react";

class Crutches extends React.Component {
    render() {
        return <div className="_crutches_box">
            <svg>
                <defs>
                    <linearGradient id="steam-gradient" x2="50%" x1="50%" y2="100%">
                        <stop stopColor="#111D2E" offset="0"/>
                        <stop stopColor="#051839" offset=".21248"/>
                        <stop stopColor="#0A1B48" offset=".40695"/>
                        <stop stopColor="#132E62" offset=".58110"/>
                        <stop stopColor="#144B7E" offset=".73760"/>
                        <stop stopColor="#136497" offset=".87279"/>
                        <stop stopColor="#1387B8" offset="1"/>
                    </linearGradient>
                </defs>
            </svg>
        </div>;
    }
}

export default Crutches;