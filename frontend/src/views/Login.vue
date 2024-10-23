<template>
  <a :href="login">
    <!-- https://developers.google.com/identity/branding-guidelines -->
    <img alt="login" src="../assets/google_login.svg">
  </a>
</template>

<script lang="ts" setup>
import {computed} from 'vue';
import {useRoute} from 'vue-router';

const route = useRoute();

const redirectPath = computed(() => route.query.redirect ? `?redirectUrl=${encodeURIComponent(route.query.redirect as string)}` : '');

const login =
    `${import.meta.env.VITE_API_SERVER_URL}/oauth2/authorization/google${redirectPath.value}`;
</script>

<style scoped>
</style>
