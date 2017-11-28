const gulp = require("gulp");
const gulpif = require("gulp-if");
const gutil = require("gulp-util");
const arrayToPipe = require("multipipe");
const plumber = require("gulp-plumber");
const server = require("gulp-server-livereload");
const webpack = require("webpack");
const webpackStream = require("webpack-stream");
const ExtractTextPlugin = require("extract-text-webpack-plugin");
const _ = require("underscore");

const Options = {
    minify: false,
    base: "./src/main/webapp/",
    scss: "./scss/application.scss",
    css_target: [
        "./build/libs/exploded/steam.usefull-1.0-SNAPSHOT.war/",
        "./src/main/webapp/"
    ],
    js_target: [
        "./build/libs/exploded/steam.usefull-1.0-SNAPSHOT.war/",
        "./src/main/webapp/"
    ],
    live_reload: {
        enabled: true,
        port: 35730,
        files: ["./build/libs/exploded/steam.usefull-1.0-SNAPSHOT.war/application.css"]
    },

    babel_config: {
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
};

gulp.task("live-reload", function () {
    gutil.log(
        gutil.colors.bgGreen(gutil.colors.black("LiveReload server: ")),
        Options.live_reload.enabled
            ? gutil.colors.bgGreen(gutil.colors.black("ENABLED")) + ", use <script src=\"http://localhost:" + Options.live_reload.port + "/livereload.js\" type=\"text/javascript\"></script>"
            : gutil.colors.bgRed(gutil.colors.white("DISABLED"))
    );
    return gulp
        .src(Options.live_reload.files)
        .pipe(gulpif(Options.live_reload.enabled,
            server({
                port: 0,
                log: "error",
                livereload: {
                    enable: true,
                    port: Options.live_reload.port
                }
            })
        ));
});

gulp.task("jsx-build", function () {
    return gulp
        .src("app/Application.jsx", {cwd: Options.base})
        .pipe(plumber())
        .pipe(webpackStream({
            devtool: "source-map",
            watch: true,
            module: {
                loaders: [{
                    test: /.jsx?$/,
                    loader: "babel-loader",
                    exclude: /node_modules/,
                    query: Options.babel_config
                }]
            },
            plugins: _.compact([Options.minify ? new webpack.optimize.UglifyJsPlugin() : null]),
            output: {filename: "application" + (Options.minify ? ".min" : "") + ".js"}
        }))
        .pipe(plumber.stop())
        .pipe(arrayToPipe(_.map(Options.js_target, (target) => gulp.dest(target).on("data", (file) => file.path.endsWith(".map") ? null : gutil.log("JS Compiled: " + file.path)))));
});

gulp.task("scss-build", function () {
    return gulp
        .src(Options.scss, {cwd: Options.base, base: Options.base})
        .pipe(plumber())
        .pipe(webpackStream({
            devtool: "source-map",
            watch: true,
            module: {
                rules: [
                    {
                        test: /\.css$/,
                        exclude: /node_modules/,
                        loader: ExtractTextPlugin.extract(['css-loader?importLoaders=1']),
                    },
                    {
                        test: /\.(sass|scss)$/,
                        exclude: /node_modules/,
                        loader: ExtractTextPlugin.extract(['css-loader', 'sass-loader', {
                            loader: 'postcss-loader',
                            options: {
                                plugins: [require('autoprefixer')({
                                    browsers: ["last 2 versions", "Safari >= 8", "ie >= 10"],
                                    cascade: true,
                                    add: true,
                                    remove: true
                                })]
                            }
                        }])
                    }
                ]
            },
            plugins: [
                new ExtractTextPlugin({filename: 'application.css', disable: false, allChunks: true}),
            ],
            output: {filename: "application.css"}
        }))

        .pipe(plumber.stop())
        .pipe(arrayToPipe(_.map(Options.css_target, (target) => gulp.dest(target).on("data", (file) => file.path.endsWith(".map") ? null : gutil.log("CSS Compiled: " + file.path)))));
});

gulp.task("default", ["scss-build", "jsx-build"]);