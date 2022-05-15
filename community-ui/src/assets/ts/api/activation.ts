import request from "@/assets/ts/utils/request"

export function getStatusById(id: string) {
    return request({
        url: '/register/status/id/' + id,
        method: 'get',
    })
}

export function activate(id: string, activationCode: string) {
    return request({
        url: '/register/activation/id/' + id + '/activationCode/' + activationCode,
        method: 'get',
    })
}