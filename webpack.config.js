const path = require('path');
const webpack = require("webpack");
const _ = require("underscore");

module.exports = function (config) {
    return {
        devtool: 'source-map',
        module: {
            loaders: [{
                test: /.jsx?$/,
                loader: 'babel-loader',
                exclude: /node_modules/,
                query: {
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
            }]
        },
        plugins: _.compact([
            config.min ? new webpack.optimize.UglifyJsPlugin() : null
        ])/*,
        output: {
            filename: 'application.js'
        }*/
    };
};
