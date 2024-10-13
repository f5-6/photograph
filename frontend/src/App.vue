<script lang="ts" setup>
const host = import.meta.env.VITE_API_SERVER_URL

const loginUrl = host + '/oauth2/authorization/google'
</script>

<template>
  <div>
    <a :href=loginUrl>
      <!-- https://developers.google.com/identity/branding-guidelines -->
      <img alt="login" src="./assets/google_login.svg">
    </a>
  </div>
</template>

<style scoped>

</style>
