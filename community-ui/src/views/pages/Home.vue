<template>
    <div class="nk-home nk-main-repair">
        <el-row>
            <el-col :span="6"></el-col>
            <el-col :span="12">
                <el-tabs type="card" class="nk-tabs" @tab-click="handleClick" v-if="!loading">
                    <el-tab-pane v-for="label in tabLabel" :label="label">
                        <nk-home-card :homeDataTable="i" v-for="i in homeInfo.info"></nk-home-card>
                        <el-pagination
                            background
                            layout="prev, pager, next"
                            v-model:currentPage="page.pageNum"
                            :total="pageTotal"
                            @current-change="handleCurrentChange"
                            style="margin-top: 30px;text-align: center;"
                        ></el-pagination>
                    </el-tab-pane>
                </el-tabs>
            </el-col>
            <el-col :span="6"></el-col>
        </el-row>
    </div>
</template>

<script setup lang="ts">
import { reactive, ref, Ref, PropType } from 'vue'
import { HomeDataTableBody, Page } from '@/assets/ts/entity'
import NkHomeCard from '@/components/NkHomeCard.vue'
import { getHomeInfoList } from '@/assets/ts/api/home'

//初始化
const info: HomeDataTableBody[] = []
const homeInfo = reactive({
    info
})

const tabLabel: Ref<string[]> = ref(['最新', '最热'])
const map = new Map([
    ["最新", "createTime"],
    ["最热", "commentCount"]
]);

const pageNum: number = 1
const pageSize: number = 7
const orderByColumn: string = "createTime"
const isAsc: string = "desc"
const pageTotal = ref(50)
const loading = ref(true)
const page = reactive({
    pageNum,
    pageSize,
    orderByColumn,
    isAsc
})

const handleClick = (tab: any, event: unknown) => {

    page.orderByColumn = map.get(tab.props.label) || "createTime"
    handleCurrentChange()

}
const handleCurrentChange = () => {

    getHomeInfoList({
        pageNum: page.pageNum,
        pageSize: page.pageSize,
        isAsc: page.isAsc,
        orderByColumn:page.orderByColumn
    }).then(res => {
        pageTotal.value = res.data.total
        homeInfo.info = res.data.rows
        loading.value = false
    })
}

//初始化
handleCurrentChange()
</script>

<style scoped>
</style>