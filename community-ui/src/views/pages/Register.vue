<template>
    <div class="nk-register nk-main-repair">
        <el-row>
            <el-col :span="8"></el-col>
            <el-col :span="8" class="nk-tabs">
                <el-row>
                    <el-col :span="11"></el-col>
                    <el-col :span="2">
                        <tbody>
                            <tr>
                                <td class="text">注&nbsp;&nbsp;&nbsp;&nbsp;册</td>
                            </tr>
                        </tbody>
                    </el-col>
                    <el-col :span="11"></el-col>
                </el-row>
                <!-- <el-row>
                    <el-col :span="2"></el-col>
                <el-col :span="20">-->
                <el-form
                    ref="registerForm"
                    :model="form"
                    status-icon
                    :rules="rules"
                    label-width="90px"
                    class="nk-registerForm"
                >
                    <el-form-item label="账号：" prop="username">
                        <el-input v-model="form.username" autocomplete="off" placeholder=" "></el-input>
                    </el-form-item>
                    <el-form-item label="密码：" prop="pass">
                        <el-input
                            v-model="form.pass"
                            type="password"
                            autocomplete="off"
                            placeholder=" "
                        ></el-input>
                    </el-form-item>
                    <el-form-item label="确认密码：" prop="checkPass">
                        <el-input
                            v-model="form.checkPass"
                            type="password"
                            autocomplete="off"
                            placeholder=" "
                        ></el-input>
                    </el-form-item>
                    <el-form-item label="邮箱：" prop="email">
                        <el-input v-model="form.email" placeholder=" "></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button
                            type="primary"
                            @click="submitForm()"
                            style="width: 100%;"
                            color="#1596AA"
                        >注册</el-button>
                    </el-form-item>
                </el-form>
                <!-- </el-col>
                    <el-col :span="2"></el-col>
                </el-row>-->
            </el-col>
            <el-col :span="8"></el-col>
        </el-row>
    </div>
</template>

<script setup lang="ts">
import { reactive, ref, Ref } from 'vue'
import type { ElForm, ElFormItem } from 'element-plus'
import { existUserByUsername, existUserByEmail, register } from '@/assets/ts/api/register'
import { router } from '@/router'
//引用表单对象
const registerForm = ref<InstanceType<typeof ElForm>>()
//表单数据
const username: string = ''
const pass: string = ''
const checkPass: string = ''
const email: string = ''
const form = reactive({
    username,
    pass,
    checkPass,
    email,
})
const validateUsername = (rule: any, value: string, callback: any) => {


    if (value === '') {
        callback(new Error('请输入您的账号！'))
    } else {
        //查看是否存在
        existUserByUsername(value).then(res => {
            if (res.data)
                callback(new Error('此账号已经存在！'))
            else
                callback()
        })

    }
}
const validatePassword = (rule: any, value: string, callback: any) => {
    if (value === '') {
        callback(new Error('请输入您的密码！'))
    } else {
        if (form.checkPass !== '') {
            registerForm.value?.validateField('checkPass', callback())
        }
        callback()
    }
}
const validateConfirmPassword = (rule: any, value: string, callback: any) => {
    if (value === '') {
        callback(new Error('请再次输入您的密码！'))
    } else if (value !== form.pass) {
        callback(new Error("两次密码不一致!"))
    } else {
        callback()
    }
}
const validateEmail = (rule: any, value: string, callback: any) => {
    const sReg = /[_a-zA-Z\d\-\.]+@[_a-zA-Z\d\-]+(\.[_a-zA-Z\d\-]+)+$/
    if (value === '') {
        callback(new Error('请输入您的邮箱！'))
    } else if (!sReg.test(value)) {
        callback(new Error('请确认邮箱是否正确！'))
    } else {
        //查看是否存在
        existUserByEmail(value).then(res => {
            if (res.data)
                callback(new Error('此邮箱已经注册！'))
            else
                callback()
        })
    }
}
const rules = reactive({
    username: [{ validator: validateUsername, trigger: 'blur' }],
    pass: [{ validator: validatePassword, trigger: 'blur' }],
    checkPass: [{ validator: validateConfirmPassword, trigger: 'blur' }],
    email: [{ validator: validateEmail, trigger: 'blur' }]
})
const submitForm = () => {
    registerForm.value?.validate((valid) => {
        if (valid) {
            //跳转
            register({
                username: form.username,
                password: form.pass,
                email: form.email
            }).then(res => {
                const id = res.data
                if (id !== -1)
                    router.push(`/unactivated/id/${id}`)
            })
        } else {
            console.log('error submit!!')
            return false
        }
    })
}

</script>

<style scoped>
.nk-registerForm {
    border-top: 1px solid var(--el-border-color-base);
    text-align: center;
    margin-top: 10px;
    padding-top: 50px;
}
.text {
    font-size: var(--el-font-size-extra-large);
    color: #1596aa;
    text-align: center;
    width: 100%;
}
</style>