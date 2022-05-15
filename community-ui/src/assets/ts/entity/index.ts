export interface User {

    username: string,
    password: string,
    salt: string,
    email: string,
    type: number,
    status: number,
    activationCode: string,
    headerUrl: string,
    createTime: string,
}
export interface DiscussPost {

    userId: number,
    title: string,
    content: string,
    type: number,
    status: number,
    createTime: string,
    commentCount: number,
    score: number,
}

export interface HomeDataTableBody {

    username: string,
    headerUrl: string,

    title: string,
    createTime: string,
    commentCount: number,
    score: number,

}

export interface RegisterBody {

    username: string,
    password: string,
    email: string

}

export interface LoginBody {

    username: string,
    password: string,
    code: string
    uuid: string

}

export interface Page {

    pageNum:number
    pageSize:number
    orderByColumn:string,
    isAsc:string

}

