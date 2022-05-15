import request from "@/assets/ts/utils/request"
import {RegisterBody} from "@/assets/ts/entity"
export function existUserByUsername(username:string){
    return request({
        url: '/register/exist/username/'+username,
        method: 'get',
    })
}

export function existUserByEmail(email:string){
    return request({
        url: '/register/exist/email/'+email,
        method: 'get',
    })
}

export function register(user:RegisterBody){
    return request({
        url: '/register',
        method: 'post',
        data: user,
    })
}