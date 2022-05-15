import request from "@/assets/ts/utils/request"
import { Page } from "@/assets/ts/entity"

export function getHomeInfoList(query: Page){
    return request({
        url: '/home/list',
        method: 'get',
        params: query
    })
}