<template>
    <div class="nk-login nk-main-repair">
        <el-row>
            <el-col :span="8"></el-col>
            <el-col :span="8" class="nk-tabs">
                <el-row>
                    <el-col :span="11"></el-col>
                    <el-col :span="2">
                        <tbody>
                            <tr>
                                <td class="text">登&nbsp;&nbsp;&nbsp;&nbsp;录</td>
                            </tr>
                        </tbody>
                    </el-col>
                    <el-col :span="11"></el-col>
                </el-row>
                <el-form
                    ref="loginForm"
                    :model="form"
                    status-icon
                    :rules="rules"
                    label-width="90px"
                    class="nk-loginForm"
                >
                    <el-form-item label="账号：" prop="accout">
                        <el-input v-model="form.accout" autocomplete="off" placeholder=" "></el-input>
                    </el-form-item>
                    <el-form-item label="密码：" prop="pass" :error="errorMessage.errorPassword">
                        <el-input
                            v-model="form.pass"
                            type="password"
                            autocomplete="off"
                            placeholder=" "
                        ></el-input>
                    </el-form-item>
                    <el-form-item label="验证码：" prop="code" :error="errorMessage.errorCode">
                        <el-input
                            v-model="form.code"
                            autocomplete="off"
                            placeholder=" "
                            style="max-width: 150px;float: left;"
                        ></el-input>
                        <el-image
                            style="width:110px;height: 40px;float: left;margin-left: 40px;"
                            :src="codeUrl"
                            @click="getCaptcha"
                        >
                            <template #error>
                                <div class="image-slot"></div>
                            </template>
                        </el-image>
                    </el-form-item>
                    <el-form-item>
                        <el-checkbox
                            v-model="form.rememberMe"
                            label="记住我"
                            name="rememberMe"
                            style="float: left;"
                        ></el-checkbox>
                        <el-link href="#" :underline="false" style="float: right;">忘记密码?</el-link>
                    </el-form-item>

                    <el-form-item>
                        <el-button
                            type="primary"
                            @click="submitForm()"
                            style="width: 100%;"
                            color="#1596AA"
                        >登录</el-button>
                    </el-form-item>
                </el-form>
            </el-col>
            <el-col :span="8"></el-col>
        </el-row>
    </div>
</template>

<script setup lang="ts">
import { reactive, ref } from 'vue'
import type { ElForm } from 'element-plus'
import { getCodeImg } from '@/assets/ts/api/login'
import userStore from '@/store/user'
import { router } from '@/router'
import { existUserByUsername } from '@/assets/ts/api/register'
import { Cookies } from '@/assets/ts/utils/cookies'
import { encrypt, decrypt } from '@/assets/ts/utils/jsencrypt'
import { ElMessage } from 'element-plus'


const loginForm = ref<InstanceType<typeof ElForm>>()

const store = userStore()

const accout: string = ''
const pass: string = ''
const code: string = ''
const uuid: string = ''
const rememberMe: boolean = false
const form = reactive({
    accout,
    pass,
    code,
    uuid,
    rememberMe
})

const codeUrl = ref('')


const errorPassword = ''
const errorCode = ''
const errorMessage = reactive({
    errorPassword,
    errorCode
})


const validateAccout = (rule: any, value: string, callback: any) => {
    if (value === '') {
        callback(new Error('请输入您的账号！'))
    }
    else {
        //查看是否存在
        existUserByUsername(value).then(res => {
            if (!res.data)
                callback(new Error('用户不存在！'))
            else
                callback()
        })
    }
}
const validatePassword = (rule: any, value: string, callback: any) => {
    if (value === '') {
        callback(new Error('请输入您的密码！'))
    } else {
        callback()
    }
}
const validateCode = (rule: any, value: string, callback: any) => {
    if (value === '') {
        callback(new Error('请输入验证码！'))
    } else {
        callback()
    }
}
const rules = reactive({
    accout: [{ validator: validateAccout, trigger: 'blur' }],
    pass: [{ validator: validatePassword, trigger: 'blur' }],
    code: [{ validator: validateCode, trigger: 'blur' }],
})

//点击登录
const submitForm = () => {
    loginForm.value?.validate((valid) => {
        if (valid) {

            if (form.rememberMe) {
                Cookies.set("username", form.accout, 30);
                Cookies.set("password", encrypt(form.pass), 30);
                Cookies.set('rememberMe', form.rememberMe, 30);
            } else {
                Cookies.remove("username");
                Cookies.remove("password");
                Cookies.remove('rememberMe');
            }

            store.login({
                username: form.accout,
                password: form.pass,
                code: form.code,
                uuid: form.uuid,
            }).then(() => {

                // ElMessage({
                //     message: '登录成功。',
                //     type: 'success',
                //     duration: 2000
                // })

                router.push('/')

            }).catch(error => {
                const msg = error.message
                if (msg === '验证码错误') {
                    errorMessage.errorCode = '验证码错误!'
                    errorMessage.errorPassword = ''
                } else if (msg === '验证码已失效') {
                    errorMessage.errorCode = '验证码已失效!'
                    errorMessage.errorPassword = ''
                } else if (msg === '用户不存在/密码错误') {
                    errorMessage.errorPassword = '密码错误！'
                    errorMessage.errorCode = ''
                }
                getCaptcha()
            })

        } else {
            console.log('error submit!!')
            return false
        }
    })
}

const getCaptcha = () => {
    getCodeImg().then(res => {
        codeUrl.value = "data:image/gif;base64," + res.img;
        form.uuid = res.uuid;
    })
}

const getCookie = () => {

    const username = Cookies.get("username")
    const password = Cookies.get("password")
    const rememberMe = Cookies.get('rememberMe')
    console.log(username, password, rememberMe)
    form.accout = username || ''
    form.pass = decrypt(password) || ''
    form.rememberMe = Boolean(rememberMe) || false

}

//初始化
getCaptcha()
getCookie()
</script>

<style scoped>
.nk-loginForm {
    border-top: 1px solid var(--el-border-color-base);
    text-align: center;
    margin-top: 10px;
    padding-top: 50px;
    vertical-align: bottom !important;
}
.text {
    font-size: var(--el-font-size-extra-large);
    color: #1596aa;
    text-align: center;
    width: 100%;
}
.el-form-item.el-input {
    vertical-align: bottom !important;
}
</style>