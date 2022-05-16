import request from "@/assets/ts/utils/request"

export function getStatusById(id: string) {
    return request({
        url: '/register/status/id/' + id,
        method: 'get',
    })
}

export function activate(id: string, activationCode: string) {
    return request({
        url: '/register/activate/id/' + id + '/code/' + activationCode,
        method: 'get',
    })
}