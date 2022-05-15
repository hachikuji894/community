export const DEFAULT = 0

export default new Map([
    [401, '认证失败，无法访问系统资源'],
    [403, '当前操作没有权限'],
    [404, '访问资源不存在'],
    [0 , '系统未知错误，请反馈给管理员']
])
