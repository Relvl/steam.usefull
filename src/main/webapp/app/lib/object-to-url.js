const _ = require("underscore");

/** Генерирует валидную закодированную URL строку из полей объекта.
 * @param {Object} object
 * @return {string}
 */
function ObjectToUrl(object) {
    let result = "";
    _.forEach(object, function (value, key) {
        if (key === "url") {
            result = value + "?" + result;
        } else {
            result += ("&" + key + "=" + encodeURIComponent(String(value)));
        }
    });
    return result;
}

module.exports = ObjectToUrl;