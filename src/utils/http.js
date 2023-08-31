//axios基础封装
import axios from 'axios'

const httpInstance = axios.create({
    baseURL: '#',//接口基地址
    timeout: 5000//接口超时时间
})

//axios请求拦截器
httpInstance.interceptors.request.use(config => {
    return config
}, e => Promise.reject(e))

//axios响应拦截器
httpInstance.interceptors.response.use(res => res.data, e => {
    return Promise.reject(e)
})
export default httpInstance