import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import postCssPxToRem from "postcss-pxtorem"
import Components from 'unplugin-vue-components/vite'
import { VantResolver } from 'unplugin-vue-components/resolvers'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    vue(),
    Components({
      resolvers: [VantResolver()],
    }),
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  },
  //Postcss-pxtorem config
  css: {
    postcss: {
      plugins: [
        postCssPxToRem({
          rootValue: 37.5, // Conversion base: 1rem = 37.5px; 
          propList: ['*'], // Select all attributes to be converted here
        })
      ]
    }
  },
})