import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';
import dotenv from 'dotenv';
dotenv.config();

// https://vite.dev/config/
export default defineConfig({
  plugins: [react()],
  server: {
    proxy: {
      '/api': {
        target: process.env.VITE_REMOTE_API,
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api/, ''),
      },
      '/exercises': {
        target: 'https://api.api-ninjas.com/v1/exercises',
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/exercises/, ''),
      },
    },
  },
})
