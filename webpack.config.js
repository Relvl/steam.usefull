const path = require('path');
const webpack = require("webpack");

module.exports = [{
    name: "sources",
    entry: {
        '/src/main/webapp/application.min.js': './src/main/webapp/app/Application.jsx',
    },
    output: {
        path: __dirname,
        filename: "[name]"
    },
    //devtool: 'source-map',
    module: {
        rules: [
            {
                test: /\.(js|jsx)$/,
                exclude: /node_modules/,
                use: {
                    loader: 'babel-loader',
                    options: {
                        presets: [
                            "babel-preset-env",
                            "babel-preset-es2015",
                            "babel-preset-es2016",
                            "babel-preset-es2017",
                            "babel-preset-react",
                            "babel-preset-stage-0",
                            "babel-preset-stage-1",
                            "babel-preset-stage-2",
                            "babel-preset-stage-3",
                            "babel-polyfill"
                        ],
                        plugins: [
                            "transform-decorators-legacy",
                            "transform-es2015-modules-commonjs"
                        ]
                    }
                }
            }
        ]
    },
    plugins: [
        new webpack.optimize.UglifyJsPlugin()
    ]
}];