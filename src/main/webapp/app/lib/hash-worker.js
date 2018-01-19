import Utils from "../Utils";

const _ = require("underscore");

class HashStruct {
    page = "";
    params = {};

    constructor(fullHash) {
        this.page = "";
        this.params = {};
        if (fullHash) {
            let parts = fullHash.split("?");
            if (parts.length > 0) {
                this.page = parts[0];
                if (this.page.startsWith("#")) {
                    this.page = this.page.substr(1);
                }
            } else {
                this.page = "";
            }
            if (parts.length > 1) {
                let params = decodeURIComponent(parts[1]).split("&");
                _.forEach(params, (paramSet) => {
                    if (paramSet.indexOf("=") > 0) {
                        let paramKey = paramSet.substring(0, paramSet.indexOf("="));
                        this.params[paramKey] = paramSet.substring(paramSet.indexOf("=") + 1);
                    } else {
                        this.params[paramSet] = true;
                    }
                });
            }
        }
    }

    getPage() {
        return this.page;
    }

    getParam(name) {
        return this.params[name];
    }

    toString() {
        return "HashStruct[" + this.page + ": " + this.params + "]";
    }
}

/** @class {HashWorker} */
class HashWorker {
    currentFullHash = "";
    currentHash = new HashStruct(this.currentFullHash);
    onHashChangeListeners = [];

    constructor() {
        window.onhashchange = this.onNativeHashChanged;
        this.onNativeHashChanged();
    }

    /** @return {HashStruct} */
    getHashStruct() {
        if (window.location.hash !== this.currentFullHash) {
            this.currentFullHash = window.location.hash;
            this.currentHash = new HashStruct(window.location.hash);
        }
        return this.currentHash;
    }

    onNativeHashChanged() {
        this.getHashStruct();
        console.log("hash changed: ", this.currentHash)/*FIXME Убрать!*/;
        _.forEach(this.onHashChangeListeners, function (listener) {
            Utils.ensureFunction(listener)(this.getHashStruct());
        });
    }

    addHashChangeListener(listener) {
        this.onHashChangeListeners.push(listener);
    }

    removeHashChangeListener(listener) {
        this.onHashChangeListeners.remove(listener);
    }
}

const hashWorker = new HashWorker();
module.exports = {hashWorker, HashStruct};