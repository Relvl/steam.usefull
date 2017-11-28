/* Файл настроек Gulp для сборки и имнификации .js, .css Сборку бандла осуществляет Webpack */

const gulp = require("gulp");
const gulpSync = require("gulp-sync")(gulp);
const gulpif = require("gulp-if");
const babel = require("gulp-babel");
const gutil = require("gulp-util");
const arrayToPipe = require("multipipe");
const concat = require("gulp-concat");
const plumber = require("gulp-plumber");
const sourcemaps = require("gulp-sourcemaps");
const sass = require("gulp-sass");
const autoprefixer = require("autoprefixer");
const postcss = require("gulp-postcss");
const cssnano = require("cssnano");
const uglify = require("gulp-uglify");
const server = require("gulp-server-livereload");
const webpack = require("webpack");
const webpackConfig = require('./webpack.config.js');

const _ = require("underscore");

const Options = {
    /** -====================================- Минификация и углификация финальных .css и .js */
    minify: false,
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

gulp.task("jsx-build", function () {
    gutil.log(gutil.colors.bgGreen(gutil.colors.black("======================= JSX building " + (Options.minify ? gutil.colors.bgRed(gutil.colors.white("[MINIFIED]")) : ""))));
    return gulp
        .src(Options.jsx, {cwd: Options.base, base: Options.base})
        .pipe(plumber())
        .pipe(sourcemaps.init())

        .pipe(babel()).on("error", (err) => gutil.log("Babel error: ", gutil.colors.red(err.message)))
        .pipe(gulpif(Options.minify, uglify()))
        .pipe(concat("application.min.js"))

        .pipe(sourcemaps.write("."))
        .pipe(plumber.stop())
        .pipe(arrayToPipe(_.map(Options.js_target, (target) => gulp.dest(target).on("data", (file) => file.path.endsWith(".map") ? null : gutil.log("JS Compiled: " + file.path)))));
});

gulp.task("all-build", gulpSync.sync(["scss-build", "jsx-build"]));

gulp.task("live-reload", function () {
    gutil.log(
        gutil.colors.bgGreen(gutil.colors.black("LiveReload server: ")),
        Options.live_reload.enabled
            ? gutil.colors.bgGreen(gutil.colors.black("ENABLED")) + ", use '<script src=\"http://localhost:' + Config.LIVE_RELOAD_PORT + '/livereload.js\" type=\"text/javascript\"></script>'"
            : gutil.colors.bgRed(gutil.colors.white("DISABLED"))
    );
    return gulp
        .src(Options.live_reload.files, {cwd: Options.base})
        .pipe(gulpif(Options.live_reload.enabled,
            server({
                port: 0,
                log: 'error',
                livereload: {
                    enable: true,
                    port: Options.live_reload.port
                }
            })
        ));
});

gulp.task("webpack", function (callback) {
    webpack(webpackConfig, function (err, stats) {
        if (err) {throw new gutil.PluginError("webpack", err);}
        gutil.log("[webpack]", stats.toString({}));
        callback();
    });
});
