import axios from 'axios'
import errorCode from '@/assets/ts/utils/errorCode'
import { getToken } from '@/assets/ts/utils/cookies';
import { tansParams } from '@/assets/ts/utils/tansParams';

axios.defaults.headers['Content-Type'] = 'application/json;charset=utf-8'

// 创建axios
const service = axios.create({
    // axios中请求配置有baseURL选项，表示请求URL公共部分
    baseURL: import.meta.env.VITE_APP_BASE_API,
    // 超时
    timeout: 60000
});

// 添加请求拦截器
service.interceptors.request.use(config => {

    if (!config?.headers) {
        throw new Error(`Expected 'config' and 'config.headers' not to be undefined`);
    }

    config.headers['Authorization'] = 'Bearer ' + getToken()
    //get请求映射params参数
    if (config.method === 'get' && config.params) {
        var url = config.url + '?' + tansParams(config.params);
        url = url.slice(0, -1);
        config.params = {};
        config.url = url;
    }

    return config;
}, error => {
    console.log(error)
    return Promise.reject(error);
});

// 添加响应拦截器
service.interceptors.response.use(res => {
    // 未设置状态码则默认成功状态
    const code: number = res.data.code || 200;
    // 获取错误信息
    const msg: string = errorCode.get(code) || res.data.msg || errorCode.get(0)
    // 二进制数据则直接返回
    if (res.request.responseType === 'blob' || res.request.responseType === 'arraybuffer') {
        return res.data
    }
    if (code === 401) {
        return Promise.reject('无效的会话，或者会话已过期，请重新登录。')
    } else if (code === 500) {

        return Promise.reject(new Error(msg))

    } else if (code !== 200) {

        return Promise.reject('error')
    } else {
        return res.data
    }
},
    error => {
        console.log('err' + error)
        let { message } = error;
        if (message == "Network Error") {
            message = "后端接口连接异常";
        }
        else if (message.includes("timeout")) {
            message = "系统接口请求超时";
        }
        else if (message.includes("Request failed with status code")) {
            message = "系统接口" + message.substr(message.length - 3) + "异常";
        }
        return Promise.reject(error)
    }
)

export default service