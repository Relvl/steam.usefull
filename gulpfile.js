/* Файл настроек Gulp для сборки и имнификации .js, .css Сборку бандла осуществляет Webpack */

const gulp = require("gulp");
const gulpif = require("gulp-if");
const gutil = require("gulp-util");
const arrayToPipe = require("multipipe");
const concat = require("gulp-concat");
const plumber = require("gulp-plumber");
const sourcemaps = require("gulp-sourcemaps");
const sass = require("gulp-sass");
const autoprefixer = require("autoprefixer");
const postcss = require("gulp-postcss");
const cssnano = require("cssnano");
const server = require("gulp-server-livereload");
const webpack = require("webpack");
const webpackStream = require("webpack-stream");

const _ = require("underscore");

const Options = {
    /** -====================================- Минификация и углификация финальных .css и .js */
    minify: true,
    /** -====================================- Путь к сорцам */
    base: "./src/main/webapp/",
    /** -====================================- Пути к JSX файлам - слежение за изменениями и сборка из них */
    jsx: [
        "./app/**/*.jsx"
    ],
    /** -====================================- Пути к SCSS файлам - слезение за изменениями и сборка из них */
    scss: [
        "./scss/**/*.scss"
    ],
    /** -====================================- Пути к финальным файлам сборки CSS. Финальные файлы будут продублированы в каждом из указанных каталогов. */
    css_target: [
        "./build/libs/exploded/steam.usefull-1.0-SNAPSHOT.war/",
        "./src/main/webapp/"
    ],
    /** -====================================- Пути к финальным файлам сборки JS. Финальные файлы будут продублированы в каждом из указанных каталогов. */
    js_target: [
        "./build/libs/exploded/steam.usefull-1.0-SNAPSHOT.war/",
        "./src/main/webapp/"
    ],
    /** -====================================- Пути, по которым будут обновляться файлы клиента. */
    live_reload: {
        enabled: false,
        port: 35730,
        files: ["./**/*.css"]
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

gulp.task("scss-build", function () {
    gutil.log(gutil.colors.bgGreen(gutil.colors.black("======================= SCSS building " + (Options.minify ? gutil.colors.bgRed(gutil.colors.white("[MINIFIED]")) : ""))));
    return gulp
        .src(Options.scss, {cwd: Options.base, base: Options.base})
        .pipe(plumber())
        .pipe(sourcemaps.init())

        .pipe(sass({outputStyle: "expanded", indentType: "tab", indentWidth: 1})).on("error", sass.logError)
        .pipe(postcss(function () {
            let plugins = [autoprefixer({browsers: ["last 2 versions", "Safari >= 8", "ie >= 10"], cascade: false})];
            if (Options.minify) {
                plugins.push(cssnano);
            }
            return plugins;
        }()))
        .pipe(concat("application.css"))

        .pipe(sourcemaps.write("."))
        .pipe(plumber.stop())
        .pipe(arrayToPipe(_.map(Options.css_target, (target) => gulp.dest(target).on("data", (file) => file.path.endsWith(".map") ? null : gutil.log("CSS Compiled: " + file.path)))));
});

gulp.task("live-reload", function () {
    gutil.log(
        gutil.colors.bgGreen(gutil.colors.black("LiveReload server: ")),
        Options.live_reload.enabled
            ? gutil.colors.bgGreen(gutil.colors.black("ENABLED")) + ", use <script src=\"http://localhost:" + Options.live_reload.port + "/livereload.js\" type=\"text/javascript\"></script>"
            : gutil.colors.bgRed(gutil.colors.white("DISABLED"))
    );
    return gulp
        .src(Options.live_reload.files, {cwd: Options.base})
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

gulp.task("wp2", function () {
    return gulp
        .src("app/Application.jsx", {cwd: Options.base})
        .pipe(plumber())
        .pipe(webpackStream({
            devtool: "source-map",
            module: {
                loaders: [{
                    test: /.jsx?$/,
                    loader: "babel-loader",
                    exclude: /node_modules/,
                    query: Options.babel_config
                }]
            },
            plugins: _.compact([
                Options.minify ? new webpack.optimize.UglifyJsPlugin() : null
            ]),
            output: {
                filename: "application" + (Options.minify ? ".min" : "") + ".js"
            }
        }))
        .pipe(plumber.stop())
        .pipe(gulp.dest(Options.base));
});