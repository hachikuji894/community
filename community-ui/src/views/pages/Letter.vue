<template>
    <div class="nk-letter nk-main-repair">
        <el-row>
            <el-col :span="6"></el-col>
            <el-col :span="12">
                <el-tabs type="card" class="nk-tabs" @tab-click="handleClick" v-if="!loading">
                    <el-tab-pane v-for="label in tabLabel" :label="label"></el-tab-pane>
                </el-tabs>
            </el-col>
            <el-col :span="6"></el-col>
        </el-row>
    </div>
</template>

<script setup lang="ts">
import { Ref, ref } from 'vue'
const tabLabel = ref(['朋友私信', '系统通知'])
const loading = ref(true)
const handleClick = (tab: any, event: unknown) => {
    console.log(tab, event)
}
</script>

<style scoped>
</style>