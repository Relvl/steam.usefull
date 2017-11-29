const _ = require("underscore");

/** Генерирует валидную закодированную URL строку из полей объекта.
 * @param {Object} object
 * @param {String} [url]
 * @return {string}
 */
function ObjectToUrl(object, url = null) {
    let result = "";
    _.forEach(object, function (value, key) {
        if (key === "url") {
            if (!url) {
                result = value + "?" + result;
            }
        } else {
            result += ("&" + key + "=" + encodeURIComponent(String(value)));
        }
    });
    if (!!url) {
        result = url + "?" + result;
    }
    return result;
}

module.exports = ObjectToUrl;