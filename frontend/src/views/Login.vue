<template>
  <div>
    <a :href=loginUrl>
      <!-- https://developers.google.com/identity/branding-guidelines -->
      <img alt="login" src="../assets/google_login.svg">
    </a>
  </div>
</template>

<script lang="ts" setup>
import {computed} from 'vue';

const loginUrl = computed(() => `${import.meta.env.VITE_API_SERVER_URL}/oauth2/authorization/google`);
</script>

<style scoped>
</style>
