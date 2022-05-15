import { defineConfig, loadEnv } from 'vite'
import vue from '@vitejs/plugin-vue'
import path from 'path';

const srcPath = path.resolve(__dirname, 'src');
export default ({ mode }) => {

  return defineConfig({
    plugins: [vue()],
    //别名配置
    resolve: {
      alias: [
        { find: '@', replacement: srcPath },
      ],
    },
    // 反向代理
    server: {
      proxy: {
        [loadEnv(mode, process.cwd()).VITE_APP_BASE_API]: {
          target: 'http://localhost:8888/',
          changeOrigin: true,
          //rewrite: path => path.replace(/^\/api/, '')
          rewrite: path => path.replace(new RegExp('^'+loadEnv(mode, process.cwd()).VITE_APP_BASE_API), '/community')
        }
      }
    }
  })
}

// https://vitejs.dev/config/
// export default defineConfig({
//   plugins: [vue()],

//   //别名配置
//   resolve: {
//     alias: [
//       { find: '@', replacement: srcPath },
//     ],
//   },

//   // 反向代理
//   server: {
//     proxy: {
//       '/api': {
//         target: 'http://localhost:8888/',
//         changeOrigin: true,
//         rewrite: path => path.replace(/^\/api/, '')
//       }
//     }
//   }
// })
