<template>
    <div class="home">
        <a :href=loginUrl>
          <!-- https://developers.google.com/identity/branding-guidelines -->
          <img alt="login" src="../assets/google_login.svg">
        </a>
    </div>
</template>

<script lang="ts">
const host = import.meta.env.VITE_API_SERVER_URL
export default {
    name: 'Home',
    data() {
        return {
            loginUrl: host + '/oauth2/authorization/google'
        }
    }
}
</script>

<style scoped>
.home {
    text-align: center;
    margin-top: 50px;
}

h1 {
    color: #42b983;
}

p {
    font-size: 18px;
}
</style>