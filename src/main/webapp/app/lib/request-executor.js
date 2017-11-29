/*
* Класс менеджера запросов.
* - Исполняет запросы в последовательности поступления и приоритета.
* - Исполняет не больше N запросов одновременно.
* - Кеширует запросы, для которых не запрещено кеширование.
* - По требованию сбрасывает кеш по указанным параметрам.
**/

const axios = require("axios");
const _ = require("underscore");
const ObjectToUrl = require("./object-to-url");

let makeRequestKey = function (endpoint, request) {
    return "key_" + endpoint + "_" + JSON.stringify(request);
};

/**
 * @class {RequestExecutor}
 **/
class RequestExecutor {
    static DEBUG_LOG = true;
    static MAX_SIMULTANEOUSLY_REQUESTS = 3;
    static CACHE_ENABLED = false;

    /** @private */
    __requestQueue = {};
    /** @private */
    __requestsInProgress = {};
    /** @private */
    __cache = {};

    /**
     * @param {string} endpoint
     * @param {Object} request
     * @param {String} [method]
     *
     * @return {Promise}
     *
     * @public */
    call(endpoint, request, method = "post") {
        let key = makeRequestKey(endpoint, request);

        // Если есть закешированный ответ - возвращаем его.
        if (RequestExecutor.CACHE_ENABLED && this.__cache.hasOwnProperty(key)) {
            return Promise.resolve(this.__cache[key]);
        }

        // Если есть запрос в очереди исполнения - возвращаем его промайс.
        if (!!this.__requestsInProgress[key]) {
            let requestInQueue = this.__requestsInProgress[key];
            return requestInQueue.__promise;
        }
        if (!!this.__requestQueue[key]) {
            let requestInQueue = this.__requestQueue[key];
            return requestInQueue.__promise;
        }

        // Добавляем в очередь. Уведомляем процессор о тике.
        let pendedRequest = new RequestExecutor.PendedRequest(endpoint, request, method);
        pendedRequest.__promise
            .then((response) => this.onRequestDone(response, key))
            .catch((error) => this.onRequestDone(error, key));
        this.__requestQueue[key] = pendedRequest;
        this.tick();
        return pendedRequest.__promise;
    }

    /** @private */
    onRequestDone(response, key) {
        delete this.__requestsInProgress[key];
        if (RequestExecutor.CACHE_ENABLED) { this.__cache[key] = response; }
        this.tick();
    }

    /** @private */
    tick() {
        while (Object.keys(this.__requestsInProgress).length < RequestExecutor.MAX_SIMULTANEOUSLY_REQUESTS && !!Object.keys(this.__requestQueue).length) {
            let key = Object.keys(this.__requestQueue)[0];
            let pendedRequest = this.__requestQueue[key];
            delete this.__requestQueue[key];
            this.__requestsInProgress[key] = pendedRequest;
            pendedRequest.execute();
        }
    }
}

RequestExecutor.PendedRequest = class {
    /**
     * @param {String} endpoint
     * @param {Object} request
     * @param {String} method
     **/
    constructor(endpoint, request, method) {
        this.__endpoint = endpoint;
        this.__request = request;
        this.__method = method;
        this.__promise = new Promise((resolve, reject) => {
            this.__resolveFunc = resolve;
            this.__rejectFunc = reject;
        });
    }

    execute() {
        axios({
            method: this.__method.toLowerCase(),
            url: this.__endpoint,
            data: this.__request
        })
            .then((response) => {
                if (RequestExecutor.DEBUG_LOG) { console.info("Response [", response.status, "]:", response)/*FIXME Убрать!*/; }
                this.__resolveFunc(response.data);
            })
            .catch((error) => {
                if (error.response) {
                    if (RequestExecutor.DEBUG_LOG) { console.error("Error[", error.response.status, "]:", error.response)/*FIXME Убрать!*/; }
                    this.__rejectFunc(error.response.data);
                } else {
                    if (RequestExecutor.DEBUG_LOG) { console.error("Error: unreachable"); }
                    this.__rejectFunc({
                        isError: true,
                        errorType: "_CLIENT_ERROR",
                        errorMessage: "Cannot send request, response is null"
                    });
                }
            });
    }
};

const instance = new RequestExecutor();
module.exports = instance;