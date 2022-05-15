<template>
    <!-- 文章卡片 -->
    <div class="nk-home-card">
        <el-row>
            <!-- 头像 -->
            <el-col :span="2" class="nk-avatar">
                <el-avatar :size="50" :src="homeDataTable.headerUrl"></el-avatar>
            </el-col>
            <!-- 帖子信息 -->
            <el-col :span="17">
                <table class="disscuss-post-info">
                    <tbody>
                        <tr>
                            <td>
                                <a href="#" style="color:black;">{{ homeDataTable.title }}</a>
                            </td>
                        </tr>
                        <tr
                            style="font-size: var(--el-font-size-extra-small);color: rgb(98, 100, 102);"
                        >
                            <td>{{ homeDataTable.username }}&nbsp;&nbsp;发布于&nbsp;&nbsp;{{ dataFormat(homeDataTable.createTime) }}</td>
                        </tr>
                    </tbody>
                </table>
            </el-col>
            <!-- 点赞 -->
            <el-col :span="5">
                <table class="re-info">
                    <tbody>
                        <tr
                            style="font-size: var(--el-font-size-extra-small);color: rgb(98, 100, 102);"
                        >
                            <td>点赞&nbsp;&nbsp;{{ homeDataTable.score }}&nbsp;&nbsp;|&nbsp;&nbsp;回帖&nbsp;&nbsp;{{ homeDataTable.commentCount }}</td>
                        </tr>
                    </tbody>
                </table>
            </el-col>
        </el-row>
    </div>
</template>

<script setup lang="ts">
import { PropType } from 'vue'
import type { HomeDataTableBody } from '@/assets/ts/entity'
import {dataFormat} from '@/assets/ts/utils/dataFormat'
defineProps({
    homeDataTable: {
        type: Object as PropType<HomeDataTableBody>,
        required: true
    },
})
</script>

<style scoped>
a:link {
    text-decoration: none;
}
a:visited {
    text-decoration: none;
}
a:hover {
    text-decoration: none;
    color: #000000;
}
a:active {
    text-decoration: none;
    color: #000000;
}
.nk-avatar {
    text-align: center;
    margin-top: 25px;
}
.nk-home-card {
    border-bottom: 1px solid var(--el-border-color-base);
}
.disscuss-post-info {
    text-align: left;
    margin-left: 20px;
    margin-top: 5px;
    margin-bottom: 5px;
}
.re-info {
    text-align: right;
    margin-right: 0px;
    margin-top: 48px;
}
</style>