import _ from "underscore";

class Utils {

    /** Метод для создания класса с множественным наследованием.
     * @param {class} baseClass
     * @param {class} mixins
     * @return {class}
     **/
    static MultiClass(baseClass, ...mixins) {
        class base extends baseClass {
            constructor(...args) {
                super(...args);
                mixins.forEach((mixin) => copyProps(this, (new mixin)));
            }
        }

        let copyProps = (target, source) => {  // this function copies all properties and symbols, filtering out some special ones
            Object.getOwnPropertyNames(source)
                .concat(Object.getOwnPropertySymbols(source))
                .forEach((prop) => {
                    if (!prop.match(/^(?:constructor|prototype|arguments|caller|name|bind|call|apply|toString|length)$/)) {
                        Object.defineProperty(target, prop, Object.getOwnPropertyDescriptor(source, prop));
                    }
                });
        };
        mixins.forEach((mixin) => { // outside contructor() to allow aggregation(A,B,C).staticFunction() to be called etc.
            copyProps(base.prototype, mixin.prototype);
            copyProps(base, mixin);
        });
        return base;
    }

    /**  */
    static foo() {
    }

    /**  */
    static ensureFunction(func) {
        return typeof func === "function" ? func : Utils.foo;
    }

}

Array.prototype.remove = function () {
    let what, a = arguments, L = a.length, ax;
    while (L && this.length) {
        what = a[--L];
        while ((ax = this.indexOf(what)) !== -1) {
            this.splice(ax, 1);
        }
    }
    return this;
};
Array.prototype.compact = function () {
    return _.compact(this);
};
Array.prototype.rest = function () {
    return _.rest(this);
};

export default Utils;