<template>
  <!-- 尾部 -->
  <div class="nk-footer">
    <el-row>
      <el-col
        :span="10"
        class="qrcode-info"
      >
        <!-- 二维码 -->
        <el-image
          class="qrcode"
          style="width:128px;"
          src="https://uploadfiles.nowcoder.com/app/app_download.png"
        />
      </el-col>
      <!-- 公司信息 -->
      <el-col
        :span="14"
        class="detail-info"
      >
        <!-- 上半部链接 -->
        <el-row>
          <el-col>
            <a
              href="#"
              class="link-info"
            >关于我们</a>
            <a
              href="#"
              class="link-info"
            >加入我们</a>
            <a
              href="#"
              class="link-info"
            >意见反馈</a>
            <a
              href="#"
              class="link-info"
            >企业服务</a>
            <a
              href="#"
              class="link-info"
            >联系我们</a>
            <a
              href="#"
              class="link-info"
            >免责声明</a>
            <a
              href="#"
              class="link-info"
            >友情链接</a>
          </el-col>
        </el-row>
        <!-- 下半部信息 -->
        <el-row>
          <el-col>
            <table class="company-info">
              <tbody>
                <tr>
                  <td>公司地址：北京市朝阳区大屯路东金泉时代3-2708北京牛客科技有限公司</td>
                </tr>
                <tr>
                  <td>联系方式：010-60728802(电话)&nbsp;&nbsp;&nbsp;&nbsp;admin@nowcoder.com</td>
                </tr>
                <tr>
                  <td>牛客科技©2018 All rights reserved</td>
                </tr>
              </tbody>
            </table>
            <table class="company-info">
              <tbody>
                <tr>
                  <td>京ICP备14055008号-4&nbsp;&nbsp;&nbsp;&nbsp;</td>
                  <el-image
                    style="width:18px;"
                    src="http://static.nowcoder.com/company/images/res/ghs.png"
                  />
                  <td>京公网安备 11010502036488号</td>
                </tr>
              </tbody>
            </table>
          </el-col>
        </el-row>
      </el-col>
    </el-row>
  </div>
  <!-- </footer> -->
</template>
<script setup lang="ts">
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
  color: #c1c1c9;
}
a:active {
  text-decoration: none;
  color: #c1c1c9;
}

.nk-footer {
  padding-top: 20px;
}
.qrcode-info {
  text-align: right;
  padding-right: 100px;
}
.qrcode {
  border-radius: 4px;
  border: 4px solid rgb(255, 255, 255);
  text-align: right;
}
.detail-info {
  border-left: 1px solid #888;
  text-align: left;
  padding-left: 18px;
}
.link-info {
  font-size: var(--el-font-size-extra-small);
  color: #eef0f7;
  line-height: 35px;
  padding-left: 16px;
  padding-right: 16px;
}
.company-info {
  font-size: var(--el-font-size-extra-small);
  color: #a5a8ad;
  padding-left: 12px;
}
</style>