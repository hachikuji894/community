export function dataFormat(value: string) {
    var year = value.substr(0, 4)
    var month = value.substr(5, 2)
    var day = value.substr(8, 2)
    var hour = value.substr(11, 2)
    var min = value.substr(14, 2)
    var second = value.substr(17, 2)
    return year + "-" + month + "-" + day + " " + hour + ":" + min + ":" + second
}