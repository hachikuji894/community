<template>
    <div class="nk-activate nk-main-repair">
        <el-row>
            <el-col :span="7"></el-col>
            <el-col :span="10" class="nk-tabs nk-activate">
                <tbody>
                    <tr>
                        <td class="text1">{{ message }}</td>
                    </tr>
                    <tr>
                        <div class="line"></div>
                    </tr>
                    <tr>
                        <td
                            class="text2"
                            style="padding-left: 20px;"
                        >系统会在&nbsp;{{ time }}&nbsp;秒后自动跳转，您也可以点此&nbsp;</td>
                        <router-link to="/" style="text-decoration: none;">
                            <td class="text-link">链接</td>
                        </router-link>
                        <td class="text2">&nbsp;手动跳转！</td>
                    </tr>
                </tbody>
            </el-col>

            <el-col :span="7"></el-col>
        </el-row>
    </div>
</template>
<script setup lang="ts">
import { ref } from 'vue'
import { router } from '@/router'
import { useRoute } from 'vue-router'
import { getStatusById, activate } from '@/assets/ts/api/activation'
//倒计时
const time = ref(10)
const countDown = () => {
    time.value--
    if (time.value === 0) {
        clearInterval(timer)
        router.push('/')
    }
}
const timer = setInterval(countDown, 1000)

//得到注册的 id
const route = useRoute()
const id = route.params.id[0]
const activationCode = route.params.activationCode[0]
const message = ref('数据异常！')

//不存在激活码
if (activationCode == null || activationCode == "")
    getStatusById(id).then(res => {
        message.value = res.msg
    })
else {
    activate(id, activationCode).then(res => {
        message.value = res.msg
    })
}
</script>
<style>
.text1 {
    font-size: var(--el-font-size-extra-large);
    padding-top: 50px;
    padding-left: 20px;
}
.text2 {
    font-size: var(--el-font-size-base);
    padding-top: 40px;
    float: left;
}
.text-link {
    font-size: var(--el-font-size-base);
    color: rgb(13, 131, 228);
    padding-top: 40px;
    float: left;
}
.line {
    border-bottom: 1px solid var(--el-border-color-base);
    width: 700px;
    margin-left: 20px;
    padding-top: 35px;
}
.nk-activate {
    height: 300px;
}
</style>
